package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.Countrylanguage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CountrylanguageRepositoryTests {
    @Autowired
    private CountrylanguageRepository countryLanguageRepository;
    @Test
    void findACountriesLanguage(){
        List<Countrylanguage> languages = countryLanguageRepository.findCountrylanguageByCountryCode("GBR");
        Countrylanguage England = languages.getFirst();
        Assertions.assertEquals("English", England.getIsOfficial());
    }


}
