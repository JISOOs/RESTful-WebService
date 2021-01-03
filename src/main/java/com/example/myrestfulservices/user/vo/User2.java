package com.example.myrestfulservices.user.vo;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfoV2")
public class User2 extends User {

    private String grade;




}
