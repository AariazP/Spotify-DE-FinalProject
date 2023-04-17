package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.control.Button;
import org.ed.utilities.MethodsUtilities;

@Getter
@Setter
public class SearchController extends Controller{

    private LeftBarController leftBarController;


    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    @FXML
    private ComboBox<String> cmbFilter;

    @FXML
    private ComboBox<String> cmbOptions;

    @FXML
    private AnchorPane paneResults;

    public void initializable() {
        cmbFilter.getItems().addAll("Canción", "Video");
        MethodsUtilities.getOptions().forEach(option -> cmbOptions.getItems().add(option));
    }


}
