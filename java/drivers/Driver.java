package drivers;

import gohtafsar.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Driver extends Frame{
	
	public Player player1 = new Player();
	
	public Driver(){
		super("GOHTAFSAR V1.0");
		prepareGUI();
		setVisible(true);
	}
	
	public void prepareGUI(){
		setSize(800,600);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});
	}
	
	
	public void test(){
		player1.status();
		
		System.out.println("FIRING WEAPON 10 TIMES");
		
		for(int i = 0; i < 10; i++)
			player1.getLO().fire();
		
		player1.getLO().status();
		
		System.out.println("RELOADING");
		
		player1.getLO().reload();
		player1.getLO().status();
		
		for(int i = 20; i < 600; i++){
			player1.setxCoord(i);
			repaint();
			try{
				Thread.sleep(50);
			}catch(Exception e){
				
			}
		}
		
		
		
	}
	
	//Paint runs whenever repaint is called in driver class - paints graphics
	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.GREEN);
		g2.fillRect(player1.getxCoord(), player1.getyCoord(), 20, 80);
		
		
		
		
   }
	
}