package com.praise.Haneasy.Screens.Forum.PostAdapter;

public class PostEntities {
    int id;
    String userName ;
    String postDesc;
    String postDate;

    public PostEntities(int id, String userName, String postDesc, String postDate) {
        this.id = id;
        this.userName = userName;
        this.postDesc = postDesc;
        this.postDate = postDate;
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

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
