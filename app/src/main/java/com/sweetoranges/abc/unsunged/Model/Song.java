package com.sweetoranges.abc.unsunged.Model;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Song {
    static final Song EMPTY_SONG = new Song("", -1, -1, -1,
            null, "", -1, "");


    public  String title;
    public  int trackNumber;
    public  int duration;
    public  String path;
    public  String albumName;
    public  int artistId;
    public  String artistName;
    private  int year;

    public Song(final String title, final int trackNumber, final int year, final int duration, final String path, final String albumName, final int artistId, final String artistName) {
        this.title = title;
        this.trackNumber = trackNumber;
        this.year = year;
        this.duration = duration;
        this.path = path;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public static String formatDuration(final int duration) {
        return String.format(Locale.getDefault(), "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    public static int formatTrack(int trackNumber) {
        int formatted = trackNumber;
        if (trackNumber >= 1000) {
            formatted = trackNumber % 1000;
        }
        return formatted;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getTrackNumber() {
        return trackNumber;
    }
    public void setTrackNumber(int trackNumber) { this.trackNumber = trackNumber; }

    public int getYear() {
        return year;
    }
    public void setYear(int year) { this.year = year; }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) { this.duration = duration; }

    public String getPath() {
        return path;
    }
    public void setPath(String path) { this.path = path; }

    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) { this.albumName = albumName; }

    public int getArtistId() {
        return artistId;
    }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) { this.artistName = artistName; }

}