package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.Country;
import com.sparta.sqlworlddatabase.entities.Countrylanguage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CountrylanguageRepositoryTests {
    @Autowired
    private CountrylanguageRepository countryLanguageRepository;
    private CountryRepository countryRepository;
    @Test
    void findACountriesLanguage(){
        Optional<Country> gb = countryRepository.findCountryByCode("GBR");
        List<Countrylanguage> languages = countryLanguageRepository.findCountrylanguageByCountryCode(gb.get());
        Countrylanguage England = languages.getFirst();
        Assertions.assertEquals("English", England.getIsOfficial());
    }
    
//    @Test
//    void findCountryLang() {
//        String countryName = "England";
//        Country england = new Country();
//        england.setName(countryName);
//        england.setCode("GBR");
//        language re
//    }

}
