package surprise;

import java.util.Random;

public class Candies implements ISurprise{

    private static final String[] candies = {"chocolate", "jelly", "fruits", "vanilla", "peanut", "salty"};
    private final String candieType;
    private final int candiesNo;

    private Candies(String candieType, int candiesNo){
        this.candieType = candieType;
        this.candiesNo = candiesNo;
    }
    public static Candies generate(){
        Random rng = new Random();
        int randomType = Math.abs(rng.nextInt()) % candies.length;
        int candiesNo = Math.abs(rng.nextInt()) % 10;
        if(candiesNo < 3){
            candiesNo = 3;
        }
        return new Candies(candies[randomType], candiesNo);
    }

    @Override
    public void enjoy() {
        System.out.println("You have received " + this.candiesNo + " candies tasting like " + this.candieType);
    }
}
