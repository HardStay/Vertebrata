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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import modeldata.DataLogin;
import modeldata.DataUser;

public class ProfilUserController implements Initializable {

    private OpenScene openScene = new OpenScene();

    @FXML
    private Label angkaHp;

    @FXML
    private Button buttonVerif;

    @FXML
    private ImageView gambarCentang;

    @FXML
    private ImageView gambarSilang;

    @FXML
    private Label labelNama;

    @FXML
    private Label labelTTL;

    @FXML
    private Label labelTitikDua;

    @FXML
    private Label labelUsername;

    @FXML
    private Text labelVerif;

    @FXML
    private Label labelVerifCentang;

    @FXML
    private Label labelVerifSilang;

    @FXML
    private Label noHp;

    @FXML
    void handleButtonBack(ActionEvent event) throws IOException{
        openScene.getStage(event, "/homepage/homepage.fxml");
    }

    @FXML
    void handleButtonLogOut(ActionEvent event) throws IOException{
        openScene.getStage(event, "/login/login.fxml");
    }

    XStream xstream = new XStream(new StaxDriver());
    DataLogin logPelapor = new DataLogin();
    ArrayList<DataUser> listUser = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        angkaHp.setVisible(false);
        noHp.setVisible(false);
        labelTitikDua.setVisible(false);
        labelVerifCentang.setVisible(false);
        labelVerifSilang.setVisible(false);
        labelVerif.setVisible(false);
        gambarCentang.setVisible(false);
        gambarSilang.setVisible(false);
        buttonVerif.setVisible(false);
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
        FileInputStream data = null;
        try {
            data = new FileInputStream("DataUser.xml");

            int isi;
            char c;
            String stringnya = "";

            while ((isi = data.read()) != -1) {
                c = (char) isi;
                stringnya += c;
            }
            ArrayList<DataUser> list = (ArrayList<DataUser>) xstream.fromXML(stringnya);
            listUser = list;

        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (data != null) {
                try {
                    data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUsername().equals(logPelapor.getLoginUser())) {
                labelNama.setText(listUser.get(i).getNama());
                labelTTL.setText(listUser.get(i).getTtl());
                labelUsername.setText(listUser.get(i).getUsername());
            }
        }
    }
}
