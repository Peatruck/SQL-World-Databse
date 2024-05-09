package com.sparta.sqlworlddatabase.service;

import com.sparta.sqlworlddatabase.entities.City;
import com.sparta.sqlworlddatabase.repositories.CityRepository;
import com.sparta.sqlworlddatabase.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparta.sqlworlddatabase.entities.Country;
import java.util.List;

import java.util.Optional;

@Service
public class WorldService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public WorldService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public void findCountryWithMostCities() {
        List<City> cities = cityRepository.findAll();

//        for (Country country : countries) {
//            System.out.println(country);
//        }



    }
}
