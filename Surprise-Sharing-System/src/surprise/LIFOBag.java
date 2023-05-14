package surprise;

import java.util.Random;

public class LIFOBag extends GenericBag {
    @Override
    public ISurprise takeOut() {
        if(!surprises.isEmpty()){
            return surprises.remove(surprises.size() - 1);
        }
        return null;
    }
}
