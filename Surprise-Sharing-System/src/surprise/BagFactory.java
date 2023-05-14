package surprise;

public class BagFactory implements IBagFactory{

    @Override
    public IBag makeBag(String type) {
        switch (BagTypesEnum.valueOf(type)){
            case RANDOM:
                return new RandomBag();
            case FIFO:
                return new FIFOBag();
            case LIFO:
                return new LIFOBag();
        }
        return null;
    }
}
