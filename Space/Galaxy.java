package space;

import java.util.ArrayList;
import java.util.List;

public class Galaxy extends Place {
    List<Place> stars;
    int starCount;

    public Galaxy(int starCount, String name){
        this.name = name;
        //Galaxies don't belong to another, larger group of things
        hasParent = false;
        //create a descriptor for the galaxy
        details = new Descriptor();

        //create the Stars within the Galaxy
        children = new ArrayList<Place>();  //use the `children` List object included in the `Place` class
        this.starCount = starCount;
        for (int i = 0; i < starCount; ++i) {
            Star myStar = new Star(this);
            myStar.name = "Star"+i;
            children.add(myStar);

        }
    }

    //generate a galaxy called 'myGalaxy' with a random number of stars between [5, 26]
    public Galaxy(){
        this(Descriptor.randInt(5, 27), "myGalaxy");
    }

    @Override
    public String generateReport(){
        String output = String.format("Name: %s\nStar Count: %d\n\n" + super.generateReport(), name, starCount);

        return output;
    }
}
