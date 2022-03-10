/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_krypton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dark Wizard
 */
public class Free_userController implements Initializable {

    @FXML
    private BorderPane borderpane;

    public void profile1() throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("user_profile.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }

    public void profile_btn(ActionEvent event) throws IOException {

        profile1();
    }
    
    public void buyprimbtn(ActionEvent event) throws IOException{
        Parent fxml2 = FXMLLoader.load(getClass().getResource("payment.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();
    }
    
    public void sym_En(ActionEvent event) throws IOException{
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Sym_En.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }
    public void sym_De(ActionEvent event) throws IOException{
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Sym_De.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }
     public void key_gen(ActionEvent event) throws IOException{
        Parent fxml2 = FXMLLoader.load(getClass().getResource("key_gen.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }
     
     public void Asym_En(ActionEvent event) throws IOException{
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Asym_En.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }
     
     public void Asym_De(ActionEvent event) throws IOException{
        Parent fxml2 = FXMLLoader.load(getClass().getResource("Asym_De.fxml"));
        Pane fxml2scene = new Pane(fxml2);

        borderpane.setCenter(fxml2);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            Parent fxml2;
            fxml2 = FXMLLoader.load(getClass().getResource("user_profile.fxml"));
            Pane fxml2scene = new Pane(fxml2);

            borderpane.setCenter(fxml2);
        } catch (IOException ex) {
            Logger.getLogger(Free_userController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
