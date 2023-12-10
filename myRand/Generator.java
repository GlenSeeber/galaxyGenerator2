package myRand;

import java.util.List;

public class Generator{

    String filename = null;
    String breaker = "\n";
    boolean recursive = false;
    List<Generator> recursiveGens = null;

    /**
     * takes in a directory to a file containing a plaintext list of
     * possible outputs, seperated by newlines.
     * @param filename The directory/filename to the file.
     */
    public Generator(String filename){
        this.filename = filename;
    }
    /**
     * takes in a directory to a file containing a plaintext list of
     * possible outputs, seperated by the character sequence defined in {@code breaker}
     * @param filename The directory/filename to the file.
     * @param breaker The character sequence used (instead of newline)
     * to seperate output entries in the file
     */
    public Generator(String filename, String breaker){
        this(filename);
        this.breaker = breaker;
    }
    /**
     * takes in a directory to a file containing a plaintext list of
     * possible outputs, seperated by the character sequence defined in {@code breaker}
     * @param filename The directory/filename to the file.
     * @param breaker The character sequence used (instead of newline)
     * to seperate output entries in the file
     * @param gens A list of generators used recursively through the possible
     * outputs. Should be referenced using their index number as in <code>${0}</code>,
     * <code>${1}</code>, <code>${2}</code>, etc, in the file, where each will be replaced with the
     * return value on a call of <code>next()</code> on the given generator.
     */
    public Generator(String filename, String breaker, List<Generator> gens){
        this(filename, breaker);
        this.recursiveGens = gens;
        recursive = true;
    }
    /**
     * @return a random message, pulled from within the file
     */
    public String next(){
        //TODO: impliment
        return "";
    }

}