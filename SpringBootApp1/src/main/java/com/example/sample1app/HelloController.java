package com.example.sample1app;

import com.example.sample1app.repositories.MessageRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import com.example.sample1app.repositories.PersonRepository;

import jakarta.transaction.Transactional;


// Controllerを使うと、テンプレートエンジンをレンダリングして表示
@Controller
public class HelloController {
    private boolean flag = false;

    @Autowired // フィールドインジェクション
    PersonRepository repository;

    @Autowired
    MessageRepository messageRepository;

    // Beanとして登録したからAutowiredでインスタンスが作れる
    @Autowired
    Post post;

    @Autowired
    SampleComponent sampleComponent;

    @Autowired
    SampleService service;

    @Autowired
    PersonDAOPersonImpl dao;

     // 初期データを生成
    @PostConstruct
    public void init() {
        // ダミーデータ1
        Person p1 = new Person();
        p1.setName("taro");
        p1.setAge(39);
        p1.setMail("taro@gmail.com");
        Message msg1 = new Message();
        msg1.setDatetime(Calendar.getInstance().getTime());
        msg1.setContent("ハロー");
        msg1.setPerson(p1);
        repository.saveAndFlush(p1);
        messageRepository.saveAndFlush(msg1);

        // ダミーデータ2
        Person p2 = new Person();
        p2.setName("hanako");
        p2.setAge(28);
        p2.setMail("hanako@gmail.com");
        repository.saveAndFlush(p2);

        // ダミーデータ3
        Person p3 = new Person();
        p3.setName("sachie");
        p3.setAge(39);
        p3.setMail("sachie@gmail.com");
        repository.saveAndFlush(p3);
    }

    @RequestMapping(value="/find", method = RequestMethod.GET)
    public ModelAndView find(ModelAndView mav){
        mav.setViewName("find");
        mav.addObject("title", "findAll");
        mav.addObject("msg", "データベースの内容を全件表示");

        Iterable<Person> list = dao.getAll();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value="/find", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest req, ModelAndView mav){
        mav.setViewName("find");
        String param = req.getParameter("find_str");

        if (param == "") {
                return new ModelAndView("redirect:/find");
        }

        mav.addObject("title", "検索結果");
        mav.addObject("msg", "[ " + param + " ]の検索結果");

        Iterable<Person> list = new ArrayList<Person>();
        if (param.contains(",")){
            String[] params = param.split(",");
            try {
                list = dao.findByAge(
                        Integer.parseInt(params[0]),
                        Integer.parseInt(params[1])
                );
            }catch(NumberFormatException e){
                e.printStackTrace();
            }

            mav.addObject("data", list);
            return mav;
        }

        // chap 5-2
        list = dao.find(param);
        mav.addObject("data", list);
        return mav;


        // chap 5-1
//
//        else{
//            mav.addObject("title", "検索結果");
//            mav.addObject("msg", "[ " + param + " ]の検索結果");
//            mav.addObject("value", param);
//
//            Pattern pattern = Pattern.compile("[0-9]*");
//
//            if(pattern.matcher(param).matches()){
//                // queryが数字
//                final long id = Integer.parseInt(param);
//                Person data = dao.findById(id);
//                Person[] list = new Person[] {data};
//                mav.addObject("data", list);
//            }else{
//                // queryが文字列
//                Iterable<Person> list = dao.findByName(param);
//                mav.addObject("data", list);
//            }
//        }
        // (TODO) 検索結果、該当データがない時のエラーハンドリング
    }

    @RequestMapping("/page/{page}")
    public ModelAndView page(ModelAndView mav, @PathVariable int page){
        final int DATA_PER_PAGE = 2;
        mav.setViewName("find");
        mav.addObject("msg", "Personを" + DATA_PER_PAGE + "件ずつ表示するよ");

        Iterable<Person> list = dao.getPage(page, DATA_PER_PAGE);
        mav.addObject("data", list);

        return mav;
    }

