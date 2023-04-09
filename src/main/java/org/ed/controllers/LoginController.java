package org.ed.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.ed.utilities.MethodsUtilities;
import org.ed.utilities.PathUtilities;

public class LoginController extends Controller{

    @FXML
    private TextField txtEmailUser;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblFaliedSesion;

    @FXML
    private Label lblIngresaContrasenia;

    @FXML
    private Label lblIngresaCorreo;


    @FXML
    void login(ActionEvent event) {

        getMain().loadStage(PathUtilities.LEFT_BAR);
    }

    @FXML
    void loginApple(MouseEvent event) {

    }

    @FXML
    void loginFacebook(MouseEvent event) {

    }

    @FXML
    void loginGoogle(MouseEvent event) {

    }

    @FXML
    void loginTelefono(MouseEvent event) {

    }

    @FXML
    void signUp(MouseEvent event) {
        getMain().loadStage(PathUtilities.register);
    }

    @FXML
    void verifyEmail(KeyEvent event) {

        if(txtEmailUser.getText().equals("")){
            txtEmailUser.styleProperty().setValue("-fx-border-color: BLACK");
            lblIngresaCorreo.setVisible(false);
        } else if(!MethodsUtilities.verifyEmail(txtEmailUser.getText())){
            lblIngresaCorreo.setVisible(true);
            txtEmailUser.styleProperty().setValue("-fx-border-color: red");
        }
        else {
            txtEmailUser.styleProperty().setValue("-fx-border-color: BLACK");
            lblIngresaCorreo.setVisible(false);
            System.out.println(" Email correcto");
        }


    }

    @FXML
    void verifyPassword(KeyEvent event) {

        if(txtPassword.getText().equals("")){
            txtPassword.styleProperty().setValue("-fx-border-color: RED");
            lblIngresaContrasenia.setVisible(true);
        } else if(txtPassword.getText().length() < 8){
            txtPassword.styleProperty().setValue("-fx-border-color: RED");
            lblIngresaContrasenia.setVisible(true);
            lblIngresaContrasenia.setText("La contraseña debe tener al menos 8 caracteres");
        } else {
            txtPassword.styleProperty().setValue("-fx-border-color: BLACK");
            lblIngresaContrasenia.setVisible(false);
        }

    }

}
