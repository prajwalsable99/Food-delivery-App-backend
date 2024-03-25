package com.prajwal.FoodApp.service;

import com.prajwal.FoodApp.dto.RestaurantDto;
import com.prajwal.FoodApp.model.Address;
import com.prajwal.FoodApp.model.Restaurant;
import com.prajwal.FoodApp.model.User;
import com.prajwal.FoodApp.repository.AddressRepository;
import com.prajwal.FoodApp.repository.RestaurantRepository;
import com.prajwal.FoodApp.repository.UserRepository;
import com.prajwal.FoodApp.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) throws Exception {

        Restaurant existingRestaurant = restaurantRepository.findByOwnerId(user.getId());
        if (existingRestaurant != null) {
            // Handle the case where the user already owns a restaurant
            // For example, throw an exception or return null indicating failure
            throw new Exception("User already owns a restaurant");
        }


        Address address=addressRepository.save(req.getAddress());

        Restaurant new_restaurant=new Restaurant();
        new_restaurant.setName(req.getName());

        new_restaurant.setOwner(user);

        new_restaurant.setDescription(req.getDescription());
        new_restaurant.setCuisineType(req.getCuisineType());

        new_restaurant.setAddress(address);
        new_restaurant.setContactInformation(req.getContactInformation());

        new_restaurant.setOpeningHours(req.getOpeningHours());
        new_restaurant.setRegistrationDate(LocalDateTime.now());
        new_restaurant.setImages(req.getImages());
        new_restaurant.setOpen(req.isOpen());

        return restaurantRepository.save(new_restaurant);

    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest req) throws Exception {
       Restaurant restaurant=findRestaurantById(restaurantId);
       if(req.getCuisineType()!=null){
           restaurant.setCuisineType(req.getCuisineType());
       }
       if(req.getName()!=null){
           restaurant.setName(req.getName());
       }
       if(req.getDescription()!=null){
           restaurant.setDescription(req.getDescription());
       }
       return restaurantRepository.save(restaurant);

    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant=findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String query) {
        return restaurantRepository.searchRestaurants(query);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant=restaurantRepository.findById(id);
        if(restaurant.isEmpty()){
            throw new Exception("Restaurant Not found");
        }
        return restaurant.get();
    }

    @Override
    public Restaurant findRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant= restaurantRepository.findByOwnerId(userId);
        if(restaurant==null){
            throw new Exception("Restaurant Not found for owner");
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addtoFavorites(Long restaurantID, User user) throws Exception {

        Restaurant restaurant=findRestaurantById(restaurantID);

        RestaurantDto restaurantDto=new RestaurantDto();
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setId(restaurant.getId());
        if(user.getFavorites().contains(restaurantDto)){
            user.getFavorites().remove(restaurantDto);
        }else {
            user.getFavorites().add(restaurantDto);
        }
        userRepository.save(user);
        return  restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant);


    }
}
