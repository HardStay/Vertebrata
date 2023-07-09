package modeldata;

import javafx.scene.control.Button;

public class DataVerifLaporan {
    private String jenisLaporan;
    private String tanggal;
    private String deskripsi;
    private String bukti;
    private String pelapor;
    private double latitude;
    private double longitude;
    private int no;
    private Button buktiFile;
    private String status;

    public DataVerifLaporan(int no, String pelapor, String jenisLaporan, String tanggal, String bukti, Button buktiFile,
            String deskripsi, double longitude, double latitude, String status) {
        this.no = no;
        this.jenisLaporan = jenisLaporan;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.bukti = bukti;
        this.buktiFile = buktiFile;
        this.pelapor = pelapor;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getPelapor() {
        return pelapor;
    }

    public void setPelapor(String pelapor) {
        this.pelapor = pelapor;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Button getBuktiFile() {
        return buktiFile;
    }

    public void setBuktiFile(Button buktiFile) {
        this.buktiFile = buktiFile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
