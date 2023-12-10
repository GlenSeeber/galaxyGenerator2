package space;

import java.util.ArrayList;
import java.util.List;

import myMath.Vector;

public class Star extends Place {
    Planet sun;
    int planetCount;
    List<Place> planets = new ArrayList<Place>();
    Vector position;    //(x, y) position in the galaxy 
    int layer;  // height "z position", snapped to either -1, 0, or 1 to reduce difficulty for mental math.

    public Star(Place parent){
        this.parent = parent;   //identify the parent object

        planetCount = 5;

        //create a new Planet that is a sun;
        sun = new Planet(this);

        //set the galactic coordinates of this Star
        position = new Vector(1.5, 2.0, 0); //TODO: generate x and y on (very flat) normalDist, and height on a different normalDist
        
        //create a Descriptor for the Star system
        details = new Descriptor(parent.details);

        //generate a list of Planets
        for (int i = 0; i < planetCount; ++i){

            Planet myPlanet = new Planet(this, i);
            myPlanet.name = "Planet"+i;
            planets.add(myPlanet);
        }

        //so we can access the list when generalizing as a Place object
        children = planets;
    }
}
