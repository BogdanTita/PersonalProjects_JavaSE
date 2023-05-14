package surprise;

import java.util.Random;

public class MinionToy implements ISurprise{

    private static final String[] names = {"Dave", "Carl", "Kevin", "Stuart", "Jerry", "Tim"};
    private static int cnt;
    private final String minionName;

    private MinionToy(String minionName){
        this.minionName = minionName;
    }

    public static MinionToy generate(){
        if(cnt == names.length - 1){
            cnt = 0;
        }
        MinionToy result = new MinionToy(names[cnt]);
        cnt++;
        return result;
    }

    @Override
    public void enjoy() {
        System.out.println("You have received a minion named " + this.minionName);
    }
}
