package ru.demianko.sdc.service;

import ru.demianko.sdc.LoggedUser;
import ru.demianko.sdc.UserTo;
import ru.demianko.sdc.model.User;
import ru.demianko.sdc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.demianko.sdc.util.PasswordUtil;


import java.util.Objects;


@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }


   @Override
    public User get(int id) throws NotFoundException {
       User user = repository.get(id);
       if(user ==null) throw new NotFoundException("Not found entity with " + id);
        return user;
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Objects.requireNonNull(email, "Email must not be empty");
        User user = repository.getByEmail(email);
        if(user ==null) throw new NotFoundException("Not found entity with " + email);
        return user;
    }

/*    @Override
    public void update(User user) {
        repository.save(user);
    }*/

/*    @Override
    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        repository.save(user);
    }*/



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new LoggedUser(u);
    }
}
