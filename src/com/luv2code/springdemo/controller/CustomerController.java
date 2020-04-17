package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //need to inject customer service
    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel){

        //get customers from the service
        List<Customer> customerList = customerService.getCustomers();

        //add the customers to the model
        theModel.addAttribute("customers", customerList);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        //create model attribute to bind form data
        Customer customer = new Customer();

        theModel.addAttribute("customer", customer);//lets us do stuff in our HTML form

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){

        //save the customer using our service
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model model){//Request param gets the field info from the HTML

        //get the customer from service
        Customer customer = customerService.getCustomer(theId);

        //set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);

        //send over to form
        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId){

        //delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }
}
