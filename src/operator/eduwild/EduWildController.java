package operator.eduwild;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import modeldata.DataEduWild;

public class EduWildController implements Initializable {
    @FXML
    private TextArea fieldDeskripsi;

    @FXML
    private TextField fieldJudul;

    @FXML
    private Label labelFile;

    @FXML
    private Button pilihFile;

    File file;
    String imageSrc;

    @FXML
    void handleButtonPilihFile(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("All Images", "*.jpeg", "*.jpg", "*.png"),
                new ExtensionFilter("JPEG Image", "*.jpeg"),
                new ExtensionFilter("JPG Image", "*.jpg"), new ExtensionFilter("PNG Image", "*.png"));
        file = fc.showOpenDialog(stage);
        if (file != null) {
            labelFile.setText(file.getName());
            labelFile.setVisible(true);
            imageSrc = file.getPath();
        } else {
            System.out.println("Tidak ada file yang dipilih");
        }
    }

    @FXML
    void handleButtonUpload(ActionEvent event) {
        if (!fieldJudul.getText().equals("") && !fieldDeskripsi.getText().equals("")) {
            ArrayList<DataEduWild> listEdu = new ArrayList<>();
            XStream xstream = new XStream(new StaxDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            FileInputStream data = null;
            try {
                data = new FileInputStream("DataEduWild.xml");

                int isi;
                char c;
                String stringnya = "";

                while ((isi = data.read()) != -1) {
                    c = (char) isi;
                    stringnya += c;
                }
                ArrayList<DataEduWild> list = (ArrayList<DataEduWild>) xstream.fromXML(stringnya);
                listEdu = list;

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
            FileOutputStream dataEdu = null;
            String judul = fieldJudul.getText();
            String deskripsi = fieldDeskripsi.getText();
            listEdu.add(new DataEduWild(judul, deskripsi, imageSrc));
            String xml = xstream.toXML(listEdu);
            try {
                dataEdu = new FileOutputStream("DataEduWild.xml");
                byte[] bytes = xml.getBytes("UTF-8");
                dataEdu.write(bytes);
            } catch (Exception e) {
                System.out.println("Perhatian: " + e.getMessage());
            } finally {
                if (dataEdu != null) {
                    try {
                        dataEdu.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Data berhasil disimpan");
            PauseTransition pt = new PauseTransition();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Upload Succesfully");
            alert.setContentText("Selamat Konten Berhasil Ditambahkan");
            pt.setDuration(Duration.seconds(1));
            pt.setOnFinished(ev -> {
                System.out.println("Upload Konten Succesfully");
                labelFile.setVisible(false);
                fieldJudul.setText("");
                fieldDeskripsi.setText("");
            });
            pt.play();
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelFile.setVisible(false);
    }
}
