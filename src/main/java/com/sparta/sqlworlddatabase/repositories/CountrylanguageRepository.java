package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.Countrylanguage;
import com.sparta.sqlworlddatabase.entities.CountrylanguageId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountrylanguageRepository extends JpaRepository<Countrylanguage, CountrylanguageId> {
    List<Countrylanguage> findCountrylanguageByCountryCode(String countryCode);
}