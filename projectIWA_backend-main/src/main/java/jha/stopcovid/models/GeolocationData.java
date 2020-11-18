package jha.stopcovid.models;

public class GeolocationData {
    private String idUser;
    private String latitude;
    private String longitude;
    private String timestamp;

    public GeolocationData(String idUser, String latitude, String longitude, String timestamp) {
        this.idUser = idUser;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public GeolocationData() {
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toString(){
        return "{idUser: " + idUser + ", latitude: " + latitude + ", longitude: " + longitude + ",timestamp: " + timestamp + "}";
    }
}
