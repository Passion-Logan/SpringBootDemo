package cn.com.scitc.movie.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "movie", catalog = "")
public class CommentEntity {
    private int id;
    private Integer filmId;
    private String content;
    private Integer rate;
    private Integer memberId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "film_id")
    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "rate")
    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return id == that.id &&
                Objects.equals(filmId, that.filmId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filmId, content, rate);
    }

    @Basic
    @Column(name = "member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
