package ryudongjae.freeboard.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ryudongjae.freeboard.api.user.UserForm;
import ryudongjae.freeboard.domain.user.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/board")
    public String board(HttpSession httpSession, Model model) {
        UserForm loginUser = (UserForm) httpSession.getAttribute("USER");
        if(loginUser != null ) {
            model.addAttribute("accountId", loginUser.getAccountId());
            model.addAttribute("accountRole" , userService.findUserRole(loginUser));
        }
        return "board";
    }

    @GetMapping("/join")
    public String join() { return "join"; }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("USER");
        return "index";
    }

}
