package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.model.Address;
import com.prajwal.FoodApp.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email) throws Exception;
    User findUserProfile(String token) throws Exception;

    Address AddUserAddress(String token,Address address) throws  Exception;

    List<Address> getUserAddresses(String token) throws Exception;

}
