package com.finvendor.api.example.dto;

import com.finvendor.common.annotations.Phone;

import javax.validation.constraints.*;
import java.io.Serializable;

public class ExampleRequestDto implements Serializable {
    //<=3 digit validation
    @NotNull
    @Digits(integer = 3, fraction = 0)
    private Integer id;

    @NotBlank
    @Size(min = 5, max = 10, message = "name length must be 5-10")
    private String name;

    @Phone(message = "Phone number must be digit")
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
