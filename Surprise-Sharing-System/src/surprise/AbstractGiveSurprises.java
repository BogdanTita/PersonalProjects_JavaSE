package surprise;

import java.util.concurrent.TimeUnit;

public abstract class AbstractGiveSurprises{

    private IBag bag;
    private int waitTime;

    public AbstractGiveSurprises(){

    }
    public AbstractGiveSurprises(String bagType, int waitTime){
        this.waitTime = waitTime;
        this.bag = new BagFactory().makeBag(bagType);
    }

    public void put(ISurprise newSurprise){
        this.bag.put(newSurprise);
    }

    public void put(IBag newSurprises){
        while(!newSurprises.isEmpty()){
            this.put(newSurprises.takeOut());
        }
    }

    public IBag getBag(){
        return bag;
    }

    public ISurprise give(){ // ///de vazut ce trebuie sa returneze
        ISurprise surprise = this.bag.takeOut();
        giveWithPassion();
        surprise.enjoy();
        try {
            TimeUnit.SECONDS.sleep(waitTime); // number of seconds to sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return surprise;
    }

    public void giveAll(){// ///de vazut ce trebuie sa returneze
        while(!bag.isEmpty()){
            this.give();
        }
    }

    public boolean isEmpty(){
        return this.bag.isEmpty();
    }

    abstract void giveWithPassion();
}
