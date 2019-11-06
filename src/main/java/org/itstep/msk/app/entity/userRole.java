package org.itstep.msk.app.entity;

import javax.persistence.*;

@Entity
@Table(name="userRoles")
public class userRole {
    @Column(name = "id")@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName="id", nullable = false)
    private User user;

    @Column(name = "nameRole")
    private String nameRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getLogin() {
        return user;
    }

    public void setLogin(User user) {
        this.user = user;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }


}
