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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dark Wizard
 */
public class LoginController implements Initializable {

    public static String name1;
    @FXML
    private Label label1;

//    @FXML
//    private Label label2;
    @FXML
    private TextField text1;

    @FXML
    private PasswordField pass1;

    public void signup(ActionEvent event) throws IOException {

        Parent fxml2 = FXMLLoader.load(getClass().getResource("sign_up.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();
    }

    public void login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String i = text1.getText();
        String j = pass1.getText();
        label1.setText("");

        Class.forName("com.mysql.cj.jdbc.Driver");

//        Connection conn = (Connection)
//        DriverManager.getConnection("jdbc:mysql://localhost:3306/try?severTimezone=UTC","root","");
        String url = "jdbc:mysql://bjn9yajxknszeuvhgczi-mysql.services.clever-cloud.com:3306/bjn9yajxknszeuvhgczi?severTimezone=UTC";
        String user = "uve5k169sb891uxy";
        String pass = "SzDHDl2UNNEWJwgOFQxe";

        Connection conn = DriverManager.getConnection(url, user, pass);

        Statement s = conn.createStatement();

        String qury = "SELECT user_name,mail,u_password,sy_cipher FROM user_table";

        ResultSet r = s.executeQuery(qury);
        int l = 0, m = 0;

        while (r.next()) {
            String name, id, mail, paid;

            name = r.getString("user_name");
            id = r.getString("u_password");
            mail = r.getString("mail");
            paid = r.getString("sy_cipher");

            if ((i.equals(name) && j.equals(id)) || (i.equals(mail) && j.equals(id))) {
                l++;
                if (paid.equals("paid")) {
                    m++;
                }
            }

        }

        if (l > 0 && l < 2) {
            User_profileController.try1(text1.getText());
            PaymentController.try1(text1.getText());
            Key_genController.try1(text1.getText());

            if (m == 0) {
                //User_profileController u = new User_profileController();

                //label2.setText("");
                label1.setText("Login Sucessfully!!");
                s.close();
                conn.close();
                Parent fxml2 = FXMLLoader.load(getClass().getResource("free_user.fxml"));
                Scene fxml2scene = new Scene(fxml2);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(fxml2scene);
                window.show();
            } else if (m == 1) {
                Parent fxml2 = FXMLLoader.load(getClass().getResource("prim_user.fxml"));
                Scene fxml2scene = new Scene(fxml2);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(fxml2scene);
                window.show();
            }

        } else {
            label1.setText("Error!!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
