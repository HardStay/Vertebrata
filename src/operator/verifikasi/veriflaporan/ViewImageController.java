package operator.verifikasi.veriflaporan;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ViewImageController implements Initializable {
    @FXML
    private HBox box;

    @FXML
    private ImageView imageFile;

    public void setImage(String data) {
        File file = new File(data);
        Image image = new Image(file.toURI().toString());
        imageFile.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
