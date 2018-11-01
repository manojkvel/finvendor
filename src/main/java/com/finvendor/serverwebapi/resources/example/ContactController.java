//package com.finvendor.serverwebapi.resources.example;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class ContactController {
//
//    @RequestMapping("/contact/{id}")
//    @ResponseBody
//    public Contact contact(@PathVariable("id") Integer id) {
//        // mimics a call to a business service
//        return new Contact(id,"Test Firstname","Test Lastname");
//    }
//
//    @RequestMapping("/contacts")
//    @ResponseBody
//    public List<Contact> contacts() {
//        // mimics a call to a business service
//        Contact contact1 = new Contact(2, "Test Firstname2", "Test Lastname2");
//        List<Contact> list=new ArrayList<>();
//        list.add(contact1);
//        return list;
//    }
//
//}