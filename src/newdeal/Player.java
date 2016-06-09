package newdeal;

import java.util.HashMap;

/**
 * Player class holds a player object to be used with the database
 * 
 * Created by 
 * @author Andre Cowie 14862344 on 5/04/2016.
 * @author Tony van Swet 0829113
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
