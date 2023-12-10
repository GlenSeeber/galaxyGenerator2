package space;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Galaxy andromeda = new Galaxy(12, "AndromedaGalaxy");

        String dir = "C:/Users/River/Desktop/";
        serializeGalaxy(andromeda, dir);

    }

    //save the galaxy as a directory of nested folders.
    public static void serializeGalaxy(Galaxy galaxy, String saveDirectory){
        Integer fileEnding = null;
        String folderName = saveDirectory + galaxy.name;

        String path = folderName;

        //create our directory where we will be saving the file to
        File buf = new File(path);
        FileWriter fw;
        
        //create the actual file on the hard drive
        //while the file already exists, try to change the file until you can create something with a unique filename
        while(buf.exists()){
            System.err.println("File already exists! Appending...");
            //increase fileEnding
            if(fileEnding == null){
                fileEnding = 0;
            }
            else{
                ++fileEnding;
            }
            path = folderName + fileEnding;
            buf = new File(path);
        }
        //creates just the parent folder for the galaxy
        buf.mkdir();

        System.out.printf("Path = \"%s\"\n", path);

        //recursively generate data files and child folders for the galaxy and its children
        createDirs(galaxy, path);
    }


    //creates a data file in the directory, then recursively creates child folders with their own
    //  data files and (if they have any) it will also create child folders for them as well.
    public static void createDirs(Place p, String dir){
        //first, create the data in your starting directory
        String fullPath = dir + "/" + p.name+"_data.txt";
        File buf = new File(fullPath);
        FileWriter fw;
        try {
            buf.createNewFile();
            fw = new FileWriter(fullPath);
            //write the data to the file now
            fw.write(p.generateReport());
            fw.close();

        } catch (IOException e) {
            System.err.printf("IOException occurred in createDirs() function!!"
                + "Current object: %s\n", p.name);
        }

        //next, either create the children folders (recursive case), or terminate if there are none (base case)
        //note: it will also terminate when it is done iterating through each child in the recursive case.

        //BASE CASE
        if(p.children == null || p.children.isEmpty()){
            //do nothing, you're done
            return;
        }

        //RECURSIVE CASE
        //create a directory for each item in p.children
        for (Place child : p.children) {
            //create a folder for the child
            String path = dir + "/" + child.name;
            buf = new File(path);
            buf.mkdir();

            //recursively fill in the contents of the folder
            createDirs(child, path);
        }
    }
}
