/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_krypton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dark Wizard
 */
public class File_EnController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;
    @FXML
    private Label label1;

    @FXML
    void En(ActionEvent event) throws IOException {

        System.out.println(text1.getText());

        try (FileInputStream fis = new FileInputStream(text1.getText())) {
            String a = text2.getText();
            int key = Integer.parseInt(a);

            byte data[] = new byte[fis.available()];

            fis.read(data);
            int i = 0;

            for (byte b : data) {
                data[i] = (byte) (b ^ key);
                i++;
            }

            // Writing new byte array value to image which
            // will Encrypt it.
            try ( // Opening a file for writing purpose
                    FileOutputStream fos = new FileOutputStream(
                            text1.getText())) {
                // Writing new byte array value to image which
                // will Encrypt it.
                fos.write(data);
                // Closing file
            }
        }
        label1.setText("Encryption Done...");
        text1.clear();
        text2.clear();

    }

    @FXML
    void file_chose(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);

        if (file != null) {
            text1.setText(file.getAbsolutePath());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
