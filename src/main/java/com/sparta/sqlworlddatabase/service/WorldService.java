package com.sparta.sqlworlddatabase.service;

import com.sparta.sqlworlddatabase.entities.City;
import com.sparta.sqlworlddatabase.repositories.CityRepository;
import com.sparta.sqlworlddatabase.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparta.sqlworlddatabase.entities.Country;

import java.util.*;
import java.util.logging.Logger;

@Service
public class WorldService {

    private static final Logger logger = Logger.getLogger(WorldService.class.getName());
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public WorldService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public void findCountryWithMostCities() {
        List<City> cities = cityRepository.findAll();
        Map<Country, Integer> countryCityCount = new HashMap<>();

        for (City city : cities) {
            Country country = city.getCountry();
            if (!countryCityCount.containsKey(country)) {
                countryCityCount.put(country, 1);
            } else {
                countryCityCount.put(country, countryCityCount.get(country) + 1);
            }
        }

       int maxCitiesCount = 0;
        Country countryWithHighestCities = null;

        for (Map.Entry<Country, Integer> entry : countryCityCount.entrySet()) {
            if (entry.getValue() > maxCitiesCount) {
                maxCitiesCount = entry.getValue();
                countryWithHighestCities = entry.getKey();
            }
        }


        //return countryCityCount;

    }

    public double findPercentageOfPopulationInLargestCity(String countryName){
        int totalPopulation = 0;
        int greatestPopulation = 0;
        String countryCode = "";
        City biggestCityInCountry = null;
        
        Optional<Country> country = countryRepository.findCountryByName(countryName);

        if(country.isPresent()){
            countryCode = country.get().getCode();
            totalPopulation = country.get().getPopulation();
        } else{
            return 0;
        }

        List<City> citiesOfCountry = cityRepository.findCitiesByCountryCode_Code(countryCode);

        for(City city: citiesOfCountry){
            int cityPopulation = city.getPopulation();
            if(cityPopulation > greatestPopulation){
                greatestPopulation = cityPopulation;
            }
        }

        double percentageOfPopulationInBiggestCity = (double) (greatestPopulation / totalPopulation) * 100;

        logger.info(String.valueOf(percentageOfPopulationInBiggestCity + "% of people from " + country.get().getName() + " live in its biggest city " + biggestCityInCountry.getName()));

        return percentageOfPopulationInBiggestCity;
    }


}
