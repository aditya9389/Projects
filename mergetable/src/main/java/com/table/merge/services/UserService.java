package com.table.merge.services;

import com.table.merge.Model.Address;
import com.table.merge.Model.OneForAll;
import com.table.merge.Model.User;
import com.table.merge.repository.AddressRepository;
import com.table.merge.repository.AllRepository;
import com.table.merge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AllRepository allRepository;

    @Transactional
    public User createUser(User user)
    {
//        OneForAll oneforall = this.createAll(user);
        log.info("-----------creating user-----------");
        return userRepository.save(user);
    }
    @Transactional
    public Address createAddress(Address address)
    {
//        OneForAll oneForAll = this.createAll(address);
        log.info("--------creating address---------");
        return addressRepository.save(address);
    }

    public OneForAll createAll(User user)
    {
        log.info("--------getting date from user table for all table--------");
        OneForAll oneForALl=new OneForAll();
        oneForALl.setUsername(user.getUsername());
        oneForALl.setUserId(user.getUserId());
        oneForALl.setEmpId(user.getEmpId());
        oneForALl.setPhoneNumber(user.getPhoneNumber());
        log.info("--------creating all table with user data---------");
        return allRepository.save(oneForALl);
    }
    public OneForAll createAll(Address address)
    {
        log.info("--------getting user data from all table to put address---------");
        OneForAll oneForAll=allRepository.findByUserId(address.getUserId())
                .orElseThrow(()->new RuntimeException("--------user not found to register this address--------"));
        oneForAll.setPlace(address.getPlace());
        oneForAll.setAltNumber(address.getAltNumber());
        log.info("---------updating all data----------");
        return allRepository.save(oneForAll);
    }
}
