package gohtafsar;

public class Fodder extends Enemy{
	public Fodder(int x, int y){
		super(x,y);
		health = 20;
		loadout = new Weapon();
		rate = 0;
		speed = 10;
	}
}