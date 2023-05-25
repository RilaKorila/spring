package com.example.sample1app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import main.java.com.example.sample1app.repositories.PersonRepository;
import main.java.com.example.sample1app.Person;

import jakarta.transaction.Transactional;
import javax.annotation.PostConstruct;


// Controllerを使うと、テンプレートエンジンをレンダリングして表示
@Controller
public class HelloController {
    private boolean flag = false;

    @Autowired
    PersonRepository repository;

     // 初期データを生成
    @PostConstruct
    public void init() {
        // ダミーデータ1
        Person p1 = new Person();
        p1.setName("taro");
        p1.setAge(39);
        p1.setMail("taro@gmail.com");
        repository.saveAndFlush(p1);

        // ダミーデータ2
        Person p2 = new Person();
        p2.setName("hanako");
        p2.setAge(28);
        p2.setMail("hanako@gmail.com");
        repository.saveAndFlush(p2);

        // ダミーデータ1
        Person p3 = new Person();
        p3.setName("sachie");
        p3.setAge(39);
        p3.setMail("sachie@gmail.com");
        repository.saveAndFlush(p3);
    }

    @RequestMapping(value="/crud", method=RequestMethod.POST)
    @Transactional
    public ModelAndView form(@ModelAttribute("formModel") Person Person, ModelAndView mav){
        repository.saveAndFlush(Person);
        return new ModelAndView("redirect:/crud/");
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        flag = !flag;
        String[] names = new String[] {"One", "Two", "Three"};

        mav.addObject("msg", "Hello,  ");
        mav.addObject("flag", flag);
        mav.addObject("names", names);
        mav.setViewName("index");

        // Person Repositoryを利用する
        Iterable<Person> list = repository.findAll();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping("/other")
    public String other(){
        return "redirect:/";
    }

    @RequestMapping("/home")
    public String home(){
        return "forward:/";
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
