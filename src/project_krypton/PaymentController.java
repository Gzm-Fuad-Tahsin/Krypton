/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_krypton;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dark Wizard
 */
public class PaymentController implements Initializable {

    static String name1;
        @FXML
    private TextField text1;

    @FXML
    private TextField text2;
    @FXML
    private Label label1;

    public void backbtn(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("free_user.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();
    }

    public static void try1(String name) {
        PaymentController.name1 = name;
    }

    public void buybtn(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        String Ac=text1.getText();
        String ref = text2.getText();
        if ((Ac.isEmpty() && ref.isEmpty()) || Ac.isEmpty()|| ref.isEmpty()){
         label1.setText("Fill Up properly");
        
        }else{

        Class.forName("com.mysql.cj.jdbc.Driver");

//        Connection conn = (Connection)
//        DriverManager.getConnection("jdbc:mysql://localhost:3306/try?severTimezone=UTC","root","");
        String url = "jdbc:mysql://bjn9yajxknszeuvhgczi-mysql.services.clever-cloud.com:3306/bjn9yajxknszeuvhgczi?severTimezone=UTC";
        String user = "uve5k169sb891uxy";
        String pass = "SzDHDl2UNNEWJwgOFQxe";

        Connection conn = DriverManager.getConnection(url, user, pass);

        Statement s = conn.createStatement();

        String qury = "UPDATE `user_table` SET `sy_cipher` = 'paid' WHERE `user_table`.`user_name` = '" + name1 + "';";

        s.executeUpdate(qury);
        s.close();
        conn.close();

        Parent fxml2 = FXMLLoader.load(getClass().getResource("prim_user.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();
    }
   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
