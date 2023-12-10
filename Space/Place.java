package space;

public abstract class Place {
    String name;
    Descriptor details;
    
    Place parent;
    boolean hasParent = true;

    public String getName(){
        return name;
    }
    public Descriptor getDescriptorObject(){    //the object that has all the special stats and things.
        return details;
    }
}
