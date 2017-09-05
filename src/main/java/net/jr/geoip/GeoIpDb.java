
package net.jr.geoip;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.CHMCache;
import com.maxmind.db.Reader;

public class GeoIpDb {

  // private static Reader country;

  private static Reader city;

  static {
    try {
      // country = loadDb("GeoLite2-Country.tar.gz");
      city = loadDb("GeoLite2-City.tar.gz");
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  private static ArchiveEntry getMmdb(TarArchiveInputStream tar) throws IOException {
    ArchiveEntry a;
    while ((a = tar.getNextEntry()) != null) {
      if (a.getName().endsWith(".mmdb")) {
        return a;
      }
    }
    throw new RuntimeException("Missing mmdb file in archive !");
  }

  private static Reader loadDb(String resourceName) throws IOException {
    URL resourceUrl = GeoIpDb.class.getClassLoader().getResource(resourceName);
    TarArchiveInputStream tar = new TarArchiveInputStream(new GZIPInputStream(resourceUrl.openStream()));
    getMmdb(tar);
    return new Reader(tar, new CHMCache());
  }

  private static String forLocale(Locale locale, JsonNode jsonObj) {
    if (jsonObj == null) {
      return null;
    }
    JsonNode child = jsonObj.get(locale.toString().replace('_', '-'));
    if (child == null) {
      child = jsonObj.get(locale.getLanguage());
    }
    if (child == null) {
      child = jsonObj.get("en");
    }
    return child == null ? null : child.asText();
  }

  public static Location getLocation(InetAddress inetAddress) throws UnknownHostException {
    return getLocation(Locale.US, inetAddress);
  }

  public static Location getLocation(Locale locale, InetAddress inetAddress) throws UnknownHostException {
    if (inetAddress == null) {
      return null;
    }
    inetAddress = InetAddress.getByAddress(inetAddress.getAddress());
    Location location = new Location();
    try {
      // fillLocation(locale, inetAddress, country, location);
      fillLocation(locale, inetAddress, city, location);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return location;
  }

  private static void fillLocation(Locale locale, InetAddress inetAddress, Reader db, Location location) throws IOException {
    JsonNode jsonNode = db.get(inetAddress);
    if (jsonNode == null) {
      return;
    } else {
      JsonNode continentNode = jsonNode.get("continent");
      if (continentNode != null) {
        location.setContinentCode(continentNode.get("code").asText());
        location.setContinent(forLocale(locale, continentNode.get("names")));
      }
      JsonNode countryNode = jsonNode.get("country");
      if (countryNode != null) {
        location.setCountry(forLocale(locale, countryNode.get("names")));
        location.setCountryIsoCode(countryNode.get("iso_code").asText());
      }
      if (location.getCountry() == null) {
        JsonNode registeredCountryNode = jsonNode.get("registered_country");
        if (countryNode != null) {
          location.setCountry(forLocale(locale, registeredCountryNode.get("names")));
          location.setCountryIsoCode(registeredCountryNode.get("iso_code").asText());
        }
      }
      JsonNode cityNode = jsonNode.get("city");
      if (cityNode != null) {
        location.setCityName(forLocale(locale, cityNode.get("names")));
      }
      JsonNode postalNode = jsonNode.get("postal");
      if (postalNode != null) {
        location.setPostalCode(postalNode.get("code").asText());
      }
      JsonNode locationNode = jsonNode.get("location");
      if (locationNode != null) {
        location.setLat(locationNode.get("latitude").asDouble());
        location.setLon(locationNode.get("longitude").asDouble());
      }
      JsonNode subvisionsNode = jsonNode.get("subdivisions");
      if (subvisionsNode != null) {
        for (int i = 0; i < subvisionsNode.size(); i++) {
          String name = forLocale(locale, subvisionsNode.get(i).get("names"));
          location.getSubdivisions().add(name);
        }
      }
    }
  }

}
