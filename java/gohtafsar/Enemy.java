package gohtafsar;
import java.util.*;

public abstract class Enemy{
	int health;
	Weapon loadout;
	
	int xCoord;
	int yCoord;
	int speed;
	
	//int w;
	//int h;
	
	public Enemy(){
		xCoord = 1080;
		yCoord = r.nextInt(1720) + 100;
	}
	
	public void loopEnemy(){
		xCoord = 1080;
		yCoord = r.nextInt(1720) + 100;
	}
	
	public void status(){
		System.out.println("HEALTH: " + health);
		System.out.println("SPEED: " + speed);
	}
}