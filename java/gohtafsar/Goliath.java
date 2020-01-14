package gohtafsar;

public class Goliath extends Enemy{
	public Goliath(int x, int y){
		super(x,y);
		health = 400;
		loadout = new Weapon(-1,-1,6);
		rate = 1;
		speed = 1;
	}
}