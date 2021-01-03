package com.example.myrestfulservices.user.controller;

import com.example.myrestfulservices.user.vo.User;
import com.example.myrestfulservices.user.service.UserDaoService;
import com.example.myrestfulservices.user.vo.User2;
import exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    private AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {

        List<User> all = service.findAll();
        return all;
    }

   // @GetMapping("/v1/users/{id}")
   // @GetMapping(value="/users/{id}/", params = "version=1")
   // 일반 브라우저에서는 실행 불가
   // @GetMapping(value="/users/{id}/", headers = "X-API-VERSION=1")
   //@GetMapping("/v1/users/{id}", produces = "application/vnd.company")
   @GetMapping("/v1/users/{id}")
    public MappingJacksonValue retrieveUserv1(@PathVariable int id) {

        User user = service.fineOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));

        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);


        return mapping;
    }

    //@GetMapping("/v2/users/{id}")
    //@GetMapping(value="/users/{id}/", params = "version=1")
    //@GetMapping(value="/users/{id}/", headers = "X-API-VERSION=2")
    @GetMapping("/v2/users/{id}")
    public MappingJacksonValue retrieveUserv2(@PathVariable int id) {

        User user = service.fineOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));

        }

        User2 userVo2 = new User2();
        BeanUtils.copyProperties(user, userVo2);
        userVo2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userVo2);
        mapping.setFilters(filters);


        return mapping;
    }


}
