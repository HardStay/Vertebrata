package modeldata;

public class DataEduWild {
    private String judul;
    private String deskripsi;
    private String fileImage;

    public String getJudul() {
        return judul;
    }

    public DataEduWild(String judul, String deskripsi, String file) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.fileImage = file;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFile() {
        return fileImage;
    }

    public void setFile(String file) {
        this.fileImage = file;
    }
}
