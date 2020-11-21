package jha.stopcovid.models;

import jdk.jfr.Timestamp;
import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class GeolocationData {
    @Length(min = 36, max = 36)
    private String idUser;
    @DecimalMax("90.0")
    @DecimalMin("-90.0")
    private Double latitude;
    @DecimalMax("90.0")
    @DecimalMin("-90.0")
    private Double longitude;
    @Length(min=10, max = 10)
    private Long timestamp;

    public GeolocationData(String idUser, Double latitude, Double longitude, Long timestamp) {
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String toString(){
        return "{idUser: " + idUser + ", latitude: " + latitude + ", longitude: " + longitude + ",timestamp: " + timestamp + "}";
    }
}
