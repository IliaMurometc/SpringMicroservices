//TODO Разделить на несколько отдельных контроллеров что бы не было все на куче
//TODO Добавить новых ендпоинтов

package com.example.demo.ui.controllers;

import com.example.demo.exceptions.UserServiceException;
import com.example.demo.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.demo.ui.model.request.UserDetailsRequestModel;
import com.example.demo.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserControllers {

    Map<String, UserRest> users;

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
        return new UserRest(createUUID(), "Ivan", "Ivanov", "Ivan.Ivanov@gmail.com");
    }


    @GetMapping(path="exception/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> getException (@PathVariable String userId) {
            String firsName = null;

            int firstNameLength = firsName.length();
            // oops

            return new ResponseEntity<UserRest>(HttpStatus.OK);
    }

    @GetMapping(path="illeg_arg_exception/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> getIllegalArgumentException (@PathVariable String userId) {
        String firsName = null;
        if (true){
            throw new IllegalArgumentException();
        }
        return new ResponseEntity<UserRest>(HttpStatus.OK);
    }

    @GetMapping(path="user_exception/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> getUserServiceException (@PathVariable String userId) {
        String firsName = null;
        if (true){
            throw new UserServiceException("the user service exception is thrown");
        }
        return new ResponseEntity<UserRest>(HttpStatus.OK);
    }

    @GetMapping(path="entity/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> getEntityUserInfo(@PathVariable String userId) {
        UserRest userRest = new UserRest();
        userRest.setUserId(createUUID());
        userRest.setFirstName("Petro");
        userRest.setSecondName("Petrov");
        userRest.setEmail("Petro.Petrov@gmail.com");
        return new ResponseEntity<UserRest>(userRest, HttpStatus.CREATED);
    }

    //TODO need to add tests for existing ant not existing user
    @GetMapping(path="get_from_map/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> getEntityUserInfoFromMap(@PathVariable String userId) {
        if (users.containsKey(userId)){
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    }

    //TODO add several tests
    @PostMapping(produces = {
                    MediaType.APPLICATION_JSON_VALUE
                    , MediaType.APPLICATION_XML_VALUE
            }, consumes = {
            MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRest> createUsers(@Valid @RequestBody UserDetailsRequestModel usersDetails) {
        UserRest userRest = new UserRest();

        String userId = createUUID();
        userRest.setUserId(userId);
        userRest.setFirstName(usersDetails.getFirstName());
        userRest.setSecondName(usersDetails.getSecondName());
        userRest.setEmail(usersDetails.getEmail());

        if (users == null){ users = new HashMap<>();
            users.put(userId, userRest);
        }

        return new ResponseEntity<UserRest>(userRest, HttpStatus.OK);
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

    //TODO need to add test methods
    @PutMapping(path="entity/{userId}", produces = {
                    MediaType.APPLICATION_JSON_VALUE
                    , MediaType.APPLICATION_XML_VALUE
            }, consumes = {
                    MediaType.APPLICATION_JSON_VALUE
                    , MediaType.APPLICATION_XML_VALUE})
    public UserRest updateUsers(@PathVariable String userId,
                              @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetailsRequestModel) {
        UserRest staredUserDetails = users.get(userId);
        staredUserDetails.setFirstName(updateUserDetailsRequestModel.getFirstName());
        staredUserDetails.setSecondName(updateUserDetailsRequestModel.getSecondName());

        users.put(userId, staredUserDetails);

        return staredUserDetails;
    }

    //TODO need to add test methods
    @DeleteMapping(path = "entity/{userId}")
    public ResponseEntity<Void> deleteUsers(@PathVariable String usedId) {
        users.remove(usedId);
        return ResponseEntity.noContent().build();
    }
}
