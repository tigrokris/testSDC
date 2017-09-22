package ru.demianko.sdc;

import ru.demianko.sdc.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import static java.util.Objects.requireNonNull;

public class LoggedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;
    private UserTo userTo;
    private User user;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        this.userTo = new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());;
        this.user=user;
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().userTo.getId();
    }

    public UserTo getUserTo() {
        return userTo;
    }
/*    public User getUser() {
        return user;
    }*/

    @Override
    public String toString() {
        return userTo.toString();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }
}