package Space;

import java.util.List;

public class Descriptor {
    
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
    
    public Descriptor(){
        //TODO: generate values
    }

    public Descriptor(Descriptor parentDescriptor){
        //TODO:generate values with averages based on parentDescriptor
    }

}
