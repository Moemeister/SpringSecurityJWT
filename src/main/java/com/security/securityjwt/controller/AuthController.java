package com.security.securityjwt.controller;

import com.security.securityjwt.domain.AuthRequest;
import com.security.securityjwt.exception.ApiRequestException;
import com.security.securityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String welcome(){
        return "Welcome!";
    }

    @PostMapping("/login")
    public @ResponseBody String generateToken(AuthRequest authRequest) throws ApiRequestException{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(), authRequest.getPass())
            );
        }catch (Exception e){
            throw new ApiRequestException("Invalid username and password", HttpStatus.BAD_REQUEST);
        }
        return jwtUtil.generateToken(authRequest.getUsername()) ;

    }
}
