package operator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import OpenScene.OpenScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class OperatorController implements Initializable {

    private OpenScene openScene = new OpenScene();

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button buttonLogOut;

    @FXML
    private Button buttonKonservasi;

    @FXML
    void handleButtonLogOut(ActionEvent event) throws IOException {
        openScene.getStage(event, "/login/login.fxml");
    }

    @FXML
    void keVerifikasi(ActionEvent event) {
        mainPane.setCenter(openScene.getPane("/operator/verifikasi/halamanverifikasi.fxml"));
    }

    @FXML
    void keEduWild(ActionEvent event) {
        mainPane.setCenter(openScene.getPane("/operator/eduwild/UploadEduWild.fxml"));
    }

    @FXML
    void keKonservasi(ActionEvent event) {
        mainPane.setCenter(openScene.getPane("/operator/konservasi/UploadKegiatan.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonKonservasi.setVisible(false);
    }
}
