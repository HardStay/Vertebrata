package operator.verifikasi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class VerifikasiController implements Initializable {
    @FXML
    private BorderPane mainPane;

    @FXML
    private Button buttonVerifDonasi;

    @FXML
    private Button buttonVerifUser;

    private static VerifikasiController instance;
    private OpenScene1 openScene1 = new OpenScene1();

    public VerifikasiController() {
        instance = this;
    }

    public static VerifikasiController getInstance() {
        return instance;
    }

    public void refreshVerif() {
        OpenScene1 object = new OpenScene1();
        mainPane.setCenter(object.getPane("/operator/verifikasi/veriflaporan/verifikasilaporan.fxml"));
    }

    @FXML
    void keVerifikasiUser(ActionEvent event) {
        OpenScene1 object = new OpenScene1();
        Pane halaman = object.getPane("/operator/verifikasi/verifuser/verifikasiuser.fxml");
        mainPane.setCenter(halaman);
    }

    @FXML
    void keVerifikasiPenggalangan(ActionEvent event) {
        Pane halaman = openScene1.getPane("/operator/verifikasi/verifpenggalangan/verifpenggalangandana.fxml");
        mainPane.setCenter(halaman);
    }

    @FXML
    void keVerifikasiLaporan() {
        OpenScene1 object = new OpenScene1();
        mainPane.setCenter(object.getPane("/operator/verifikasi/veriflaporan/verifikasilaporan.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonVerifDonasi.setVisible(false);
        buttonVerifUser.setVisible(false);
    }
}
