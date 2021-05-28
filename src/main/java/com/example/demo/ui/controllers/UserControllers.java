package com.example.demo.ui.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllers {

    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue = "1") int page,
                           @RequestParam(value="limit", defaultValue = "50") int limit,
                           @RequestParam(value="sort", defaultValue = "desc", required = false) String sort) {
        return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort ;
    }

    @GetMapping(path="/{userId}")
    public String getUsers(@PathVariable String userId) {
        return "get user was called with userId = " + userId;
    }

    @PostMapping
    public String createUsers() {
        return "create user was called";
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
