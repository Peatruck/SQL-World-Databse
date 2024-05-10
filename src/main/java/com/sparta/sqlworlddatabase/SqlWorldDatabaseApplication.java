package com.sparta.sqlworlddatabase;

import com.sparta.sqlworlddatabase.entities.Country;
import com.sparta.sqlworlddatabase.entities.Countrylanguage;
import com.sparta.sqlworlddatabase.repositories.CityRepository;
import com.sparta.sqlworlddatabase.repositories.CountryRepository;
import com.sparta.sqlworlddatabase.repositories.CountrylanguageRepository;
import com.sparta.sqlworlddatabase.service.WorldService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@SpringBootApplication
public class SqlWorldDatabaseApplication {
	private static final Logger logger = Logger.getLogger(SqlWorldDatabaseApplication.class.getName());



	public static void main(String[] args) {
		SpringApplication.run(SqlWorldDatabaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(CityRepository cityRepository, CountryRepository countryRepository,
									CountrylanguageRepository countrylanguageRepository, WorldService worldService) {
		return args -> {
			logger.fine("Country with no Head of State: " + countryRepository.findCountriesByHeadOfStateIsNull().getFirst().getName());
			logger.fine("Country with highest number of cities: " + worldService.findCountryWithMostCities().getKey().getName()
					+ ". Number of Cities: " + worldService.findCountryWithMostCities().getValue());
			logger.fine("Total percentage of people living in United Kingdom's biggest city is : " + worldService.findPercentageOfPopulationInLargestCity("United Kingdom") + "%");
			logger.fine("For the UK: " + worldService.getPrimaryLanguage("United Kingdom").getPercentage().toString() + "% of the country speak " + worldService.getPrimaryLanguage("United Kingdom").getId().getLanguage().toString());
			logger.fine("The 5 districts with the smallest population in the world are: "  + worldService.get5SmallestDistricts().toString());

		};
	}
}
