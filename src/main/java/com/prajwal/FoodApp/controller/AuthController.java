package com.prajwal.FoodApp.controller;

import com.prajwal.FoodApp.config.JwtProvider;
import com.prajwal.FoodApp.model.Cart;
import com.prajwal.FoodApp.model.USER_ROLE;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.repository.CartRepository;
import com.prajwal.FoodApp.repository.UserRepository;
import com.prajwal.FoodApp.request.LoginRequest;

import com.prajwal.FoodApp.response.AuthResponse;
import com.prajwal.FoodApp.service.CustomUserDetailsService;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CartRepository cartRepository;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

        User isExist=userRepository.findByEmail(user.getEmail());

        if(isExist!=null){
            throw new Exception("email id already exist");
        }
        User newuser=new User();
        newuser.setEmail(user.getEmail());
        newuser.setFullName(user.getFullName());
        newuser.setRole(user.getRole());
        newuser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepository.save(newuser);

        Cart cart=new Cart();

        cart.setCustomer(savedUser);
        cart.setTotal(1L);
        cartRepository.save(cart);

        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setRole(savedUser.getRole());
        authResponse.setJwt(token);
        authResponse.setMessage("User created successfully");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);


    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest req){

        String email= req.getEmail();
        String password= req.getPassword();
        Authentication authentication=authenticate(email,password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setMessage("Signed in Successfully");
        authResponse.setJwt(jwt);

        Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
        String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
        if(userDetails==null){
            throw  new BadCredentialsException("invalid details");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }

}
