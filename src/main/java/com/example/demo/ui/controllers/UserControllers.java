package com.example.demo.ui.controllers;

import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserControllers {

    @GetMapping(path="/simple/{userId}")
    public String getUsers(@PathVariable String userId) {
        return "get user was called with userId = " + userId;
    }

    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue = "1") int page,
                           @RequestParam(value="limit", defaultValue = "50") int limit,
                           @RequestParam(value="sort", defaultValue = "desc", required = false) String sort) {
        return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort ;
    }

    @GetMapping(path="/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE
    })
    public UserRest getMoreUserInfo(@PathVariable String userId) {
        return new UserRest(1, "Ivan", "Ivanov", "Ivan.Ivanov@gmail.com");
    }

    @GetMapping(path="entity/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> getEntityUserInfo(@PathVariable String userId) {
        UserRest userRest = new UserRest();
        userRest.setUserId(2);
        userRest.setFirstName("Petro");
        userRest.setSecondName("Petrov");
        userRest.setEmail("Petro.Petrov@gmail.com");
        return new ResponseEntity<UserRest>(userRest, HttpStatus.CREATED);
    }

    @PostMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
                    , MediaType.APPLICATION_XML_VALUE
            },
            consumes = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE
    }
    )
    public ResponseEntity<UserRest> createUsers(@Valid @RequestBody UserDetailsRequestModel usersDetails) {
        UserRest userRest = new UserRest();

        userRest.setUserId(123);
        userRest.setFirstName(usersDetails.getFirstName());
        userRest.setSecondName(usersDetails.getSecondName());
        userRest.setEmail(usersDetails.getEmail());

        return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
    }

    @PutMapping
    public String updateUsers() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUsers() {
        return "delete user was called";
    }
}
