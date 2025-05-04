package com.example.demo.services;


import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.exceptionHandler.ExceptionHandler;
import com.example.demo.models.Customer;
import com.example.demo.models.Department;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto getCustomerById(long id) {

        return customerRepository.findById(id)
                .map(this::customerDtoConverter)
                .orElseThrow(()-> new ExceptionHandler("Customer Not Found"));

    }


    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                //.filter(customer -> customer.getGender().equalsIgnoreCase("Male"))  // this is for filtering
                //.sorted(Comparator.comparing(Customer::getLastName)) // for sorting lamda has been used here
                .map(this::customerDtoConverter)
                .collect(Collectors.toList());
    }


    private CustomerDto customerDtoConverter(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail(customer.getEmail());
        customerDto.setGender(customer.getGender());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setDepartment(departmentDtoConverter(customer.getDepartments()));

        return customerDto;
    }

    private DepartmentDto departmentDtoConverter(Department department) {

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName(department.getDepName());
        departmentDto.setLocation(department.getLocation());
        departmentDto.setManager(department.getManagerName());
        return departmentDto;
    }
}
