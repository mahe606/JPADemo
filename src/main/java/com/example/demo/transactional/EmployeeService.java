package com.example.demo.transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeeService {
    @Autowired
    private  EmployeeRepository employeeRepository;

    @Autowired
    private AddressService addressService;


    @Transactional
    public Employee addEmployee(Employee emp){
        Employee newEmp = employeeRepository.save(emp);

        // Since this method is defined as @Transactional the employee will not be stored as the address is null
        // if @Transactional is not given the employee data will be saved even when the address is null, which is wrong
        EmpAddress empAddress = null;
        empAddress.setEmployee(emp);
        empAddress.setCity("Kovai");

        addressService.addAddress(empAddress);
        return newEmp;
    }
}
