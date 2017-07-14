package com.lis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *<p> Title: ExceptionController </p>
 *<p> Description: 全局异常的处理，方式一：基于注解，方式二：xml配置，参考 springmvc-servlet.xml</p>
 *
 * @author lis
 * @since 2017年3月14日
 */

@ControllerAdvice   //跟局部异常的区别就在这里,局部异常是Controler <<<=====================================注意
@RequestMapping("/globalEx")
public class GlobalExceptionController {
    
    //局部异常，当前Controller有效，出错自动跳转 error.jsp页面
    @ExceptionHandler
    public ModelAndView exceptionHandler(ArrayIndexOutOfBoundsException ex){
        ModelAndView mv = new ModelAndView("error"); //error，返回的页面 
        mv.addObject("exception", ex); //相当于request.setAttribute
        System.out.println("in testExceptionHandler");
        return mv; //return error
    }
    
    //注解异常测试:ArrayIndexOutOfBoundsException
    //http://localhost/springmvcDemo/globalEx/globalEx
    @RequestMapping("/globalEx")
    public String globalEx(){
        int[] a = {2};
        System.out.println(a[1]);
        return "hello";
    }
    
    //xml配置异常测试
    //http://localhost/springmvcDemo/globalEx/xmlGlobalEx
    @RequestMapping("/xmlGlobalEx")
    public String xmlGlobalEx(){
        int a = 1/0;
        return "hello";
    }
}
