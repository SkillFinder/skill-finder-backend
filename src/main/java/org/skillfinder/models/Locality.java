package org.skillfinder.models;

import lombok.Data;

@Data
public class Locality {

    private String country;

    private String city;

    public Locality(String city, String country) {
        this.country = country;
        this.city = city;
    }

}
