package space;

import java.util.List;

import myMath.Vector;

public class Star extends Place {
    Planet sun;
    int planetCount;
    List<Planet> planets;
    Vector position;    //(x, y) position in the galaxy 
    //TODO: re-impliment the Vector stuff
    int layer;  // height "z position", snapped to either -1, 0, or 1 to reduce difficulty for mental math.

    public Star(Place parent){
        this.parent = parent;   //identify the parent object

        //create a new planet that is a sun;
        sun = new Planet(this);

        planetCount = 5;    //TODO: Generate normalDist

        position = new Vector(1.5, 2.0, 0); //TODO: generate x and y on (very flat) normalDist, and height on a different normalDist
        //TODO: re-impliment above
        
        details = new Descriptor();

        for (int i = 0; i < planetCount; ++i){
            double orbitRadius = 1.7;   //TODO: randomly generate orbitRadius

            Planet myPlanet = new Planet(this, i, orbitRadius);
            planets.add(myPlanet);
        }
    }
}
