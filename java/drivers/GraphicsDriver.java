package drivers;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GraphicsDriver extends Frame {
       
   public GraphicsDriver(){
      super("GOHTAFSAR V1.0");
      prepareGUI();
   }


   private void prepareGUI(){
      setSize(400,400);
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      }); 
   }    

   @Override
   public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
         RenderingHints.VALUE_ANTIALIAS_ON);
      Font font = new Font("Serif", Font.PLAIN, 24);
      g2.setFont(font);
      g2.drawString("This is THE FIRST GRAPHICS of gohtafsar", 50, 70); 
	  
	  
   }
}