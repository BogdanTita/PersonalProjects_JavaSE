package surprise;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GiveSurpriseAndApplause applause = new GiveSurpriseAndApplause("RANDOM", 1);
        ISurprise[] surprises = GatherSurprises.gather(4);
        for(ISurprise item: surprises){
            applause.put(item);
        }
        applause.give();

        GiveSurpriseAndHug hugs = new GiveSurpriseAndHug("FIFO", 1);
        surprises = GatherSurprises.gather(3);
        for(ISurprise item : surprises){
            hugs.put(item);
        }
        hugs.give();

        applause.put(hugs.getBag());
        applause.giveAll();

        GiveSurpriseAndSing songs = new GiveSurpriseAndSing("LIFO", 1);
        surprises = GatherSurprises.gather(2);
        for(ISurprise item : surprises){
            songs.put(item);
        }
        songs.giveAll();
    }
}
