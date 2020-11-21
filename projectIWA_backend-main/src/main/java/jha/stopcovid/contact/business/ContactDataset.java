package jha.stopcovid.contact.business;

import java.util.ArrayList;

public class ContactDataset {

    private int lastMinuteChecked;
    private ArrayList<ContactData> contactList1;
    private ArrayList<ContactData> contactList2;

    public ContactDataset() {
        lastMinuteChecked = -1;
        contactList1 = new ArrayList<ContactData>();
        contactList2 = new ArrayList<ContactData>();
    }

    public int getLastMinuteChecked() {
        return lastMinuteChecked;
    }

    public void setLastMinuteChecked(int lastMinuteChecked) {
        this.lastMinuteChecked = lastMinuteChecked;
    }

    public ArrayList<ContactData> getContactList1() {
        return contactList1;
    }

    public void setContactList1(ArrayList<ContactData> contactList1) {
        this.contactList1 = contactList1;
    }

    public ArrayList<ContactData> getContactList2() {
        return contactList2;
    }

    public void clearContactList2() {
        this.contactList2.clear();
    }

    public void clearContactList1() {
        this.contactList1.clear();
    }

    public void setContactList2(ArrayList<ContactData> contactList2) {
        this.contactList2 = contactList2;
    }

    public void addToContactList1(ContactData contact) {
        this.contactList1.add(contact);
    }

    public void addToContactList2(ContactData contact) {
        this.contactList2.add(contact);
    }
}
