package gohtafsar;

public class Player{
	//player has health and shields
	int health = 1;
	int shields = 4;
	
	//coords will be for position, speed used to update coords
	int xCoord = 100;
	int yCoord = 200;
	int speed = 4;
	
	//control array, 0 WASD, 1 TFGH, 2 IJKL, 3 arrow keys
	int controls;
	//weapon object
	Weapon loadout;
	
	//width, height
	int w = 66;
	int h = 90
	
	//default constructor gives player default weapon (assault rifle)
	public Player(){
		controls = 0;
		loadout = new Weapon();
	}
	
	public Player(int cont){
		controls = cont;
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
	
	public int getW(){
		return w;
	}
	
	public int getH(){
		return h;
	}
	
	public void setxCoord(int x){
		xCoord = x;
	}
	
	public void setyCoord(int y){
		yCoord = y;
	}
}