package com.example.nguyen__le.googlemaps;

public class Contact {

    String name;
    String phoneNumber;
    String address;
    String notes;

    public Contact(String name, String phoneNumber, String address, String notes){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.notes = notes;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getAddress(){
        return address;
    }

    public String getNotes(){
        return notes;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
}
