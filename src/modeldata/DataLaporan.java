package modeldata;

public class DataLaporan {
    private String jenisLaporan;
    private String tanggal;
    private String deskripsi;
    private String bukti;
    private String pelapor;
    private double longitude;
    private double latitude;
    private String status;

    public DataLaporan() {
    }

    public DataLaporan(String jenisLaporan, String tanggal, String deskripsi, String bukti, String pelapor,
            String status, double longitude, double latitude) {
        this.jenisLaporan = jenisLaporan;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.bukti = bukti;
        this.pelapor = pelapor;
        this.status = status;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
