package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.control.Button;
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
    private AnchorPane paneResults;

    @FXML
    public void initialize() {
        super.setMain(MainFactory.getMain());
        try {
            MethodsUtilities.getOptions().forEach(option -> cmbOptions.getItems().add(option));

            //cargo el panel de resultados
            FXMLLoader fxml = getMain().loadFXML(PathUtilities.SEARCHITEMS);
            AnchorPane paneAux = fxml.load();
            paneResults.getChildren().add(paneAux);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
