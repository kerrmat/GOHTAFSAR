package gohtafsar;

public class Bullet{
	int xCoord;
	int yCoord;
	int velocity;
	int type;
	
	public void Bullet(int x, int y, int v, int t){
		xCoord = x;
		yCoord = y;
		velocity = v;
		type = t;
	}
	
	public void carry(){
		xCoord += velocity;
	}
}