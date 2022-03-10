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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dark Wizard
 */
public class User_profileController implements Initializable {

    static String name1;
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;
    
    @FXML
    private Label label41;

    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();

        
    }

    public static void try1(String name) {
        User_profileController.name1 = name;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        Connection conn = (Connection)
//        DriverManager.getConnection("jdbc:mysql://localhost:3306/try?severTimezone=UTC","root","");
        String url1 = "jdbc:mysql://bjn9yajxknszeuvhgczi-mysql.services.clever-cloud.com:3306/bjn9yajxknszeuvhgczi?severTimezone=UTC";
        String user = "uve5k169sb891uxy";
        String pass = "SzDHDl2UNNEWJwgOFQxe";

        Connection conn;
        try {
            conn = DriverManager.getConnection(url1, user, pass);
            Statement s = conn.createStatement();

            String qury = "SELECT Name,user_name,mail,Phone_number,u_password,sy_cipher FROM user_table where user_name = '" + name1 + "'";

            ResultSet r = s.executeQuery(qury);

            while (r.next()) {

                String name, uname, mail, pnumber,paid;
                name = r.getString("Name");
                uname = r.getString("user_name");
                mail = r.getString("mail");
                pnumber = r.getString("Phone_number");
                paid = r.getString("sy_cipher");
                label1.setText(name);
                label2.setText(uname);
                label3.setText(mail);
                label4.setText(pnumber);
                if (paid.equals("No")) {
                    label41.setText("Not Paid");
                }
                else{
                    label41.setText("Paid");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
