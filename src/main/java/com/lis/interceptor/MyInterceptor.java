package com.lis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *<p> Title: MyInterceptor </p>
 *<p> Description:设置一个自定义拦截器 , 需要在springmvc-servlet.xml文件做相应配置</p>
 *
 * @author lis
 * @since 2017年3月14日
 */

//实现HandlerInterceptor接口
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2) throws Exception {
        System.out.println("前置preHandle");
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
        System.out.println("后置postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
                    throws Exception {
        System.out.println("最终afterCompletion");
    }
}
