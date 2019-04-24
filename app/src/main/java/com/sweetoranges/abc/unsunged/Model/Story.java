package com.sweetoranges.abc.unsunged.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by frank on 12/13/16.
 */

public class Story {
    public String imagePath = "https://carhubstorage.blob.core.windows.net/cloud991/";

    @SerializedName("profileID")
    @Expose
    private Integer profileID;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("UploadedAt")
    @Expose
    private String UploadedAt;
//    @SerializedName("Actors")
//    @Expose
//    private String actors;
//    @SerializedName("Plot")
//    @Expose
//    private String plot;
//    @SerializedName("Director")
//    @Expose
//    private String director;


//
//    @SerializedName("Cover")
//    @Expose
//    private String cover;
    public Story() {
    }
    public Story(Integer profileID, String username, String UploadedAt) {
        super();
        this.profileID = profileID;
        this.username = username;
        this.UploadedAt = UploadedAt;
//        this.actors = actors;
//        this.plot = plot;
//        this.director = director;




       // this.cover = cover;
    }

    /**
     *
     * @return
     * The movieId
     */
    public Integer getProfileID() {
        return profileID;
    }
    public void setProfileID(Integer profileID) {
        this.profileID = profileID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUploadedAt() {
        return UploadedAt;
    }
    public void setUploadedAt(String UploadedAt) {
        this.UploadedAt = UploadedAt;
    }

//
//    public String getActors() {
//        return actors;
//    }
//
//    /**
//     *
//     * @param actors
//     * The Actors
//     */
//    public void setActors(String actors) {
//        this.actors = actors;
//    }
//
//    /**
//     *
//     * @return
//     * The plot
//     */
//    public String getPlot() {
//        return plot;
//    }
//
//    /**
//     *
//     * @param plot
//     * The Plot
//     */
//    public void setPlot(String plot) {
//        this.plot = plot;
//    }
//
//    /**
//     *
//     * @return
//     * The director
//     */
//    public String getDirector() {
//        return director;
//    }
//
//    /**
//     *
//     * @param director
//     * The Director
//     */
//    public void setDirector(String director) {
//        this.director = director;
//    }
//
//    /**
//     *
//     * @return
//     * The cover
//     */




//    public String getCover() {
//        return imagePath + cover;
//        // return "https://carhubstorage.blob.core.windows.net/cloud991/cobra.jpg";
//    }
//    public void setCover(String cover) {
//        this.cover = cover;
//    }
}

