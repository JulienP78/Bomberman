package bbman;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

public class main 
{	
	static boolean isFirstGame = true;
	
	public static void main(String[] args) 
	{	
		Ground ground = createGround();
		Player[] players = createPlayers(ground);
		if(isFirstGame)
		{
			isFirstGame=false;
			ground.displayMenu();
		}

		startGame(ground, players);
		Sound gameMusic = new Sound("game_music");

		double timerForMusicLoop = java.lang.System.currentTimeMillis();
			
		while (noPlayerIsDead(players))
		{
			if(java.lang.System.currentTimeMillis()-timerForMusicLoop > 34285)
			{
				gameMusic = new Sound("game_music");
				timerForMusicLoop=java.lang.System.currentTimeMillis();
			}
			play(ground, players);
		}
		gameMusic.stop();
		ground.displayGameOver(players);
	}
	
	public static Ground createGround()
	{
		int numberOfRow=21;
		int numberOfLine=17;
		int halfWidthOfRow=25;
		int halfHeigthOfLine=25;

		StdDraw.setCanvasSize(halfWidthOfRow*numberOfRow*2,halfHeigthOfLine*numberOfLine*2); // taille de la fenêtre
		StdDraw.setXscale(0, halfWidthOfRow*numberOfRow*2); // axe des abscisses
		StdDraw.setYscale(0, halfHeigthOfLine*numberOfLine*2); // axe des ordonnées
		
		StdDraw.show(0);

		Ground ground=new Ground(numberOfRow,numberOfLine,halfWidthOfRow,halfHeigthOfLine); // instanciation du ground
		return ground;
	}
	
	public static Player[] createPlayers(Ground ground)
	{
		int numberOfPlayers=2;
		Player [] players=new Player [numberOfPlayers] ;
		
		int idPlayer;
		int positionX;
		int positionY;
		
		for (int i=0;i<players.length;i++)
		{	
			idPlayer = i;
			positionX=0;
			positionY=0;
			if(idPlayer==0)
			{
				positionX=3*ground.getHalfWidthOfRow(); // position en x du joueur 1
				positionY=3*ground.getHalfHeigthOfLine(); // position en y du joueur 1
			}
			else if(idPlayer==1)
			{
				positionX=(ground.getNumberOfRow()*(ground.getHalfWidthOfRow()*2))-(3*ground.getHalfWidthOfRow()); // position en x du joueur 2
				positionY=(ground.getNumberOfLine()*(ground.getHalfHeigthOfLine()*2))-(3*ground.getHalfHeigthOfLine()); // position en y du joueur 2 
			}
			players[i]=new Player(ground,idPlayer, positionX, positionY);	// instanciation des joueurs
		}
		return players;
	}
	
	public static void startGame(Ground ground , Player[] players)
	{
		ground.draw(players); // on dessine le début de partie
		Sound sound = new Sound("321GO");
		for(int i = 3 ; i >=1 ; i--)
		{
			StdDraw.picture(ground.getHalfWidthOfRow()*2*10.5, ground.getHalfHeigthOfLine()*2*8.5, "start_game_" + i + ".png", 500, 500);
			StdDraw.show();
			pause(1000);
		}
		
	}
	
	public static void play(Ground ground, Player[] players)
	{
		listenToPlayersAction(players, ground); // on écoute les saisis des deux joueurs
		
		for (int i=0; i<players.length; i++)
		{	
			ground=players[i].getBonus(ground); // on regarde si le joueur est sur une case avec un bonus
			for (int j=0; j<players[i].getBombs().length;j++)
			{
				ground=players[i].bombe[j].manage(ground, players); // on fait la gestion les bombes
			}
		}
		ground.draw(players); // on redessine le tout
		
		int test=(int)(Math.random()*500);
		int newTest=(int)(Math.random()*7);
		if(test==1)
		{
			Sound sentence = new Sound("phrase"+(newTest+1));
		}
		pause(5);
	}
	
	public static void listenToPlayersAction(Player [] players, Ground ground)
	{	
		// ----------------------------------- touches du joueur 1 ---------------------------------
		if (StdDraw.isKeyPressed(KeyEvent.VK_Z))
		{	
			players[0].moveTo("up",ground);
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_Q))
		{	
			players[0].moveTo("left",ground);
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_S))
		{	
			players[0].moveTo("down",ground);
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_D))
		{	
			players[0].moveTo("right",ground);
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_A))
		{	
			ground=players[0].dropBomb(ground);	
		}
		
		// ----------------------------------- touches du joueur 2 ---------------------------------

		if (StdDraw.isKeyPressed(KeyEvent.VK_UP))
		{	
			players[1].moveTo("up",ground);
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
		{	
			players[1].moveTo("left",ground);
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
		{	
			players[1].moveTo("down",ground);
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
		{	
			players[1].moveTo("right",ground);
		}
		if (StdDraw.isKeyPressed(KeyEvent.VK_SHIFT))
		{	
			ground=players[1].dropBomb(ground);
		}
	}

	public static boolean noPlayerIsDead(Player[] players)
	{
		for (int i = 0 ; i < players.length ; i++)
		{
			if(players[i].getNumberOfLife()<=0)
			{
				return false;
			}
		}
		return true;
	}
	
	public static void pause(int mili)
	{	
		long time=java.lang.System.currentTimeMillis();
		while (java.lang.System.currentTimeMillis()-time<mili);
	}
}