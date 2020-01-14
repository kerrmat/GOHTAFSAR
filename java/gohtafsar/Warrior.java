package gohtafsar;

public class Warrior extends Enemy{
	public Warrior(int x, int y){
		super(x,y);
		health = 100;
		loadout = new Weapon(-1,-1,5);
		rate = 4;
		speed = 4;
	}
}