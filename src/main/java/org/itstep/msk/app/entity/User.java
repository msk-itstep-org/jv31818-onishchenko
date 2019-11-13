package org.itstep.msk.app.entity;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements UserDetails, Serializable {

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

    @OneToMany(fetch = FetchType.LAZY,targetEntity = UserRole.class,mappedBy = "user")
    private Set<UserRole> userRole = new HashSet<UserRole>(0);


    public User() {
    }


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





    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getEnabledd();
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
    public void setFirstName(String name) {
        this.firstName = firstName;
    }


    public Set<UserRole> getUserRole() {
        return userRole;
    }
    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
