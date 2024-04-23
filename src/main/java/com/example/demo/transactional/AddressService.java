package com.example.demo.transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public EmpAddress addAddress(EmpAddress empAddress){
        EmpAddress newEmpAddress = addressRepository.save(empAddress);
        return newEmpAddress;
    }
}
