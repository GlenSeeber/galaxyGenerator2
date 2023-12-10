package space;

import java.util.ArrayList;
import java.util.List;

import myRand.*;

public class Galaxy extends Place {
    private List<Place> stars;
    private int starCount;

    public Galaxy(int starCount){
        hasParent = false;  //Galaxies don't belong to another, larger group of things

        stars = new ArrayList<Place>();

        //TODO: generate name
        name = "foo";

        details = new Descriptor();

        this.starCount = starCount;
        for (int i = 0; i < starCount; ++i) {
            Place myStar = new Galaxy();    //TODO: change to `new Star();`
            stars.add(myStar);
        }
    }

    public Galaxy(){
        //TODO: generate random number of stars
        this(Numbers.getRandomStarCount());
    }

    public List<Place> getStars() {
        return stars;
    }

    public int getStarCount() {
        return starCount;
    }
}
