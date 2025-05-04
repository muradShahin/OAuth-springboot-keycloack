package com.example.demo.controller;



import com.example.demo.dto.CustomerDto;
import com.example.demo.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CustomerController {

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int customerId) {

        CustomerDto customerRecord = customerService.getCustomerById(customerId);
        //logger.info(customerRecord.getFirstName());
        return new ResponseEntity<>(customerRecord, HttpStatus.OK);

    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {


      /**  System.out.println("auth Name :"+auth.getName());
        System.out.println("auth details :"+auth.getDetails());
        List<String> roles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        for (String role : roles) {
            System.out.println(role);
        }

        Jwt jwt = token.getToken();
        String scopeString = jwt.getClaimAsString("scope"); // or "scp" for Azure
        List<String> scopes = Arrays.asList(scopeString.split(" "));

        System.out.println("Scopes: ****");
        for (String scope : scopes) {

            System.out.println(scope);

        }
       */
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }
}
