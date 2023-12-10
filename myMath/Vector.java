package myMath;

public class Vector {
    public double x;
    public double y;
    public double z;
    public int layer;

    //Vector2
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    //Vector3
    public Vector(double x, double y, double z){
        this(x, y);
        this.z = z;
    }

    //Vector2 with integer height, snapped to a limited level of precision
    public Vector(double x, double y, int layer){
        this(x, y);
        this.layer = layer;
    }

    //You could maybe add some Vector math here as well, maybe even steal it from Unity's C# Vector3/Vector2 class code idk.
}
