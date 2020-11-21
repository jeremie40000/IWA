package jha.stopcovid.contact.business;

public class ContactData {

    private String idUser1;
    private String idUser2;
    private String timestamp;


    public ContactData(String idUser1, String idUser2, String timestamp) {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.timestamp = timestamp;
    }

    public ContactData() {
    }

    public String getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(String idUser1) {
        this.idUser1 = idUser1;
    }

    public String getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(String idUser2) {
        this.idUser2 = idUser2;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "idUser1='" + idUser1 + '\'' +
                ", idUser2='" + idUser2 + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
