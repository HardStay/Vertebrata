package multiplescene.edukasi;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class samplecontentController {
    @FXML
    private ImageView imageContent;

    @FXML
    private Label labelContent;

    @FXML
    private Label labelJudul;

    public void setJudul(String data) {
        labelJudul.setText(data);
    }

    public void setContent(String data) {
        labelContent.setText(data);
    }

    public void setImage(String data) {
        File file = new File(data);
        Image image = new Image(file.toURI().toString());
        imageContent.setImage(image);
    }
}
