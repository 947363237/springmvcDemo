package com.lis.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lis.model.Person;

/**
 *<p> Title: JsonController </p>
 *<p> Description: 返回json</p>
 *
 * @author lis
 * @since 2017年3月14日
 */
@Controller
@RequestMapping("/json")
public class JsonController {
    
    //http://localhost/springmvcDemo/json/user
    @ResponseBody
    @RequestMapping("/user")
    public Person get(){
        Person u = new Person();
        u.setAge(11);
        u.setName("jayjay");
        return u;
    }
    
    //http://localhost/springmvcDemo/json/list
    @ResponseBody
    @RequestMapping("/list")
    public List<Person> getList(){
        Person u = new Person();
        u.setAge(11);
        u.setName("jayjay");
        List<Person> list = new ArrayList<Person>();
        list.add(u);
        list.add(u);
        list.add(u);
        return list;
    }
    
    //http://localhost/springmvcDemo/json/map
    @ResponseBody
    @RequestMapping("/map")
    public Map getMap(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","lis");
        map.put("age",33);
        map.put("birthday",new Date());
        return map;
    }
}