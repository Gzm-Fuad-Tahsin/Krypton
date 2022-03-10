/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_krypton;


import java.net.URL;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;
import static project_krypton.User_profileController.name1;

/**
 * FXML Controller class
 *
 * @author Dark Wizard
 */
public class Key_genController implements Initializable {

    @FXML
    private TextArea textarea1;

    @FXML
    private TextArea textarea2;
    @FXML
    private Label label1;
    static String name1;
    
    String mail;
    
    
    

    @FXML
    void gen_key(ActionEvent event) throws NoSuchAlgorithmException {
        //Creating KeyPair generator object
      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
      
      //Initializing the KeyPairGenerator
      keyPairGen.initialize(2056);
      
      //Generating the pair of keys
      KeyPair pair = keyPairGen.generateKeyPair();
      //System.out.println(pair);
      // this is text from tahsin
      
      //Getting the private key from the key pair
      Base64.Encoder encoder = Base64.getEncoder();
      PrivateKey privKey = pair.getPrivate();
      
      String a = privKey.toString();
       //System.out.println(a);
       
       Key key = new SecretKeySpec(a.getBytes(),0,a.getBytes().length, "RSA");  
       
      // textarea1.setText(encoder.encodeToString(key.getEncoded()));
       
      // System.out.println(encoder.encodeToString(a.getEncoded()));
      
     textarea2.setText(encoder.encodeToString(privKey.getEncoded()));
     //System.out.println(privKey);
      
      //Getting the public key from the key pair
      PublicKey publicKey = pair.getPublic(); 
      textarea1.setText(encoder.encodeToString(publicKey.getEncoded()));
       //System.out.println(publicKey);
     // System.out.println("Keys generated");

    }
    
    
    
     public static void try1(String name) {
        Key_genController.name1 = name;
    }
    
    @FXML
    void mail_me(ActionEvent event) throws NoSuchAlgorithmException, MessagingException {
        
        callme();
        
        

      
    }
    
    public void callme(){
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

            String qury = "SELECT mail FROM user_table where user_name = '" + name1 + "'";

            ResultSet r = s.executeQuery(qury);
            
            

            while (r.next()) {

                mail = r.getString("mail");

            }
        } catch (SQLException ex) {
            Logger.getLogger(User_profileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String to = mail;

        // email ID of Sender.
        String from = "krypton.official.service@gmail.com";
        String pass1 = "iamlonly01";

        // using host as localhost
        String host = "localhost";
        String sub = "Your Public Key And Private Key";
        String content = "Public Key : \n"+textarea1.getText()+"\nPrivate Key : \n"+textarea2.getText();
        

        // Getting system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true"); //authentication
        properties.put("mail.smtp.starttls.enable", "true"); //authentication
        properties.put("mail.smtp.host", "smtp.gmail.com"); //authentication
        properties.put("mail.smtp.port", "587"); //authentication
        
        


        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
           protected  PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(from, pass1);
           }
        });
        try{
             // MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From Field: adding senders email to from field.
            message.setFrom(from);
            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            //subject of mail
            message.setSubject(sub);
            
            //content
            message.setText(content);
            
            //transport to the reciever
            Transport.send(message);
            label1.setText("mail sent");
            
            
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
