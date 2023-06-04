package com.example.samplesecurityapp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleSecurityController {

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav){
        mav.setViewName("index");
        mav.addObject("title", "みんなのページ");
        mav.addObject("msg", "ここはみんな見れるよー");
        return mav;
    }

    @RequestMapping("/secret")
    public ModelAndView secret(ModelAndView mav, HttpServletRequest request){
        String user = request.getRemoteUser();

        mav.setViewName("secret");
        mav.addObject("title", "ひみつのページ");
        mav.addObject("msg", user + "だけの秘密！内緒だよ！");
        return mav;
    }
}
