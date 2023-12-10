package space;

import java.util.List;

public class Planet extends Place {
    
    List<Place> regions;    //TODO: change `Place` to `Region`
    
    //TODO: add a map class and variable??

    boolean isSun;

    int massIndex;   //see below
    /*
     * 0:  Small space object (like a Rocket, ISS, small satelite, or even just a tiny camera)
     * 1:   Small space rock
     * 
     * 2:   Larger space objects like Pluto or a Moon
     * 3:   Very small planets, like Mercury
     * 4:   Slightly larger planets, like that of Venus, Earth, Mars, etc
     * 5:   The larger end of planets you could realistically exist on. Bigger than Earth, smaller than Jupiter (no examples exist)
     * 6:   Huge, stuff like the Gas Giants of Jupiter, Saturn, Urinus, Neptune. Doesn't necessarily have to be made of gas.
     * 
     * 7:   A Sun, big enough to create nuclear fusion in the core.
     * 8:   So big that it has become a Black Hole, Dwarf Star, or anything else involving the death of very large stars.
     */
    String[] massInfo = {
        "the size of a small man-made object, like a satelite or a rocket",
        "the size of a comet or meteor",
        "the size of a dwarf planet like Pluto or Titan",
        "the size of a small planet like Mercury",
        "the size of a medium planet like Venus, Earth, or Mars",
        "the size of a larger planet, somewhere between Earth and Jupiter",
        "the size of a giant planet, like Jupiter, Saturn, Uranus, or Neptune",
        "the size of a Sun or Star",
        "the size of a Black Hole, or other ultra-massive object"
    };

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
        this.orbitNumber = orbit;

        details = new Descriptor(parent.details);

        //random number between [2, 6]
        massIndex = Descriptor.randInt(2, 6 + 1);
    }

    /**
     * Creates the new planet as a sun. Do not use if you are trying to create a non-solar planet.
     * @param parent The Star object it belongs to.
     */
    public Planet(Place parent){
        this.parent = parent;
        this.isSun = true;

        // 8% chance for it to be a black hole
        if (Descriptor.randInt(0, 100) <= 8){
            massIndex = 8;
        }else{ //otherwise just make it a sun
            massIndex = 7;
        }
    }
    
    @Override
    public String generateReport() {
        String sunInfo;
        if (isSun){
            sunInfo = "This planet is a Sun!!";
        } else {
            sunInfo = "Orbit number: " + orbitNumber;
        }
        
        String output = String.format("Name: %s\n%s\nObject is about %s\n\n", name, sunInfo, massInfo[massIndex]);

        output += super.generateReport();

        return output;
    }

}
