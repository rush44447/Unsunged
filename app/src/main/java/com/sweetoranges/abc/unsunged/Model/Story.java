package com.sweetoranges.abc.unsunged.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Story{
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("albumName")
        @Expose
        private String albumName;
        @SerializedName("thumbnail")
        @Expose
        private int thumbnail;
        @SerializedName("uploadedAt")
        @Expose
        private String uploadedAt;
        @SerializedName("viewed")
        @Expose
        private boolean viewed;
        @SerializedName("priority")
        @Expose
        private int priority;
        @SerializedName("repeatedViews")//how many times story has been played
        @Expose
        private int repeatedViews;
        @SerializedName("successful")//did it drive useer to the song
        @Expose
        private boolean successful;
        @SerializedName("songType")//if succesful what is the song type whch drives user
        @Expose
        private int songType;

    public Story(Integer id,String username,String albumName,Integer thumbnail,String uploadedAt,Boolean viewed,Integer priority,Integer repeatedViews,Boolean successful,Integer songType){
        this.id=id;
        this.username=username;
        this.albumName=albumName;
        this.thumbnail=thumbnail;
        this.uploadedAt=uploadedAt;
        this.viewed=viewed;
        this.priority=priority;
        this.repeatedViews=repeatedViews;
        this.successful=successful;
        this.songType=songType;
    }

    public int getId() {
            return id;
        }
    public void setId(int id) {
            this.id = id;
        }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getSongname() { return albumName; }
    public void setSongname(String songname) { this.albumName = songname; }

    public Integer getThumbnail() { return thumbnail; }
    public void setThumbnail(Integer thumbnail) { this.thumbnail = thumbnail; }

    public String getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(String uploadedAt) { this.uploadedAt = uploadedAt; }

    public Boolean getViewed() { return viewed; }
    public void setViewed(Boolean viewed) { this.viewed = viewed; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }

    public Integer getRepeatedView() { return repeatedViews; }
    public void setRepeatedViews(Integer repeatedViews) { this.repeatedViews = repeatedViews; }

    public Boolean getSuccessful() { return successful; }
    public void setSuccessful(Boolean successful) { this.successful = successful; }

    public Integer getSongType() { return songType; }
    public void setSongType(Integer songType) { this.songType = songType; }
}
