package com.jsonservice.controller;


import antlr.collections.List;
import com.jsonservice.DAO.MessageRepository;
import com.jsonservice.model.JMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

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

    @GetMapping("/json")
    public String getJson(Model model2){
        Iterable<JMessage> all = messageRepository.findAll();
        int tabSpaces = 0;

        ArrayList line2 = new ArrayList();
        for (JMessage jMessage : all){
            String test = jMessage.getText();

            String[] lines = test.split("\n");
            for(int i = 0; i<lines.length;i++){
                String[] split = lines[i].split("\\n");
                for(int j = 0; j<split.length;j++){
                    String spaces = null;
                    if(split[j].indexOf("{")>=0){
                        tabSpaces = tabSpaces + 10;
                    }else if(split[j].indexOf("}")>0){
                        tabSpaces = tabSpaces + 10;
                    }
                    for (int l = 0; l < tabSpaces; l++){
                        spaces = spaces + " ";
                    }
                    split[j] = spaces + split[j];
                    line2.add(split[j]);
                }
            }
            System.out.println(line2);
            model2.addAttribute("lines", line2);
        }
        return "test";
    }
}



