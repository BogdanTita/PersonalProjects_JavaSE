package surprise;

import java.util.ArrayList;
import java.util.Collection;

public abstract class GenericBag implements IBag {

    ArrayList<ISurprise> surprises = new ArrayList<ISurprise>();

    @Override
    public void put(ISurprise newSurprise) {
        this.surprises.add(newSurprise);
    }

    @Override
    public void put(IBag bagOfSurprises) {
        while(!bagOfSurprises.isEmpty()){
            this.put(bagOfSurprises.takeOut());
        }
    }

    @Override
    public boolean isEmpty() {
        return surprises.size() == 0;
    }

    @Override
    public int size() {
        return surprises.size();
    }
}
