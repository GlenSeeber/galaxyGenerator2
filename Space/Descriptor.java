package space;

import java.util.List;
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
    double domesticTrade;   //how much they trade among themselves 
    //(whatever layer this applies to. This will count for foreign trade 1 layer deeper.)

    double foreignTrade;    //how much do they trade with places outside themselves
    double exports; //how much do they export goods (as opposed to being a buyer of imports)
    double imports; //how much do they import goods (as opposed to selling their own goods)
    double stability;   //how long their economy can last without going through major crisis

    //SOCIAL
    double acceptance;  //acceptance of minority groups and "others"
    double communitySize;   //small towns, or sprawling cities?
    double multiculturalism;    //how much do they integrate different cultures?
    double travel;  //how often do folks from here visit other places? How likely are you to find someone from here to be very far from home?

    //DEMOGRAPHICS
    List<Double> raceDemographic;   //percentages of each race, should add up to ~100%
    List<String> races; //the actual races those demographics reference, indexes correspond.
    
    List<Double> languageDemographic; //language percentages
    List<String> languages; //the actual languages
    
    //get an array of all values that are just a single number
    double indexables[] = {economicIndex, domesticTrade, foreignTrade, exports, imports, stability, acceptance,
        communitySize, multiculturalism, travel};



    //assign each variable with the same starting value `val`.
    public Descriptor(double val){
        for (int i = 0; i < indexables.length; ++i) {
            indexables[i] = val;
        }
    }

    //assign each variable with the starting value 5.0
    public Descriptor(){
        this(5.0);
    }

    //assign each variable randomly, deviating slightly from the parent descriptor (based on the value of `diff`).
    public Descriptor(Descriptor parentDescriptor){
        //iterate through each, assign a random number
        for (int i = 0; i < indexables.length; ++i) {
            double parentVal = parentDescriptor.indexables[i];  //this is the parent value for this iteration

            //gets a random number within the range. We're using int's here, so multiply by 100 to get it in cents
                //then do float division by 100 to get a double value of reasonable precision.
            indexables[i] = randDouble(parentVal - diff, parentVal + diff);
        }
    }

    public double[] getIndexables() {
        return indexables;
    }


    /**
     * @returns a random integer in the range [min, max).
     */
    private int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    
    private double randDouble(double min, double max){
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
