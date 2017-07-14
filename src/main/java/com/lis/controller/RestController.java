package com.lis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *<p> Title: RestController </p>
 *<p> Description: RESTFul风格的SringMVC</p>
 *
 * @author lis
 * @since 2017年3月14日
 */

@Controller
@RequestMapping("/rest")
public class RestController {
    
    //http://localhost/springmvcDemo/rest/user/{id}
    @RequestMapping(value="/user/{id}",method=RequestMethod.GET)
    public String get(@PathVariable("id") Integer id){
        System.out.println("get"+id);
        return "/hello";
    }
    
    //可以用谷歌 PostMan工具测试
    //http://localhost/springmvcDemo/rest/user/{id}
    @RequestMapping(value="/user/{id}",method=RequestMethod.POST)
    public String post(@PathVariable("id") Integer id){
        System.out.println("post"+id);
        return "/hello";
    }
    
    //http://localhost/springmvcDemo/rest/user/{id}
    @RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
    public String put(@PathVariable("id") Integer id){
        System.out.println("put"+id);
        return "/hello";
    }
    
    //http://localhost/springmvcDemo/rest/user/{id}
    @RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        System.out.println("delete"+id);
        return "/hello";
    }
    
    
}
