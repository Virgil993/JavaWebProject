package com.example.DeliveryApp.service;

import com.example.DeliveryApp.entity.Address;
import com.example.DeliveryApp.entity.Address;
import com.example.DeliveryApp.entity.User;
import com.example.DeliveryApp.entity.User;
import com.example.DeliveryApp.exception.addressException.AddressDoesNotExistException;
import com.example.DeliveryApp.exception.addressException.AddressValidationException;
import com.example.DeliveryApp.exception.userException.UserDoesNotExistException;
import com.example.DeliveryApp.exception.userException.UserDoesNotExistException;
import com.example.DeliveryApp.mapper.AddressMapper;
import com.example.DeliveryApp.mapper.AddressMapper;
import com.example.DeliveryApp.repository.AddressRepository;
import com.example.DeliveryApp.repository.UserRepository;
import com.example.DeliveryApp.request.addressRequest.AddressRequest;
import com.example.DeliveryApp.response.addressResponse.AddressResponse;
import com.example.DeliveryApp.response.addressResponse.AddressResponse;
import com.example.DeliveryApp.response.addressResponse.DeleteAddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public AddressResponse createAddress(AddressRequest request){
        if(request.getCity().isBlank() || request.getHouse().isBlank() || request.getStreet().isBlank() || request.getUserId() == null ){
            throw new AddressValidationException("City, street, house and userId are all mandatory fields");
        }
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if(userOptional.isEmpty()){
            throw new UserDoesNotExistException("User doesn't exist with this Id");
        }
        final var user = userOptional.get();
        Address address = AddressMapper.toEntity(request,user);
        address = addressRepository.save(address);
        return AddressMapper.toResponse(address);
    }

    public List<AddressResponse> getAddressesByUser(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserDoesNotExistException("User doesn't exist with this Id");
        }
        List<Address> addresses = addressRepository.findByUserId(userId);
        List<AddressResponse> response = new ArrayList<>();
        for(var i=0;i<addresses.size();i++){
            response.add(AddressMapper.toResponse(addresses.get(i)));
        }
        return response;

    }

    public AddressResponse getAddressById(Long addressId){
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if(addressOptional.isEmpty()){
            throw new AddressDoesNotExistException("Address doesn't exist with this Id");
        }
        final var address = addressOptional.get();
        return AddressMapper.toResponse(address);
    }

    public DeleteAddressResponse deleteAddress(Long addressId){
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if(addressOptional.isEmpty()){
            throw new AddressDoesNotExistException("Address doesn't exist with this Id");
        }
        addressRepository.deleteById(addressId);

        DeleteAddressResponse response = new DeleteAddressResponse();
        response.setMessage("Address deleted successfully!");
        return response;
    }
}
