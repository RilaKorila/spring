package mrs.reserveApp.app;

import mrs.reserveApp.domain.model.RoleName;
import mrs.reserveApp.domain.model.User;
import mrs.reserveApp.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.Role;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav){
        mav.setViewName("index");

        List<User> users = userRepository.findAll();
        mav.addObject("users", users);

        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(ModelAndView mav){
        mav.setViewName("add");
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam String firstName, @RequestParam String lastName, ModelAndView mav){
        User user = new User(0, firstName, lastName, "password", RoleName.USER);
        userRepository.saveAndFlush(user);
        return new ModelAndView("redirect:/");
    }
}
