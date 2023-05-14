package surprise;

import java.util.Random;

public class RandomBag extends GenericBag {
    @Override
    public ISurprise takeOut() {
        if(surprises.size() != 0){
            Random rng = new Random();
            int random = Math.abs(rng.nextInt()) % surprises.size();
            return surprises.remove(random);
        }
        return null;
    }
}
