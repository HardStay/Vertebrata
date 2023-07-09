package profile;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import OpenScene.OpenScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modeldata.DataKomunitas;
import modeldata.DataLogin;

public class ProfilKomunitasController implements Initializable {

    private OpenScene openScene = new OpenScene();

    @FXML
    private Label labelAlamat;

    @FXML
    private Label labelJumlahAnggota;

    @FXML
    private Label labelNama;

    @FXML
    private Label labelNoHp;

    @FXML
    private Label labelUsername;

    @FXML
    void handleButtonBack(ActionEvent event) throws IOException {
        openScene.getStage(event, "/homepage/homepage.fxml");
    }

    @FXML
    void handleButtonLogOut(ActionEvent event) throws IOException {
        openScene.getStage(event, "/login/login.fxml");
    }

    XStream xstream = new XStream(new StaxDriver());
    DataLogin logPelapor = new DataLogin();
    ArrayList<DataKomunitas> listKomunitas = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        FileInputStream dataKomunitas = null;
        try {
            dataKomunitas = new FileInputStream("DataKomunitas.xml");

            int isi;
            char c;
            String stringnya = "";

            while ((isi = dataKomunitas.read()) != -1) {
                c = (char) isi;
                stringnya += c;
            }
            ArrayList<DataKomunitas> list = (ArrayList<DataKomunitas>) xstream.fromXML(stringnya);
            listKomunitas = list;
        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (dataKomunitas != null) {
                try {
                    dataKomunitas.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < listKomunitas.size(); i++) {
            if (listKomunitas.get(i).getUsername().equals(logPelapor.getLoginUser())) {
                labelNama.setText(listKomunitas.get(i).getNama());
                labelUsername.setText(listKomunitas.get(i).getUsername());
                labelAlamat.setText(listKomunitas.get(i).getAlamat());
                labelJumlahAnggota.setText(listKomunitas.get(i).getJumlah() + " anggota");
                labelNoHp.setText(listKomunitas.get(i).getNoHp());
            }
        }
    }

}
