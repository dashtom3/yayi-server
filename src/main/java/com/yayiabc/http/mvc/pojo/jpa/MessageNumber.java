package com.yayiabc.http.mvc.pojo.jpa;

/**
 * 获取个人消息中心的消息数量，问答信息TODO
 */
public class MessageNumber {

    private Integer commentNumber;//评论消息

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public MessageNumber() {
    }

    public MessageNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    @Override
    public String toString() {
        return "MessageNumber{" +
                "commentNumber=" + commentNumber +
                '}';
    }
}
