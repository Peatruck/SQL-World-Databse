package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CountryRepositoryTests {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void findCountriesByHeadOfStateIsNull_ReturnsANonEmptyList() {
        List<Country> missingHeadOfStates = countryRepository.findCountriesByHeadOfStateIsNull();
        Assertions.assertFalse(missingHeadOfStates.isEmpty());
    }
}
