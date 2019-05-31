package com.sweetoranges.abc.unsunged.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quick {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}