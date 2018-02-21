package com.jsonservice.controller;


import com.jsonservice.DAO.MessageRepository;
import com.jsonservice.model.JMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@ComponentScan("com.jsonservice.DAO")
@RequestMapping("/")
public class RestControll {

    private final static int STR_LENGTH = 250;

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String send(@RequestParam MultipartFile file) throws IOException {

        String message = new String(file.getBytes());
        int length = message.length();
        int splitnum = 1 + length / STR_LENGTH;
        String[] split = new String[splitnum];

        for (int i = 0, j = 0; j < splitnum - 1; i = i + STR_LENGTH, j++) {
            split[j] = message.substring(i, i + STR_LENGTH);
        }
        split[splitnum - 1] = message.substring(length - STR_LENGTH, length);

        for (int i = 0; i < split.length; i++) {
            System.out.println(i);
            System.out.println(split[i]);
            messageRepository.save(new JMessage(split[i]));
        }

        return "send";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model) {
        return "index";
    }

}
