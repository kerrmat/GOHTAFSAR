import gohtafsar.*;

class main{
	public static void main(String[] args){
		Player player1 = new Player();
		player1.status();
		
		System.out.println("FIRING WEAPON 10 TIMES");
		
		for(int i = 0; i < 10; i++)
			player1.getLO().fire();
		
		player1.getLO().status();
		
		System.out.println("RELOADING");
		
		player1.getLO().reload();
		player1.getLO().status();
	}
}