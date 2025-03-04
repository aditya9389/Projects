package com.table.merge.controllers;

import com.table.merge.Model.Address;
import com.table.merge.Model.OneForAll;
import com.table.merge.Model.User;
import com.table.merge.repository.AllRepository;
import com.table.merge.repository.UserRepository;
import com.table.merge.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AllRepository allRepository;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping(value = "/giveadd")
    public ResponseEntity<Address> giveAdd(@RequestBody Address address)
    {
        User user=userRepository.findByUserId(address.userId)
                .orElseThrow(()-> new RuntimeException("user is not there"));
        return ResponseEntity.ok(userService.createAddress(address));
    }

    @GetMapping("/getInfo")
    public ResponseEntity<Optional<OneForAll>> getAllDetails(@RequestBody Integer empId)
    {
        return ResponseEntity.ok(allRepository.findByUserId(empId));
    }
}
