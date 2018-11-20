package cn.com.scitc.movie.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film", schema = "movie", catalog = "")
public class FilmEntity {
    private int id;
    private String title;
    private String subtitle;
    private String href;
    private String cover;
    private String info;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Basic
    @Column(name = "href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(subtitle, that.subtitle) &&
                Objects.equals(href, that.href) &&
                Objects.equals(cover, that.cover) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subtitle, href, cover, info);
    }
}
