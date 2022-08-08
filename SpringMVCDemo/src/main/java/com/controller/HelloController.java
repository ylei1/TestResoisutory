package com.controller;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
    //value---->{接口1， 接口2， 接口n...}
    //method---->请求方法，默认接受所以方法
    //params----->请求参数{"key=value"/key=!value"/"key"/"!key"...} 所有条件必须同时满足
    //headers---->请求头，操作如params
    //路径支持正则匹配符号，如？，*等
    @RequestMapping(value="/")
    public String index() {
        return "index";
    }
}
