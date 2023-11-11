package Space;

public abstract class Place {
    String name;
    Object stuff;
    
    Place parent;
    boolean hasParent = true;

    public String getName(){
        return name;
    }
    public Object getDescriptorObject(){    //the object that has all the special stats and things.
        return stuff;
    }
}
