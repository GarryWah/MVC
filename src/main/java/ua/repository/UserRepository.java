package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.entity.User;

import java.util.Date;


/**
 * Created by Admin on 1/21/2017.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);
    User findByName(String name);
    User findByAge(Integer age);
    User findByCreatedDate(Date date);
}
