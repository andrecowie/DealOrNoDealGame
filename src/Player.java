import java.util.HashMap;

/**
 * Created by Andre Cowie 14862344 on 5/04/2016.
 */
public class Player {
    private String name;
    private HashMap<Case, Integer> games;

    public Player(String contestant_name){
        //if contestant name is in db load previous game and comment on how much money they left with and what case they selected potentially sysdate of when they played?
        setName(contestant_name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
