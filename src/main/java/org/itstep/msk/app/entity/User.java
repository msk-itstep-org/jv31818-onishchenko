package org.itstep.msk.app.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@OneToMany
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "enabled")
    private Boolean enabledd;


    @OneToMany(fetch = FetchType.LAZY,targetEntity = org.itstep.msk.app.entity.userRole.class,mappedBy = "user")
    private Set<userRole> userRole = new HashSet<userRole>(0);

    public Boolean getEnabledd() {
        return enabledd;
    }

    public void setEnabledd(Boolean enabledd) {
        this.enabledd = enabledd;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<org.itstep.msk.app.entity.userRole> getUserRole() {
        return userRole;
    }

//    public void setUserRole(Set<org.itstep.msk.app.entity.userRole> userRole) {
//        this.userRole = userRole;
//    }
}
