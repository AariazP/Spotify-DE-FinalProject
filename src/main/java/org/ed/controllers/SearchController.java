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

    @FXML
    public void initialize() {
        super.setMain(MainFactory.getMain());
        super.setData(DataFactory.getInsatance());
        try {
            MethodsUtilities.getOptions().forEach(option -> cmbOptions.getItems().add(option));
            MethodsUtilities.getSearchOptions().forEach(option -> cmbSearchOptions.getItems().add(option));

            //cargo el panel de resultados
            cargarPane();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void cargarPane() throws IOException {

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

    private void buscarTotal(String busqueda) {
    }

    private void buscarParcial(String busqueda) {
    }

    private void buscarNombre(String busqueda) throws Exception {

        BinaryTree<Artist> arbolito = Domain.getInstance().getIArtist().getArtists();

        Artist artist = new Artist(busqueda);

        Artist art = arbolito.find(artist);


        if(art == null){

            txtbuscar.setText("No se encontro su busqueda");
        }else{

            getData().setSongs(art.getOwnSongs());
            cargarPane();

        }
    }



    private DoubleLinkedList<Song> buscarNombreArt(BinaryTree<Artist> arbolito) {

        return null;


    }


}
