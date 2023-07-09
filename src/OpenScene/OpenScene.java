/*Vertebrata*/
package OpenScene;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OpenScene {
    private Pane halaman;

    public Pane getPane(String fileName) {
        try {
            URL fileHalaman = getClass().getResource(fileName);

            if (fileHalaman == null) {
                throw new java.io.FileNotFoundException("Halaman tidak ditemukan");
            }

            new FXMLLoader();
            halaman = FXMLLoader.load(fileHalaman);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Tidak ditemukan halaman tersebut");
            System.out.println("Perhatian: " + e.getMessage());
        }

        return halaman;
    }

    public void getStage(ActionEvent event, String filename) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(filename));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void getStage(MouseEvent event, String filename) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(filename));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
