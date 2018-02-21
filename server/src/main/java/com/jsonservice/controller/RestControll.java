package com.jsonservice.controller;


import com.jsonservice.DAO.MessageRepository;
import com.jsonservice.model.JMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@ComponentScan("com.jsonservice.DAO")
@RequestMapping("/")
public class RestControll {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String send(@RequestParam MultipartFile file) throws IOException {

        String message = new String(file.getBytes());
        String name = file.getOriginalFilename();
        ;
        messageRepository.save(new JMessage(message, name));

        return "send";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        return "index";
    }

    @GetMapping("/table")
    public String getNamesFromDB(Model model) {
        Iterable<JMessage> all = messageRepository.findAll();
        model.addAttribute("jmessages", all);
        return "result";
    }
}



