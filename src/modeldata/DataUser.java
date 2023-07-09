package modeldata;

import java.util.ArrayList;

public class DataUser implements Data {
    private String username;
    private String password;
    private String nama;
    private String ttl;
    private String jkelamin;
    ArrayList<DataLaporan> laporan;

    public DataUser(String username) {
        this.username = username;
    }

    public DataUser(String username, String password, String nama, String ttl, String jkelamin,
            ArrayList<DataLaporan> laporan) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.ttl = ttl;
        this.jkelamin = jkelamin;
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

    public void setAlamat(String nama) {
        this.nama = nama;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public void setJkelamin(String jkelamin) {
        this.jkelamin = jkelamin;
    }

    @Override
    public String getNama() {
        return nama;
    }

    public String getTtl() {
        return ttl;
    }

    public String getJkelamin() {
        return jkelamin;
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

    @Override
    public void setNama(String nama) {
        this.nama = nama;
    }
}
