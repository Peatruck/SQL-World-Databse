package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query(value = "select ID, Name, CountryCode, District, Population from city where CountryCode = ?1", nativeQuery = true)
    List<City> findCitiesByCountryCode(String countryCode);
}