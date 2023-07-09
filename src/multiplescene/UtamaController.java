package multiplescene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import OpenScene.OpenScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class UtamaController implements Initializable {
    private OpenScene openSceneMulti = new OpenScene();

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label labelJudul;

    @FXML
    private Button buttonLogOut;

    @FXML
    void handleButtonHome(MouseEvent event) throws IOException {
        openSceneMulti.getStage(event, "/homepage/homepage.fxml");
    }

    @FXML
    void handleButtonLogOut(ActionEvent event) throws IOException {
        openSceneMulti.getStage(event, "/login/login.fxml");
    }

    @FXML
    void keEdukasi(ActionEvent event) {
        mainPane.setCenter(openSceneMulti.getPane("/multiplescene/edukasi/edukasi.fxml"));
        labelJudul.setText("EDUWILD");
    }

    @FXML
    void keLapor(ActionEvent event) {
        mainPane.setCenter(openSceneMulti.getPane("/multiplescene/lapor/lapor.fxml"));
        labelJudul.setText("LAPOR");
    }

    @FXML
    void kePantau(ActionEvent event) {
        mainPane.setCenter(openSceneMulti.getPane("/multiplescene/pantau/pantau.fxml"));
        labelJudul.setText("PANTAU");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
