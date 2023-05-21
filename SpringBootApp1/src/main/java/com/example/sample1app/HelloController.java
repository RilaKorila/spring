package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


// Controllerを使うと、テンプレートエンジンをレンダリングして表示
@Controller
public class HelloController {
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.addObject("msg", "Hello,  ");
        mav.setViewName("index");
        // model.addAttribute("msg", "これはコントローラーが用意したメッセージです");
        return mav;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView form(
        @RequestParam("text1") String name, 
        @RequestParam(value="check", required=false) boolean isHidden,
        @RequestParam("flowers") String flower,
        ModelAndView mav) 
        {
            try {
                if (isHidden){
                    mav.addObject("msg", "Hello,  *********-san" + flower);
                }
                else{
                    mav.addObject("msg", "Hello,  " + name + "-san" + flower);
                }
            } catch (NullPointerException e) {
                //TODO: handle exception
                mav.addObject("nullらしい...");
            }
        mav.addObject("value", name);
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/{num}")
    public String index(@PathVariable int num, Model model) {
        final String msg = "あなたの点数は .... " + String.valueOf(num) + "点です！！👏";
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping("/greeting/{name}")
    public String index(@PathVariable String name, Model model) {
        final String msg = "Hello,  " + name + "-san🌷";
        model.addAttribute("msg", msg);
        return "index";
    }

    // @RequestMapping("/{temp}")
    // public String index(@PathVariable String temp) {
    //     return temp.equals("index") ? "index" : "other";
    // }
}
