import java.util.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/////////////////
/////OBJECTS/////
/////////////////
//Player Character
/**
 * Contains all information pertinent to a "player character"; position, health, color, etc.
 * 
 * @author Alexander
 */
class playerCharacter{
	int x = 0;
	int y = 60;
	int w = 40;
	int h = 80;
	int health = 5;
	int weapon = 0;
	//0 = AR, 1 = SHTGN, 2 = RL, 3 = SR
	int stance = 0;
	//0 = Standing, 1 = Crouching, 2 = Prone
	
	int color;
	//0 = GREEN, 1 = RED, 2 = BLUE, 3 = BLACK
	
	/**
	 * Default constructor for playerCharacter
	 */
	public playerCharacter(){
		color = 0;
	}
	
	/**
	 * Custom constructor for playerCharacter
	 * @param color the color of the object (essentially its identifier)
	 */
	public playerCharacter(int color){
		this.color = color;
	}
}

//Enemy Character
/**
 * Contains all information pertinent to an "enemy character"; position, health, type, etc.
 * 
 * @author Alexander
 */
class enemyCharacter{
	static Random r = new Random();
	
	int x = 1200;
	int w = 40;
	int h = 80;
	int dx = -5;
	int step = 0;
	
	int y;
	int health;
	int weapon;
	//0 = AR, 1 = SHTGN, 2 = RL, 3 = SR
	
	/**
	 * Default constructor for enemyCharacter
	 */
	public enemyCharacter(){
		y = 60;
		h = 1;
		weapon = 0;
	}
	
	/**
	 * Custom constructor for enemyCharacter
	 * 
	 * @param y the y position of the object
	 * @param health the health the object has
	 * @param weapon the type of weapon the object has
	 */
	public enemyCharacter(int y, int health, int weapon){
		this.y = y;
		this.health = health;
		this.weapon = weapon;
	}
	
	/**
	 * Teleports an enemy that has left the screen back onto the screen
	 */
	public void checkBound(){
		if (x < -40){
			x = 1200;
			y = r.nextInt(425) + 60;
		}
	}
}

//Friendly Bullet
/**
 * Contains all information pertinent to a "friendly bullet"; position, speed, type, etc.
 * 
 * @author Alexander
 */
class frBullet{
	int w = 20;
	int h = 10;

	int x;
	int y;
	int type;
	//0 = AR, 1 = SHTGN, 2 = RL, 3 = SR
	
	/**
	 * Default constructor for frBullet
	 */
	public frBullet(){
		x = 0;
		y = 0;
		type = 0;
	}
	
	/**
	 * Custom constructor for frBullet
	 * 
	 * @param x the x position of the object
	 * @param y the y position of the object
	 * @param type the type of the object
	 */
	public frBullet(int x, int y, int type){
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	/**
	 * Checks for a collision between a frBullet and enemyCharacter
	 * 
	 * @param ec for an enemyCharacter
	 * @return true if the frBullet collides with the enemyCharacter
	 * @return false if the bullet does not collide
	 */
	public boolean hit(enemyCharacter ec){
		if (x + 10 > ec.x && y + 5 > ec.y && y + 5 < ec.y + 80){
			return true;
		}
		return false;
	}
	
	/**
	 * Removes an offscreen bullet
	 * 
	 * @return true if bullet is offscreen
	 * @return false if bullet is not offscreen
	 */
	public boolean delete(){
		if (x > 1200){
			return true;
		}
		return false;
	}
}

//Enemy Bullet
/**
 * Contains all information pertinent to an "enemy bullet"; position, speed, type, etc.
 * 
 * @author Alexander
 */
class enBullet{
	int w = 20;
	int h = 10;

	int x;
	int y;
	int type;
	//0 = AR, 1 = SHTGN, 2 = RL, 3 = SR
	
	/**
	 * Default constructor for enBullet
	 */
	public enBullet(){
		x = 0;
		y = 0;
		type = 0;
	}
	
