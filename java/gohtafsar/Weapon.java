package gohtafsar;

public class Weapon{
	//mag is bullets in mag, capacity is total possible bullets
	//each gun has a number of appropriate ammo
	//using type variable instead of polymorphism, may change
	int mag;
	int capacity;
	int ammo;
	int type;
	
	//default weapon is assault rifle
	public Weapon(){
		mag = 30;
		capacity = 30;
		ammo = -1;
		type = 1;
	}
	
	//full mag is at cap, so c works for both
	public Weapon(int c, int a, int t){
		mag = c;
		capacity = c;
		ammo = a;
		type = t;
	}
	
	//fire gun, update mag, no bullet created yet
	public void fire(){
		if(mag != 0 && capacity != 0)
			mag -= 1;
		else if(mag == -1)
			System.out.println("CREATE ENEMY BULLET");
	}
	
	//handles all logic for reloading, should be feature complete
	//NOTE: ammo == -1 is shorthand for infinite ammo
	public void reload(){
		if(ammo == -1)
			mag = capacity;
		else if(ammo > 0 && ammo >= (capacity - mag)){
			ammo -= (capacity - mag);
			mag = capacity;
		}
		else if(ammo > 0 && ammo < (capacity - mag)){
			mag += ammo;
			ammo = 0;
		}
	}
	
	public void status(){
		System.out.println("MAG/CAP: " + mag + "/" + capacity);
		System.out.println("AMMO: " + ammo);
	}
}