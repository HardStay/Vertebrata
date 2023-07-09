package multiplescene.pantau;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import modeldata.DataKomunitas;
import modeldata.DataUser;

public class PantauController implements Initializable {
    @FXML
    private ImageView petaIndo;

    @FXML
    private AnchorPane panePeta;

    ArrayList<DataUser> dataUser = new ArrayList<>();
    ArrayList<DataKomunitas> dataKomunitas = new ArrayList<>();

    XStream xstream = new XStream(new StaxDriver());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xstream.addPermission(AnyTypePermission.ANY);
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
            dataUser = list;

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
        FileInputStream datas = null;
        try {
            datas = new FileInputStream("DataKomunitas.xml");

            int isi;
            char c;
            String stringnya = "";

            while ((isi = datas.read()) != -1) {
                c = (char) isi;
                stringnya += c;
            }
            ArrayList<DataKomunitas> list = (ArrayList<DataKomunitas>) xstream.fromXML(stringnya);
            dataKomunitas = list;

        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
        } finally {
            if (datas != null) {
                try {
                    datas.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < dataUser.size(); i++) {
            for (int j = 0; j < dataUser.get(i).getLaporanSize(); j++) {
                if (dataUser.get(i).getStatus(j).equals("DIPROSES")) {
                    Circle markabulatan = new Circle(dataUser.get(i).getLongitude(j), dataUser.get(i).getLatitude(j),
                            15, Color.RED);
                    markabulatan.setOpacity(0.35);
                    panePeta.getChildren().add(markabulatan);
                }
            }
        }
        for (int i = 0; i < dataKomunitas.size(); i++) {
            for (int j = 0; j < dataKomunitas.get(i).getLaporanSize(); j++) {
                if (dataKomunitas.get(i).getStatus(j).equals("DIPROSES")) {
                    Circle markabulatan = new Circle(dataKomunitas.get(i).getLongitude(j),
                            dataKomunitas.get(i).getLatitude(j), 15, Color.RED);
                    markabulatan.setOpacity(0.35);
                    panePeta.getChildren().add(markabulatan);
                }
            }
        }
    }

}
