package jha.stopcovid.geolocation.business;

import jha.stopcovid.geolocation.services.GeolocationService;
import jha.stopcovid.contact.business.ContactData;
import jha.stopcovid.geolocation.models.GeolocationData;

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
            if (GeolocationService.distanceIsValid(values.get(element).getLatitude(), values.get(element).getLongitude(), geolocationData.getLatitude(), geolocationData.getLongitude())) {
                arrayContact.add(new ContactData(geolocationData.getIdUser(), values.get(element).getIdUser(), Long.toString(timestamp)));
            }
        }
        return arrayContact;
    }
}
