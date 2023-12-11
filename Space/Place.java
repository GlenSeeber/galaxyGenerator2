package space;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Place {
    String name;
    Descriptor details;
    List<Place> children;

    Place parent;
    boolean hasParent = true;
    Galaxy galaxy;


    public Place(Place parent){
        galaxy = parent.galaxy;
    }

    //included so I don't need to use the Place(Place) constructor for the Galaxy class
    public Place(){
        //do nothing
    }

    /**
     * will assign stats for languageDemographics and racialDemographics. Should be called after assigning
     * `galaxy` and `hasParent` to their correct values
     */
    public void doStats(){
        //if it is a galaxy, assign demographics irrespective of parent object demographics (it has no parent object)
        if(!hasParent){
            assignDemographic(galaxy.languages, details.languageDemographics);
            assignDemographic(galaxy.races, details.racialDemographics);
        } 
        //otherwise, assign it demographics based on the parent
        else{
            details.languageDemographics = 
                assignRelativeDemographic(galaxy.languages, parent.details.languageDemographics, details.languageDemographics);
            details.racialDemographics =
                assignRelativeDemographic(galaxy.races, parent.details.racialDemographics, details.racialDemographics);
        }
    }

    /**
     * Assigns stats by referencing the parent object's demographics. Has a small chance of removing a stat from the demographic
     * and including a new one instead. For those which it does not overwrite, it will deviate them by +/- (0.9 * 25%),
     * or in the case that the value is less than 25%, it will deviate by +/- (0.9 * n%), where n is the value of the stat.
     * @param statList the list of all possible stats for that type (for instance, the language list, attached to the Galaxy object)
     * @param parentDemographic the Map<> object which contains the related demographics data for the parent object
     * @param writeTo the Map<> object you wish this function to write the new data to for this child object
     */
    public Map<String, Double> assignRelativeDemographic(List<String> statList, Map<String, Double> parentDemographic, Map<String, Double> writeTo){
        double percentLeft = 100.0;
        int size = statList.size();
        writeTo = new HashMap<String,Double>(parentDemographic);
        List<String> newStats = new ArrayList<String>();
        //5 random stats
        for(int i = 0; i < writeTo.size(); ++i){
            double percent;
            String myStat;
            //Stats have a 20% chance of being replaced
            if(Descriptor.randDouble(0.0, 1.0) < 0.2){
                //delete a random value
                String removeVal = randomInSet(writeTo.keySet(), 1).get(0);
                System.out.printf("removing '%s' from `writeTo`\n", removeVal);
                writeTo.remove(removeVal);
                
                //get the percentage for the new stat
                percent = randomPercentage(i, percentLeft);
                percentLeft -= percent;

                //picks a random stat in the list of stats from the Galaxy object
                //NOTE: there is a small chance that the same stat value will be picked. Keep in mind
                //  when balancing the probability of a stat being replaced (compensate by using a higher probability)
                do{
                    myStat = statList.get(Descriptor.randInt(0, size));
                } while (writeTo.containsKey(myStat));

                //add the new stat to the list newStats
                newStats.add(myStat);

                //now put the stat in with it's chosen percentage onto the demographic HashMap
                writeTo.put(myStat, percent);
            }
        }

        //iterate through the demographic map and deviate the stats borrowed from parent object
        double diff = 0.0;
        int remaining = writeTo.size();
        for (String key : writeTo.keySet()) {
            //decrement remaining counter
            remaining--;

            //don't mess with new stats
            if(newStats.contains(key)){
                System.out.printf("Skipping this stat (contents of newStats:  %s\n"+
                    "\tname: %s\n"+
                    "\tparent: %s\n\n", 
                    Arrays.toString(newStats.toArray()), name, parent.name);
                continue;
            }
            

            //max change should be either 90% of original value, or 90% of 25 (so that if the value is 26 we don't reduce it by more than 90%)
            double max;
            double ratioOfChange = 0.9; //a value can't change by more than 0.9x it's original value
            if(writeTo.get(key) > 25.0){
                max = 25.0 * ratioOfChange;
            } else {
                max = writeTo.get(key) * ratioOfChange;
            }

            //generate value
            double change = Descriptor.randDouble(-1 * max, max);

            //ensure that all values add up to 100% by messing with the final result a bit
            if(remaining == 0){
                change = -1 * diff;
            }
            diff += change;

            //change the value by the calculated amount
            writeTo.put(key, writeTo.get(key) + change);
        }

        return writeTo;
    }


    /**
     * Randomly creates demographics for some stat, assigning random percentages to each, which should add up to 100%
     * 
     * This function should only be used on the Galaxy level, or if you're trying to create an isolated region who does
     * not adhere to the greater demographics of its parent object.
     * 
     * Defaults to only list the top 5 stats. This can be specified differently using the (int, double, int) version of the function
     * 
     * @param stat a List<String> of the thing (language, race, etc) you want to be creating a demographic for
     * @param writeTo the reference to the Map<String, Double> object you want the demographics written to
     */
    public static Double randomPercentage(int iterator, double percentLeft){
        double min, max;
        if(iterator == 0){
            min = 25.0;
            max = 75.0;
        } else{
            max = percentLeft;
            if (percentLeft > 50.0){
                min = 25.0;
            } else{
                min = 0.0;
            }
        }

        //now actually pick a random percent number
        Double percent;
        if (max < 0.01){    //if we're out of room, just default everything else to 0.01%
            percent = 0.01;
        } else{
            percent = Descriptor.randDouble(min, max);
        }
        return percent;
    }

    /**
     * Randomly creates demographics for some stat, assigning random percentages to each, which should add up to 100%
     * 
     * This function should only be used on the Galaxy level, or if you're trying to create an isolated region who does
     * not adhere to the greater demographics of its parent object.
     * 
     * Defaults to only doing the top 5 for the stat group. Use the (List<String>, Map<String, Double>, int) version to change that.
     * 
     * @param stat a List<String> of the thing (language, race, etc) you want to be creating a demographic for
     * @param writeTo the reference to the Map<String, Double> object you want the demographics written to
     */
    public void assignDemographic(List<String> stat, Map<String, Double> writeTo){
        assignDemographic(stat, writeTo, 5);
    }

    /**
     * Randomly creates demographics for some stat, assigning random percentages to each, which should add up to 100%
     * 
     * This function should only be used on the Galaxy level, or if you're trying to create an isolated region who does
     * not adhere to the greater demographics of its parent object.
     * 
     * @param stat a List<String> of the thing (language, race, etc) you want to be creating a demographic for
     * @param writeTo the reference to the Map<String, Double> object you want the demographics written to
     * @param count the amount of stats to pull from the list and place onto the Map. Default is 5.
     */
    public void assignDemographic(List<String> stat, Map<String, Double> writeTo, int count){
        double percentLeft = 100.0;
        int size = stat.size();
        //System.out.println("Commencing assignDemographic() for "+ name);
        //5 random stats
        for(int i = 0; i < count; ++i){
            //picks a random stat in the list of stats from the Galaxy object
            String myStat;
            do{
                myStat = stat.get(Descriptor.randInt(0, size));
            } while (writeTo.containsKey(myStat));

            //System.out.printf("percentLeft = %3.2f\n\trunning randomPercentage() now...\n", percentLeft);
            
            Double percent = randomPercentage(i, percentLeft);
            percentLeft -= percent;

            //System.out.printf("percentLeft = %3.2f\n\tpercent = %3.2f\n\t\tstat = %s\n", percentLeft, percent, myStat);


            //now put the stat in with it's chosen percentage onto the demographic HashMap
            writeTo.put(myStat, percent);
        }
    }

    /**
     * Returns a list of several random objects in the given set. The quantity is specified by `count`.
     * Should work in O(n) time, as a result of the way Set's work. The objects can be of any type.
     * @param mySet the set from which to pull the objects.
     * @param count how many random objects to return
     * @return a List of objects, quantity specified by the `count` parameter
     */
    public static <T> List<T> randomInSet(Set<T> mySet, int count){
        int size = mySet.size();
        if(size < count){
            throw new IllegalArgumentException();
        }

        List<Integer> oldValues = new ArrayList<Integer>();
        List<T> output = new ArrayList<T>();

        //do this `count` times
        for (int i = 0; i < count; i++) {
            int a = 0;
            int b;
            //keep picking random values until you find something not in the list of old values
            do{
                b = Descriptor.randInt(0, size);
            } while(oldValues.contains(b));

            //add b to the list of oldValues
            oldValues.add(b);

            //if a == b, return the given element, otherwise, increase a until they are equal.
            //  since we can't have a value `b` higher than `size`, it's garunteed to return a value (and it's random)
            for (T obj : mySet) {
                if(a == b){
                    output.add(obj);
                }
                ++a;
            }
        }
        return output;

    }

    public String getName(){
        return name;
    }
    public Descriptor getDescriptorObject(){    //the object that has all the special stats and things.
        return details;
    }

    //intended to be overwritten, can still be used as `super.generateReport()` in the overwrite.
    public String generateReport(){
        String output = "Racial Demographics:\n";
        for (String key : details.racialDemographics.keySet()) {
            output += String.format("%s:\t%2.2f\n", key, details.racialDemographics.get(key));
        }
        output += "\n";


        //Add the indexables stats name and value to the bottom of the report.
        for (int i = 0; i < details.indexables.length; i++) {
            output += String.format("%s: %2.2f\n", details.indexableNames[i], details.indexables[i]);
        }
        
        return output;
    }
}
