package surprise;

import java.util.Random;

public class FortuneCookie implements ISurprise{

    private static final String[] quotes = {"The fortune you seek is in another cookie.", "A closed mouth gathers no feet.", "A conclusion is simply the place where you got tired of thinking.",
        "A cynic is only a frustrated optimist", "A foolish man listens to his heart. A wise man listens to cookies", "You will die alone and poorly dressed.",
        "A fanatic is one who can't change his mind, and won't change the subject.", "If you look back, you’ll soon be going that way.",
        "You will live long enough to open many fortune cookies.", "An alien of some sort will be appearing to you shortly.", "Do not mistake temptation for opportunity.",
        "Flattery will go far tonight.", "He who laughs at himself never runs out of things to laugh at.", "He who laughs last is laughing at you.", "He who throws dirt is losing ground.",
        "Some men dream of fortunes, others dream of cookies.", "The greatest danger could be your stupidity.", "We don’t know the future, but here’s a cookie.",
        "The world may be your oyster, but it doesn't mean you'll get its pearl.", "Don’t eat the paper."};
    private final String message;

    private FortuneCookie(String message){
        this.message = message;
    }
    public static FortuneCookie generate(){
        Random rng = new Random();
        int random = Math.abs(rng.nextInt()) % quotes.length;
        return new FortuneCookie(quotes[random]);
    }

    @Override
    public void enjoy() {
        System.out.println("This is your fortune: " + this.message);
    }
}
