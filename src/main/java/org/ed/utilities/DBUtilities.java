package org.ed.utilities;

public class DBUtilities {

    public static final String URL = "jdbc:mysql://mysql5045.site4now.net/db_a994d2_anrumo";

    public static final String USER ="a994d2_anrumo";

    public static final String PASSWORD ="123Anrumito*";

    public static final String QUERY_UPLOAD_IMAGES = "INSERT INTO images (name, url) VALUES (?, ?)";

    public static final String QUERY_DOWNLOAD_IMAGES = "SELECT * FROM images WHERE name = ?";

    public static final String addUser = "INSERT INTO users(id, username, password, nationality, email, name) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String addArtist = "INSERT INTO users (id, id_user, isBand, ownSongs) VALUES (?, ?, ?, ?)";

    public static final String addRelation = "INSERT INTO users_songs (user_id, song_id) VALUES (?, ?)";

    public static final String getUsers = "SELECT * FROM users";

    public static final String getFavs = "SELECT * FROM users_songs";

    public static final String getArtist = "SELECT * FROM artists";

    public static final String getSongs = "SELECT * FROM songs";
}
