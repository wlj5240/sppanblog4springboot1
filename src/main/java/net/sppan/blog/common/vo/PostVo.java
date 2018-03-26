package net.sppan.blog.common.vo;

import net.sppan.blog.entity.Blog;

public class PostVo extends Blog {
    private Long prePostId;
    private String prePostTitle;

    private Long nextPostId;
    private String nextPostTitle;

    public Long getPrePostId() {
        return prePostId;
    }

    public void setPrePostId(Long prePostId) {
        this.prePostId = prePostId;
    }

    public String getPrePostTitle() {
        return prePostTitle;
    }

    public void setPrePostTitle(String prePostTitle) {
        this.prePostTitle = prePostTitle;
    }

    public Long getNextPostId() {
        return nextPostId;
    }

    public void setNextPostId(Long nextPostId) {
        this.nextPostId = nextPostId;
    }

    public String getNextPostTitle() {
        return nextPostTitle;
    }

    public void setNextPostTitle(String nextPostTitle) {
        this.nextPostTitle = nextPostTitle;
    }
}
