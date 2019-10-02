package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("customers")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("customers/index");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("customers/{id}")
    public ModelAndView showInformation(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("customers/info");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("customersUpdate")
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("customers/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/create";
    }
    @PostMapping("customers/save")
    public String createCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        customerService.save(customer);
        model.addAttribute("customers", customerService.findAll());
        return "customers/index";
    }
}

