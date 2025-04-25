package nhatbaodinh.jpa.services;

import nhatbaodinh.jpa.models.Customer;
import nhatbaodinh.jpa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }
}
