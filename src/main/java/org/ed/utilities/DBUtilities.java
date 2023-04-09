package org.ed.utilities;

public class DBUtilities {

    public static final String URL = "jdbc:mysql://MYSQL8003.site4now.net/db_a96001_spotify";
    public static final String USER ="a96001_spotify";
    public static final String PASSWORD ="kebjy7-zyTqiz-zi";

    public static final String QUERY_UPLOAD_IMAGES = "INSERT INTO images (name, url) VALUES (?, ?)";

    public static final String QUERY_DOWNLOAD_IMAGES = "SELECT * FROM images WHERE name = ?";

    public static final String addUser = "INSERT INTO users (id, username, password, email, name, nationality, favoritesSongs) VALUES (?, ?, ?, ?, ?, ?, ?)";


    public static final String getUsers = "SELECT * FROM users";
}
