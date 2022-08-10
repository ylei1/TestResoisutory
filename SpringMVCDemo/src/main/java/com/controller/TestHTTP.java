package com.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@Controller
@RequestMapping(value="/testHTTP")
public class TestHTTP {
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return "success";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestBody(RequestEntity<String> requestEntity) {
        System.out.println("RequestEntity: " + requestEntity);
        System.out.println("RequestBody: " + requestEntity.getBody());
        System.out.println("RequestHeaders: " + requestEntity.getHeaders());
        return "success";
    }

    @RequestMapping("/testResponseUseServlet")
    public void testResponseUseServlet(HttpServletResponse response) throws Exception {
        response.getWriter().println("Hello, response");
    }

    @RequestMapping("/testResponseUseSpring")
    @ResponseBody
    public String testResponseUseSpring() throws Exception {
        return "return a response body";
    }

    @RequestMapping("/testDownload")
    public ResponseEntity<byte[]> testDownload(HttpSession session) throws Exception {
        String path = session.getServletContext().getRealPath("static/file.txt");
        try(InputStream input = new FileInputStream(path)) {
            byte[] buf = new byte[input.available()];
            input.read(buf);
            System.out.println(Arrays.toString(buf));

            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline;filename=hehe.txt"); //suffix is needed?
            HttpStatus statusCode = HttpStatus.OK;
            return new ResponseEntity<>(buf, headers, statusCode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
