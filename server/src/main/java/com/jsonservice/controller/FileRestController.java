package com.jsonservice.controller;

import com.jsonservice.model.JMessage;
import com.jsonservice.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@ComponentScan("com.jsonservice.DAO")
@RequestMapping("/")
public class FileRestController {

    /*@Autowired
    private Parser parser;

    @RequestMapping("newfile")
    public ModelAndView newfile(@RequestParam(value = "name") String name)  {
        return new ModelAndView("file","htmlString",parser.parse(RestControll.getMessage(name)));
    }*/


}
