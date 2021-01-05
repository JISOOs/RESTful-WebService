package com.example.myrestfulservices.user.vo;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {

    private Integer id;

    @Size(min = 2, message = "Name은 2글자 이상을 입력해 주세요")
    private String name;
    @Past
    private Date joinDate;

    private String password;
    private String ssn;


}
