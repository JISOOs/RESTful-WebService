package com.example.myrestfulservices.user.vo;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("UserInfo")
public class User {

    private Integer id;

    @Size(min = 2, message = "Name은 2글자 이상을 입력해 주세요")
    private String name;
    @Past
    private Date joinDate;

    private String password;
    private String ssn;


}
