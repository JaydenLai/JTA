package com.hz.syxx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pefan_Li
 * Created Time 2018/8/6 23:51.
 */
@RestController
public class HelloWorldController {
    private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String name){
        logger.info("Hello, {}",name);
        return "Hello, "+name;
    }

    @GetMapping("/sayHi")
    public String sayHi(@RequestParam String name){
        logger.info("Hi, {}",name);
        return "Hi, "+name;
    }
}
