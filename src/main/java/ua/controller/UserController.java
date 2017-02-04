package ua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ua.entity.User;
import ua.service.UserService;
import ua.validator.UserValidator;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ua.util.ParamBuilder.buildParams;

/**
 * Created by Admin on 1/24/2017.
 */
@Controller
@RequestMapping("/admin/user")
@SessionAttributes(names="user")
public class UserController {
    @Autowired
    private UserService userService;
    @ModelAttribute("user")
    public User getForm(){
        return new User();
    }
    @InitBinder("user")
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new UserValidator(userService));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "createdDate", new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(false));
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable){
        model.addAttribute("users",userService.findAll(pageable));

        return "admin/user";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable){
        userService.delete(id);
        return "redirect:/admin/user"+buildParams(pageable);
    }

    @RequestMapping(method=POST)
    public String save(@ModelAttribute("user") @Valid User user, BindingResult br,@RequestParam String action, SessionStatus status,Model model, @PageableDefault Pageable pageable){
        switch(action.toLowerCase()){
            case "save":
                if(br.hasErrors()){
                    model.addAttribute("users",userService.findAll(pageable));
                    return "admin/user";
                }
                userService.save(user);
                status.setComplete();
                return "redirect:/admin/user";

                case "search":
                 User founded =userService.findByName(user.getName());
                    if (founded==null){
                        return "redirect:/admin/user"+buildParams(pageable);
                    }
                model.addAttribute("user",founded);
                return "redirect:/admin/user"+buildParams(pageable);
        }
        return "redirect:/admin/user"+buildParams(pageable);

    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id,Model model, @PageableDefault Pageable pageable){
        model.addAttribute("user",userService.findOne(id));
        model.addAttribute("users", userService.findAll(pageable));
        return "admin/user";
    }

    @RequestMapping("/cancel")
    public String cancel(SessionStatus status, @PageableDefault Pageable pageable){
        status.setComplete();
        return "redirect:/admin/user"+buildParams(pageable);
    }
    @RequestMapping("/search")
    public String search(SessionStatus status, @PageableDefault Pageable pageable){
        status.setComplete();
        return "redirect:/admin/findUser"+buildParams(pageable);
    }
}
