package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;


// Controllerを使うと、テンプレートエンジンをレンダリングして表示
@Controller
public class HelloController {
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "これはコントローラーが用意したメッセージです");
        return "index";
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
