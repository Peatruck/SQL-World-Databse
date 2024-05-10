package com.sparta.sqlworlddatabase.service;

import com.sparta.sqlworlddatabase.entities.Country;
import com.sparta.sqlworlddatabase.service.WorldService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorldServiceTests {

    @Autowired
    private WorldService worldService;

    @Test
    void testCountryWithMostCitiesShouldReturnGreaterThan0Cities() {
        Map.Entry<Country, Integer> result = worldService.findCountryWithMostCities();
        Assertions.assertTrue(result.getValue() > 0);
    }

    @Test
    void testCountryWithMostCitiesShouldNotReturnNull() {
        Map.Entry<Country, Integer> result = worldService.findCountryWithMostCities();
        Assertions.assertNotNull(result);
    }

    @Test
    void testCountryWithMostCitiesShouldNotReturnNullNumberOfCities() {
        Map.Entry<Country, Integer> result = worldService.findCountryWithMostCities();
        Assertions.assertNotNull(result.getValue());
    }

    @Test
    void find5SmallestDistricts_ReturnsAListOfLength5() {
        List<String> districts = worldService.get5SmallestDistricts();
        assertEquals(5, districts.size());
    }

    @Test
    void find5SmallestDistricts_ReturnsExpectedDistricts() {
        List<String> expected = List.of("West Island", "Fakaofo", "Home Island", "Wallis", "Länsimaa");
        List<String> districts = worldService.get5SmallestDistricts();
        assertEquals(expected, districts);
    }

    @Test
    void findNSmallestDistricts_ReturnsAListOfLengthN() {
        int n = 10;
        List<String> districts = worldService.getNSmallestDistricts(n);
        assertEquals(n, districts.size());
    }

    @Test
    @Transactional
    void updateCountryName_ShouldCorrectlyUpdateName() {
        Optional<Country> updated = worldService.updateCountryName("ABW", "New Aruba");
        Assumptions.assumeFalse(updated.isEmpty());
        Assertions.assertEquals("New Aruba", updated.get().getName());
    }

    @Test
    @Transactional
    void updateCountryName_ShouldReturnAnEmptyOptionalWhenAnInvalidCountryCodeIsProvided() {
        Optional<Country> updated = worldService.updateCountryName("ZZZ", "Not Used");
        Assertions.assertTrue(updated.isEmpty());
    }

}