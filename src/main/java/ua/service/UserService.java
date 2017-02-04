package ua.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 1/21/2017.
 */
public interface UserService {
    List<User> findAll();
    void delete(int id);
    User findOne(int id);
    void save(User user);
    User findByName(String name);
    User findByAge(Integer age);
    User findByCreatedDate(Date date);
    Page<User> findAll(Pageable pageable);
}
