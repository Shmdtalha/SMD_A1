package com.example.a1;

public class Person {

    String email, fullName, contactInformation, country, address;

    public Person(String email, String fullName, String contactInformation, String country, String address) {
        this.email = email;
        this.fullName = fullName;
        this.contactInformation = contactInformation;
        this.country = country;
        this.address = address;
    }

    public Person() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
