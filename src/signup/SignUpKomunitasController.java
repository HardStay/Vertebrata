package signup;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import modeldata.DataKomunitas;
import modeldata.DataLaporan;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;

public class SignUpKomunitasController implements Initializable {
    @FXML
    private TextArea alamat;

    @FXML
    private TextField jumlahAnggota;

    @FXML
    private TextField namaKomunitas;

    @FXML
    private TextField nomorTelepon;

    @FXML
    private Button pilihFile;

    @FXML
    private Button registerKomunitas;

    @FXML
    private TextArea tujuan;

    @FXML
    private TextField passKomunitas;

    @FXML
    private TextField usernameKomunitas;

    @FXML
    private Label labelFile;

    File file;

    @FXML
    void handleButtonPilihFile(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF File", "*.pdf"));
        file = fc.showOpenDialog(stage);
        labelFile.setText(file.getName());
        labelFile.setVisible(true);
    }

    @FXML
    void handleButtonRegisterKomunitas(ActionEvent event) {
        if (!usernameKomunitas.getText().equals("") && !passKomunitas.getText().equals("")) {
            ArrayList<DataKomunitas> listKomunitas = new ArrayList<>();
            XStream xstream = new XStream(new StaxDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            FileInputStream data = null;
            try {
                data = new FileInputStream("DataKomunitas.xml");

                int isi;
                char c;
                String stringnya = "";

                while ((isi = data.read()) != -1) {
                    c = (char) isi;
                    stringnya += c;
                }
                ArrayList<DataKomunitas> list = (ArrayList<DataKomunitas>) xstream.fromXML(stringnya);
                listKomunitas = list;

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
            FileOutputStream dataKomunitas = null;
            String username = usernameKomunitas.getText();
            String pass = passKomunitas.getText();
            String nama = namaKomunitas.getText();
            String alamatK = alamat.getText();
            String tujuanK = tujuan.getText();
            String jumlah = jumlahAnggota.getText();
            String noHp = nomorTelepon.getText();
            ArrayList<DataLaporan> laporan = new ArrayList<>();
            listKomunitas.add(new DataKomunitas(username, pass, nama, alamatK, tujuanK, jumlah, noHp, file, laporan));
            String xml = xstream.toXML(listKomunitas);
            try {
                dataKomunitas = new FileOutputStream("DataKomunitas.xml");
                byte[] bytes = xml.getBytes("UTF-8");
                dataKomunitas.write(bytes);
            } catch (Exception e) {
                System.out.println("Perhatian: " + e.getMessage());
            } finally {
                if (dataKomunitas != null) {
                    try {
                        dataKomunitas.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Data berhasil disimpan");
            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(1));
            pt.setOnFinished(ev -> {
                System.out.println("SignUp Komunitas Succesfully");
                registerKomunitas.getScene().getWindow().hide();
                Stage login = new Stage();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
                    Scene scene = new Scene(root);
                    login.setScene(scene);
                    login.show();
                    login.setResizable(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            pt.play();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelFile.setVisible(false);
    }
}
