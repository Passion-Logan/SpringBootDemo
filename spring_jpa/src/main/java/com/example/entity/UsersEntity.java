package com.example.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "test")
public class UsersEntity {
    private int uId;
    private String uUsername;
    private String uPassword;

    @Id
    @Column(name = "u_id")
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "u_username")
    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    @Basic
    @Column(name = "u_password")
    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsersEntity that = (UsersEntity) o;
        return uId == that.uId &&
                Objects.equals(uUsername, that.uUsername) &&
                Objects.equals(uPassword, that.uPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uId, uUsername, uPassword);
    }
}
