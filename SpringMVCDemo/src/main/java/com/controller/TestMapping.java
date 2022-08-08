package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/test")
public class TestMapping {

    @RequestMapping(value="/testMethod", method= RequestMethod.GET)
    public String testMethod() {
        return "index1";
    }

    @RequestMapping(value="/testParams", params={"username"})
    public String testParams() {
        return "index1";
    }
}