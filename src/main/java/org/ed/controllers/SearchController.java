package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.control.Button;
import org.alejandroArias.model.BinaryTree;
import org.alejandroArias.model.DoubleLinkedList;
import org.ed.model.Artist;
import org.ed.model.Domain;
import org.ed.model.Song;
import org.ed.patterns.DataFactory;
import org.ed.patterns.MainFactory;
import org.ed.utilities.MethodsUtilities;
import org.ed.utilities.PathUtilities;

import java.io.IOException;
import java.util.Iterator;

@Getter
@Setter
public class SearchController extends Controller {

    private LeftBarController leftBarController;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnNext;
    @FXML
    private ComboBox<String> cmbOptions;
    @FXML
    private ComboBox<String> cmbSearchOptions;
    @FXML
    private AnchorPane paneResults;

    @FXML
    private HBox HBoxList1;

    @FXML
    private HBox HBoxList2;

    @FXML
    private HBox HBoxList3;

    @FXML
    private TextField txtbuscar;

    private DoubleLinkedList<Song> songss;

    @FXML
    public void initialize() {
        super.setMain(MainFactory.getMain());
        super.setData(DataFactory.getInsatance());
        songss = new DoubleLinkedList<>();
        try {
            MethodsUtilities.getOptions().forEach(option -> cmbOptions.getItems().add(option));
            MethodsUtilities.getSearchOptions().forEach(option -> cmbSearchOptions.getItems().add(option));

            //cargo el panel de resultados
            cargarPane();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void cargarPane() throws IOException {

        FXMLLoader fxml = getMain().loadFXML(PathUtilities.SEARCHITEMS);
        AnchorPane paneAux = fxml.load();
        paneResults.getChildren().add(paneAux);
    }

    @FXML
    void buscar(MouseEvent event) throws Exception {

        String busqueda = txtbuscar.getText();
        String tipo = cmbSearchOptions.getValue();

            if(busqueda != null || busqueda != " " || tipo != null){

                if(tipo == "Artista"){
                    buscarNombre(busqueda);
                } else if (tipo == "Coincidencia parcial") {
                    buscarParcial(busqueda);
                } else if (tipo == "Coincidencia total") {
                    buscarTotal(busqueda);
                }else{
                    cmbSearchOptions.setPromptText("Escoja una opcion");
                }
            }
    }

    private void buscarTotal(String busqueda) throws IOException, InterruptedException {


        BinaryTree<Artist> arbolito = Domain.getInstance().getIArtist().getArtists();

        BinaryTree<Artist> arbolitoDer = new BinaryTree<>(arbolito.rootNode().getRight());

        SearchThread threadIz = new SearchThread(busqueda, arbolito, true, true);
        SearchThread threadDer = new SearchThread(busqueda, arbolitoDer, true);

        threadDer.start();
        threadIz.start();

        threadIz.join();
        threadDer.join();

        DoubleLinkedList<Song> songs = threadIz.songs.merge(threadDer.songs);

        if(!songs.isEmpty()){

            getData().setSongs(songs);
            cargarPane();
        }else{

            txtbuscar.setText("No se encontro su búsqueda");
        }

    }

    private void buscarParcial(String busqueda) throws IOException, InterruptedException {

        BinaryTree<Artist> arbolito = Domain.getInstance().getIArtist().getArtists();

        BinaryTree<Artist> arbolitoDer = new BinaryTree<>(arbolito.rootNode().getRight());

        SearchThread threadIz = new SearchThread(busqueda, arbolito, true, false);
        SearchThread threadDer = new SearchThread(busqueda, arbolitoDer, false);


        threadDer.start();
        threadIz.start();

        threadIz.join();
        threadDer.join();

        DoubleLinkedList<Song> songs = threadIz.songs.merge(threadDer.songs);

        if(!songs.isEmpty()){

            getData().setSongs(songs);
            cargarPane();
        }else{

            txtbuscar.setText("No se encontro su búsqueda");
        }
    }

    private void buscarNombre(String busqueda) throws Exception {

        BinaryTree<Artist> arbolito = Domain.getInstance().getIArtist().getArtists();

        Artist artist = new Artist(busqueda);

        Artist art = arbolito.find(artist);


        if(art == null){

            txtbuscar.setText("No se encontro su búsqueda");
        }else{

            getData().setSongs(art.getOwnSongs());
            cargarPane();
        }
    }

    @FXML
    void ejecutarOp(MouseEvent event) {

        String opcion = cmbOptions.getValue();

        if(opcion == "Cerrar sesión" && opcion != null){

            getMain().loadStage(PathUtilities.LOGIN);
        }
    }

    private class SearchThread extends Thread{

        private String[] busqueda;
        private DoubleLinkedList<Song> songs;
        private BinaryTree<Artist> artists;
        private boolean esRaiz;
        private boolean esCompleta;

        public SearchThread(String busqueda, BinaryTree<Artist> artists, boolean esCompleta){

            this.busqueda = busqueda.split(" ");
            this.artists = artists;
            songs = new DoubleLinkedList<>();
            esRaiz = false;
            this.esCompleta = esCompleta;
        }

        public SearchThread(String busqueda, BinaryTree<Artist> artists, boolean esRaiz, boolean esCompleta) {
            this.busqueda = busqueda.split(" ");
            this.artists = artists;
            this.esRaiz = esRaiz;
            songs = new DoubleLinkedList<>();
            this.esCompleta = esCompleta;
        }

        @Override
        public void run(){

            if(esCompleta){
                songs = buscarCompleta(busqueda, artists, songs);
            }else songs = buscarParcial(busqueda, artists, songs);


        }

        private DoubleLinkedList<Song> buscarParcial(String[] busqueda, BinaryTree<Artist> artists, DoubleLinkedList<Song> songs){

            if(artists.rootNode() == null){

                return songs;
            }

            Artist art = artists.root();
            Iterator<Song> it = art.getOwnSongs().iterator();

            while(it.hasNext()){

                Song aux = it.next();

                for(int i = 0; i < busqueda.length; i++){


                        if(busqueda[i].equalsIgnoreCase(aux.getName()))songs.add(aux);

                        if(busqueda[i].equalsIgnoreCase(aux.getAutor().getName()))songs.add(aux);

                        if(busqueda[i].equalsIgnoreCase(aux.getGender().toString()))songs.add(aux);

                }
            }

            BinaryTree<Artist> arbolitoIz = new BinaryTree<>(artists.rootNode().getLeft());
            BinaryTree<Artist> arbolitoDer = new BinaryTree<>(artists.rootNode().getRight());

            if(esRaiz){

                songs = songs.merge(buscarParcial(busqueda, arbolitoIz, songs));
                esRaiz = false;
            }else{

                songs = songs.merge(buscarParcial(busqueda, arbolitoIz, songs));
                songs = songs.merge(buscarParcial(busqueda, arbolitoDer, songs));
            }

            return songs;
        }


        private DoubleLinkedList<Song> buscarCompleta(String[] busqueda, BinaryTree<Artist> artists, DoubleLinkedList<Song> songs){

            if(artists.rootNode() == null){

                return songs;
            }

            Artist art = artists.root();
            Iterator<Song> it = art.getOwnSongs().iterator();

            boolean flag1, flag2, flag3;

            while(it.hasNext()){


                Song aux = it.next();
                flag1 = flag2 = flag3 = false;

                for(int i = 0; i < busqueda.length; i++){

                    if(busqueda[i].equalsIgnoreCase(aux.getName()))flag1 = true;
                    if(busqueda[i].equalsIgnoreCase(aux.getAutor().getName()))flag2 = true;
                    if(busqueda[i].equalsIgnoreCase(aux.getGender().toString()))flag3 = true;

                }

                if(flag1 && flag2 && flag3)songs.add(aux);
            }

            BinaryTree<Artist> arbolitoIz = new BinaryTree<>(artists.rootNode().getLeft());
            BinaryTree<Artist> arbolitoDer = new BinaryTree<>(artists.rootNode().getRight());


            if(esRaiz){

                songs = songs.merge(buscarCompleta(busqueda, arbolitoIz, songs));
                esRaiz = false;
            }else{

                songs = songs.merge(buscarCompleta(busqueda, arbolitoIz, songs));
                songs = songs.merge(buscarCompleta(busqueda, arbolitoDer, songs));
            }

            return songs;
        }

    }
}
