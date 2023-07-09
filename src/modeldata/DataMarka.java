package modeldata;

public class DataMarka {
    private double latitude;
    private double longitude;

    public DataMarka(double longitude, double latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public DataMarka() {
    }

    public double getY() {
        return latitude;
    }

    public void setY(double latitude) {
        this.latitude = latitude;
    }

    public void setX(double longitude) {
        this.longitude = longitude;
    }

    public double getX() {
        return longitude;
    }
}
