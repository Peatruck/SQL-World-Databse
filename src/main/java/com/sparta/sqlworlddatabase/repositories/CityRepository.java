package com.sparta.sqlworlddatabase.repositories;

import com.sparta.sqlworlddatabase.entities.City;
import com.sparta.sqlworlddatabase.projections.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {


    // Spring Data query
    List<City> findCitiesByCountryCode_Code(String countryCode);
    List<City> findCitiesByDistrict(String district);
    List<District> findUniqueDistrictBy();

}