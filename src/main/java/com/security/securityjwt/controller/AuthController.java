package com.security.securityjwt.controller;

import com.security.securityjwt.DTO.TokenResponse;
import com.security.securityjwt.DTO.UserDTO;
import com.security.securityjwt.domain.AuthRequest;
import com.security.securityjwt.domain.User;
import com.security.securityjwt.exception.ApiRequestException;
import com.security.securityjwt.service.UserService;
import com.security.securityjwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.SpringVersion;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public String welcome(){
        return "Welcome!";
    }

    @PostMapping(value= "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponse> generateToken(AuthRequest authRequest) throws ApiRequestException{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(), authRequest.getPass())
            );
        }catch (Exception e){
            throw new ApiRequestException("Invalid username and password", HttpStatus.BAD_REQUEST);
        }
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwtUtil.generateToken(authRequest.getUsername()));
        return new ResponseEntity<TokenResponse>(tokenResponse, HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UserDTO> save(@PathVariable("id") Integer id){
        User usuario = userService.findOne(id);
        UserDTO userDTO = modelMapper.map(usuario, UserDTO.class);
     return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UserDTO>> findAll(){
        System.out.println(SpringVersion.getVersion());
        List<User> usuarios = userService.findAll();
        return new ResponseEntity<>(usuarios.stream().map(users -> modelMapper.map(users, UserDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK) ;
    }

    @PostMapping("/usuario")
    public ResponseEntity<String> save(@RequestBody User userRequest){
        User newUser = userService.save(userRequest);
        return new ResponseEntity(newUser,HttpStatus.CREATED);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody User userRequest){

        User user = userService.update(id, userRequest);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return new ResponseEntity(userDTO,HttpStatus.OK);
    }
}
