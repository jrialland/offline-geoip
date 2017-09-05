

[![Build Status](https://travis-ci.org/jrialland/offline-geoip.svg)](https://travis-ci.org/jrialland/offline-geoip)
[![codecov](https://codecov.io/gh/jrialland/offline-geoip/branch/master/graph/badge.svg)](https://codecov.io/gh/jrialland/offline-geoip)


Gives the country and city for an ipv4 address.

```
location = GeoIpDb.getLocation(Locale.FRENCH, InetAddress.getByName("202.201.64.112"));
Assert.assertEquals("Lanzhou", location.getCityName());
```

This library uses [Maxmind free databases](https://dev.maxmind.com/geoip/geoip2/geolite2/).
