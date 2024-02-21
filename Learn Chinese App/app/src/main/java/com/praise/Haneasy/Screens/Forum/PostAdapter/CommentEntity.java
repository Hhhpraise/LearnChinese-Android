package com.praise.Haneasy.Screens.Forum.PostAdapter;

public class CommentEntity {
    int id;
    String userName ;
    String commentTxt;
    String commentDate;

    public CommentEntity(int id, String userName, String commentTxt, String commentDate) {
        this.id = id;
        this.userName = userName;
        this.commentTxt = commentTxt;
        this.commentDate = commentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentTxt() {
        return commentTxt;
    }

    public void setCommentTxt(String commentTxt) {
        this.commentTxt = commentTxt;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}
