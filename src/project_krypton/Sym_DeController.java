
package project_krypton;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



/**
 * FXML Controller class
 *
 * @author Dark Wizard
 */
public class Sym_DeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static SecretKeySpec secretKey;
    private static byte[] key;
    
    
    @FXML
    private TextField text1;

    @FXML
    private TextArea textarea1;

    @FXML
    private TextArea textarea2;
    
     public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            System.out.println(key);
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    public void De(ActionEvent event) {
    final String secretKey = text1.getText();
     
    String originalString = textarea1.getText();
    String decryptedString = Sym_DeController.decrypt(originalString, secretKey) ;
    textarea1.clear();
    text1.clear();
    textarea2.setText(decryptedString);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
