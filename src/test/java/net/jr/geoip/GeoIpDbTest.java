package net.jr.geoip;

import org.junit.Assert;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

/**
 * Created by Julien.Rialland on 05/09/17.
 */
public class GeoIpDbTest {

    @Test
    public void testSimple() throws Exception {
        Location location = GeoIpDb.getLocation(InetAddress.getByName("80.124.164.139"));
        Assert.assertEquals("Hopital-Camfrout", location.getCityName());
    }

    @Test
    public void testChinese() throws Exception {
        Location location = GeoIpDb.getLocation(Locale.SIMPLIFIED_CHINESE, InetAddress.getByName("202.201.64.112"));
        Assert.assertEquals("兰州", location.getCityName());

        location = GeoIpDb.getLocation(Locale.FRENCH, InetAddress.getByName("202.201.64.112"));
        Assert.assertEquals("Lanzhou", location.getCityName());
    }

    @Test
    public void testNull() throws Exception {
        Location location = GeoIpDb.getLocation(null);
        Assert.assertNull(location);
    }


    @Test(expected = UnknownHostException.class)
    public void testUnknownHost() throws Exception {
        Location location = GeoIpDb.getLocation(InetAddress.getByName("unknown.host.nonexistentdomain"));
        System.out.println(location.getCountry());
    }
}
