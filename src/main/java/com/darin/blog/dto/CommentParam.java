package com.darin.blog.dto;

public class CommentParam {

    private String nickname;
    private String content;
    private String avatar;
    private Long blogId;

    public CommentParam(){}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "CommentParam{" +
                "nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", blogId=" + blogId +
                '}';
    }
}
