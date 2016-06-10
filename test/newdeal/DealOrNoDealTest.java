/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newdeal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abc
 */
public class DealOrNoDealTest {
    
    public DealOrNoDealTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Offer method, of class DealOrNoDeal.
     */
    @Test
    public void testOffer() {
        System.out.println("Offer");
        DealOrNoDeal instance = new DealOrNoDeal(" ", 0);
        int expResult = 39107;
        int result = instance.Offer();
        assertEquals(expResult, result);
    }

    
    /**
     * Test of welcomePlayer method, of class DealOrNoDeal.
     */
    @Test
    public void testWelcomePlayer() {
        System.out.println("welcomePlayer");
        String expResult = " ";
        InputStream in = new ByteArrayInputStream(" ".getBytes());
        System.setIn(in);
        String result = DealOrNoDeal.welcomePlayer();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCases method, of class DealOrNoDeal.
     */
    @Test(expected=NullPointerException.class)
    public void testGetCases() {
        System.out.println("getCases");
        DealOrNoDeal instance = null;
        Case[] expResult = null;
        Case[] result = instance.getCases();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setCases method, of class DealOrNoDeal.
     */
    @Test
    public void testSetCases() {
        System.out.println("setCases");
        Case[] cases = new Case[26];
        DealOrNoDeal instance = new DealOrNoDeal("", 0);
        instance.setCases(cases);
    }

    /**
     * Test of getSelectedCase method, of class DealOrNoDeal.
     */
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testGetSelectedCase() {
        System.out.println("getSelectedCase");
        for(int x=0;x<100;x++){
            DealOrNoDeal instance = new DealOrNoDeal("", x);
            int expResult=1;
            if(x >= 0 && x<26){
                expResult = x;
            }
            int result = instance.getSelectedCase();
            assertEquals(expResult, result);
        }    
    }

    /**
     * Test of setSelectedCase method, of class DealOrNoDeal.
     */
    @Test
    public void testSetSelectedCase() {
        System.out.println("setSelectedCase");
        DealOrNoDeal instance = new DealOrNoDeal("", 1);
        for(int x = 0;x<26; x++){
            int selectedCase = x;
            instance.setSelectedCase(selectedCase);
        }
    }

    /**
     * Test of getContestant method, of class DealOrNoDeal.
     */
    @Test
    public void testGetContestant() {
        System.out.println("getContestant");
        DealOrNoDeal instance = new DealOrNoDeal("", 1);
        Player expResult = new Player("");
        Player result = instance.getContestant();
        assertEquals(expResult, result);
        
    }
    
}
