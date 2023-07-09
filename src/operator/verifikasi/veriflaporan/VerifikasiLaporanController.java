package operator.verifikasi.veriflaporan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import static javafx.collections.FXCollections.observableArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modeldata.DataKomunitas;
import modeldata.DataUser;
import modeldata.DataVerifLaporan;
import operator.verifikasi.VerifikasiController;

public class VerifikasiLaporanController implements Initializable {

    ObservableList<DataVerifLaporan> dataVerif = observableArrayList();

    @FXML
    private TextField fieldNo;

    @FXML
    private AnchorPane layout;

    @FXML
    private TableView<DataVerifLaporan> table;

    @FXML
    private TableColumn<DataVerifLaporan, String> kolomBukti;

    @FXML
    private TableColumn<DataVerifLaporan, String> kolomDeskripsi;

    @FXML
    private TableColumn<DataVerifLaporan, String> kolomJenis;

    @FXML
    private TableColumn<DataVerifLaporan, Double> kolomLatitude;

    @FXML
    private TableColumn<DataVerifLaporan, Double> kolomLongitude;

    @FXML
    private TableColumn<DataVerifLaporan, String> kolomNama;

    @FXML
    private TableColumn<DataVerifLaporan, Integer> kolomNo;

    @FXML
    private TableColumn<DataVerifLaporan, String> kolomTanggal;

    @FXML
    private TableColumn<DataVerifLaporan, String> kolomStatus;

