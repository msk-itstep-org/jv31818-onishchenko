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
