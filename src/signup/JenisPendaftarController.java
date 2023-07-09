package signup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JenisPendaftarController implements Initializable {
    @FXML
    private Button komunitas;

    @FXML
    private Button user;

    @FXML
    void handleButtonKomunitas(ActionEvent event) throws IOException {
        komunitas.getScene().getWindow().hide();
        Stage signup1 = new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource("/signup/SignUpKomunitas.fxml"));
        Scene scene1 = new Scene(root1);
        signup1.setScene(scene1);
        signup1.show();
        signup1.setResizable(false);
    }

    @FXML
    void handleButtonUser(ActionEvent event) throws IOException {
        user.getScene().getWindow().hide();
        Stage signup2 = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/signup/SignUpUser.fxml"));
        Scene scene2 = new Scene(root2);
        signup2.setScene(scene2);
        signup2.show();
        signup2.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
