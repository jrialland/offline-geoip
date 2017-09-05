package net.jr.geoip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julien.Rialland on 05/09/17.
 */
public class Location {

    private String continent;

    private String continentCode;

    private String country;

    /** alpha2 country iso code */
    private String countryIsoCode;

    private String cityName;

    private String postalCode;

    private double lat;

    private double lon;

    private List<String> subdivisions = new ArrayList<>();

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public List<String> getSubdivisions() {
        return subdivisions;
    }

    public void setSubdivisions(List<String> subdivisions) {
        this.subdivisions = subdivisions;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
