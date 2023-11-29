package com.sermaluc.challenge.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sermaluc.challenge.entity.Phone;
import com.sermaluc.challenge.entity.User;
import com.sermaluc.challenge.repository.PhoneRepository;
import com.sermaluc.challenge.repository.UserRepository;
import com.sermaluc.challenge.service.UserService;
import com.sermaluc.challenge.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.Nonnull;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Value("${app.security.jwt.expirationTime}")
    String expirationTime;

    @Value("${app.security.jwt.secretKey}")
    String secretKey;

    @Override
    public List<User> findAllUser(){

        return userRepository.findAll();

    }

    @Override
    public User saveUser(UserVO userVO) {

        if (!EmailValidator.getInstance().isValid(userVO.getEmail())){
            throw new RuntimeException("El email es invalido");
        }

        String token = generateToken(userVO.getName(), userVO.getEmail());

        List<Phone> phones = new ArrayList<>();
        userVO.getPhones().forEach((phoneVO) -> {
            Phone phone =	phoneRepository.save( phoneVO.toPhone() );
            log.info("saving phones {}", phone);
            phones.add(phone);
        });
        User user = userVO.toUser(token , phones);
        log.info("saving user {}", user);

        return userRepository.save(user);
    }

    public boolean checkEmailvalidity(String emailaddress){
        String email_regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
        boolean b = emailaddress.matches(email_regex);
        return b;
    }

    protected String generateToken(@Nonnull String username , @Nonnull String email) {
        Instant now = Instant.now();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date dateExpired = Date.from(now.plus(Long.parseLong(expirationTime.trim()), ChronoUnit.SECONDS));

        return JWT.create()
                .withSubject("authentication")
                .withClaim("name", username)
                .withClaim("email", email )
                .withExpiresAt(dateExpired )
                .sign(algorithm);
    }

}
