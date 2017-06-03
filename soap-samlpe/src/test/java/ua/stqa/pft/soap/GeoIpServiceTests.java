package ua.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by amalinkovskiy on 6/3/2017.
 */
public class GeoIpServiceTests {
    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("95.158.43.6");
        assertEquals(geoIP.getCountryCode(), "UKR");
    }

}
