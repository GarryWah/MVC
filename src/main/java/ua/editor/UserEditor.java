package ua.editor;

import ua.entity.User;
import ua.service.UserService;

import java.beans.PropertyEditorSupport;

/**
 * Created by Admin on 2/2/2017.
 */
public class UserEditor extends PropertyEditorSupport {
    private final UserService service;

    public UserEditor(UserService service) {
        this.service = service;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        User user = service.findOne(Integer.valueOf(text));
        setValue(user);

    }
}
