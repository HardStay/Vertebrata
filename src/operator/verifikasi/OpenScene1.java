/*Vertebrata*/
package operator.verifikasi;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class OpenScene1 {
    private Pane halaman;

    public Pane getPane(String fileName) {
        try {
            URL fileHalaman = getClass().getResource(fileName);
            if (fileHalaman == null) {
                throw new java.io.FileNotFoundException("Halaman tidak ditemukan");
            }

            halaman = FXMLLoader.load(fileHalaman);

        } catch (Exception e) {
            System.err.println("test: " + e.getMessage());
            System.out.println("Tidak ditemukan halaman tersebut");
        }

        return halaman;
    }
}
