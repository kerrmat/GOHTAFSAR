package gohtafsar;

public abstract class Bullet{
	//common to all bullets. position and speed
	int xCoord;
	int yCoord;
	int velocity;
	
	//abstract constructor
	public Bullet(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	//common to all bullets. position updated by speed
	public void carry(){
		xCoord += velocity;
	}
	
	public void status(){
		System.out.println("COORDS: " + xCoord + "/" + yCoord);
		System.out.println("VELOCITY: " + velocity);
	}
	
	//bullet behavior. unique to each child, so abstract here
	//public abstract void execute();
}