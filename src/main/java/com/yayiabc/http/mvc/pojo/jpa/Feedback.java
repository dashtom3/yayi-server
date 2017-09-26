package com.yayiabc.http.mvc.pojo.jpa;
/**
 * 意见反馈表
 * @author xiaojiang
 *
 */
public class Feedback extends BasePojo{
    private int feedId;
    private String phone;
    private String message;
    private int state;

    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedId=" + feedId +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                ", state=" + state +
                '}';
    }
}