    @RequestMapping(value = "/crud", method = RequestMethod.GET)
    public ModelAndView crud(@ModelAttribute("formModel") Person Person,
                             ModelAndView mav){
        mav.setViewName("crud");
        mav.addObject("title", "データベースの情報を表示するよ");
        mav.addObject("msg", "入力情報はDBに保存されるよ. ちゃんと入力してね");

        // Person Repositoryを利用する
        Iterable<Person> list = repository.findAllOrderByName();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value="/crud", method=RequestMethod.POST)
    @Transactional
    public ModelAndView form(@ModelAttribute("formModel") @Validated Person Person,
                             BindingResult result,
                             ModelAndView mav){
        ModelAndView res = null;
        System.out.println(result.getFieldErrors());

        if (!result.hasErrors()){
            // validation結果にエラーがなければ、DBを更新しリダイレクト
            repository.saveAndFlush(Person);
            res = new ModelAndView("redirect:/crud");
        }else{
            mav.setViewName("crud");
            mav.addObject("title", "データベースの情報を表示するよ");
            mav.addObject("msg", "formの形式が変だよ");
            Iterable<Person> personList = repository.findAll();
            mav.addObject("data", personList);
            res = mav;
        }
        return res;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        flag = !flag;
        String[] names = new String[] {"One", "Two", "Three"};

        mav.setViewName("index");
        mav.addObject("msg", "Hello,  ");
        mav.addObject("flag", flag);
        mav.addObject("names", names);

        // Person Repositoryを利用する
         Iterable<Person> list = repository.findAllOrderByName();
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

    // 指定したIDのオブジェクトの値が、formに入力された状態(変更可能状態)で表示される
    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute Person Person, @PathVariable int id, ModelAndView mav) {
        mav.setViewName("edit");
        mav.addObject("title", "Edit Person");
        mav.addObject("msg", "Please edit Person data");

        Optional<Person> data = repository.findById((long)id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    // 編集後、送信ボタンが押されたらPOSTメソッドが呼ばれる
    // DBをアップデートする
    @RequestMapping(value="/edit", method=RequestMethod.POST)
    @Transactional
    public ModelAndView update(@ModelAttribute Person Person, ModelAndView mav) {
        repository.saveAndFlush(Person);
        return new ModelAndView("redirect:/");
    }

    // 指定したIDのオブジェクトの値を表示する
    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView delete(@ModelAttribute Person Person, @PathVariable int id, ModelAndView mav) {
        mav.setViewName("delete");
        mav.addObject("title", "Delete Person");
        mav.addObject("msg", "Can I delete this person data??");

        Optional<Person> data = repository.findById((long)id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    // 編集後、送信ボタンが押されたらPOSTメソッドが呼ばれる
    // DBをアップデートする
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    @Transactional
    public ModelAndView remove(@RequestParam long id, ModelAndView mav) {
        repository.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView form(
            @ModelAttribute("formModel") @Validated Person person,
            BindingResult result,
            @RequestParam("text1") String name,
            @RequestParam(value="check", required=false) boolean isHidden,
            @RequestParam("flowers") String flower,
            ModelAndView mav)
    {
        ModelAndView res = null;
        System.out.println(result.getFieldErrors());
        if(!result.hasErrors()){
            // formにエラーが含まれていなければ、DBを更新しリダイレクト
            repository.saveAndFlush(person);
            res = new ModelAndView("redirect:/");
        }else{
            mav.setViewName("index");
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
            res = mav;
        }

        return res;
    }

    @RequestMapping("/{num}")
    public String index(@PathVariable int num, Model model) {
        final String msg = "あなたの点数は .... " + num + "点です！！👏";
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping("/greeting/{name}")
    public String index(@PathVariable String name, Model model) {
        final String msg = "Hello,  " + name + "-san🌷";
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping("/bean")
    public ModelAndView bean(ModelAndView mav){
        mav.setViewName("bean");
        mav.addObject("title", "Bean sample");
        mav.addObject("msg", post);
        mav.addObject("msg2", sampleComponent.message());
        mav.addObject("data", service.getAllPosts());
        // dummy data
        // mav.addObject("data", new Post[]{service.getDummyPost()});

        return mav;
    }

    @RequestMapping(value = "/bean", method = RequestMethod.POST)
    public ModelAndView bean(HttpServletRequest request, ModelAndView mav){
        String param = request.getParameter("find_str");
        mav.setViewName("bean");
        mav.addObject("title", "Bean sample");
        mav.addObject("msg", param);
        Post post = service.getAndSavePost(Integer.parseInt(param));
        mav.addObject("data", new Post[]{post});
        return mav;
    }

    @RequestMapping("/local")
    public ModelAndView localData(ModelAndView mav){
        mav.setViewName("bean");
        mav.addObject("title", "Local Data");
        mav.addObject("msg", "repositoryを使ってlocalデータをひっぱるよ");
        mav.addObject("local", true);
        mav.addObject("data", service.getLocalPosts());
        return mav;
    }
    // @RequestMapping("/{temp}")
    // public String index(@PathVariable String temp) {
    //     return temp.equals("index") ? "index" : "other";
    // }
}