	/**
	 * Custom constructor for enBullet
	 * 
	 * @param x the x position of the object
	 * @param y the y position of the object
	 * @param type the type of the object
	 */
	public enBullet(int x, int y, int type){
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	/**
	 * Checks for a collision between a enBullet and playerCharacter
	 * 
	 * @param pc for a playerCharacter
	 * @return true if the enBullet collides with the playerCharacter
	 * @return false if the bullet does not collide
	 */
	public boolean hit(playerCharacter pc){
		if (x + 10 < pc.x + 40 && x +10 > pc.x && y + 5 > pc.y && y + 5 < pc.y + 80){
			return true;
		}
		return false;
	}
	
	/**
	 * Removes an offscreen bullet
	 * 
	 * @return true if bullet is offscreen
	 * @return false if bullet is not offscreen
	 */
	public boolean delete(){
		if (x < -20){
			return true;
		}
		return false;
	}
}

/*
class powerup{
	static Random r = new Random();
	
	int x = 1200;
	int s = 30;
	int dx = -5;
	
	int y;
	int type;
	//0 = health, 1 = camo, 2 = air strike
}
*/

////////////////////////////////////////////
/////PROGRAM START: OBJECT DEFINITIONS/////
////////////////////////////////////////////
/**
 * Contains all logic necessary to run GOHTAFTSAR. 
 * Class extends JPanel and implements KeyListener
 * Java window appears on execution wherein the game takes place.
 * 
 * @author Alexander
 */
@SuppressWarnings("serial")
public class GOHTAFTSAR extends JPanel implements KeyListener{
	static Scanner kb = new Scanner(System.in);
	static Random r = new Random();
	static GOHTAFTSAR game = new GOHTAFTSAR();
	ArrayList<playerCharacter> playerList;
	ArrayList<frBullet> bulletList;
	ArrayList<enemyCharacter> enemyList;
	ArrayList<enBullet> projList;
	
	///////////////
	/////PAINT/////
	///////////////
	/**
	 * Paints all of the game's objects
	 * 
	 * @param g for Graphics
	 */
	public void paint(Graphics g){
		super.paint(g);
		
		//Player Character
		for (playerCharacter pc : game.playerList){
			g.setColor(Color.WHITE);
			g.fillRect(pc.color * 250 + 25 + 40 * pc.color, 20, 250, 20);
			if (pc.color == 0)
				g.setColor(Color.GREEN);
			if (pc.color == 1)
				g.setColor(Color.RED);
			if (pc.color == 2)
				g.setColor(Color.BLUE);
			if (pc.color == 3)
				g.setColor(Color.BLACK);
			g.fillRect(pc.x, pc.y, pc.w, pc.h);
			g.fillRect(pc.color * 250 + 25 + 40 * pc.color, 20, pc.health * 50, 20);
		}
		
		//Friendly Bullet
		for (frBullet b : game.bulletList){
			g.fillRect(b.x, b.y, b.w, b.h);
		}
		
		//Enemy Character
		for (enemyCharacter ec : game.enemyList){
			if (ec.weapon == 0)
				g.setColor(Color.GREEN);
			if (ec.weapon == 1)
				g.setColor(Color.RED);
			if (ec.weapon == 2)
				g.setColor(Color.BLUE);
			if (ec.weapon == 3)
				g.setColor(Color.BLACK);
			g.fillRect(ec.x, ec.y, ec.w, ec.h);
		}
		
		//Enemy Bullet
		g.setColor(Color.BLACK);
		for (enBullet b : game.projList){
			g.fillRect(b.x, b.y, b.w, b.h);
		}
	}
	
	/////////////////////////////
	/////KEY INPUT VARIABLES/////
	/////////////////////////////
	static boolean p1l;
	static boolean p1r;
	static boolean p1u;
	static boolean p1d;
	static boolean p1f;
	static boolean p1c;
	
	static boolean p2l;
	static boolean p2r;
	static boolean p2u;
	static boolean p2d;
	static boolean p2f;
	static boolean p2c;
	
	static boolean p3l;
	static boolean p3r;
	static boolean p3u;
	static boolean p3d;
	static boolean p3f;
	static boolean p3c;
	
	static boolean p4l;
	static boolean p4r;
	static boolean p4u;
	static boolean p4d;
	static boolean p4f;
	static boolean p4c;
	
