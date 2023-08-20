package com.example.kios.controller;

import com.example.kios.contant.Constants;
import com.example.kios.model.response.UserResponse;
import com.example.kios.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> user) throws AuthException {
        String userName = (String) user.get("username");
        String password = (String) user.get("password");

        if(userName == null || password == null) {
            throw new AuthException("Invalid user/password");
        }

        UserResponse response = userService.validateUser(userName, password);

        if(response == null) {
            Map<String, Object> map = new HashMap<>();;
            map.put("response", response);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST );
        }

        return new ResponseEntity<>(generateJWTToken(response), HttpStatus.OK);
    }

    private Map<String, Object> generateJWTToken(UserResponse user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS512, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("address", user.getAddress())
                .claim("roleCode", user.getRoleCode())
                .compact();
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", user);

        return map;
    }



}
