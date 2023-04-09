package org.ed.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.ed.model.Domain;
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
    private CheckBox rememberUser;


    @FXML
    void login(ActionEvent event) {

        if(Domain.getInstance().login(txtEmailUser.getText(), txtPassword.getText())){
            if(rememberUser.isSelected()){
                MethodsUtilities.saveUser(txtEmailUser.getText(), txtPassword.getText());
            }
            getMain().loadStage(PathUtilities.LEFT_BAR);
        } else {
            lblFaliedSesion.setVisible(true);
        }


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
        getMain().loadStage(PathUtilities.REGISTER);
    }

    @FXML
    void verifyEmail(KeyEvent event) {

        if(txtEmailUser.getText().equals("")){
            txtEmailUser.styleProperty().setValue("-fx-border-color: BLACK");
            lblIngresaCorreo.setVisible(false);
        }else {
            txtEmailUser.styleProperty().setValue("-fx-border-color: BLACK");
            lblIngresaCorreo.setVisible(false);
        }


    }

    @FXML
    void verifyPassword(KeyEvent event) {

        if(txtPassword.getText().equals("")){
            txtPassword.styleProperty().setValue("-fx-border-color: BLACK");
            lblIngresaContrasenia.setVisible(false);
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
