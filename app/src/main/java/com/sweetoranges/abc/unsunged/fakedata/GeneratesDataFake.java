package com.sweetoranges.abc.unsunged.fakedata;

import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;

public class GeneratesDataFake {

    private final ArrayList<StreamingRequest> dataObjects = new ArrayList<>();
    public GeneratesDataFake() {
    }
    private final ArrayList<String> status = new ArrayList<String>() {
        {
            add("New");
            add("New");
            add("1 Month ago");
            add("Old");
            add("Old");
            add("New");
        }
    };
    private final ArrayList<Boolean> commentsEnabled = new ArrayList<Boolean>() {
        {
            add(true);
            add(false);
            add(true);
            add(true);
            add(true);
            add(false);
        }
    };
    private final ArrayList<String> category = new ArrayList<String>() {
        {
            add("Hindi");
            add("English");
            add("Hindi");
            add("English");
            add("Hindi");
            add("English");
        }
    };
    private final ArrayList<String> audioFileId = new ArrayList<String>() {
        {
            add("High");
            add("Low");
            add("High");
            add("Low");
            add("High");
            add("Low");
        }
    };
    private final ArrayList<String> titles = new ArrayList<String>() {
        {
            add("Tere Bin");
            add("Demons");
            add("Believer");
            add("Better Not");
            add("Agar Tum Saath Ho");
            add("Jaane kyun");
        }
    };
    private final ArrayList<Double> duration = new ArrayList<Double>() {
        {
            add(02.00);
            add(03.00);
            add(02.00);
            add(03.00);
            add(02.00);
            add(03.00);
        }
    };
    private final ArrayList<String> url = new ArrayList<String>() {
        {
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");

        }
    };
    private final ArrayList<String> mp3Url = new ArrayList<String>() {
        {
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
        }
    };
    private final ArrayList<String> secureMp3Url = new ArrayList<String>() {
        {
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
            add("https://api.clyp.it/idshnmkl");
        }
    };
    private final ArrayList<Integer> photos = new ArrayList<Integer>() {
        {
            add(R.drawable.imgview);
            add(R.drawable.modelb);
            add(R.drawable.imgview);
            add(R.drawable.modelb);
            add(R.drawable.imgview);
            add(R.drawable.modelc);

           }
    };

    public ArrayList<StreamingRequest> generateDataFake() {
        for (int i = 0; i < 12; i++) {
            StreamingRequest dataObject = new StreamingRequest();
            dataObject.setStatus(status.get(i));
            dataObject.setCommentsEnabled(commentsEnabled.get(i));
            dataObject.setTitle(titles.get(i));
            dataObject.setCategory(category.get(i));
            dataObject.setAudioFileId(audioFileId.get(i));
            dataObject.setMp3Url(audioFileId.get(i));
            dataObject.setUrl(audioFileId.get(i));
            dataObject.setPhoto(photos.get(i));
            dataObjects.add(dataObject);
        }
        return dataObjects;
    }
}
