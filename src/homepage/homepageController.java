package homepage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import OpenScene.OpenScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modeldata.DataLogin;

public class homepageController implements Initializable {

    private OpenScene openScene = new OpenScene();

    @FXML
    private ImageView imageDonasi;

    @FXML
    private Button buttonDonasi;

    @FXML
    private Button buttonEdu;

    @FXML
    private Button buttonLapor;

    @FXML
    private Button buttonPantau;

    @FXML
    private Label contentDonasi;

    public void handleButtonEdu(ActionEvent event) throws IOException {
        openScene.getStage(event, "/multiplescene/HalamanUtama.fxml");
    }

    XStream xstream = new XStream(new StaxDriver());
    DataLogin logPelapor = new DataLogin();

    @FXML
    void handleButtonProfile(MouseEvent event) throws IOException {
        xstream.addPermission(AnyTypePermission.ANY);
        FileInputStream dataLog = null;
        try {
            dataLog = new FileInputStream("DataLogin.xml");

            int isi;
            char c;
            String stringnya = "";

            while ((isi = dataLog.read()) != -1) {
                c = (char) isi;
                stringnya += c;
            }
            logPelapor = (DataLogin) xstream.fromXML(stringnya);
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (dataLog != null) {
                try {
                    dataLog.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (logPelapor.getUser().equals("User")) {
            openScene.getStage(event, "/profile/profiluser.fxml");
        } else if (logPelapor.getUser().equals("Komunitas")) {
            openScene.getStage(event, "/profile/profilkomunitas.fxml");
        }
    }

    @FXML
    void handleButtonLapor(ActionEvent event) throws IOException {
        openScene.getStage(event, "/multiplescene/HalamanUtama.fxml");
        // buttonLapor.getScene().getWindow().hide();
        // Stage login = new Stage();
        // Parent root;
        // try {
        // root =
        // FXMLLoader.load(getClass().getResource("/multiplescene/HalamanUtama.fxml"));
        // Scene scene = new Scene(root);
        // login.setScene(scene);
        // login.show();
        // login.setResizable(false);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    @FXML
    void handleButtonPantau(ActionEvent event) throws IOException {
        openScene.getStage(event, "/multiplescene/HalamanUtama.fxml");
        // buttonPantau.getScene().getWindow().hide();
        // Stage login = new Stage();
        // Parent root;
        // try {
        // root =
        // FXMLLoader.load(getClass().getResource("/multiplescene/HalamanUtama.fxml"));
        // Scene scene = new Scene(root);
        // login.setScene(scene);
        // login.show();
        // login.setResizable(false);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageDonasi.setVisible(false);
        buttonDonasi.setVisible(false);
        contentDonasi.setVisible(false);
    }
}
