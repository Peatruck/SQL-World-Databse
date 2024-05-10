package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findCountriesByHeadOfStateIsNull();

    Optional<Country> findCountryByName(String name);
    //Optional<Country> findCountryByMostCities();
    Optional<Country> findCountryByCode(String code);
}
