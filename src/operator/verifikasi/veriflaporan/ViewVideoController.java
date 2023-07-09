package operator.verifikasi.veriflaporan;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class ViewVideoController implements Initializable {
    @FXML
    private Button buttonPause;

    @FXML
    private Button buttonPlay;

    @FXML
    private Button buttonReset;

    @FXML
    private MediaView mediaView;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    @FXML
    void handleButtonPause(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    void handleButtonPlay(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void handleButtonReset(ActionEvent event) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }

    public void setVideo(String data) {
        file = new File(data);
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
