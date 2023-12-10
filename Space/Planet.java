package space;

import java.util.List;

public class Planet extends Place {
    
    List<Place> regions;    //TODO: change `Place` to `Region`
    
    //TODO: add a map class and variable??

    boolean isSun;

    int massIndex;   //see below
    /*
     * -1:  Small space object (like a Rocket, ISS, small satelite, or even just a tiny camera)
     * 0:   Small space rock
     * 
     * 1:   Larger space objects like Pluto or a Moon
     * 2:   Very small planets, like Mercury
     * 3:   Slightly larger planets, like that of Venus, Earth, Mars, etc
     * 4:   The larger end of planets you could realistically exist on. Bigger than Earth, smaller than Jupiter (no examples exist)
     * 5:   Huge, stuff like the Gas Giants of Jupiter, Saturn, Urinus, Neptune. Doesn't necessarily have to be made of gas.
     * 
     * 6:   A Sun, big enough to create nuclear fusion in the core.
     * 7:   So big that it has become a Black Hole, Dwarf Star, or anything else involving the death of very large stars.
     */


    int orbitNumber;


    /**
     * Creates a non-Solar planet with the specified orbital details
     * @param parent the Star it belongs to
     * @param orbit the orbit number (mercury, venus, earth, mars, ...) == (0, 1, 2, 3, ...)
     * @param orbitRadius the avg distance (radius) from the planet/orbit to the sun
     */
    public Planet(Place parent, int orbit){
        this.parent = parent;
        this.isSun = false;

        if (!isSun){
            this.orbitNumber = orbit;
        }

        details = new Descriptor(parent.details);
    }

    /**
     * Creates the new planet as a sun. Do not use if you are trying to create a non-solar planet.
     * @param parent The Star object it belongs to.
     */
    public Planet(Place parent){
        this.parent = parent;
        this.isSun = true;
        massIndex = 6;  //later you might add a small random chance of it being 7 (a black hole)
    }
    
    
}
