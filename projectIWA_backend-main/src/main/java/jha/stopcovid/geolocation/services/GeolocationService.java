package jha.stopcovid.geolocation.services;

import org.springframework.stereotype.Service;

@Service
public class GeolocationService {

    public static boolean distanceIsValid(Double lat1, Double long1, Double lat2, Double long2) {
        if ((lat1 == lat2) & (long1 == long2)) {
            return true;
        }
        else {
            var radLat1 = Math.PI * lat1 / 180;
            var radLat2 = Math.PI * lat2 / 180;
            var theta = long1 - long2;
            var radTheta = Math.PI * theta / 180;
            var dist = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
            if (dist > 1) {
                dist = 1;
            }
            dist = Math.acos(dist);
            dist = dist * 180 / Math.PI;
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344 * 1000;
            return dist <= 50;
        }
    }
}
