package ru.demianko.sdc.model;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;


public class User extends NamedEntity {

    protected String email;

    protected String password;

    protected Set<Role> roles;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(List<Role> roles) {
        this.roles = EnumSet.copyOf(roles);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", roles=" + roles +
                ')';
    }


}