    @FXML
    void handleButtonRemove(ActionEvent event) {
        if (!fieldNo.getText().equals("")) {
            int index = Integer.parseInt(fieldNo.getText()) - 1;
            if (index < counter) {
                userloop: for (int i = 0; i < dataUser.size(); i++) {
                    for (int j = 0; j < dataUser.get(i).getLaporanSize(); j++) {
                        if (dataVerif.get(index).getLongitude() == dataUser.get(i).getLongitude(j)
                                && dataVerif.get(index).getLatitude() == dataUser.get(i).getLatitude(j)) {
                            dataUser.get(i).removeLaporan(j);
                            break userloop;
                        }
                    }
                }
                dataVerif.remove(index);
                FileOutputStream dataLaporanOut = null;
                String xml = xstream.toXML(dataUser);
                try {
                    dataLaporanOut = new FileOutputStream("DataUser.xml");
                    byte[] bytes = xml.getBytes("UTF-8");
                    dataLaporanOut.write(bytes);
                } catch (Exception e) {
                    System.out.println("Perhatian: " + e.getMessage());
                } finally {
                    if (dataLaporanOut != null) {
                        try {
                            dataLaporanOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                komunitasloop: for (int i = 0; i < dataKomunitas.size(); i++) {
                    for (int j = 0; j < dataKomunitas.get(i).getLaporanSize(); j++) {
                        if (dataVerif.get(index).getLongitude() == dataKomunitas.get(i).getLongitude(j)
                                && dataVerif.get(index).getLatitude() == dataKomunitas.get(i).getLatitude(j)) {
                            dataKomunitas.get(i).removeLaporan(j);
                            break komunitasloop;
                        }
                    }
                }
                dataVerif.remove(index);
                FileOutputStream dataKomunitasOut = null;
                String xmla = xstream.toXML(dataKomunitas);
                try {
                    dataKomunitasOut = new FileOutputStream("DataKomunitas.xml");
                    byte[] bytes = xmla.getBytes("UTF-8");
                    dataKomunitasOut.write(bytes);
                } catch (Exception e) {
                    System.out.println("Perhatian: " + e.getMessage());
                } finally {
                    if (dataKomunitasOut != null) {
                        try {
                            dataKomunitasOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            fieldNo.setText("");
            VerifikasiController.getInstance().refreshVerif();
        }
    }

    @FXML
    void handleButtonSelesai(ActionEvent event) {
        if (!fieldNo.getText().equals("")) {
            int index = Integer.parseInt(fieldNo.getText()) - 1;
            if (index < counter) {
                userloop: for (int i = 0; i < dataUser.size(); i++) {
                    for (int j = 0; j < dataUser.get(i).getLaporanSize(); j++) {
                        if (dataVerif.get(index).getLongitude() == dataUser.get(i).getLongitude(j)
                                && dataVerif.get(index).getLatitude() == dataUser.get(i).getLatitude(j)) {
                            dataUser.get(i).ubahStatusLaporan(dataVerif.get(index).getBukti(), "SELESAI");
                            break userloop;
                        }
                    }
                }
                dataVerif.get(index).setStatus("SELESAI");
                FileOutputStream dataLaporanOut = null;
                String xml = xstream.toXML(dataUser);
                try {
                    dataLaporanOut = new FileOutputStream("DataUser.xml");
                    byte[] bytes = xml.getBytes("UTF-8");
                    dataLaporanOut.write(bytes);
                } catch (Exception e) {
                    System.out.println("Perhatian: " + e.getMessage());
                } finally {
                    if (dataLaporanOut != null) {
                        try {
                            dataLaporanOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                komunitasloop: for (int i = 0; i < dataKomunitas.size(); i++) {
                    for (int j = 0; j < dataKomunitas.get(i).getLaporanSize(); j++) {
                        if (dataVerif.get(index).getLongitude() == dataKomunitas.get(i).getLongitude(j)
                                && dataVerif.get(index).getLatitude() == dataKomunitas.get(i).getLatitude(j)) {
                            dataKomunitas.get(i).ubahStatusLaporan(dataVerif.get(index).getBukti(), "SELESAI");
                            break komunitasloop;
                        }
                    }
                }
                dataVerif.get(index).setStatus("SELESAI");
                FileOutputStream dataKomunitasOut = null;
                String xmla = xstream.toXML(dataKomunitas);
                try {
                    dataKomunitasOut = new FileOutputStream("DataKomunitas.xml");
                    byte[] bytes = xmla.getBytes("UTF-8");
                    dataKomunitasOut.write(bytes);
                } catch (Exception e) {
                    System.out.println("Perhatian: " + e.getMessage());
                } finally {
                    if (dataKomunitasOut != null) {
                        try {
                            dataKomunitasOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            fieldNo.setText("");
            VerifikasiController.getInstance().refreshVerif();
        }
    }

    @FXML
    void handleButtonTerima(ActionEvent event) {
        if (!fieldNo.getText().equals("")) {
            int index = Integer.parseInt(fieldNo.getText()) - 1;
            if (index < counter) {
                userloop: for (int i = 0; i < dataUser.size(); i++) {
                    for (int j = 0; j < dataUser.get(i).getLaporanSize(); j++) {
                        if (dataVerif.get(index).getLongitude() == dataUser.get(i).getLongitude(j)
                                && dataVerif.get(index).getLatitude() == dataUser.get(i).getLatitude(j)) {
                            dataUser.get(i).ubahStatusLaporan(dataVerif.get(index).getBukti(), "DIPROSES");
                            break userloop;
                        }
                    }
                }
                dataVerif.get(index).setStatus("DIPROSES");
                FileOutputStream dataLaporanOut = null;
                String xml = xstream.toXML(dataUser);
                try {
                    dataLaporanOut = new FileOutputStream("DataUser.xml");
                    byte[] bytes = xml.getBytes("UTF-8");
                    dataLaporanOut.write(bytes);
                } catch (Exception e) {
                    System.out.println("Perhatian: " + e.getMessage());
                } finally {
                    if (dataLaporanOut != null) {
                        try {
                            dataLaporanOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                komunitasloop: for (int i = 0; i < dataKomunitas.size(); i++) {
                    for (int j = 0; j < dataKomunitas.get(i).getLaporanSize(); j++) {
                        if (dataVerif.get(index).getLongitude() == dataKomunitas.get(i).getLongitude(j)
                                && dataVerif.get(index).getLatitude() == dataKomunitas.get(i).getLatitude(j)) {
                            dataKomunitas.get(i).ubahStatusLaporan(dataVerif.get(index).getBukti(), "DIPROSES");
                            break komunitasloop;
                        }
                    }
                }
                dataVerif.get(index).setStatus("DIPROSES");
                FileOutputStream dataKomunitasOut = null;
                String xmla = xstream.toXML(dataKomunitas);
                try {
                    dataKomunitasOut = new FileOutputStream("DataKomunitas.xml");
                    byte[] bytes = xmla.getBytes("UTF-8");
                    dataKomunitasOut.write(bytes);
                } catch (Exception e) {
                    System.out.println("Perhatian: " + e.getMessage());
                } finally {
                    if (dataKomunitasOut != null) {
                        try {
                            dataKomunitasOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            fieldNo.setText("");
            VerifikasiController.getInstance().refreshVerif();
        }
    }

    @FXML
    void handleButtonTolak(ActionEvent event) {
        if (!fieldNo.getText().equals("")) {
            int index = Integer.parseInt(fieldNo.getText()) - 1;
            if (index < counter) {
                userloop: for (int i = 0; i < dataUser.size(); i++) {
                    for (int j = 0; j < dataUser.get(i).getLaporanSize(); j++) {
                        if (dataVerif.get(index).getLongitude() == dataUser.get(i).getLongitude(j)
                                && dataVerif.get(index).getLatitude() == dataUser.get(i).getLatitude(j)) {
                            dataUser.get(i).ubahStatusLaporan(dataVerif.get(index).getBukti(), "DITOLAK");
                            break userloop;
                        }
                    }
                }
                dataVerif.get(index).setStatus("DITOLAK");
                FileOutputStream dataLaporanOut = null;
                String xml = xstream.toXML(dataUser);
                try {
                    dataLaporanOut = new FileOutputStream("DataUser.xml");
                    byte[] bytes = xml.getBytes("UTF-8");
                    dataLaporanOut.write(bytes);
                } catch (Exception e) {
                    System.out.println("Perhatian: " + e.getMessage());
                } finally {
                    if (dataLaporanOut != null) {
                        try {
                            dataLaporanOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                komunitasloop: for (int i = 0; i < dataKomunitas.size(); i++) {
                    for (int j = 0; j < dataKomunitas.get(i).getLaporanSize(); j++) {
                        if (dataVerif.get(index).getLongitude() == dataKomunitas.get(i).getLongitude(j)
                                && dataVerif.get(index).getLatitude() == dataKomunitas.get(i).getLatitude(j)) {
                            dataKomunitas.get(i).ubahStatusLaporan(dataVerif.get(index).getBukti(), "DITOLAK");
                            break komunitasloop;
                        }
                    }
                }
                dataVerif.get(index).setStatus("DITOLAK");
                FileOutputStream dataKomunitasOut = null;
                String xmla = xstream.toXML(dataKomunitas);
                try {
                    dataKomunitasOut = new FileOutputStream("DataKomunitas.xml");
                    byte[] bytes = xmla.getBytes("UTF-8");
                    dataKomunitasOut.write(bytes);
                } catch (Exception e) {
                    System.out.println("Perhatian: " + e.getMessage());
                } finally {
                    if (dataKomunitasOut != null) {
                        try {
                            dataKomunitasOut.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            fieldNo.setText("");
            VerifikasiController.getInstance().refreshVerif();
        }
    }

    ArrayList<Button> arrButton = new ArrayList<>();

    private void handleButtonAction(ActionEvent event) throws IOException {
        for (int i = 0; i < banyakLaporan; i++) {
            if (event.getSource() == arrButton.get(i)) {
                if (i < counter) {
                    int counterUser = 0;
                    int tandaUser = 0;
                    int tandaBukti = 0;
                    userloop: for (int k = 0; k < dataUser.size(); k++) {
                        for (int j = 0; j < dataUser.get(k).getLaporanSize(); j++) {
                            if (counterUser == i) {
                                tandaUser = k;
                                tandaBukti = j;
                                break userloop;
                            }
                            counterUser++;
                        }
                    }
                    if (dataUser.get(tandaUser).getBukti(tandaBukti).endsWith("4")) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SampleViewVideo.fxml"));
                        VBox cardBox = fxmlLoader.load();
                        ViewVideoController vVideoController = fxmlLoader.getController();
                        vVideoController.setVideo(dataUser.get(tandaUser).getBukti(tandaBukti));
                        layout.getChildren().add(cardBox);
                    } else {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SampleViewImage.fxml"));
                        HBox cardBox = fxmlLoader.load();
                        ViewImageController vImageController = fxmlLoader.getController();
                        vImageController.setImage(dataUser.get(tandaUser).getBukti(tandaBukti));
                        layout.getChildren().add(cardBox);
                    }
                } else {
                    int counterKomunitas = counter;
                    int tandaKomunitas = counter;
                    int tandaBukti = counter;
                    komunitasloop: for (int k = 0; k < dataKomunitas.size(); k++) {
                        for (int j = 0; j < dataKomunitas.get(k).getLaporanSize(); j++) {
                            if (counterKomunitas == i) {
                                tandaKomunitas = k;
                                tandaBukti = j;
                                break komunitasloop;
                            }
                            counterKomunitas++;
                        }
                    }
                    if (dataKomunitas.get(tandaKomunitas).getBukti(tandaBukti).endsWith("4")) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SampleViewVideo.fxml"));
                        VBox cardBox = fxmlLoader.load();
                        ViewVideoController vVideoController = fxmlLoader.getController();
                        vVideoController.setVideo(dataKomunitas.get(tandaKomunitas).getBukti(tandaBukti));
                        layout.getChildren().add(cardBox);
                    } else {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("SampleViewImage.fxml"));
                        HBox cardBox = fxmlLoader.load();
                        ViewImageController vImageController = fxmlLoader.getController();
                        vImageController.setImage(dataKomunitas.get(tandaKomunitas).getBukti(tandaBukti));
                        layout.getChildren().add(cardBox);
                    }
                }
            }
        }
    }

    ArrayList<DataUser> dataUser = new ArrayList<>();
    ArrayList<DataKomunitas> dataKomunitas = new ArrayList<>();

    XStream xstream = new XStream(new StaxDriver());
    private int counter;
    private int banyakLaporan;

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
        counter = 0;
        banyakLaporan = 0;
        for (int j = 0; j < dataUser.size(); j++) {
            for (int i = 0; i < dataUser.get(j).getLaporanSize(); i++) {
                Button button = new Button("Lihat File");
                button.setStyle("-fx-text-fill: #ffffff;");
                arrButton.add(button);
                arrButton.get(counter).setOnAction(event -> {
                    try {
                        handleButtonAction(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                counter++;
            }
        }
        banyakLaporan = counter;
        for (int j = 0; j < dataKomunitas.size(); j++) {
            for (int i = counter; i < counter + dataKomunitas.get(j).getLaporanSize(); i++) {
                Button button = new Button("Lihat File");
                button.setStyle("-fx-text-fill: #ffffff;");
                arrButton.add(button);
                arrButton.get(banyakLaporan).setOnAction(event -> {
                    try {
                        handleButtonAction(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                banyakLaporan++;
            }
        }
        int counterButton = 0;
        for (int i = 0; i < dataUser.size(); i++) {
            for (int j = 0; j < dataUser.get(i).getLaporanSize(); j++) {
                dataVerif.add(
                        new DataVerifLaporan((counterButton + 1), dataUser.get(i).getPelapor(j),
                                dataUser.get(i).getJenisLaporan(j),
                                dataUser.get(i).getTanggal(j), dataUser.get(i).getBukti(j),
                                arrButton.get(counterButton),
                                dataUser.get(i).getDeskripsi(j), dataUser.get(i).getLongitude(j),
                                dataUser.get(i).getLatitude(j), dataUser.get(i).getStatus(j)));
                counterButton++;
            }
        }
        for (int i = 0; i < dataKomunitas.size(); i++) {
            for (int j = 0; j < dataKomunitas.get(i).getLaporanSize(); j++) {
                dataVerif.add(
                        new DataVerifLaporan((counterButton + 1), dataKomunitas.get(i).getPelapor(j),
                                dataKomunitas.get(i).getJenisLaporan(j),
                                dataKomunitas.get(i).getTanggal(j), dataKomunitas.get(i).getBukti(j),
                                arrButton.get(counterButton),
                                dataKomunitas.get(i).getDeskripsi(j), dataKomunitas.get(i).getLongitude(j),
                                dataKomunitas.get(i).getLatitude(j), dataKomunitas.get(i).getStatus(j)));
                counterButton++;
            }
        }
        kolomNo.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, Integer>("no"));
        kolomNama.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, String>("pelapor"));
        kolomJenis.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, String>("jenisLaporan"));
        kolomTanggal.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, String>("tanggal"));
        kolomBukti.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, String>("buktiFile"));
        kolomDeskripsi.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, String>("deskripsi"));
        kolomLongitude.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, Double>("longitude"));
        kolomLatitude.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, Double>("latitude"));
        kolomStatus.setCellValueFactory(new PropertyValueFactory<DataVerifLaporan, String>("status"));

        table.setItems(dataVerif);
    }
}
