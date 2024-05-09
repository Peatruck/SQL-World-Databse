package com.sparta.sqlworlddatabase.service;

import com.sparta.sqlworlddatabase.entities.City;
import com.sparta.sqlworlddatabase.entities.Countrylanguage;
import com.sparta.sqlworlddatabase.repositories.CityRepository;
import com.sparta.sqlworlddatabase.repositories.CountryRepository;
import com.sparta.sqlworlddatabase.repositories.CountrylanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparta.sqlworlddatabase.entities.Country;
import com.sparta.sqlworlddatabase.projections.District;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.logging.Logger;

@Service
public class WorldService {

    private static final Logger logger = Logger.getLogger(WorldService.class.getName());
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CountrylanguageRepository countrylanguageRepository;


    @Autowired
    public WorldService(CityRepository cityRepository, CountryRepository countryRepository, CountrylanguageRepository countrylanguageRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.countrylanguageRepository = countrylanguageRepository;
    }


    private List<City> getAllCities() {
        return cityRepository.findAll();
    }

    private Map<Country, Integer> produceListOfCountriesAndNumberOfCities() {
        Map<Country, Integer> countryCityCount = new HashMap<>();

        for (City city : getAllCities()) {
            Country countryCount = city.getCountry();
            if (!countryCityCount.containsKey(countryCount)) {
                countryCityCount.put(countryCount, 1);
            } else {
                countryCityCount.put(countryCount, countryCityCount.get(countryCount) + 1);
            }
        }
        return countryCityCount;
    }

    public int findNumberOfCitiesByCountry(Country country) {
        return produceListOfCountriesAndNumberOfCities().get(country);
    }

    public Map.Entry<Country, Integer> findCountryWithMostCities() {
        Map<Country, Integer> countryCityCount = produceListOfCountriesAndNumberOfCities();

       int maxCitiesCount = 0;
        Country countryWithHighestCities = null;

        for (Map.Entry<Country, Integer> entry : countryCityCount.entrySet()) {
            if (entry.getValue() > maxCitiesCount) {
                maxCitiesCount = entry.getValue();
                countryWithHighestCities = entry.getKey();
            }
        }

       return new AbstractMap.SimpleEntry<Country, Integer>(countryWithHighestCities, maxCitiesCount);

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
                biggestCityInCountry = city;
            }
        }

        double percentageOfPopulationInBiggestCity =  ((double) greatestPopulation / (double) totalPopulation) * 100;
        percentageOfPopulationInBiggestCity = Math.round(percentageOfPopulationInBiggestCity*100.0)/100.0;

        return percentageOfPopulationInBiggestCity;
    }

    public List<String> get5SmallestDistricts() {
        List<String> smallestDistricts = getNSmallestDistricts(5);
        logger.info("Five smallest districts: " + smallestDistricts);
        return smallestDistricts;
    }

    public List<String> getNSmallestDistricts(int numberOfDistricts) {
        List<District> districts = cityRepository.findDistinctDistrictBy();
        Map<String, Integer> districtPops = constructMapOfDistrictPopulations(districts);
        return districtPops.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(numberOfDistricts)
                .map(Map.Entry::getKey)
                .toList();
    }

    private Map<String, Integer> constructMapOfDistrictPopulations(List<District> districts) {
        Map<String, Integer> districtPops = new HashMap<>();
        for (District district : districts) {
            String districtName = district.district();
            List<City> citiesInDistrict = cityRepository.findCitiesByDistrict(districtName);
            districtPops.put(districtName, calculateDistrictPopulation(citiesInDistrict));
        }
        return districtPops;
    }

    private int calculateDistrictPopulation(List<City> cities) {
        return cities.stream()
                .map(City::getPopulation)
                .reduce(0, Integer::sum);
    }

    public void deleteCountry(String countryCode) {
        Optional<Country> country = countryRepository.findCountryByName(countryCode);
        if (country.isPresent()) {
            countryRepository.delete(country.get());
            logger.fine("Deleted country: " + country.get().getName());
        } else {
            logger.warning("Unable to find country with code " + countryCode);
        }
    }

    public Optional<Country> updateCountryName(String countryCode, String newCountryName) {
        Optional<Country> countryOptional = countryRepository.findCountryByCode(countryCode);
        Optional<Country> updatedCountry = Optional.empty();
        if (countryOptional.isPresent()) {
            logger.fine("Found country with code " + countryCode);
            Country country = countryOptional.get();
            country.setName(newCountryName);
            Country updated = countryRepository.save(country);
            logger.fine("Country name updated to " + updated.getName());
            updatedCountry = Optional.of(updated);
        } else {
            logger.warning("Unable to find country with code " + countryCode);
        }
        return updatedCountry;
    }

    public Countrylanguage getPrimaryLanguage(String country){
        Optional<Country> gb = countryRepository.findCountryByName(country);
        List<Countrylanguage> languages = countrylanguageRepository.findCountrylanguageByCountryCodeAndIsOfficial(gb.get(), "T");
        Countrylanguage aCountriesLanguage = languages.getFirst();
        return aCountriesLanguage;
    }



}
