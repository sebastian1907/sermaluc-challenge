package com.sermaluc.challenge.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sermaluc.challenge.entity.Phone;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneVO {

    @JsonProperty(required = true)
    private String number;

    @JsonProperty(required = true)
    private String citycode;

    @JsonProperty(required = true)
    private String countrycode;

    public Phone toPhone() {
        return new Phone(this.number, this.citycode, this.countrycode);
    }

}
