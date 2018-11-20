package cn.com.scitc.movie.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "member", schema = "movie", catalog = "")
public class MemberEntity {
    private int id;
    private String account;
    private String password;
    private String nickname;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberEntity that = (MemberEntity) o;
        return id == that.id &&
                Objects.equals(account, that.account) &&
                Objects.equals(password, that.password) &&
                Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, password, nickname);
    }
}
