package myRand;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Generator {
    private File file;
    private Scanner scnr;
    private int lines;

    public Generator(String dir) throws FileNotFoundException{
        file = new File(dir);
        scnr = new Scanner(file);
        lines = checkLines();

    }

    public Generator(File file) throws FileNotFoundException{
        this.file = file;
        scnr = new Scanner(file);

        // The idea here is that you find the number of lines, and the next() func will get a random number between 0 and that number,
        //      then it will return the corresponding line. This means that you can't really do piecewise names, randomly
        //      generating each piece of the whole output.
        //
        // Check out the scripts on https://www.fantasynamegenerators.com for the better way.
        lines = checkLines();
    }

    //how many lines are in the file (or, how many newline characters are in the file -1)
    public int checkLines(){
        int lines = 0;
        while(scnr.hasNextLine()){
            scnr.nextLine();
            ++lines;
        }
        return lines;
    }

    public int getLines(){
        return lines;
    }

    public String next(){
        

        return "";
    }
}
