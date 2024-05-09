package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.City;
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
    void findCitiesByCountryCode_ReturnsANonEmptyListWhenAValidCountryCodeIsProvided() {
        String countryCode = "ALB";
        List<City> cities = cityRepository.findCitiesByCountryCode(countryCode);
        Assertions.assertFalse(cities.isEmpty());
    }
}
