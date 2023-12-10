package space;

import java.io.FileNotFoundException;

import myRand.Generator;

public class Tester {

    public static void main(String[] args) {
        try {
            Generator gen = new Generator("C:/Users/River/Desktop/test.txt");
            System.out.println(gen.getLines());
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
