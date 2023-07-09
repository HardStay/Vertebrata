package multiplescene.lapor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import modeldata.DataKomunitas;
import modeldata.DataLaporan;
import modeldata.DataLogin;
import modeldata.DataMarka;
import modeldata.DataUser;

public class LaporController implements Initializable {
    @FXML
    private TextArea fieldDeskripsi;

    @FXML
    private Button kirim;

    @FXML
    private Button pilihFile;

    @FXML
    private ChoiceBox<String> pilihJenis;

    @FXML
    private DatePicker tanggal;

    @FXML
    private Button pilihLokasi;

    @FXML
    private Label labelFile;

    private String jenislaporan;
    private String tanggalLaporan;
    private String bukti;

    File file;

    public void getJenisLaporan(ActionEvent event) {
        jenislaporan = pilihJenis.getValue();
    }

    ArrayList<DataUser> listUser = new ArrayList<>();
    ArrayList<DataKomunitas> listKomunitas = new ArrayList<>();
    DataLogin logPelapor = new DataLogin();
    DataMarka logMarka = new DataMarka();
    XStream xstream = new XStream(new StaxDriver());

    @FXML
    void handleButtonKirim(ActionEvent event) {
        xstream.addPermission(AnyTypePermission.ANY);
        if (file != null && !fieldDeskripsi.getText().equals("")) {
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
            if (logPelapor.getUser().equals("User")) {
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Konfirmasi Kirim Laporan");
                alert.setContentText("Apakah anda yakin ingin mengirim laporan?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    FileOutputStream dataLaporan = null;
                    String deskripsi = fieldDeskripsi.getText();
                    FileInputStream dataMarka = null;
                    try {
                        dataMarka = new FileInputStream("DataMarka.xml");

                        int isi;
                        char c;
                        String stringnya = "";

                        while ((isi = dataMarka.read()) != -1) {
                            c = (char) isi;
                            stringnya += c;
                        }
                        logMarka = (DataMarka) xstream.fromXML(stringnya);

                    } catch (Exception e) {
                        System.err.println("test: " + e.getMessage());
                    } finally {
                        if (dataMarka != null) {
                            try {
                                dataMarka.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    for (int i = 0; i < listUser.size(); i++) {
                        if (listUser.get(i).getUsername().equals(logPelapor.getLoginUser())) {
                            DataLaporan dataL = new DataLaporan(jenislaporan, tanggalLaporan, deskripsi,
                                    bukti, logPelapor.getLoginUser(), "DIKIRIM", logMarka.getX(), logMarka.getY());
                            listUser.get(i).tambahkanLaporan(dataL);
                            break;
                        }
                    }
                    String xml = xstream.toXML(listUser);
                    try {
                        dataLaporan = new FileOutputStream("DataUser.xml");
                        byte[] bytes = xml.getBytes("UTF-8");
                        dataLaporan.write(bytes);
                    } catch (Exception e) {
                        System.out.println("Perhatian: " + e.getMessage());
                    } finally {
                        if (dataLaporan != null) {
                            try {
                                dataLaporan.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println("Data berhasil disimpan");
                    PauseTransition pt = new PauseTransition();
                    pt.setDuration(Duration.seconds(1));
                    pt.setOnFinished(ev -> {
                        System.out.println("Submit Laporan Succesfully");
                        labelFile.setVisible(false);
                        pilihJenis.getItems().clear();
                        flag = true;
                        fieldDeskripsi.setText("");
                    });
                    pt.play();
                } else {
                    labelFile.setVisible(false);
                    pilihJenis.getItems().clear();
                    flag = true;
                    fieldDeskripsi.setText("");
                }
            } else if (logPelapor.getUser().equals("Komunitas")) {
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
                Alert alertKomunitas = new Alert(Alert.AlertType.CONFIRMATION);
                alertKomunitas.setHeaderText("Konfirmasi Kirim Laporan");
                alertKomunitas.setContentText("Apakah anda yakin ingin mengirim laporan?");
                Optional<ButtonType> resultKomunitas = alertKomunitas.showAndWait();
                if (resultKomunitas.get() == ButtonType.OK) {
                    FileOutputStream dataLaporan = null;
                    String deskripsi = fieldDeskripsi.getText();
                    FileInputStream dataMarka = null;
                    try {
                        dataMarka = new FileInputStream("DataMarka.xml");

                        int isi;
                        char c;
                        String stringnya = "";

                        while ((isi = dataMarka.read()) != -1) {
                            c = (char) isi;
                            stringnya += c;
                        }
                        logMarka = (DataMarka) xstream.fromXML(stringnya);

                    } catch (Exception e) {
                        System.err.println("test: " + e.getMessage());
                    } finally {
                        if (dataMarka != null) {
                            try {
                                dataMarka.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    for (int i = 0; i < listKomunitas.size(); i++) {
                        if (listKomunitas.get(i).getUsername().equals(logPelapor.getLoginUser())) {
                            DataLaporan dataL = new DataLaporan(jenislaporan, tanggalLaporan, deskripsi,
                                    bukti, logPelapor.getLoginUser(), "DIKIRIM", logMarka.getX(), logMarka.getY());
                            listKomunitas.get(i).tambahkanLaporan(dataL);
                            break;
                        }
                    }
                    String xml = xstream.toXML(listKomunitas);
                    try {
                        dataLaporan = new FileOutputStream("DataKomunitas.xml");
                        byte[] bytes = xml.getBytes("UTF-8");
                        dataLaporan.write(bytes);
                    } catch (Exception e) {
                        System.out.println("Perhatian: " + e.getMessage());
                    } finally {
                        if (dataLaporan != null) {
                            try {
                                dataLaporan.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println("Data berhasil disimpan");
                    PauseTransition pt = new PauseTransition();
                    pt.setDuration(Duration.seconds(1));
                    pt.setOnFinished(ev -> {
                        System.out.println("Submit Laporan Succesfully");
                        labelFile.setVisible(false);
                        pilihJenis.getItems().clear();
                        flag = true;
                        fieldDeskripsi.setText("");
                    });
                    pt.play();
                } else {
                    labelFile.setVisible(false);
                    pilihJenis.getItems().clear();
                    flag = true;
                    fieldDeskripsi.setText("");
                }
            }
        }
    }

    @FXML
    void handleButtonTanggal(ActionEvent event) {
        LocalDate localDate = tanggal.getValue();
        String pattern = "dd MMMM yyyy";
        tanggalLaporan = localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    @FXML
    void handleButtonPilihFile(ActionEvent event) {
        Stage stage = new Stage();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("All Images or Video", "*.jpeg", "*.jpg", "*.png", "*.mp4"),
                new ExtensionFilter("JPEG Image", "*.jpeg"),
                new ExtensionFilter("JPG Image", "*.jpg"), new ExtensionFilter("PNG Image", "*.png"),
                new ExtensionFilter("MP4 Video", "*.mp4"));
        file = fc.showOpenDialog(stage);
        if (file != null) {
            labelFile.setText(file.getName());
            labelFile.setVisible(true);
            bukti = file.getPath();
        } else {
            System.out.println("Tidak ada file yang dipilih");
        }
    }

    @FXML
    void handleButtonPilihLokasi(ActionEvent event) throws IOException {
        pilihLokasi.getScene().getWindow();
        Stage lokasi = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("PetaLapor.fxml"));
        Scene scene = new Scene(root);
        lokasi.setScene(scene);
        lokasi.show();
        lokasi.setResizable(false);
    }

    private boolean flag = true;

    @FXML
    void handleClickJenisLaporan(MouseEvent event) {
        if (flag) {
            pilihJenis.getItems().add("Perburuan Liar");
            pilihJenis.getItems().add("Kemunculan Satwa Liar");
            flag = false;
        }
        pilihJenis.setOnAction(this::getJenisLaporan);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelFile.setVisible(false);
    }
}
