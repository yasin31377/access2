package com.mkyong.controller;

import com.mkyong.model.AccessModel;
import com.mkyong.service.AccessMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @Autowired
    AccessMService accessMService;

    @RequestMapping(value = "/api/find",method = RequestMethod.GET)
    public List<AccessModel> home(){
        return  accessMService.findAll();
    }
}