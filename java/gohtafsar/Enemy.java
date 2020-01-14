package gohtafsar;

public abstract class Enemy{
	int health;
	int rate;
	Weapon loadout;
	
	int xCoord;
	int yCoord;
	int speed;
	
	public Enemy(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public void status(){
		System.out.println("HEALTH: " + health);
		System.out.println("SPEED: " + speed);
	}
}