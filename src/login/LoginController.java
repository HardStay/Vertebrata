package login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import OpenScene.OpenScene;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import modeldata.DataKomunitas;
import modeldata.DataLogin;
import modeldata.DataUser;

public class LoginController implements Initializable {

    private OpenScene openScene = new OpenScene();

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button login;

    @FXML
    private ToggleGroup jenisUser;

    @FXML
    private RadioButton rButtonKomunitas;

    @FXML
    private RadioButton rButtonUser;

    @FXML
    private RadioButton rButtonOperator;

    @FXML
    private Button signUp;

    String pilihanJenisUser = "";

    @FXML
    void handleButtonRadio(ActionEvent event) {
        if (rButtonUser.isSelected()) {
            pilihanJenisUser = "User";
        } else if (rButtonKomunitas.isSelected()) {
            pilihanJenisUser = "Komunitas";
        } else if (rButtonOperator.isSelected()) {
            pilihanJenisUser = "Operator";
        }
    }

    public void handleButtonLogin(ActionEvent event) throws IOException {
        if (!usernameField.getText().equals("") && !passwordField.getText().equals("")) {
            String username = usernameField.getText();
            String pass = passwordField.getText();
            XStream xstream = new XStream(new StaxDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            if (pilihanJenisUser.equals("User")) {
                FileInputStream dataU = null;
                try {
                    dataU = new FileInputStream("DataUser.xml");

                    int isi;
                    char c;
                    String stringnya = "";

                    while ((isi = dataU.read()) != -1) {
                        c = (char) isi;
                        stringnya += c;
                    }
                    ArrayList<DataUser> listUser = (ArrayList<DataUser>) xstream.fromXML(stringnya);
                    boolean flag = true;
                    for (int i = 0; i < listUser.size(); i++) {
                        if (username.equals(listUser.get(i).getUsername())
                                && pass.equals(listUser.get(i).getPassword())) {
                            DataLogin logUser = new DataLogin(username, pilihanJenisUser);
                            FileOutputStream dataUser = null;
                            String xml = xstream.toXML(logUser);
                            try {
                                dataUser = new FileOutputStream("DataLogin.xml");
                                byte[] bytes = xml.getBytes("UTF-8");
                                dataUser.write(bytes);
                            } catch (Exception e) {
                                System.out.println("Perhatian: " + e.getMessage());
                            } finally {
                                if (dataUser != null) {
                                    try {
                                        dataUser.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            flag = false;
                            PauseTransition pt = new PauseTransition();
                            pt.setDuration(Duration.seconds(1));
                            pt.setOnFinished(ev -> {
                                System.out.println("Login Succesfully");
                                try {
                                    openScene.getStage(event, "/homepage/homepage.fxml");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            pt.play();
                            break;
                        }
                    }
                    if (!username.equals(listUser.get(0).getUsername()) && flag == true
                            || !pass.equals(listUser.get(0).getPassword()) && flag == true) {
                        System.out.println("Ada yang salah");
                    }
                } catch (Exception e) {
                    System.err.println("test: " + e.getMessage());
                } finally {
                    if (dataU != null) {
                        try {
                            dataU.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (pilihanJenisUser.equals("Komunitas")) {
                // XStream xstream = new XStream(new StaxDriver());
                // xstream.addPermission(AnyTypePermission.ANY);
                FileInputStream dataK = null;
                try {
                    dataK = new FileInputStream("DataKomunitas.xml");
                    int isi;
                    char c;
                    String stringnya = "";

                    while ((isi = dataK.read()) != -1) {
                        c = (char) isi;
                        stringnya += c;
                    }

                    ArrayList<DataKomunitas> listKomunitas = (ArrayList<DataKomunitas>) xstream.fromXML(stringnya);
                    boolean flag = true;
                    for (int i = 0; i < listKomunitas.size(); i++) {
                        if (username.equals(listKomunitas.get(i).getUsername())
                                && pass.equals(listKomunitas.get(i).getPassword())) {
                            DataLogin logKomunitas = new DataLogin(username, pilihanJenisUser);
                            FileOutputStream dataKomunitas = null;
                            String xml = xstream.toXML(logKomunitas);
                            try {
                                dataKomunitas = new FileOutputStream("DataLogin.xml");
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
                            flag = false;
                            PauseTransition pt = new PauseTransition();
                            pt.setDuration(Duration.seconds(1));
                            pt.setOnFinished(ev -> {
                                System.out.println("Login Succesfully");
                                try {
                                    openScene.getStage(event, "/homepage/homepage.fxml");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            pt.play();
                            break;
                        }
                    }
                    if (!username.equals(listKomunitas.get(0).getUsername()) && flag == true
                            || !pass.equals(listKomunitas.get(0).getPassword()) && flag == true) {
                        System.out.println("Ada yang salah");
                    }

                } catch (Exception e) {
                    System.err.println("test: " + e.getMessage());
                } finally {
                    if (dataK != null) {
                        try {
                            dataK.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (pilihanJenisUser.equals("Operator")) {
                if (username.equals("Operator")
                        && pass.equals("operators")) {
                    DataLogin logOperator = new DataLogin(username, pilihanJenisUser);
                    FileOutputStream dataOperator = null;
                    String xml = xstream.toXML(logOperator);
                    try {
                        dataOperator = new FileOutputStream("DataLogin.xml");
                        byte[] bytes = xml.getBytes("UTF-8");
                        dataOperator.write(bytes);
                    } catch (Exception e) {
                        System.out.println("Perhatian: " + e.getMessage());
                    } finally {
                        if (dataOperator != null) {
                            try {
                                dataOperator.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    PauseTransition pt = new PauseTransition();
                    pt.setDuration(Duration.seconds(1));
                    pt.setOnFinished(ev -> {
                        System.out.println("Login Succesfully");
                        try {
                            openScene.getStage(event, "/operator/operator.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    pt.play();
                }
            }
        }
    }

    @FXML
    void handleButtonSignUp(ActionEvent event) throws IOException {
        signUp.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/signup/JenisPendaftar.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    void handleButtonSecret(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            rButtonOperator.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rButtonOperator.setVisible(false);
    }

}
