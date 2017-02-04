package ua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.entity.User;
import ua.service.UserService;

import java.util.regex.Pattern;

/**
 * Created by Admin on 2/2/2017.
 */
public class UserValidator implements Validator {
    private final UserService userService;
    private final static Pattern PATTERN =Pattern.compile("^[^\\d]+$");

    public UserValidator(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user=(User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","","Can't be empty!");

        if (!user.getName().equals("")&&!PATTERN.matcher(user.getName()).matches()){
            errors.rejectValue("name","","Name not include digits!");
        }
       if(user.getId()==null){
           if(userService.findByName(user.getName())!=null){
               errors.rejectValue("name","","User with this name already exists!");
           }
       }
/*       if(user.getName()!=null&&user.getAge()==null&&user.getIsAdmin()==null&&user.getCreatedDate()==null){
           if(userService.findByName(user.getName())==null){
               errors.rejectValue("name","","User with this name not already exists!");
           }
       }*/
    }
}
