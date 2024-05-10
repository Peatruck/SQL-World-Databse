package com.sparta.sqlworlddatabase.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ColumnDefault("''")
    @Column(name = "Name", nullable = false, length = 35)
    private String name;

    @ColumnDefault("''")
    @Column(name = "District", nullable = false, length = 20)
    private String district;

    @ColumnDefault("0")
    @Column(name = "Population", nullable = false)
    private Integer population;

    @ManyToOne
    @JoinColumn(name = "CountryCode", nullable = false)
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }


}