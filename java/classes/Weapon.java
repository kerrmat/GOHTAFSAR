package gohtafsar;

public class Weapon{
	int mag;
	int capacity;
	int ammo;
	int type;
	
	public Weapon(){
		mag = 30;
		capacity = 30;
		ammo = -1;
		type = 1;
	}
	
	public Weapon(int m, int c, int a, int t){
		mag = m;
		capacity = c;
		ammo = a;
		type = t;
	}
	
	public void fire(){
		mag -= 1;
	}
	
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