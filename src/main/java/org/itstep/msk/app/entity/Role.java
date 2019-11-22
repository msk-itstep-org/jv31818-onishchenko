package org.itstep.msk.app.entity;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Column(name = "id")@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role")
    private String role;

    public Role(String role_user) {
        this.role=role_user;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
