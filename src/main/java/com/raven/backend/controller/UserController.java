package com.raven.backend.controller;

import com.raven.backend.entity.User;
import com.raven.backend.service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/db")
//@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    private static final String SERVICE_A = "serviceA";

    @GetMapping("/test")
    @RateLimiter(name = SERVICE_A, fallbackMethod = "serviceFallBack")
    public String test(){
        return "test";
    }

    public String serviceFallBack(Throwable t){
        return "serviceFallBack" + t.getMessage();
    }

    @RateLimiter(name = SERVICE_A, fallbackMethod = "serviceFindFallBack")
    @GetMapping
    public ResponseEntity<List<User>> find() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    public ResponseEntity<List<User>> serviceFindFallBack(Throwable t) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ArrayList<>());
    }

//    public List<User> serviceFindFallBack(Throwable t){
//        return new ArrayList<>();
//    }

    @GetMapping("find/{id}")
    public Optional<User> findById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/save")
    public User save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUser(id);
    }


}
