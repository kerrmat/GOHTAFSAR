package gohtafsar;

public class Warrior extends Enemy{
	public Warrior(){
		health = 4;
		loadout = new Weapon(-1,-1,5,180);
		speed = -4;
	}
}