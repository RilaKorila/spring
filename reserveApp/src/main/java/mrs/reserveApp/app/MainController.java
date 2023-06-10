package mrs.reserveApp.app;

//import mrs.reserveApp.domain.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

//    @Autowired
//    UserRepository userRepository;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav){
        mav.setViewName("index");
        return mav;
    }
}
