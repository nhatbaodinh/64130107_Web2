package nhatbaodinh.jpa.controllers;

import nhatbaodinh.jpa.models.Customer;
import nhatbaodinh.jpa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {
  @Autowired
  CustomerRepository customerRepository;

  @GetMapping("/")
  public String getAllStudents(ModelMap model) {
    List<Customer> customers = customerRepository.findAll();
    model.addAttribute("customers", customers);
    return "customer";
  }
}
