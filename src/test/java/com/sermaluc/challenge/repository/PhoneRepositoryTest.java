package com.sermaluc.challenge.repository;

import com.sermaluc.challenge.entity.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PhoneRepositoryTest {

    static final String NUMBER = "976433032";
    static final String CITY_CODE = "10";
    static final String COUNTRY_CODE = "+51";

    @Autowired
    PhoneRepository phoneRepository;

    @Test
    public void createNewPhone() {
        Phone phone = new Phone(NUMBER, CITY_CODE, COUNTRY_CODE);

        phone = phoneRepository.save(phone);

        Assertions.assertNotNull(phone);
        Assertions.assertNotNull(phone.getPhoneId());
        Assertions.assertNotNull(phone.getNumber());
        Assertions.assertNotNull(phone.getCountrycode());
        Assertions.assertNotNull(phone.getCitycode());
    }
}
