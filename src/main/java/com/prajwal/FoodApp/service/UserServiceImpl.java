package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.config.JwtProvider;
import com.prajwal.FoodApp.model.Address;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");

        }
        return user;
    }

    @Override
    public User findUserProfile(String token) throws Exception {

        String email=jwtProvider.getEmailFromToken(token);

        User user=findUserByEmail(email);

        return user;



    }

    @Override
    public Address AddUserAddress(String token,Address address) throws Exception {
        User user=findUserProfile(token);


        Address newaddress=new Address();
        newaddress.setStreet(address.getStreet());
        newaddress.setCity(address.getCity());
        newaddress.setState(address.getState());
        newaddress.setCountry(address.getCountry());
        newaddress.setPostalCode(address.getPostalCode());

        user.getAddresses().add(newaddress);
        userRepository.save(user);
        return newaddress;
    }

    @Override
    public List<Address> getUserAddresses(String token) throws Exception {
        User user=findUserProfile(token);
       return  user.getAddresses();
    }


}
