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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class Sign_upController implements Initializable {

    @FXML
    private Label label1;

    @FXML
    private TextField text1;

    @FXML
    private TextField text2;

    @FXML
    private TextField text3;

    @FXML
    private TextField text4;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    public void back(ActionEvent event) throws IOException {

        Parent fxml2 = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene fxml2scene = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(fxml2scene);
        window.show();
    }

    public void signup(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        String Pass_Regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$!%^&+=])" + "(?=\\S+$).{8,20}$";

        Pattern pattern = Pattern.compile(regex);
        Pattern p = Pattern.compile(Pass_Regex);
        String name = text1.getText();
        String user_name = text2.getText();
        String phonenumber = text4.getText();
        String email = text3.getText();
        Matcher matcher = pattern.matcher(email);
        
        String f_pass = pass1.getText();
        String c_pass = pass2.getText();
        Matcher mm = p.matcher(f_pass);

        if (name.isEmpty() || user_name.isEmpty() || phonenumber.isEmpty() || email.isEmpty() || f_pass.isEmpty() || c_pass.isEmpty()) {

            label1.setText("Fill up All information.");
        } else if (name.isEmpty() && user_name.isEmpty() && phonenumber.isEmpty() && email.isEmpty() && f_pass.isEmpty() && c_pass.isEmpty()) {

            label1.setText("Fill up All information.");
        } else {

            if (matcher.matches()) {
               if (mm.matches()) {
                if (f_pass.equals(c_pass)) {

                    Class.forName("com.mysql.cj.jdbc.Driver");

//        Connection conn = (Connection)
//        DriverManager.getConnection("jdbc:mysql://localhost:3306/try?severTimezone=UTC","root","");
                    String url = "jdbc:mysql://bjn9yajxknszeuvhgczi-mysql.services.clever-cloud.com:3306/bjn9yajxknszeuvhgczi?severTimezone=UTC";
                    String user = "uve5k169sb891uxy";
                    String pass = "SzDHDl2UNNEWJwgOFQxe";

                    Connection conn = DriverManager.getConnection(url, user, pass);

                    Statement s = conn.createStatement();

                    String qury = "SELECT user_name,mail FROM user_table ";
                    //Where user_name ='"+user_name+"' or mail ='"+email+"'

                    ResultSet r = s.executeQuery(qury);
                    int l = 0, m = 0;

                    while (r.next()) {
                        String name1, mail;

                        name1 = r.getString("user_name");
                        mail = r.getString("mail");

                        if (user_name.equals(name1)) {
                            l++;
                        }
                        if (email.equals(mail)) {
                            m++;
                        }

                    }
                    //System.out.println(l);
                   // System.out.println(m);
                    if (l > 0 && m > 0) {
                        //label2.setText("");
                        label1.setText("user name and email is all ready exist");

                    } else if (l > 0 && m == 0) {
                        label1.setText("user name is all ready exist");
                    } else if (l == 0 && m > 0) {
                        label1.setText("email is all ready exist");
                    } else {
                        String q = "INSERT INTO `user_table`(`Name`, `user_name`, `mail`, `Phone_number`, `u_password`,`sy_cipher`) VALUES ('" + name + "','" + user_name + "','" + email + "','" + phonenumber + "','" + c_pass + "','No')";
                        s.executeUpdate(q);
                        s.close();
                        conn.close();
                        User_profileController.try1(text2.getText());
                        PaymentController.try1(text2.getText());
                        Key_genController.try1(text1.getText());

                        Parent fxml2 = FXMLLoader.load(getClass().getResource("payment.fxml"));
                        Scene fxml2scene = new Scene(fxml2);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(fxml2scene);
                        window.show();

                    }

                } else {
                    label1.setText("Password and confirm password not matched");
                }

            } else {
                label1.setText("Password must contain both case and one speacial cherectar and one digit.");
            }
        }else {
                label1.setText("Email is Invalid.");
            }
    }
 }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
