package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.City;
import com.sparta.sqlworlddatabase.projections.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Assertions;

import java.util.List;

@SpringBootTest
public class CityRepositoryTests {

    @Autowired
    CityRepository cityRepository;

    @Test
    void findCitiesByCountryCode_ReturnsANonEmptyWhenAValidCountryCodeIsProvided() {
        String countryCode = "ALB";
        List<City> cities = cityRepository.findCitiesByCountryCode_Code(countryCode);
        Assertions.assertFalse(cities.isEmpty());
    }

    @Test
    void findCitiesByCountryCode_ReturnsAListContainingCitiesFromTheCorrectCountry() {
        String countryCode = "ALB";
        List<City> cities = cityRepository.findCitiesByCountryCode_Code(countryCode);
        Assertions.assertEquals(countryCode, cities.get(0).getCountry().getCode());
    }
    
    @Test
    void findCitiesByCountryCode_ReturnsAnEmptyListWhenProvidedWithAnInvalidCode() {
        String countryCode = "ZZZ";
        List<City> cities = cityRepository.findCitiesByCountryCode_Code(countryCode);
        Assertions.assertTrue(cities.isEmpty());
    }

    @Test
    void findUniqueDistrictBy_ReturnsListOfUniqueDistricts() {
        List<District> districts = cityRepository.findUniqueDistrictBy();
        System.out.println(districts);
    }
}
