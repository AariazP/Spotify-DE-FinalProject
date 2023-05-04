package org.ed.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.ed.model.Domain;
import org.ed.patterns.MainFactory;
import org.ed.services.DBConnection;
import org.ed.utilities.PathUtilities;
import org.ed.utilities.ViewUtilities;
import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    private Stage stage;
    private Scene scenePrevious;

    public static void main(String[] args) {

        DBConnection.getInstance();
        Domain.getInstance();
        launch();

    }


    @Override
    public void start(Stage primaryStage){
        MainFactory.setMain(this);
        stage = primaryStage;
        inicializarVentana();
        if(Domain.getInstance().isUserLogged()){
            loadStage(PathUtilities.LEFT_BAR);
        } else {
            loadStage(PathUtilities.LOGIN);
        }

    }

    /**
     * Method that initializes the window with the desired characteristics
     */
    private void inicializarVentana() {
        //la ventana no se puede redimensionar
        stage.setResizable(false);
        //la ventana se abre en pantalla completa
        stage.setFullScreen(true);
        //la ventana no tiene bordes
        if(!stage.isShowing()) stage.initStyle(StageStyle.UNDECORATED);
        //cambio el icono de la ventana
        stage.getIcons().add(ViewUtilities.getIcon("logo"));
    }

    /**
     * This method loads the stage with the desired path
     * @param path path of the fxml file
     */
    public void loadStage(String path){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            inicializarVentana();
            if(!stage.isShowing())stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public FXMLLoader loadFXML(String path){
        return new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
    }


    public void loadVideo() {
        scenePrevious = stage.getScene();
        loadStage(PathUtilities.VIDEOVIEW);
    }
}