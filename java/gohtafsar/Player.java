package gohtafsar;

public class Player{
	//player has health and shields
	int health = 100;
	int shields = 100;
	
	//coords will be for position, speed used to update coords

	int xCoord = 20;
	int yCoord = 200;
	int speed = 4;
	
	//controls 0 will correspond to WASD, 1 to arrow keys
	int controls = 0;
	//weapon object
	Weapon loadout;
	
	//default constructor gives player default weapon (assault rifle)
	public Player(){
		loadout = new Weapon();
	}
	
	public Player(int x, int y, int cont, Weapon weap){
		xCoord = x;
		yCoord = y;
		controls = cont;
		loadout = weap;
	}
	
	public void status(){
		System.out.println("HEALTH: " + health);
		System.out.println("SHIELDS: " + shields);
		System.out.println("LOADOUT: ");
			loadout.status();
		System.out.println("CONTROLS: " + controls);
	}
	
	//getters and setters
	public Weapon getLO(){
		return loadout;
	}
	
	public int getxCoord(){
		return xCoord;
	}
	
	public int getyCoord(){
		return yCoord;
	}
	
	public void setxCoord(int x){
		xCoord = x;
	}
	
	public void setyCoord(int y){
		yCoord = y;
	}
}