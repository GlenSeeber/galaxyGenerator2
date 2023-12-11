package space;

import java.util.ArrayList;
import java.util.List;

import culture.Defaults;

public class Galaxy extends Place {
    int starCount;

    //this is a list of all languages that exist within the galaxy
    //  (all child objects will draw languages only from this list)
    List<String> languages;

    //this is a list of all races that exist within the galaxy
    //  (all child objects will draw races only from this list)
    List<String> races;


    public Galaxy(int starCount, String name){
        this.name = name;
        //Galaxies don't belong to another, larger group of things
        hasParent = false;
        //Weird thing, all Place objects have a `galaxy` variable referring to the parent Galaxy object.
        //  Given that this IS a Galaxy, we just point it to itself (`this`).
        this.galaxy = this;

        //create a descriptor for the galaxy
        details = new Descriptor();

        //pull race and language lists from the Defaults class
        //      (if you want something different, you need to make a model galaxy,
        //      change the attributes and use the Galaxy(Galaxy) constructor)
        languages = Defaults.languages;
        races = Defaults.races;

        //Assigns stats and percentages for language and racial demographics
        doStats();

        //make all the child stars (this also adds them to the `children` List object included in the Place class)
        createStars(starCount);
    }

    //creates a galaxy and it's recursive children based on a model Galaxy object 
    //  (but not referencing that object's recursive children)
    public Galaxy(Galaxy galaxy){
        //Galaxies don't belong to another, larger group of things
        hasParent = false;
        //read the Galaxy(int, String) constructor if this variable is confusing you.
        //  This has nothing to do with the parameter that gets passed into this constructor.
        this.galaxy = this;
        //start copying things from the model
        name = galaxy.name;
        details = galaxy.details;
        languages = galaxy.languages;
        races = galaxy.races;
        starCount = galaxy.starCount;

        //Assigns stats and percentages for language and racial demographics
        doStats();

        //recursively begin creating all the child objects
        createStars(starCount);
    }

    //generates a galaxy called 'myGalaxy' with a random number of stars between [5, 26]
    public Galaxy(){
        this(Descriptor.randInt(5, 27), "myGalaxy");
    }

    public void createStars(int starCount){
        //create the Stars within the Galaxy
        children = new ArrayList<Place>();  //use the `children` List object included in the `Place` class
        this.starCount = starCount;
        for (int i = 0; i < starCount; ++i) {
            Star myStar = new Star(this, "Star "+i);
            children.add(myStar);
        }
    }

    @Override
    public String generateReport(){
        String output = String.format(
            "Name: %s\n" +
            "Star Count: %d\n\n" + 
            
            super.generateReport(), name, starCount);

        return output;
    }
}
