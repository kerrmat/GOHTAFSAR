package gohtafsar;

public class Fodder extends Enemy{
	public Fodder(){
		health = 1;
		loadout = new Weapon(0,0,0,0);
		speed = -8;
	}
}