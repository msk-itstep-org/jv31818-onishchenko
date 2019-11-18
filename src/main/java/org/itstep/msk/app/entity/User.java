package org.itstep.msk.app.entity;

import org.hibernate.validator.constraints.UniqueElements;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@OneToMany
    private Integer id;

    @Column(name = "username")
    @UniqueElements
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "enabled")
    private Boolean enabledd;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name="id_user",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_role",referencedColumnName = "id")}
    )
    private Set<Role> userRole;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getEnabledd() {
        return enabledd;
    }

    public void setEnabledd(Boolean enabledd) {
        this.enabledd = enabledd;
    }

    public Set<Role> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<Role> userRole) {
        this.userRole = userRole;
    }
}
