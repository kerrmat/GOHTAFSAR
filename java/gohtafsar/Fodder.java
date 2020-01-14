package gohtafsar;

public class Fodder extends Enemy{
	public Fodder(int x, int y){
		super(x,y);
		health = 50;
		loadout = new Weapon(-1,-1,7);
		rate = 6;
		speed = 6;
	}
}