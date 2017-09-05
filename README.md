

[![Build Status](https://travis-ci.org/jrialland/offline-geoip.svg)](https://travis-ci.org/jrialland/offline-geoip)
[![codecov](https://codecov.io/gh/jrialland/offline-geoip/branch/master/graph/badge.svg)](https://codecov.io/gh/jrialland/offline-geoip)


Gives the country and city for an ipv4 address.

Example code

```
location = GeoIpDb.getLocation(Locale.FRENCH, InetAddress.getByName("202.201.64.112"));
Assert.assertEquals("Lanzhou", location.getCityName());
```


```
This product includes GeoLite2 data created by MaxMind, available from
<a href="http://www.maxmind.com">http://www.maxmind.com</a>.
```

