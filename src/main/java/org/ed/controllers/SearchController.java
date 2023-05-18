package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.control.Button;
import org.alejandroArias.model.BinaryTree;
import org.alejandroArias.model.DoubleLinkedList;
import org.ed.model.Artist;
import org.ed.model.Domain;
import org.ed.model.Song;
import org.ed.patterns.MainFactory;
import org.ed.utilities.MethodsUtilities;
import org.ed.utilities.PathUtilities;

import java.io.IOException;

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
    private TextField txtbuscar;

    @FXML
    public void initialize() {
        super.setMain(MainFactory.getMain());
        try {
            MethodsUtilities.getOptions().forEach(option -> cmbOptions.getItems().add(option));
            MethodsUtilities.getSearchOptions().forEach(option -> cmbSearchOptions.getItems().add(option));

            //cargo el panel de resultados
            FXMLLoader fxml = getMain().loadFXML(PathUtilities.SEARCHITEMS);
            AnchorPane paneAux = fxml.load();
            paneResults.getChildren().add(paneAux);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void buscar(KeyEvent event) {

        String busqueda = txtbuscar.toString();

        if(event.getCode().equals(KeyCode.ENTER)){
            if(busqueda != null || busqueda != " "){
                switch (cmbSearchOptions.getSelectionModel().getSelectedItem()){

                    case "Artista":  buscarNombre(busqueda);
                    break;
                    case "Coincidencia parcial": buscarParcial(busqueda);
                    break;
                    case "Coincidencia total": buscarTotal(busqueda);
                    break;
                    default: cmbSearchOptions.setPromptText("Escoja una opcion");

                }
            }
        }
    }

    private void buscarTotal(String busqueda) {
    }

    private void buscarParcial(String busqueda) {
    }

    private void buscarNombre(String busqueda) {

        BinaryTree<Artist> arbolito = Domain.getInstance().getIArtist().getArtists();

        Artist artist = new Artist(busqueda);
        Artist art = arbolito.find(artist);

        DoubleLinkedList<Song> canciones = buscarNombreArt(arbolito);



    }

    private DoubleLinkedList<Song> buscarNombreArt(BinaryTree<Artist> arbolito) {

        return null;


    }


}
