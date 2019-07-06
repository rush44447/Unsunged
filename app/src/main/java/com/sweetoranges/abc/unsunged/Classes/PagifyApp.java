package com.sweetoranges.abc.unsunged.Classes;

import android.app.Application;

import com.sweetoranges.abc.unsunged.Model.Song;
import com.sweetoranges.abc.unsunged.R;

import java.util.ArrayList;
import java.util.List;

public class PagifyApp extends Application {
    private static List<Song> mDatabase;
    public static List<Song> getAlbumDatabase() {
        if (mDatabase == null) {
            mDatabase = new ArrayList<>();
            mDatabase.add(new Song("Lonerism",1,2019,3,"R.drawable.lady", "Millenials",2,"Tame Impala"));
            mDatabase.add(new Song("Art Angels",1,2019,3,"R.drawable.ladyc", "Millenials",2,"Grimes"));
            mDatabase.add(new Song("Atlas",1,2019,3,"R.drawable.ladyc", "Millenials",2,"Real Estate"));
//            mDatabase.add(new Song(R.drawable.born_to_die, "Born To Die", "Lana del Rey"));
//            mDatabase.add(new Song(R.drawable.coeur_de_pirate, "Coeur de Pirate",
//                    "Coeur de Pirate"));
//            mDatabase.add(new Song(R.drawable.days_of_abandon, "Days of Abandon",
//                    "The Pains of Being " +
//                            "Pure at Heart"));
//            mDatabase.add(new Song(R.drawable.in_colour, "In Colour", "Jamie XX"));
//            mDatabase.add(new Song(R.drawable.in_the_mountain, "In the Mountain in the Cloud",
//                    "Portugal. The Man"));
//            mDatabase.add(new Song(R.drawable.melody, "Melody's Echo Chamber",
//                    "Melody's Echo Chamber"));
//            mDatabase.add(new Song(R.drawable.royal_blood, "Royal Blood", "Royal Blood"));
//            mDatabase.add(new Song(R.drawable.sun_shy, "Sun Shy", "Dresses"));
//            mDatabase.add(new Song(R.drawable.the_flower_lane, "The Flower Lane", "Ducktails"));
//            mDatabase.add(new Song(R.drawable.the_joshua_tree, "The Joshua Tree", "U2"));
//            mDatabase.add(new Song(R.drawable.the_world_wont_listen, "The World Won't Listen",
//                    "The Smiths"));
//            mDatabase.add(new Song(R.drawable.torches, "Torches", "Foster the People"));
//            mDatabase.add(new Song(R.drawable.xy, "X&Y", "Coldplay"));
        }
        return mDatabase;
    }
}
