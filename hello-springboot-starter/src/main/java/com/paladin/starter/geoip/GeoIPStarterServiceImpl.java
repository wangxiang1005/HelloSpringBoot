package com.paladin.starter.geoip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import com.paladin.starter.IPStarterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoIPStarterServiceImpl implements IPStarterService {
    /**
     * 响应成功状态码
     */
    private final String OKOK = "1";

    @Value("${ip.convert.conf.mmdb.url}")
    private String url;

    /*public static void main(String[] args) {
        String ip = "39.156.66.10";
        System.out.println("ip is "+ ip);

        IpInfo ipInfo = new IpInfo(ip, "N/A");
        File database = new File("paladin-ip-address-starter/src/main/resources/static/GeoLite2-City.mmdb");

        DatabaseReader reader = null;
        InetAddress ipAddress = null;
        CityResponse response = null;
        try {
            reader = new DatabaseReader.Builder(database).build();
            ipAddress = InetAddress.getByName(ip);
            try {
                response = reader.city(ipAddress);
            } catch (GeoIp2Exception e) {
                System.out.println("--11-->>>>>>"+e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("--22-->>>>>>"+e.getMessage());
        }

        Country country = response.getCountry();
        System.out.println("getIsoCode="+country.getIsoCode());
        System.out.println("getName="+country.getName());
        System.out.println("getNames="+country.getNames().get("zh-CN"));
        ipInfo.setCountryName(country.getNames().get("zh-CN"));

        Subdivision subdivision = response.getMostSpecificSubdivision();
        System.out.println("subdivision.toString="+subdivision.toString());
        System.out.println("subdivision.getName="+subdivision.getName());
        System.out.println("subdivision.getIsoCode="+subdivision.getIsoCode());

        City city = response.getCity();
        System.out.println("city.getName()="+city.getName());
        ipInfo.setCityName(city.getName());

        Postal postal = response.getPostal();
        System.out.println("postal.getCode()="+postal.getCode());

        Location location = response.getLocation();
        System.out.println("location.getLatitude()="+location.getLatitude());
        System.out.println("location.getLongitude()="+location.getLongitude());
        ipInfo.setLatitude(location.getLatitude().toString());
        ipInfo.setLongitude(location.getLongitude().toString());
    }*/

    @Override
    public String ipAnalyze(String ip) {
        System.out.println("ip is "+ ip);
        System.out.println("url is "+ url);
        if (StringUtils.isBlank(ip)) {
            return "";
        }
        File database = new File(url);

        DatabaseReader reader = null;
        InetAddress ipAddress = null;
        CityResponse response = null;
        try {
            reader = new DatabaseReader.Builder(database).build();
            ipAddress = InetAddress.getByName(ip);
            try {
                response = reader.city(ipAddress);
            } catch (GeoIp2Exception e) {
                System.out.println("--11-->>>>>>"+e.getMessage());
                return "";
            }
        } catch (IOException e) {
            System.out.println("--22-->>>>>>"+e.getMessage());
            return "";
        }

        Country country = response.getCountry();
        System.out.println("getIsoCode="+country.getIsoCode());
        System.out.println("getName="+country.getName());
        System.out.println("getNames="+country.getNames().get("zh-CN"));

        Subdivision subdivision = response.getMostSpecificSubdivision();
        System.out.println("subdivision.toString="+subdivision.toString());
        System.out.println("subdivision.getName="+subdivision.getName());
        System.out.println("subdivision.getIsoCode="+subdivision.getIsoCode());

        City city = response.getCity();
        System.out.println("city.getName()="+city.getName());

        Postal postal = response.getPostal();
        System.out.println("postal.getCode()="+postal.getCode());

        Location location = response.getLocation();
        System.out.println("location.getLatitude()="+location.getLatitude());
        System.out.println("location.getLongitude()="+location.getLongitude());

        return city.getName();
    }
}