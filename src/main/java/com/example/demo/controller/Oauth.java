package com.example.demo.controller;
import com.example.demo.Util;
import com.example.demo.pojo.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Oauth {

    public static Map usermap = new HashMap<String,UserInfo>();
    public static Map codemap = new HashMap<String,String>();


    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        return "login";
    }

    /**
     * 登录验证服务
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping("/logins")
    public void  login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
         String name = request.getParameter("name");
         String password = request.getParameter("password");
         System.out.println(name + "+++" + password);
     //   boolean result = name.equals("") && password.equals("") ? true : false;
      //  System.out.println(name + "+++" + password);
//        if (result) {
//            return ;
//        } else {
            //modify begin
//            request.setAttribute("name", name);
//            request.getRequestDispatcher("/oauth1").forward(request, response);
            //modify end
            UserInfo userInfo = new UserInfo();
            userInfo.setName(password);
            String  code = Util.makeCode(password);
            usermap.put(code,userInfo);
            String redirect_url = request.getParameter("redirect_url");
            String state = request.getParameter("state");
            System.out.println( "url:" + redirect_url);
            System.out.println("code:"+code);
            System.out.println("state:"+state);
            state  = StringUtils.substringBefore(state, "?");
            response.sendRedirect(redirect_url+"&code="+code+"&state="+state);


   //     }

    }

    /**
     * 生成code
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/oauth1")
    public String oauth1(@RequestParam String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        String  code = Util.makeCode(name);
        usermap.put(code,userInfo);
        return code;
    }

    /**
     * 通过code换取access_token
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/oauth2")
    public void oauth2(@RequestParam String code,HttpServletResponse response) throws JSONException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String access_token =Util.makeAccess_token(code);
        System.out.println("进入oauth2！！");
        codemap.put(access_token,code);
        System.out.println(access_token);
        Map map = new HashMap();
        map.put("access_token",access_token);
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject(map);
        out.println(json);

    }

    /**
     * 通过access_token 换取user
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/oauth3")
    public void oauth3(@RequestParam String access_token, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String code  = (String)codemap.get(access_token);
        UserInfo userInfo = (UserInfo) usermap.get(code);
        Map map = new HashMap();
        map.put("name", userInfo.getName());
        map.put("userid", userInfo.getName());
        PrintWriter out = response.getWriter();
        System.out.println("进入oauth3！！");

        JSONObject json = new JSONObject(map);
        out.println(json);

    }


}
