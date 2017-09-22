package ru.demianko.sdc.service;


import ru.demianko.sdc.UserTo;
import ru.demianko.sdc.model.User;
import org.springframework.security.acls.model.NotFoundException;


public interface UserService {

    User save(User user);


    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

/*    void update(User user);*/

/*    void update(UserTo user);*/

}

