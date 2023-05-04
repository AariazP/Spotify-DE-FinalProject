package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import org.ed.patterns.MainFactory;
import org.ed.utilities.PathUtilities;

public class VideoController extends Controller{

    @FXML
    private WebView webView;


    @FXML
    void initialize(){
        super.setMain(MainFactory.getMain());
        webView.getEngine().load("https://www.youtube.com/watch?v=QH2-TGUlwu4");
    }


    @FXML
    void loadHome(MouseEvent event) {
        //detengo el webView

        webView = null;
        getMain().loadStage(PathUtilities.LEFT_BAR);
    }
    

}
