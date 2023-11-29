package com.sermaluc.challenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="PHONE")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long phoneId;
    @Column(name="NUMBER")
    private String number;
    @Column(name="CYTY_CODE")
    private String citycode;
    @Column(name="COUNTRY_CODE")
    private String countrycode;

    public Phone(String number, String cityCode, String countryCode) {
        this.number = number;
        this.citycode = cityCode;
        this.countrycode = countryCode;
    }
}
