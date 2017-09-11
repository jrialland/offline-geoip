
package net.jr.geoip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class GeoIpDbTest {

  @Test
  public void testSimple() throws Exception {
    Location location = GeoIpDb.getLocation(InetAddress.getByAddress(new byte[] { 80, 124, (byte) 164, (byte) 139 }));
    Assert.assertTrue(location.getCityName().equals("Saint-Urbain") || location.getCityName().equals("Hopital-Camfrout"));
    System.out.println(location);
  }

  @Test
  public void testChinese() throws Exception {
    Location location1 = GeoIpDb.getLocation(Locale.SIMPLIFIED_CHINESE, InetAddress.getByName("202.201.64.112"));
    Assert.assertEquals("兰州", location1.getCityName());
    System.out.println(location1);

    Location location2 = GeoIpDb.getLocation(Locale.FRENCH, InetAddress.getByName("202.201.64.112"));
    Assert.assertEquals("Lanzhou", location2.getCityName());
    System.out.println(location2);
    
    Assert.assertEquals("Province de Gansu", location2.getSubdivisions().get(0));
    Assert.assertEquals("Chine",location2.getCountry());
    
    Assert.assertNotEquals(location1.hashCode(), location2.hashCode());
    Assert.assertEquals(location1, location2);
  }

  @Test
  public void testNull() throws Exception {
    Location location = GeoIpDb.getLocation(null);
    Assert.assertEquals(Location.NotFound, location);
  }

  @Test(expected = UnknownHostException.class)
  public void testUnknownHost() throws Exception {
    GeoIpDb.getLocation(InetAddress.getByName("unknown.host.nonexistentdomain"));
  }
  
  @Test
  public void testHashCode() {
    Location.NotFound.hashCode();
  }
  

  @Test
  public void testEquals() {
    Location.NotFound.equals(null);
  }
}
