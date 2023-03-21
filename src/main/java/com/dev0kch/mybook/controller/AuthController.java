package com.dev0kch.mybook.controller;


import com.dev0kch.mybook.model.AuthRequest;
import com.dev0kch.mybook.model.Mobinaute;
import com.dev0kch.mybook.repository.UserRepository;
import com.dev0kch.mybook.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;


    public AuthController(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity generateToken(@RequestBody AuthRequest authRequest)throws Exception{
        ResponseEntity token;
        try {


            Mobinaute user = userRepository.findByUsername(authRequest.getNomUtilisateur());
            if (passwordEncoder.matches(authRequest.getMotPasse(), user.getMotPasse()) &&
                    user.getNomUtilisateur().equals(authRequest.getNomUtilisateur())){

                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getNomUtilisateur(), user.getMotPasse())
                );
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("authorization", jwtUtil.generateToken(authRequest.getNomUtilisateur()));

//                token = ResponseEntity.ok().header(responseHeaders.toString()).body("Success");
                token = new ResponseEntity(responseHeaders, HttpStatus.OK);
            }else {
                throw new Exception("Invalid username or password");

            }



        }catch (Exception e){

            throw new Exception("Invalid username or password");
        }

        return token;
    }

    @PostMapping("/authenticate/register")
    public Mobinaute login(@RequestBody Mobinaute user) throws Exception {
        String encryptedPassword = passwordEncoder.encode(user.getMotPasse());
        user.setMotPasse(encryptedPassword);
        if (userRepository.findByUsername(user.getNomUtilisateur()) == null){
            userRepository.save(user);
        }
        else {
            throw new Exception("username is already exist");
        }
        return user;
    }


    // if response is true, the token is expired
    // if response is false, the token is valid
    @PostMapping("/authenticate/validate_token")
    public Boolean validateToken(@RequestBody  String token){
        boolean isValidateToken= true;
        try {
            isValidateToken =  jwtUtil.isTokenExpired(token);
        }catch (Exception e){
            isValidateToken = true;
        }
        return isValidateToken;
    }


}
