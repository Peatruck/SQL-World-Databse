package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CountryRepositoryTests {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void findCountriesByHeadOfStateIsNull_ReturnsANonEmptyList() {
        List<Country> missingHeadOfStates = countryRepository.findCountriesByHeadOfStateIsNull();
        Assertions.assertFalse(missingHeadOfStates.isEmpty());
    }

    @Test
    void findCountriesByHeadOfStateIsNull_ReturnsAListContainingSanMarino() {
        List<Country> missingHeadOfStates = countryRepository.findCountriesByHeadOfStateIsNull();
        Country sanMarino = missingHeadOfStates.getFirst();
        Assertions.assertEquals("San Marino", sanMarino.getName());
    }

    @Test
    void findCountryByName_ReturnsCorrectCountryWhenProvidedWithAValidName() {
        String countryName = "Albania";
        Country albania = new Country();
        albania.setName(countryName);
        albania.setCode("ALB");
        Country result = countryRepository.findCountryByName(countryName).get();
        Assertions.assertEquals(albania.getCode(), result.getCode());
    }

    @Test
    void findCountryByName_ReturnsAnEmptyOptionalWhenANonExistingNameIsProvided() {
        String countryName = "Not a name";
        Optional<Country> result = countryRepository.findCountryByName(countryName);
        Assertions.assertTrue(result.isEmpty());
    }


    @Test
    void findCountryByCode_ReturnsCorrectCountryWhenProvidedWithAValidCode(){
        String countryCode = "GBR"; // UK's country code
        Country gb = new Country();
        gb.setCode(countryCode);
        gb.setName("United Kingdom");
        Country result = countryRepository.findCountryByCode(countryCode).get();
        Assertions.assertEquals(gb.getName(), result.getName());
    }

    @Test
    void findCountryByCode_ReturnsAnEmptyOptionalWhenANonExistingNameIsProvided(){
        String countryCode = "ZZZ";
        Optional<Country> result = countryRepository.findCountryByCode(countryCode);
        Assertions.assertTrue(result.isEmpty());
    }

}
