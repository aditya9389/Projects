package com.table.merge.services;

import com.table.merge.Model.Address;
import com.table.merge.Model.OneForAll;
import com.table.merge.Model.User;
import com.table.merge.repository.AddressRepository;
import com.table.merge.repository.AllRepository;
import com.table.merge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AllRepository allRepository;

    public User createUser(User user)
    {
        OneForAll oneforall = this.createAll(user);
        return userRepository.save(user);
    }
    public Address createAddress(Address address)
    {
        OneForAll oneForAll = this.createAll(address);
        return addressRepository.save(address);
    }

    public OneForAll createAll(User user)
    {
        OneForAll oneForALl=new OneForAll();
        oneForALl.setUsername(user.getUsername());
        oneForALl.setUserId(user.getUserId());
        oneForALl.setEmpId(user.getEmpId());
        oneForALl.setPhoneNumber(user.getPhoneNumber());
        return allRepository.save(oneForALl);
    }
    public OneForAll createAll(Address address)
    {
        OneForAll oneForAll=allRepository.findByUserId(address.getUserId())
                .orElseThrow(()->new RuntimeException("user not found to register this address"));
        oneForAll.setPlace(address.getPlace());
        oneForAll.setAltNumber(address.getAltNumber());
        allRepository.deleteById(address.getUserId());

        return allRepository.save(oneForAll);
    }

}
