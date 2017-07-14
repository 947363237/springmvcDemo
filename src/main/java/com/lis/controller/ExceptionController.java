package com.lis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *<p> Title: ExceptionController </p>
 *<p> Description: 局部异常(当前Controller有效)</p>
 *
 * @author lis
 * @since 2017年3月14日
 */

@Controller
@RequestMapping("/ex")
public class ExceptionController {
    
    //局部异常，当前Controller有效，出错自动跳转 error.jsp页面
    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("error"); //error，返回的页面 
        mv.addObject("exception", ex); //相当于request.setAttribute
        System.out.println("in testExceptionHandler");
        return mv; //return error
    }
    
    //http://localhost/springmvcDemo/ex/byZero
    @RequestMapping("/byZero")
    public String error(){
        int i = 5/0;
        return "hello";
    }
}
