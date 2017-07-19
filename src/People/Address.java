package People;

/**
 Address.java
 Wholesale

 Copyright © 2017 Lambton. All rights reserved.
 */

public class Address
{
    static int numID;
    private int addressId;
    private String street;
    private String city;
    private String province;
    private String zipCode;
    private String country;


    public Address(String street, String city, String province, String zipCode, String country)
    {
        Address.numID += 1;

        this.addressId = Address.numID;
        this.street = street;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.country = country;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
