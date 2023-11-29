package com.sermaluc.challenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sermaluc.challenge.entity.Phone;
import com.sermaluc.challenge.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class UserVO {

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private String email;

    @JsonProperty(required = true)
    private String password;

    @JsonProperty(required = true)
    private Collection<PhoneVO> phones = new ArrayList<PhoneVO>();

    public User toUser(String token , Collection<Phone> phones) {
        return new User(this.name, this.email,this.password , token , phones);
    }
}
