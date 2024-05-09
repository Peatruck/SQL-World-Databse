package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.Country;
import com.sparta.sqlworlddatabase.entities.Countrylanguage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CountrylanguageRepositoryTests {
    @Autowired
    private CountrylanguageRepository countryLanguageRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Test
    void findACountriesLanguage(){
        Optional<Country> gb = countryRepository.findCountryByCode("GBR");
        List<Countrylanguage> officialLanguages = countryLanguageRepository.findCountrylanguageByCountryCodeAndIsOfficial(gb.get(), "T");
        Countrylanguage unitedKingdom = officialLanguages.getFirst();
        Assertions.assertEquals("English", unitedKingdom.getId().getLanguage());
    }

    //For a given country, approximately how many people speak its most popular official language?
    @Test
    void findIfItIsAnOfficialLanguage(){
        Optional<Country> gb = countryRepository.findCountryByCode("GBR");
        List<Countrylanguage> languages = countryLanguageRepository.findCountrylanguageByCountryCodeAndIsOfficial(gb.get(), "T");
        Countrylanguage unitedKingdom = languages.getFirst();
        System.out.println(languages);

        Assertions.assertEquals("T", unitedKingdom.getIsOfficial());
    }

    @Test
    void findPercentageOfOfficialLanguageSpeakers(){
        Optional<Country> gb = countryRepository.findCountryByCode("GBR");
        List<Countrylanguage> languages = countryLanguageRepository.findCountrylanguageByCountryCodeAndIsOfficial(gb.get(), "T");
        Countrylanguage unitedKingdom = languages.getFirst();
        BigDecimal percentageOfEnglishSpeakers = BigDecimal.valueOf(97.3);

        Assertions.assertEquals(percentageOfEnglishSpeakers, unitedKingdom.getPercentage());
    }
    

}
