/**
 * DisplayPrize Class extends JPanel with a selected image placed within the 
 * JPanel
 * 
 * @author Andre Cowie 14862344 
 * @author Tony van Swet 0829113 
 * 
 * @version 29/05/2016
 */
package newdeal;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;



/**
 *
 * @author TonyAlien
 */
public class DisplayPrize extends JPanel{
    private Image img;
    
    public DisplayPrize(){
        this.img = Toolkit.getDefaultToolkit().createImage("raining-money.jpg");
    }
    public void paint(Graphics g){
        g.drawImage(img, 0 , 0 ,null);
    }
}

