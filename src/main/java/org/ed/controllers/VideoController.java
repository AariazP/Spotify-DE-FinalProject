package org.ed.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import org.ed.patterns.MainFactory;

public class VideoController extends Controller{

    @FXML
    private WebView webView;


    @FXML
    void initialize(){
        super.setMain(MainFactory.getMain());
        webView.getEngine().load(getSelectSong().getUrl());
    }
}
