package com.sparta.sqlworlddatabase.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "countrylanguage")
public class Countrylanguage {
    @EmbeddedId
    private CountrylanguageId id;

    @MapsId("countryCode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("''")
    @JoinColumn(name = "CountryCode", nullable = false)
    private Country countryCode;

    @ColumnDefault("'F'")
    @Lob
    @Column(name = "IsOfficial", nullable = false)
    private String isOfficial;

    @ColumnDefault("0.0")
    @Column(name = "Percentage", nullable = false, precision = 4, scale = 1)
    private BigDecimal percentage;

    public CountrylanguageId getId() {
        return id;
    }

    public void setId(CountrylanguageId id) {
        this.id = id;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

}