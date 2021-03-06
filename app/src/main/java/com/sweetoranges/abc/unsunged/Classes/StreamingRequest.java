package com.sweetoranges.abc.unsunged.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sweetoranges.abc.unsunged.R;

public class StreamingRequest {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("CommentsEnabled")
    @Expose
    private Boolean commentsEnabled;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("AudioFileId")
    @Expose
    private String audioFileId;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Duration")
    @Expose
    private Double duration;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("Mp3Url")
    @Expose
    private String mp3Url;
    @SerializedName("SecureMp3Url")
    @Expose
    private String secureMp3Url;
    @SerializedName("OggUrl")
    @Expose
    private String oggUrl;
    @SerializedName("SecureOggUrl")
    @Expose
    private String secureOggUrl;
    @SerializedName("DateCreated")
    @Expose
    private String dateCreated;

    private int photo = R.drawable.imgview;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCommentsEnabled() {
        return commentsEnabled;
    }

    public void setCommentsEnabled(Boolean commentsEnabled) {
        this.commentsEnabled = commentsEnabled;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAudioFileId() {
        return audioFileId;
    }

    public void setAudioFileId(String audioFileId) {
        this.audioFileId = audioFileId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public String getSecureMp3Url() {
        return secureMp3Url;
    }

    public void setSecureMp3Url(String secureMp3Url) {
        this.secureMp3Url = secureMp3Url;
    }

    public String getOggUrl() {
        return oggUrl;
    }

    public void setOggUrl(String oggUrl) {
        this.oggUrl = oggUrl;
    }

    public String getSecureOggUrl() {
        return secureOggUrl;
    }

    public void setSecureOggUrl(String secureOggUrl) {
        this.secureOggUrl = secureOggUrl;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

}