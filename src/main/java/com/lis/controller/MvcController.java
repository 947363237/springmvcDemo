package com.lis.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Param;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lis.model.Person;
import com.lis.model.User;

@Controller
@RequestMapping("/mvc")
public class MvcController {

    @Resource
    HttpServletRequest request;
    
    //1,基础
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
    
    //2,自动匹配参数
    //http://localhost/springmvcDemo/mvc/person?name=lis&age=100
    @RequestMapping("/person")
    public String toPerson(String name,double age){
        System.out.println(name+" "+age);
        return "hello";
    }
    
    //3,自动装箱
    //http://localhost/springmvcDemo/mvc/person1?name=lis2&age=23
    @RequestMapping("/person1")
    public String toPerson(Person p){
        System.out.println(p);
        return "hello";
    }
    
    //4,使用InitBinder来处理Date类型的参数，将传递过来的字符串自动包装成日期
    //http://localhost/springmvcDemo/mvc/date?date=2018-11-11
    @RequestMapping("/date") 
    public String date(Date date){
        System.out.println(date);
        return "hello";
    }
    //4.1 @InitBinder 字符串转日期类型
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                true));
    }
    
    //5,向前台传递参数
    //http://localhost/springmvcDemo/mvc/show
    @RequestMapping("/show")
    public String showPerson(Map<String,Object> map){
        Person p =new Person();
        map.put("data", p);
        p.setAge(20);
        p.setName("jayjay");
        return "show";
    }
    
    //6,Ajax调用方式：PrintWriter
    //http://localhost/springmvcDemo/mvc/toAjax 浏览器看效果
    @RequestMapping("/toAjax")
    public String toAjax(){
        return "ajaxPage";
    }
    //6.2,PrintWriter直接写
    @RequestMapping("/getPerson")
    public void getPerson(PrintWriter pw,String name){
        pw.write("hello,"+name);        
    }
    
    //7,重定向
    //http://localhost/springmvcDemo/mvc/redirect
    @RequestMapping("/redirect")
    public String redirect(){
        return "redirect:hello";
    }
    
    //8,文件上传
    //http://localhost/springmvcDemo/mvc/toAjax
    @RequestMapping(value="/upload",method=RequestMethod.POST)
    public void upload(HttpServletRequest req,PrintWriter pw) throws Exception{
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");        
        File dirFile = new File(req.getSession().getServletContext().getRealPath("/")+"upload");
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        fileName = sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.'));
        String filePath = dirFile.toPath()+File.separator+fileName;
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        pw.write("upload complete position: 发发发"+filePath);
    }
    
    //9,使用@RequestParam注解指定参数的name
    //http://localhost/springmvcDemo/mvc/param
    @RequestMapping(value="/param")
    public String testRequestParam(
            @RequestParam(value="id",defaultValue="3") Integer id,
            @RequestParam(value="name",required=false)String name){
        System.out.println(id+" "+name);
        return "hello";
    }    
    
    /**
     * 10,RedirectAttributes 的用法，【这个很好用】
     * 参考文献：
     *      http://www.cnblogs.com/youngjoy/p/3919656.html
     *      http://bbs.csdn.net/topics/391034118?page=1
     * @param attributes
     * @return
     */
    //http://localhost/springmvcDemo/mvc/redirectController
    @RequestMapping("/redirectController")
    public String redirectController(RedirectAttributes attributes){
        //addAttribute想当时，重定向直接将参数拼接到url后方，存在弊端，直接暴露，还有肯能乱码，参数长度有限制，可能缓存
        attributes.addAttribute("name","张三");        
        /*
         * attributes.addFlashAttribute 的用法:
         * 如果重定向另一个 ctrol，用 @ModelAttribute("gender") String gender 这种形式获取，注意名字，和接受的参数类型
         * 如果是jsp页面,用el表达式直接获取：${gender},${age},${user}
         * 原理是放到session中，session在跳到页面后马上移除对象。所以你刷新一下后这个值就会丢掉。
         * */
        attributes.addFlashAttribute("gender", "男"); 
        attributes.addFlashAttribute("age", "30");
        attributes.addFlashAttribute("user", new User("张三", new Date(), "男", 30));
        
        return "redirect:/mvc/getRedirectAttributes"; //这里也可以直接跳jsp页面
    }
    @RequestMapping("/getRedirectAttributes")
    public String hello(@ModelAttribute("gender") String gender,@ModelAttribute("age") String age,
            @ModelAttribute("user") User user/*,RedirectAttributes attributes*/){ //注意：使用@ModelAttribute 的bean对象，需要提供默认构造函数
        //Map<String, ?> flashAttributes = attributes.getFlashAttributes(); //! 不行,获取后，size为0
        System.out.println("name="+request.getParameter("name"));
        System.out.println("gender="+gender+", age="+age+" ");
        System.out.println("user="+user);
        return "hello";
    }
    
    //11,转发到ctrol
    //http://localhost/springmvcDemo/mvc/forwardController
    @RequestMapping("/forwardController")
    public String forwardController(RedirectAttributes attributes){
        request.setAttribute("user", new User("张三", new Date(), "男", 30));
        return "forward:/mvc/forwardTest";
    }
    @RequestMapping("/forwardTest")
    public String forwardTest(){
        User user = (User) request.getAttribute("user");
        System.out.println(user);
        return "hello";
    }
}
