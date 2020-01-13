package gohtafsar;

public class Player{
	int health = 100;
	int shields = 100;
	
	int xCoord = 0;
	int yCoord = 0;
	int speed = 1;
	
	Controller controls;
	Weapon loadout;
	
	public Player(){
		controls = new Controller();
		loadout = new Weapon();
	}
	
	public Player(int x, int y, Controller cont, Weapon weap){
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
		System.out.println("CONTROLS: ");
			controls.status();
	}
}