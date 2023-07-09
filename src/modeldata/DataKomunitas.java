package modeldata;

import java.io.File;
import java.util.ArrayList;

public class DataKomunitas implements Data {
    private String username;
    private String password;
    private String nama;
    private String alamat;
    private String tujuan;
    private String jumlah;
    private String noHp;
    private File file;
    ArrayList<DataLaporan> laporan;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public DataKomunitas(String username) {
        this.username = username;
    }

    public DataKomunitas(String username, String password, String nama, String alamat, String tujuan, String jumlah,
            String noHp, File file, ArrayList<DataLaporan> laporan) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.tujuan = tujuan;
        this.jumlah = jumlah;
        this.noHp = noHp;
        this.file = file;
        this.laporan = laporan;
    }

    public void tambahkanLaporan(DataLaporan laporans) {
        this.laporan.add(laporans);
    }

    public void ubahStatusLaporan(String bukti, String statusBaru) {
        for (DataLaporan laporan : laporan) {
            if (laporan.getBukti().equals(bukti)) {
                laporan.setStatus(statusBaru);
                break;
            }
        }
    }

    public void removeLaporan(int index) {
        laporan.remove(index);
    }

    public int getLaporanSize() {
        return laporan.size();
    }

    public String getPelapor(int index) {
        return laporan.get(index).getPelapor();
    }

    public String getBukti(int index) {
        return laporan.get(index).getBukti();
    }

    public String getDeskripsi(int index) {
        return laporan.get(index).getDeskripsi();
    }

    public String getTanggal(int index) {
        return laporan.get(index).getTanggal();
    }

    public String getJenisLaporan(int index) {
        return laporan.get(index).getJenisLaporan();
    }

    public double getLatitude(int index) {
        return laporan.get(index).getLatitude();
    }

    public double getLongitude(int index) {
        return laporan.get(index).getLongitude();
    }

    public String getStatus(int index) {
        return laporan.get(index).getStatus();
    }

    @Override
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    @Override
    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTujuan() {
        return tujuan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getNoHp() {
        return noHp;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
