package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value="/test")
public class TestMapping {

    @RequestMapping(value="/testMethod", method= RequestMethod.GET)
    public String testMethod() {
        return "index1";
    }

    @RequestMapping(value="/testGetParams")
    public String testGetParams(String username, String password) {
        //形参需要和请求参数名对应,可以是部分请求参数，若多个请求参数名一致，可用数组接收(String[])
        //也可以使用注解定义：@RequestParam(String username)String username
//        System.out.println(username+password);
        return "index1";
    }

    @RequestMapping(value="/testCookie")
    public String testCookie(HttpServletRequest request, @CookieValue(value="JSESSIONID")String sessionId) {
        HttpSession session = request.getSession();
        System.out.println("JSESSIONID: " + sessionId);
        return "index1";
    }

    @RequestMapping(value="/testParams", params={"username"})
    public String testParams() {
        return "index1";
    }

    @RequestMapping(value="/testRestful/{id}")
    public String testRestful(@PathVariable("id")Integer id) {
        return "index1";
    }

    //Restful API无法私用HttpServletRequest获取请求参数
    @RequestMapping(value="/testRestful/{id}/{username}")
    public String testRestful(@PathVariable("id")Integer id, @PathVariable("username")String username) {
        return "index1";
    }

    //可以使用POJO接收请求参数，不存在的参数为类型默认值
    @RequestMapping(value="/testGetParamsUsePOJO")
    public String testGetParamsUsePOJO(User user) {
        System.out.println(user.toString());
        return "index1";
    }
}


class User {
    private String username;
    private String password;
    private int sex;

    public User(String username, String password, int sex) {
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public User() {};

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSex() {
        return sex;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }
}