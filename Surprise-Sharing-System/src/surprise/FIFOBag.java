package surprise;

import java.util.Random;

public class FIFOBag extends GenericBag {
    @Override
    public ISurprise takeOut() {
        if(!surprises.isEmpty()){
            return surprises.remove(0);
        }
        return null;
    }
}
