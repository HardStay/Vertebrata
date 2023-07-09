package multiplescene.lapor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import modeldata.DataMarka;

public class PetaLaporController implements Initializable {
    @FXML
    private Button buttonSelesai;

    @FXML
    private Label labelLatitude;

    @FXML
    private Label labelLongitude;

    @FXML
    private AnchorPane panePeta;

    @FXML
    private ImageView petaIndo;

    private boolean flag = true;

    private double latitude;
    private double longitude;

    @FXML
    void handleButtonMarka(MouseEvent event) {
        longitude = event.getX();
        latitude = event.getY();
        labelLongitude.setText(String.valueOf(longitude));
        labelLatitude.setText(String.valueOf(latitude));
        Circle markabulatan = new Circle(longitude, latitude, 5, Color.RED);
        if (flag == true) {
            panePeta.getChildren().add(markabulatan);
            flag = false;
        } else {
            panePeta.getChildren().set(6, markabulatan);
        }
    }

    @FXML
    void handleButtonSelesai(ActionEvent event) throws IOException {
        if (panePeta.getChildren().size() == 7) {
            DataMarka logMarka = new DataMarka(longitude, latitude);
            XStream xstream = new XStream(new StaxDriver());
            FileOutputStream dataMarka = null;
            String xml = xstream.toXML(logMarka);
            try {
                dataMarka = new FileOutputStream("DataMarka.xml");
                byte[] bytes = xml.getBytes("UTF-8");
                dataMarka.write(bytes);
            } catch (Exception e) {
                System.out.println("Perhatian: " + e.getMessage());
            } finally {
                if (dataMarka != null) {
                    try {
                        dataMarka.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Data berhasil disimpan");
            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(1));
            pt.setOnFinished(ev -> {
                System.out.println("Tandai Lokasi Succesfully");
                buttonSelesai.getScene().getWindow().hide();
            });
            pt.play();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
