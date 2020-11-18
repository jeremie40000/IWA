package jha.stopcovid.services;

import jha.stopcovid.models.ContactData;
import jha.stopcovid.models.GeolocationData;

import java.util.ArrayList;

public class LocalisationDataset {

    private long timestamp;
    private ArrayList<GeolocationData> values = new ArrayList<GeolocationData>();

    public LocalisationDataset() {
        timestamp = 0;
    }

    public void clearValues(){
        values.clear();
    }
    public void setTimestamp(long newTimestamp){
        this.timestamp = newTimestamp;
    }
    public void addValue(GeolocationData newObject){
        this.values.add(newObject);
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public ArrayList<GeolocationData> getValues() {
        return this.values;
    }

    public ArrayList<ContactData> geolocationComparison(GeolocationData geolocationData) {
        ArrayList<ContactData> arrayContact = new ArrayList<ContactData>();
        for (int element = 0; element < values.size(); element++) {
            if (GeolocationService.distanceIsValid(Double.parseDouble(values.get(element).getLatitude()), Double.parseDouble(values.get(element).getLongitude()), Double.parseDouble(geolocationData.getLatitude()), Double.parseDouble(geolocationData.getLongitude()))) {
                arrayContact.add(new ContactData(geolocationData.getIdUser(), values.get(element).getIdUser(), Long.toString(timestamp)));
            }
        }
        return arrayContact;
    }
}
