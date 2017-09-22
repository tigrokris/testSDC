package ru.demianko.sdc.web;

import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestParam;
import ru.demianko.sdc.UserTo;
import ru.demianko.sdc.model.Role;
import ru.demianko.sdc.model.User;
import ru.demianko.sdc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import ru.demianko.sdc.util.PasswordUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller

public class HelloController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:hello";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {

        if (!result.hasErrors()) {
            try {
                User user = new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.ROLE_USER);
                user.setId(null);
                user.setPassword(PasswordUtil.encode(user.getPassword()));
                user.setEmail(user.getEmail().toLowerCase());

                service.save(user);
                status.setComplete();
                model.addAttribute("message1", "You are registered!");

                return "login";

            } catch (DataIntegrityViolationException ex) {
                model.addAttribute("message", "User with this email exist already!");
                result.rejectValue("email", "duplicate email");
                return "error";
            }
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model, @RequestParam(value = "error", required = false) boolean error) {
        if (error) {
            model.addAttribute("message", "Wrong password or Email!");
            return "error";
        }
        model.addAttribute("message1", "For registration please enter your login, email and password(more 5 symbols).");

        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}