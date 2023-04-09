package org.ed.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.ed.controllers.Controller;
import org.ed.model.Domain;
import org.ed.services.DBConnection;
import org.ed.utilities.PathUtilities;
import org.ed.utilities.ViewUtilities;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    private Stage stage;

    public static void main(String[] args) {

        DBConnection.getInstance();
        Domain.getInstance();
        launch();

    }


    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        inicializarVentana(false);
        loadStage(PathUtilities.login);

    }

    /**
     * Method that initializes the window with the desired characteristics
     */
    private void inicializarVentana(boolean flag) {
        //la ventana no se puede redimensionar
        stage.setResizable(false);
        //la ventana se abre en pantalla completa
        stage.setFullScreen(true);
        //la ventana no tiene bordes
        if(!flag) stage.initStyle(StageStyle.UNDECORATED);
        //cambio el icono de la ventana
        stage.getIcons().add(ViewUtilities.getIcon("logo"));
    }


    public void loadStage(String path){
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
        try {
            Pane pane = loader.load();
            Controller controller = loader.getController();
            controller.setMain(this);
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            inicializarVentana(true);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    public FXMLLoader getAlertPane() {
        return new FXMLLoader(Objects.requireNonNull(getClass().getResource(PathUtilities.alert)));
    }
}