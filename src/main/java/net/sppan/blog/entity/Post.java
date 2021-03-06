package net.sppan.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import net.sppan.blog.common.Constat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_blog")
public class Post implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3578273403164774283L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Lob
    private String content;

    @JsonFormat(pattern = Constat.DATETIME_FORMAT, timezone = "GMT+8")
    private Date createAt;

    /**
     * 推荐
     */
    private Integer featured;

    /**
     * 权限级别<br>
     * 0 公开 1私密
     */
    private Integer privacy;

    /**
     * 状态 暂时不启用
     */
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(length = 500)
    private String summary;

    private String tags;

    private String title;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer views;

    /**
     * 点赞次数
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer upVote;

    /**
     * 点踩次数
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer downVote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getFeatured() {
        return featured;
    }

    public void setFeatured(Integer featured) {
        this.featured = featured;
    }

    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getUpVote() {
        return upVote;
    }

    public void setUpVote(Integer upVote) {
        this.upVote = upVote;
    }

    public Integer getDownVote() {
        return downVote;
    }

    public void setDownVote(Integer downVote) {
        this.downVote = downVote;
    }
}
