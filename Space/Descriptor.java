package space;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Descriptor {
    // +/- difference allowed between parent and child values
    //ex: if a galaxy has an economicIndex of 5.0, then all stars within must have values in the range [3.0, 7.0]
    //  then if a star within has a value of 4.5, all planets must have values in the range [2.5, 6.5].
    //  note how the planet (not a direct child of the galaxy) is allowed to go beyond the initial limits of the stars.
    //
    //The purpose here is to simulate a kind of normal distribution without having to do as much math or impliment all of that.
    private double diff = 2.0;
    
    //ECONOMICS
    double economicIndex;   //general index of economic power
    double exports; //how much do they export goods (as opposed to being a buyer of imports)

    //SOCIAL
    double acceptance;  //acceptance of minority groups and "others"
    double communitySize;   //small towns, or sprawling cities?
    double travel;  //how often do folks from here visit other places? How likely are you to find someone from here to be very far from home?

    //DEMOGRAPHICS

    //The languages spoken here, and the percentage at which they are spoken by the population (should add up to 100%)
    Map<String, Double> languageDemographics = new HashMap<String, Double>();

    //The races and percentages that they make up the area (should add up to 100%)
    Map<String, Double> racialDemographics = new HashMap<String, Double>();

    //get an array of all values that are just a single number
    double indexables[] = {economicIndex, exports, acceptance, communitySize, travel};
    //and descriptive names for each variable
    String indexableNames[] = {"Economic Index", "Exports", "Social Acceptance", "Community Size", "Travel"};


    //assign each variable with the same starting value `val`.
    public Descriptor(double val){
        for (int i = 0; i < indexables.length; ++i) {
            indexables[i] = val;
        }
    }

    //start the descriptor out randomly deviated from 5.0 on all values (so the galaxy stats don't look boring/unnatural)
    public Descriptor(){
        this(new Descriptor(5.0));
    }

    //assign each variable randomly, deviating slightly from the parent descriptor (based on the value of `diff`).
    public Descriptor(Descriptor parentDescriptor){
        //iterate through each, assign a random number
        for (int i = 0; i < indexables.length; ++i) {
            double parentVal = parentDescriptor.indexables[i];  //this is the parent value for this iteration

            //gets a random number within the range. We're using int's here, so multiply by 100 to get it in cents
                //then do float division by 100 to get a double value of reasonable precision.
            indexables[i] = randDouble((parentVal - diff), (parentVal + diff + 1));
        }

        languageDemographics = parentDescriptor.languageDemographics;
        racialDemographics = parentDescriptor.racialDemographics;
    }

    public double[] getIndexables() {
        return indexables;
    }


    /**
     * @param min
     * @param max
     * @returns a random integer in the range [min, max).
     */
    public static int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    
    /**
     * @param min
     * @param max
     * @returns a random double in the range [`min`, `max`]
     */
    public static double randDouble(double min, double max){
        //generate random number between 0 and 1;
        double r = ThreadLocalRandom.current().nextDouble();
        //the distance currently is 1. Make it into whatever distance between min and max is.
        double distance = max - min;
        //multiply the value so that the range matches
        r *= distance;
        //now shift it over so that the lowest value is equal to the min value
        r += min;

        return r;
    }
}
