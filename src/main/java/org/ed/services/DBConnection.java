package org.ed.services;

import lombok.Getter;
import lombok.Setter;
import org.alejandroArias.model.BinaryTree;
import org.alejandroArias.model.HashMap;
import org.alejandroArias.model.LinkedList;
import org.alejandroArias.model.TreeTraversalOrder;
import org.ed.model.Artist;
import org.ed.model.Gender;
import org.ed.model.Song;
import org.ed.model.User;
import org.ed.patterns.ArtistBuilder;
import org.ed.patterns.SongBuilder;
import org.ed.patterns.UserBuilder;
import org.ed.utilities.DBUtilities;

import java.io.*;
import java.sql.*;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
public class DBConnection {
    private static DBConnection instance;


    private Connection connection;

    private DBConnection(){
        try {
            connection = DriverManager.getConnection(DBUtilities.URL, DBUtilities.USER, DBUtilities.PASSWORD);
            if (connection != null) {
                System.out.println("Conexión a base de datos " + DBUtilities.URL + " ... Ok");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static DBConnection getInstance(){
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }


    public ResultSet query(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }


    public void uploadImage(String path){
        File imageFile = new File(path);
        try (InputStream inputStream = new FileInputStream(imageFile)) {
            // Crea la conexión a la base de datos
            PreparedStatement pstmt = connection.prepareStatement(DBUtilities.QUERY_UPLOAD_IMAGES);
            pstmt.setString(1, "home");
            pstmt.setBinaryStream(2, inputStream);

            // Ejecuta la consulta
            pstmt.executeUpdate();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }



    public void downloadImage(String imageName, String savePath) {
        try {
            // Crea la conexión a la base de datos

            // Crea la consulta PreparedStatement para obtener los datos de la imagen
            PreparedStatement pstmt = connection.prepareStatement(DBUtilities.QUERY_DOWNLOAD_IMAGES);
            pstmt.setString(1, imageName);

            // Ejecuta la consulta y obtén los resultados
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Obtiene los datos de la imagen del ResultSet
                Blob blob = rs.getBlob("image");

                // Crea un objeto InputStream a partir de los datos del Blob
                InputStream inputStream = blob.getBinaryStream();

                // Crea un objeto FileOutputStream para escribir los datos de la imagen en el archivo
                FileOutputStream outputStream = new FileOutputStream(savePath);

                // Crea un búfer para leer los datos de la imagen del InputStream y escribirlos en el FileOutputStream
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // Cierra los streams
                outputStream.close();
                inputStream.close();
            }

            // Cierra los recursos
            rs.close();
            pstmt.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public void saveArtists(Artist artist){

        try (PreparedStatement preparedStatement = connection.prepareStatement(DBUtilities.addArtist)) {

            preparedStatement.setLong(1, artist.getId());
            //preparedStatement.setString(2, "");
            //preparedStatement.setBoolean(3, artist.getIsBand());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            updateConexion(artist);
        }
    }

    public void saveUsers(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DBUtilities.addUser)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(4, user.getNationality());
            //preparedStatement.setString(7, "");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            updateConexion(user);
            e.printStackTrace();
        }

    }

    private void updateConexion(User user) {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DBUtilities.URL, DBUtilities.USER, DBUtilities.PASSWORD);
                saveUsers(user);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void loadUsers(HashMap<String, User> users) {

        try{
          PreparedStatement preparedStatement = connection.prepareStatement(DBUtilities.getUsers);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new UserBuilder()
                        .id(rs.getLong("id"))
                        .userName(rs.getString("username"))
                        .password(rs.getString("password"), false)
                        .email(rs.getString("email"))
                        .name(rs.getString("name"))
                        .nationality(rs.getString("nationality"))
                        //.loadFavoritesSongs(rs.getString("favoritesSongs"))
                        .build();
                users.put(user.getUserName(), user);
            }

            //updateID(users);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadArtists(BinaryTree<Artist> artists, HashMap<String, User> users) {

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(DBUtilities.getArtist);

            ResultSet rs = preparedStatement.executeQuery();

            Long id;
            Set<String> usuarios = users.keySet();

            while (rs.next()) {

                id = rs.getLong("user_id");
                Iterator<String> it = usuarios.iterator();
                User user = null;
                while(it.hasNext() && user == null){

                    String clave = it.next();
                    User aux = users.get(clave);
                    if(aux.getId() == id){
                        user = aux;
                    }
                }

                Artist artist = new ArtistBuilder()
                        .id(rs.getLong("id"))
                        .userName(user.getUserName())
                        .password(user.getPassword())
                        .email(user.getEmail())
                        .name(user.getName())
                        .nationality(user.getNationality())
                        .build();
                artists.add(artist);
            }

            //updateID(users);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadSongs(LinkedList<Song> songs, BinaryTree<Artist> artists){

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(DBUtilities.getSongs);

            ResultSet rs = preparedStatement.executeQuery();
            Long id;

            while(rs.next()){

                Song song = new SongBuilder()
                        .id(rs.getLong("id"))
                        .url(rs.getString("url"))
                        .name(rs.getString("name"))
                        .gender(Gender.getGender(rs.getInt("gender_id")))
                        .build();
                songs.add(song);
                id = rs.getLong("artist_id");
                boolean flag = false;
                Iterator<Artist> it = artists.traverse(TreeTraversalOrder.IN_ORDER);
                while(it.hasNext() && !flag){

                    Artist aux = it.next();
                    if(aux.getId() == id){

                        aux.getOwnSongs().add(song);
                        song.setAutor(aux);
                        flag = true;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method find the biggest id in the database and set the id to user
     * @param users
     */
    private void updateID(HashMap<String, User> users) {

        long id = 0;
        for(String key : users.keySet()){
            if(users.get(key).getId() > id){
                id = users.get(key).getId();
            }
        }
        //User.setId(id);
    }
}
