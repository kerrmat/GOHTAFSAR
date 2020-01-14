package gohtafsar;

public class Specials extends Enemy{
	public Specials(int x, int y){
		super(x,y);
		health = 80;
		loadout = new Weapon(-1,-1,7);
		rate = 6;
		speed = 4;
	}
}