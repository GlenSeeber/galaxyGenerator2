package space;

import java.util.List;

public class Planet extends Place {
    
    List<Place> regions;    //TODO: change `Place` to `Region`
    
    //TODO: add a map class and variable??

    boolean isSun;

    double mass;
    int orbit;
    double orbitRadius;

    Descriptor info;

    /**
     * Creates a non-Solar planet with the specified orbital details
     * @param parent the Star it belongs to
     * @param orbit the orbit number (mercury, venus, earth, mars, ...) == (0, 1, 2, 3, ...)
     * @param orbitRadius the avg distance (radius) from the planet/orbit to the sun
     */
    public Planet(Place parent, int orbit, double orbitRadius){
        this.parent = parent;
        this.isSun = false;

        if (!isSun){
            this.orbit = orbit;
            this.orbitRadius = orbitRadius;
        }

        details = new Descriptor(super.getDescriptorObject());
    }

    /**
     * Creates the new planet as a sun. Do not use if you are trying to create a non-solar planet.
     * @param parent The Star object it belongs to.
     */
    public Planet(Place parent){
        this.parent = parent;
        this.isSun = true;
    }
    
    
}
