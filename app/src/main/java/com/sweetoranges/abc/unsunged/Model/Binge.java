package com.sweetoranges.abc.unsunged.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binge {
    @SerializedName("id")
    @Expose
    private int id;//universal id
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("coverName")
    @Expose
    private String coverName;// campaign or tour name or movie name
    @SerializedName("albumName")
    @Expose
    private String albumName;// song name
    @SerializedName("thumbnail")    // show as a wallpaper
    @Expose
    private int thumbnail;
    @SerializedName("successful")//did it drive useer to the song from story or recommendations
    @Expose
    private boolean successful;
    @SerializedName("songType")
    @Expose
    private int songType;// jazz, rock.....
    @SerializedName("audio")
    @Expose
    private int audio;// the actual mp3 uncompressed ND BEAUTIFIED
    @SerializedName("audioLength")
    @Expose
    private int audioLength;// total length
    @SerializedName("lastPaused")
    @Expose
    private int lastPaused; // time in long or string
    @SerializedName("typeOfListener")
    @Expose
    private int typeOfListener;// what kind of  people hear it more
    @SerializedName("classification")
    @Expose
    private int classification;
    // sub category
    //     if instrumental
    //             then  which intrumental -guitear , piano, drums.....
    //     if just music
    //             then which type smooth relaxing, low , 3d, high bass, edm, travelling, trance
    // if    sunged lyrical
    //             then playback, ....
    @SerializedName("uploadyear")
    @Expose
    private int uploadyear;
    @SerializedName("uploadmonth")
    @Expose
    private int uploadmonth;
    @SerializedName("uploaddate")
    @Expose
    private int uploaddate;
    @SerializedName("uploadtime")
    @Expose
    private String uploadtime;
    @SerializedName("views")
    @Expose
    private int views;
    @SerializedName("likes")
    @Expose
    private int likes;
    @SerializedName("likes_today")//likes/voteins today   show this in trending
    @Expose
    private int likes_today;
    @SerializedName("likes_per_day")//likes/voteins today
    @Expose
    private int likes_per_day;
    @SerializedName("vote_ins")
    @Expose
    private int vote_ins;  // also show
//    "vote_ins": {
//        "id": 12
//        "id": 13...
//    }

    @SerializedName("stars")
    @Expose
    private int stars;// stars if any
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("comment")
    @Expose
    private String comment;
//    "comment": {
//        "total_comments": 123,
//        "ids" : {1,2,3,12,13.......}
//    }// check if any of followers of users have liked or voted   or any verified account has
    public Binge(Integer id,String username,String coverName,String albumName,Integer thumbnail,Boolean successful,Integer songType,Integer audio,Integer audioLength,Integer lastPaused,
                 Integer typeOfListener,Integer classification,Integer uploadyear, Integer uploadmonth,Integer uploaddate,String uploadtime,Integer views,Integer likes, Integer likes_today,
                 Integer likes_per_day,Integer vote_ins,Integer stars, String description,String video,String comment){
        this.id=id;
        this.username=username;
        this.coverName=coverName;
        this.albumName=albumName;
        this.thumbnail=thumbnail;
        this.successful=successful;
        this.songType=songType;
        this.audio=audio;
        this.audioLength=audioLength;
        this.lastPaused=lastPaused;
        this.typeOfListener=typeOfListener;
        this.classification=classification;
        this.uploadyear=uploadyear;
        this.uploadmonth=uploadmonth;
        this.uploaddate=uploaddate;
        this.uploadtime=uploadtime;
        this.views=views;
        this.likes=likes;
        this.likes_today=likes_today;
        this.likes_per_day=likes_per_day;
        this.vote_ins=vote_ins;
        this.stars=stars;
        this.description=description;
        this.video=video;
        this.comment=comment;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCoverName() { return coverName; }
    public void setCoverName(String coverName) { this.coverName = coverName; }

    public String getAlbumName() { return albumName; }
    public void setAlbumName(String albumName) { this.albumName=albumName; }

    public int getThumbnail() { return thumbnail; }
    public void setThumbnail(int thumbnail) { this.thumbnail = thumbnail; }

    public boolean getSuccessful() { return successful; }
    public void setSuccessful(boolean successful) { this.successful = successful; }

    public int getSongType() { return songType; }
    public void setSongType(int songType) { this.songType = songType; }

    public int getAudio() { return audio; }
    public void setAudio(int audio) { this.audio = audio; }

    public int getAudioLength() { return audioLength; }
    public void setAudioLength(int audioLength) { this.audioLength = audioLength; }

    public int getLastPaused() { return lastPaused; }
    public void setLastPaused(int lastPaused) { this.lastPaused = lastPaused; }

    public int getTypeOfListener() { return typeOfListener; }
    public void setTypeOfListener(int typeOfListener) { this.typeOfListener = typeOfListener; }

    public int getClassification() { return classification; }
    public void setClassification(int classification) { this.classification = classification; }

    public int getUploadyear() { return uploadyear; }
    public void setUploadyear(int uploadyear) { this.uploadyear = uploadyear; }

    public int getUploadmonth() { return uploadmonth; }
    public void setUploadmonth(int uploadmonth) { this.uploadmonth = uploadmonth; }

    public int getUploaddate() { return uploaddate; }
    public void setUploaddate(int uploaddate) { this.uploaddate = uploaddate; }

    public String getUploadtime() { return uploadtime; }
    public void setUploadtime(String uploadtime) { this.uploadtime = uploadtime; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public int getLikes_today() { return likes_today; }
    public void setLikes_today(int likes_today) { this.likes_today = likes_today; }

    public int getLikes_per_day() { return likes_per_day; }
    public void setLikes_per_day(int likes_per_day) { this.likes_per_day = likes_per_day; }

    public int getVote_ins() { return vote_ins; }
    public void setVote_ins(int vote_ins) { this.vote_ins = vote_ins; }

    public int getStars() { return stars; }
    public void setStars(int stars) { this.stars = stars; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getVideo() { return video; }
    public void setVideo(String video) { this.video = video; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
