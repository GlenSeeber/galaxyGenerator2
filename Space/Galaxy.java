package space;

import java.util.ArrayList;
import java.util.List;

public class Galaxy extends Place {
    List<Place> stars;
    int starCount;

    public Galaxy(int starCount, String name){
        this.name = name;

        hasParent = false;  //Galaxies don't belong to another, larger group of things

        stars = new ArrayList<Place>();

        //TODO: generate name
        name = "foo";

        details = new Descriptor(5.0);

        this.starCount = starCount;
        for (int i = 0; i < starCount; ++i) {
            Star myStar = new Star(this);    //TODO: change to `new Star();`
            myStar.name = "Star"+i;
            stars.add(myStar);

        }
        //So that we can access this list when generalizing as just a Place object
        children = stars;
    }

    public Galaxy(){
        //TODO: generate random number of stars
        this(12, "myGalaxy");
    }

    @Override
    public String generateReport(){
        String output = String.format("Name: %s\nStar Count: %d\n\n" + super.generateReport(), name, starCount);

        return output;
    }
}
