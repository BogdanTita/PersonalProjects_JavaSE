package surprise;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Random;

public final class GatherSurprises {

    public static ISurprise gather(){
        Random rng = new Random();
        int random = Math.abs(rng.nextInt()) % 3;
        switch (random){
            case 0:
                return FortuneCookie.generate();
            case 1:
                return Candies.generate();
            case 2:
                return MinionToy.generate();
        }
        return null;
    }

    public static ISurprise[] gather(int n){
        ISurprise[] surprises = new ISurprise[n];
        for(int i = 0; i < n; i++){
            surprises[i] = GatherSurprises.gather();
        }
        return surprises;
    }

    private GatherSurprises(){ //pt a nu putea fi instantiat

    }
}
