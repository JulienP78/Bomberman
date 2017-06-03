package bbman;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import javax.sound.sampled.*;
import java.applet.Applet;
import java.applet.AudioClip;

public class Terrain 
{	private int [][] tab;
	
	private int nbColonne;
	private int nbLine;
	
	private int width;
	private int heigth;
	

	public Terrain (int nbColonne, int nbLine, int width, int heigth)
	{	
		this.tab=new int [nbColonne][nbLine];
		this.nbColonne=nbColonne;
		this.nbLine=nbLine;
		
		this.width=width;
		this.heigth=heigth;
	
		int i=0;
		int j=0;
		
		for (i=0;i<nbColonne;i++)
		{	
			for (j=0;j<nbLine;j++)
			{	
				if ((i==0)||(j==0)||(i==nbColonne-1)||(j==nbLine-1)||((i%2==0)&&(j%2==0)))
					this.tab [i][j]=1;
				else
					this.tab [i][j]=2;
			}
		}
		
		this.tab [1][1]=0;
		this.tab [2][1]=0;
		this.tab [3][1]=0;
		this.tab [3][2]=0;
		this.tab [1][2]=0;
		this.tab [1][3]=0;
		this.tab [2][3]=0;
		
		this.tab [nbColonne-2][nbLine-2]=0;
		this.tab [nbColonne-3][nbLine-2]=0;
		this.tab [nbColonne-4][nbLine-2]=0;
		this.tab [nbColonne-4][nbLine-3]=0;
		this.tab [nbColonne-2][nbLine-3]=0;
		this.tab [nbColonne-2][nbLine-4]=0;
		this.tab [nbColonne-3][nbLine-4]=0;
	}

	public int getwidth()
	{
		return this.width;
	}
	
	public int getheigth()
	{
		return this.heigth;
	}
	
	public int gettab(int x, int y)
	{
		return this.tab[x][y];
	}

	
	public void put (int x, int y)
	{
		this.tab[x][y]=1;
	}
	
	public void set (int x, int y, int value)
	{
		this.tab[x][y]=value;
	}
		
	public int test (int x, int y)
	{
		if (this.tab[x][y]==1)
			return 1;
	
		if (this.tab[x][y]==2)
			return 1;
		
		return 0;
	}

	public void draw_all (Joueur [] joueur)
	{
		int i=0;
		int j=0;
		
		int rayon;
		
		if (this.width>this.heigth)
			rayon=this.heigth;
		else
			rayon=this.width;
		
		int orgx=this.width;
		int orgy=this.heigth;

		for (i=0;i<this.nbColonne;i++)
		{	for (j=0;j<this.nbLine;j++)
			{	if (this.tab[i][j]==0)
				{
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				}
				if (this.tab[i][j]==2)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Caisse.png", 50, 50);
				}
				else if (this.tab[i][j]==1)
				{	
					StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Pelouse.png", 50, 50);
				}
				else if (this.tab[i][j]>=666)
				{	
					StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Explosion.png", 50, 50);
				}
				else if (this.tab[i][j]==10)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_flamme_bleue.png", 35, 33);
				}
				else if (this.tab[i][j]==20)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_flamme_jaune.png", 35, 33);
				}
				else if (this.tab[i][j]==30)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_flamme_rouge.png", 35, 33);
				}
				else if (this.tab[i][j]==40)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_bombe_rouge.png", 35, 33);
				}
				else if (this.tab[i][j]==50)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_vie.png", 35, 33);
				}
				else if (this.tab[i][j]==60)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_speed_up.png", 35, 33);
				}
				else if (this.tab[i][j]==70)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_speed_down.png", 35, 33);
				}
				else if (this.tab[i][j]==80)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_bombe_plus.png", 35, 33);
				}
				else if (this.tab[i][j]==90)
				{	StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "Herbe.png", 50, 50);
				StdDraw.picture((i*2*this.width)+orgx, (j*2*this.heigth)+orgy, "bonus_bombe_moins.png", 35, 33);
				}
			}
		}
		StdDraw.setPenColor(255, 255, 255);
		StdDraw.setPenRadius(200);
		StdDraw.picture(this.getwidth()*2*18.9, this.getheigth()*2*16.5, "J2.png", 200, 90);
		StdDraw.picture(this.getwidth()*2*20.6, this.getheigth()*2*15.5, "NombreVies.png", 30, 30);
		StdDraw.text(this.getwidth()*2*20.6, this.getheigth()*2*14.5, "X " + joueur[1].getlife());
		StdDraw.picture(this.getwidth()*2*20.6, this.getheigth()*2*13.5, "NombreBombes.png", 30, 30);
		StdDraw.text(this.getwidth()*2*20.6, this.getheigth()*2*12.5, "X " + joueur[1].getnbbomb());
		
		StdDraw.picture(this.getwidth()*2*2.1, this.getheigth()*2*0.5, "J1.png", 200, 90);
		StdDraw.picture(this.getwidth()*2*0.5, this.getheigth()*2*2.5, "NombreVies.png", 30, 30);
		StdDraw.text(this.getwidth()*2*0.5, this.getheigth()*2*1.5, "X " + joueur[0].getlife());
		StdDraw.picture(this.getwidth()*2*0.5, this.getheigth()*2*4.5, "NombreBombes.png", 30, 30);
		StdDraw.text(this.getwidth()*2*0.5, this.getheigth()*2*3.5, "X " + joueur[0].getnbbomb());
		
		
		
		for (i=0;i<joueur.length;i++)
		{
			for (j=0; j<joueur[i].getnbbombe ();j++)
				joueur[i].bombe[j].draw(this);
		}
		for (i=0;i<joueur.length;i++)
			joueur[i].draw();
	}

	
}