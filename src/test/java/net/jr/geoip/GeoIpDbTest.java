
package net.jr.geoip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;

public class GeoIpDbTest {

  @Test
  public void testSimple() throws Exception {
    Location location = GeoIpDb.getLocation(InetAddress.getByAddress(new byte[] { 80, 124, (byte) 164, (byte) 139 }));
    Assert.assertNotNull(location);
    if( ! Arrays.asList("Brest", "Hopital-Camfrout", "Saint-Urbain").contains(location.getCityName())) {
        Assert.fail("Invalid location " + location.toString());
    }
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
