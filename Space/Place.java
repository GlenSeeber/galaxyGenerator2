package space;

import java.util.Arrays;
import java.util.List;

public abstract class Place {
    String name;
    Descriptor details;
    
    Place parent;
    boolean hasParent = true;

    List<Place> children;

    public String getName(){
        return name;
    }
    public Descriptor getDescriptorObject(){    //the object that has all the special stats and things.
        return details;
    }

    //intended to be overwritten, but i don't wanna handle all of that right now.
    public String generateReport(){
        String output = "uh-oh! it looks like the dev never implemented the generateReport() function yet!\n"
            + "That's okay, for now, just take a list of the values from the indexables list from the Descriptor:\n\n"
            + "Name: " + name + "\n\n"
            + Arrays.toString(details.indexables);
        
        return output;
    }
}
