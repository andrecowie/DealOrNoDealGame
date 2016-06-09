package newdeal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * DealOrNoDealGUI Class creates and populates a JFrame with the functionality
 * of the deal or no deal game.
 * 
 * @author Andre Cowie 14862344 
 * @author Tony van Swet 0829113 
 * 
 * @version 29/05/2016
 */
public class DBTest {

    public static void main(String[] args) {
        DBInteractions db = new DBInteractions();
        db.establishConnection();
        db.createGamesTable();
    }
}
