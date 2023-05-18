package org.ed.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.ed.model.Domain;
import org.ed.model.User;
import org.ed.patterns.MainFactory;
import org.ed.patterns.UserBuilder;
import org.ed.utilities.MethodsUtilities;
import org.ed.utilities.PathUtilities;

public class RegisterController extends Controller{


    @FXML
    private Button btnRegistrarse;

    @FXML
    private ComboBox<String> cmbMes;

    @FXML
    private Label lblCoincideEmail;

    @FXML
    private Label lblDebeEmail;

    @FXML
    private Label lblIngresaPais;

    @FXML
    private Label lblMensaje;

    @FXML
    private Label lblNameInUse;

    @FXML
    private Label lblPasswordSafe;

    @FXML
    private Label lblSeleccionaSexo;

    @FXML
    private Label lblverifyName;

    @FXML
    private RadioButton radioMen;

    @FXML
    private RadioButton radioWoman;

    @FXML
    private TextField txtAnio;

    @FXML
    private TextField txtDia;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPais;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtVerifyEmail;

    private String name;
    private String userName;
    private String email;
    private String password;
    private String country;
    private String day;
    private String month;
    private String year;

    private boolean isMen;

    private boolean camposLlenos;



    @FXML
    void confirmEmail(KeyEvent ignoredEvent) {

        if(txtEmail.getText().equals(txtVerifyEmail.getText())){
            lblCoincideEmail.setVisible(false);
            txtVerifyEmail.setStyle("-fx-border-color: black");
        }else {
            lblCoincideEmail.setText("Los correos no coinciden");
            lblCoincideEmail.setVisible(true);
            txtVerifyEmail.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    void login(MouseEvent event) {

    }

    @FXML
    void loginFacebook(MouseEvent event) {

    }

    @FXML
    void loginGoogle(MouseEvent event) {

    }

    @FXML
    void register(MouseEvent event) {
        loadDomain();
        if(camposLlenos){
            User user = new UserBuilder()
                    .name(name)
                    .userName(userName)
                    .email(email)
                    .password(password)
                    .nationality(country)
                    .build();
            Domain.getInstance().addUser(user);
            getMain().loadStage(PathUtilities.LOGIN);
        }


    }


    private void loadDomain(){

        String mensaje = "";

        name = txtName.getText();
        userName = txtUserName.getText();
        email = txtEmail.getText();
        password = txtPassword.getText();
        country = txtPais.getText();
        day = txtDia.getText();
        month = cmbMes.getValue();
        year = txtAnio.getText();

        if(radioMen.isSelected()) {
            isMen = true;
        }else if(radioWoman.isSelected()){
            isMen = false;
        }else {
            lblSeleccionaSexo.setText("Debe seleccionar un sexo");
            lblSeleccionaSexo.setVisible(true);
            mensaje += "Debe seleccionar un sexo";
        }


        if(name.equals("")) mensaje += "Debe ingresar un nombre";
        if(userName.equals("")) mensaje += "Debe ingresar un nombre de usuario";
        if(email.equals("")) mensaje += "Debe ingresar un correo";
        if(password.equals("")) mensaje += "Debe ingresar una contraseña";
        if(country.equals("")) mensaje += "Debe ingresar un país";
        if(day.equals("")) mensaje += "Debe ingresar un día";
        if(month.equals("")) mensaje += "Debe ingresar un mes";
        if(year.equals("")) mensaje += "Debe ingresar un año";

        if(mensaje.equals("")) {
            camposLlenos = true;
        }else {
            lblMensaje.setText(mensaje);
            lblMensaje.setVisible(true);
            camposLlenos = false;
        }
    }

    @FXML
    void verifyCountry(KeyEvent ignoredEvent) {

        if(txtPais.getText().equals("")){
            lblIngresaPais.setText("Debe ingresar un país");
            lblIngresaPais.setVisible(true);
            txtPais.setStyle("-fx-border-color: red");
        }else {
            lblIngresaPais.setVisible(false);
            txtPais.setStyle("-fx-border-color: black");
        }

    }

    @FXML
    void verifyDate(KeyEvent event) {

        if(txtDia.getText().equals("") || txtAnio.getText().equals("") || cmbMes.getValue().equals("")){
            lblMensaje.setText("Debe ingresar una fecha de nacimiento");
            lblMensaje.setVisible(true);
            txtDia.setStyle("-fx-border-color: red");
            txtAnio.setStyle("-fx-border-color: red");
            cmbMes.setStyle("-fx-border-color: red");
        }else {
            lblMensaje.setVisible(false);
            txtDia.setStyle("-fx-border-color: black");
            txtAnio.setStyle("-fx-border-color: black");
            cmbMes.setStyle("-fx-border-color: black");
        }


    }

    @FXML
    void verifyEmail(KeyEvent ignoredEvent) {

        if(txtEmail.getText().equals("")){
            lblDebeEmail.setText("Debe ingresar un correo electrónico");
            lblDebeEmail.setVisible(true);
            txtEmail.setStyle("-fx-border-color: red");
        }

        else if(!MethodsUtilities.verifyEmail(txtEmail.getText())) {
            lblDebeEmail.setText("Debe ingresar un correo electrónico válido");
            lblDebeEmail.setVisible(true);
            txtEmail.setStyle("-fx-border-color: red");
        }else {
            lblDebeEmail.setVisible(false);
            txtEmail.setStyle("-fx-border-color: black");
        }


    }

    @FXML
    void verifyName(KeyEvent ignoredEvent) {

        if(txtName.getText().equals("")){
            lblverifyName.setText("Debe ingresar un nombre");
            lblverifyName.setVisible(true);
            txtName.setStyle("-fx-border-color: red");
        }else {
            lblverifyName.setVisible(false);
            txtName.setStyle("-fx-border-color: black");
        }

    }

    @FXML
    void verifyPassword(KeyEvent ignoredEvent) {

        if(txtPassword.getText().equals("")){
            lblPasswordSafe.setText("Debe ingresar una contraseña");
            lblPasswordSafe.setVisible(true);
            txtPassword.setStyle("-fx-border-color: red");
        } else if (!MethodsUtilities.verifyPassword(txtPassword.getText())) {

            lblPasswordSafe.setText("La contraseña no es segura");
            lblPasswordSafe.setVisible(true);
            txtPassword.setStyle("-fx-border-color: red");


        } else {
            lblPasswordSafe.setVisible(false);
            txtPassword.setStyle("-fx-border-color: black");
        }
    }

    @FXML
    void verifySelection(MouseEvent ignoredEvent) {
        lblSeleccionaSexo.setVisible(!radioMen.isSelected() && !radioWoman.isSelected());
    }

    @FXML
    void verifyUsername(KeyEvent ignoredEvent) {

        if(txtUserName.getText().equals("")){

            lblNameInUse.setText("Debe ingresar un nombre de usuario");
            lblNameInUse.setVisible(true);
            txtUserName.setStyle("-fx-border-color: red");

        } else if (Domain.getInstance().existUser(txtUserName.getText())) {

            lblNameInUse.setText("El nombre de usuario ya existe");
            lblNameInUse.setVisible(true);
            txtUserName.setStyle("-fx-border-color: red");

        } else {

            lblNameInUse.setVisible(false);
            txtUserName.setStyle("-fx-border-color: black");

        }

    }





    @FXML
    void back(ActionEvent ignoredEvent) {
        back();
    }

    private void back(){
        getMain().loadStage(PathUtilities.LOGIN);
    }



    @FXML
    void initialize() {
       ToggleGroup group = new ToggleGroup();
       radioMen.setToggleGroup(group);
       radioWoman.setToggleGroup(group);
       cmbMes.getItems().addAll("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
        super.setMain(MainFactory.getMain());
    }

}