	/**
	 * Method for recognizing keyboard input; when a key is a "high" (pressed)
	 * 
	 * @param e for KeyEvent
	 */
	public void keyPressed(KeyEvent e){
		//PLAYER 1
		if (e.getKeyChar() == 'a')
	        p1l = true;
        if (e.getKeyChar() == 'd')
            p1r = true;
        if (e.getKeyChar() == 'w')
            p1u = true;
        if (e.getKeyChar() == 's')
            p1d = true;
        if (e.getKeyChar() == 'e')
            p1f = true;
		if (e.getKeyChar() == 'z')
	        p1c = true;
        
        //PLAYER 2
        if (e.getKeyChar() == 'f')
	        p2l = true;
        if (e.getKeyChar() == 'h')
            p2r = true;
        if (e.getKeyChar() == 't')
            p2u = true;
        if (e.getKeyChar() == 'g')
            p2d = true;
        if (e.getKeyChar() == 'y')
            p2f = true;
		if (e.getKeyChar() == 'v')
	        p2c = true;
        
        //PLAYER 3
        if (e.getKeyChar() == 'j')
	        p3l = true;
        if (e.getKeyChar() == 'l')
            p3r = true;
        if (e.getKeyChar() == 'i')
            p3u = true;
        if (e.getKeyChar() == 'k')
            p3d = true;
        if (e.getKeyChar() == 'o')
            p3f = true;
		if (e.getKeyChar() == 'm')
	        p3c = true;
        
        //PLAYER 4
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
	        p4l = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            p4r = true;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            p4u = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            p4d = true;
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD0)
            p4f = true;
		if (e.getKeyCode() == KeyEvent.VK_NUMPAD1)
            p4c = true;
	}
	/**
	 * Method for recognizing keyboard input; when a key is a "low" (released)
	 * 
	 * @param e for KeyEvent
	 */
	public void keyReleased(KeyEvent e){
		//PLAYER 1
		if (e.getKeyChar() == 'a')
	        p1l = false;
        if (e.getKeyChar() == 'd')
            p1r = false;
        if (e.getKeyChar() == 'w')
            p1u = false;
        if (e.getKeyChar() == 's')
            p1d = false;
        if (e.getKeyChar() == 'e')
            p1f = false;
        if (e.getKeyChar() == 'z')
	        p1c = false;
        
        //PLAYER 2
        if (e.getKeyChar() == 'f')
	        p2l = false;
        if (e.getKeyChar() == 'h')
            p2r = false;
        if (e.getKeyChar() == 't')
            p2u = false;
        if (e.getKeyChar() == 'g')
            p2d = false;
        if (e.getKeyChar() == 'y')
            p2f = false;
        if (e.getKeyChar() == 'v')
	        p2c = false;
        
        //PLAYER 3
        if (e.getKeyChar() == 'j')
	        p3l = false;
        if (e.getKeyChar() == 'l')
            p3r = false;
        if (e.getKeyChar() == 'i')
            p3u = false;
        if (e.getKeyChar() == 'k')
            p3d = false;
        if (e.getKeyChar() == 'o')
            p3f = false;
        if (e.getKeyChar() == 'm')
	        p3c = false;
        
        //PLAYER 4
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
	        p4l = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            p4r = false;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            p4u = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            p4d = false;
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD0)
            p4f = false;
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD1)
            p4c = false;
	}
	/**
	 * Method for recognizing keyboard input; not used, but required by KeyListener
	 * 
	 * @param e for KeyEvent
	 */
	public void keyTyped(KeyEvent e){}
	
	////////////////////////////////////
	/////GAME START: INITIALIZATION/////
	////////////////////////////////////
	/**
	 * Main method for execution of game
	 * 
	 * @author Alexander
	 */
	public static void main(String[] args) throws InterruptedException{
		game.playerList = new ArrayList<playerCharacter>();
		game.bulletList = new ArrayList<frBullet>();
		game.enemyList = new ArrayList<enemyCharacter>();
		game.projList = new ArrayList<enBullet>();
		
		//Number of Players
		System.out.println("How many players? ");
        int playerCount = kb.nextInt();
        if (playerCount < 1)
        	playerCount = 1;
        if (playerCount > 4)
        	playerCount = 4;
        for (int i = 0; i < playerCount; i++){
        	game.playerList.add(new playerCharacter(i));
        }
        
        //Frame Creation
        JFrame frame = new JFrame("\'Gears of Halo Theft Auto 5: The Salvation Army Edition\' Demastered");
        frame.addKeyListener(game);
        frame.setSize(1200, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        
        //Game Loop Initialization
        int oneStepFire = 40;
        int twoStepFire = 40;
        int thrStepFire = 40;
        int fouStepFire = 40;
        
        int oneStepCrouch = 3;
        int twoStepCrouch = 3;
        int thrStepCrouch = 3;
        int fouStepCrouch = 3;
        
        int enSpawnAR = 0;
        int enSpawnSG = 0;
        int enSpawnRPG = 0;
        int enSpawnBOS = 0;
        
        int yPos = 0;
        
        ///////////////////
        /////GAME LOOP/////
        ///////////////////
        while(true){
        	//Enemy Spawn
        	if (enSpawnAR == 60){
        		enSpawnAR = 0;
        		yPos = r.nextInt(425) + 60;
        		game.enemyList.add(new enemyCharacter(yPos, 1, 0));
        	}
        	if (enSpawnSG == 120){
        		enSpawnSG = 0;
        		yPos = r.nextInt(425) + 60;
        		game.enemyList.add(new enemyCharacter(yPos, 1, 1));
        	}
        	if (enSpawnRPG == 200){
        		enSpawnRPG = 0;
        		yPos = r.nextInt(425) + 60;
        		game.enemyList.add(new enemyCharacter(yPos, 1, 2));
        	}
        	if (enSpawnBOS == 300){
        		enSpawnBOS = 0;
        		yPos = r.nextInt(425) + 60;
        		game.enemyList.add(new enemyCharacter(yPos, 4, 3));
        	}
        	
        	//Enemy Loop
        	for (enemyCharacter ec : game.enemyList){
        		ec.x += ec.dx;
        		ec.step += 1;
        		if (ec.step == 80){
        			ec.step = 0;
        			game.projList.add(new enBullet(ec.x - 20, ec.y + 20, ec.weapon));
        		}
        		ec.checkBound();
        	}
        	
        	//Enemy Bullet Loop
        	for (enBullet b : game.projList){
        		if (b.type == 0)
        			b.x += -10;
        		if (b.type == 1)
        			b.x += -10;
        		if (b.type == 2)
        			b.x += -15;
        		if (b.type == 3)
        			b.x += -20;
        		for (int i = 0; i < game.playerList.size(); i++){
        			if (b.hit(game.playerList.get(i))){
        				game.playerList.get(i).health -= 1;
        				b.y = -10;
        				b.x = -20;
        			}
        		}
        	}
        	
        	//Enemy Bullet Deletion
        	for (int i = 0; i < game.projList.size(); i++){
                if (game.projList.get(i).delete()){
                    game.projList.remove(i);                }
            }
        	
        	//Player Loop
        	for (playerCharacter pc : game.playerList){
        		//PLAYER 1
        		if (p1l && pc.color == 0 && pc.x > 0 && pc.stance != 2)
        			pc.x -= 5;
        		if (p1r && pc.color == 0 && pc.x < 1145 && pc.stance != 2)
        			pc.x += 5;
        		if (p1u && pc.color == 0 && pc.y > 60 && pc.stance != 2)
        			pc.y -= 5;
        		if (p1d && pc.color == 0 && pc.y < 485 && pc.stance != 2)
        			pc.y += 5;
        		if (p1f && pc.color == 0 && oneStepFire > 40 && pc.stance == 0){
        			oneStepFire = 0;
        			game.bulletList.add(new frBullet(pc.x + 40, pc.y + 20, pc.weapon));
        		}
        		if (p1c && pc.color == 0 && oneStepCrouch >= 3){
        			oneStepCrouch = 0;
        			if (pc.stance == 0){
        				pc.stance = 1;
        				pc.y += 25;
        				pc.h = 55;
        			}
        			else if (pc.stance == 1){
        				pc.stance = 2;
        				pc.y += 25;
        				pc.h = 30;
        			}
        			else if (pc.stance == 2){
            			pc.stance = 0;
            			pc.y -= 50;
            			pc.h = 80;
        			}
        		}
        		
        		//PLAYER 2
        		if (p2l && pc.color == 1 && pc.x > 0 && pc.stance != 2)
        			pc.x -= 5;
        		if (p2r && pc.color == 1 && pc.x < 1145 && pc.stance != 2)
        			pc.x += 5;
        		if (p2u && pc.color == 1 && pc.y > 60 && pc.stance != 2)
        			pc.y -= 5;
        		if (p2d && pc.color == 1 && pc.y < 485 && pc.stance != 2)
        			pc.y += 5;
        		if (p2f && pc.color == 1 && twoStepFire > 40 && pc.stance == 0){
        			twoStepFire = 0;
        			game.bulletList.add(new frBullet(pc.x + 40, pc.y + 20, pc.weapon));
        		}
        		if (p2c && pc.color == 1 && twoStepCrouch >= 3){
        			twoStepCrouch = 0;
        			if (pc.stance == 0){
        				pc.stance = 1;
        				pc.y += 25;
        				pc.h = 55;
        			}
        			else if (pc.stance == 1){
        				pc.stance = 2;
        				pc.y += 25;
        				pc.h = 30;
        			}
        			else if (pc.stance == 2){
            			pc.stance = 0;
            			pc.y -= 50;
            			pc.h = 80;
        			}
        		}
        		
        		//PLAYER 3
        		if (p3l && pc.color == 2 && pc.x > 0 && pc.stance != 2)
        			pc.x -= 5;
        		if (p3r && pc.color == 2 && pc.x < 1145 && pc.stance != 2)
        			pc.x += 5;
        		if (p3u && pc.color == 2 && pc.y > 60 && pc.stance != 2)
        			pc.y -= 5;
        		if (p3d && pc.color == 2 && pc.y < 485 && pc.stance != 2)
        			pc.y += 5;
        		if (p3f && pc.color == 2 && thrStepFire > 40 && pc.stance == 0){
        			thrStepFire = 0;
        			game.bulletList.add(new frBullet(pc.x + 40, pc.y + 20, pc.weapon));
        		}
        		if (p3c && pc.color == 2 && thrStepCrouch >= 3){
        			thrStepCrouch = 0;
        			if (pc.stance == 0){
        				pc.stance = 1;
        				pc.y += 25;
        				pc.h = 55;
        			}
        			else if (pc.stance == 1){
        				pc.stance = 2;
        				pc.y += 25;
        				pc.h = 30;
        			}
        			else if (pc.stance == 2){
            			pc.stance = 0;
            			pc.y -= 50;
            			pc.h = 80;
        			}
        		}
        		
        		//PLAYER 4
        		if (p4l && pc.color == 3 && pc.x > 0 && pc.stance != 2)
        			pc.x -= 5;
        		if (p4r && pc.color == 3 && pc.x < 1145 && pc.stance != 2)
        			pc.x += 5;
        		if (p4u && pc.color == 3 && pc.y > 60 && pc.stance != 2)
        			pc.y -= 5;
        		if (p4d && pc.color == 3 && pc.y < 485 && pc.stance != 2)
        			pc.y += 5;
        		if (p4f && pc.color == 3 && fouStepFire > 40 && pc.stance == 0){
        			fouStepFire = 0;
        			game.bulletList.add(new frBullet(pc.x + 40, pc.y + 20, pc.weapon));
        		}
        		if (p4c && pc.color == 3 && fouStepCrouch >= 3){
        			fouStepCrouch = 0;
        			if (pc.stance == 0){
        				pc.stance = 1;
        				pc.y += 25;
        				pc.h = 55;
        			}
        			else if (pc.stance == 1){
        				pc.stance = 2;
        				pc.y += 25;
        				pc.h = 30;
        			}
        			else if (pc.stance == 2){
            			pc.stance = 0;
            			pc.y -= 50;
            			pc.h = 80;
        			}
        		}
        		
        		//Prone "Move"
        		if (pc.stance == 2)
        			pc.x -= 5;
    			
	    		//Player "Deletion"
	    		if (pc.health == 0){
	    			pc.x = 1200;
	    			pc.y = 600;
	    		}
	    		if (pc.x < 0){
	    			pc.x = 1200;
	    			pc.y = 600;
	    		}
        	}
        	
        	//Friendly Bullet Loop
        	for (frBullet b : game.bulletList){
        		if (b.type == 0)
        			b.x += 10;
        		if (b.type == 1)
        			b.x += 10;
        		if (b.type == 2)
        			b.x += 15;
        		if (b.type == 3)
        			b.x += 20;
        		for (int i = 0; i < game.enemyList.size(); i++){
        			if (b.hit(game.enemyList.get(i))){
        				game.enemyList.get(i).health -= 1;
        				if (game.enemyList.get(i).health == 0)
        					game.enemyList.remove(i);
        				b.y = -10;
        				b.x = 1240;
        			}
        		}
        	}
        	
        	//Friendly Bullet Deletion
        	for (int i = 0; i < game.bulletList.size(); i++){
                if (game.bulletList.get(i).delete()){
                    game.bulletList.remove(i);               
                }
            }
        	
        	
        	//Mechanics
        	Thread.sleep(50);
        	game.repaint();
        	
        	oneStepFire += 1;
        	twoStepFire += 1;
        	thrStepFire += 1;
        	fouStepFire += 1;
        	
        	oneStepCrouch += 1;
            twoStepCrouch += 1;
            thrStepCrouch += 1;
            fouStepCrouch += 1;
        	
        	enSpawnAR += 1;
        	enSpawnSG += 1;
        	enSpawnRPG += 1;
        	enSpawnBOS += 1;
        }
	}
}

/*
-unique bullets should spawn non-uniques
	-a shotgun "bullet" spawns two additional regular bullets
	
-powerups
	-active camo
	-health pack
	-air strike
	-berserk(?)
	
-weapons
	-shotgun
	-rpg
	-sniper
	-sword(?)
	-minigun(?)
*/