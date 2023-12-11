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

    public Star(Place parent, String name){
        super(parent);
        this.parent = parent;   //identify the parent object

        this.name = name;

        planetCount = 5;

        //create a new Planet that is a sun;
        sun = new Planet(this);

        //set the galactic coordinates of this Star
        position = new Vector(1.5, 2.0, 0); //TODO: generate x and y on (very flat) normalDist, and height on a different normalDist
        
        //create a Descriptor for the Star system
        details = new Descriptor(parent.details);

        //assign language and racial demographics
        doStats();

        //generate a list of Planets
        for (int i = 0; i < planetCount; ++i){

            Planet myPlanet = new Planet(this, i, "Planet "+i);
            planets.add(myPlanet);
        }

        //so we can access the list when generalizing as a Place object
        children = planets;
    }

    @Override
    public String generateReport() {
        String output = String.format("Name: %s\nNumber of Planets: %d\n\n"
        + "Planet names (closest to farthest from sun):\n", name, planetCount);

        for (int i = 0; i < planets.size(); i++) {
            output += String.format("    %d) %s\n", i+1, planets.get(i).name);
        }
        output += "\n";

        output += super.generateReport();

        return output;
    }
}
