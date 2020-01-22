package gohtafsar;

public class Weapon{
	//CURRENT WEAPONS BALANCING:
	//type: mag/capacity, rate - name
	//type 0: 0,0 - unarmed
	//type 1: 30,120 - assault rifle
	//type 2: 
	//type 3:
	//type 4: 
	//type 5: -1,180 - alien blaster
	//type 6: -1,480 - alien artillery
	//type 7: -1,360 - alien gatling
	
	//mag is bullets in mag, capacity is total possible bullets
	//each gun has a number of appropriate ammo
	//using type variable instead of polymorphism, may change
	int mag;
	int capacity;
	int ammo;
	int type;
	
	//rate is a set value, determines how frequent a weapon can fire
	//cooldown is the var used to track abiliy to fire
	int rate;
	int cooldown = 0;
	
	//default weapon is assault rifle
	public Weapon(){
		mag = 30;
		capacity = 30;
		ammo = -1;
		type = 1;
		rate = 120;
	}
	
	//full mag is at cap, so c works for both
	public Weapon(int c, int a, int t, int r){
		mag = c;
		capacity = c;
		ammo = a;
		type = t;
		rate = r;
	}
	
	//fire gun, update mag, no bullet created yet
	public void fire(){
		//player firing
		if(mag > 0 && cooldown > rate)
			mag -= 1;
			System.out.println("CREATE BULLET");
		//enemy firing
		else if(mag == -1){
			System.out.println("CREATE BULLET");
		}
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