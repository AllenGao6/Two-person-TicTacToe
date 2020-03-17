
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.Serializable;  


public class Game implements Serializable
{
	
  private int[][] table;
  
  private int turn;
  private int onescore;
  private int twoscore;
  private boolean newG;
  private int first;
  
  
  
  public Game()
  {
    turn = 0;
	onescore = 0;
	twoscore = 0;
	
  	table = new int[3][3];
    
		for(int i = 0;i<table.length;i++)
    {
    	for(int x = 0;x<table[i].length;x++)
      {
      	table[i][x] = 0;
      }
    }
    
	newG = false;
  }
  
  public Dimension getPreferredSize() 
	{
		return new Dimension(800,800);
	}
  
  /*public void printTable()
  {
  	for(int r = 0; r<table.length; r++)
    {
    	for(int c = 0; c<table[r].length; c++)
      {
      	System.out.print(table[r][c] + " ");
      }
      System.out.println();
    }
  }*/
  
  public void insertXO(int a, int b)
  {
		if(table[a-1][b-1] > 0)
        {
        	turn++;
        }
      	else if(turn%2==0)
        {
        	table[a-1][b-1] = 1;
        }
        else if(turn%2==1)
        {
        	table[a-1][b-1] = 2;
        }
    		turn++;
 
  }
   public int checkTicTacToe()
  {
  	if(table[0][0] == 1 && table[1][0] == 1 && table[2][0] == 1)
      return 1;
    
    if(table[0][1] == 1 && table[1][1] == 1 && table[2][1] == 1)
      return 1;
    
    if(table[0][2] == 1 && table[1][2] == 1 && table[1][2] == 1)
      return 1;
    
    if(table[0][0] == 1 && table[0][1] == 1 && table[0][2] == 1)
      return 1;
    
    if(table[1][0] == 1 && table[1][1] == 1 && table[1][2] == 1)
      return 1;
    
    if(table[2][0] == 1 && table[2][1] == 1 && table[2][2] == 1)
      return 1;
    
    if(table[0][0] == 1 && table[1][1] == 1 && table[2][2] == 1)
      return 1;
    
    if(table[1][2] == 1 && table[2][1] == 1 && table[3][0] == 1)
      return 1;
  
	if(table[0][0] == 2 && table[1][0] == 2 && table[2][0] == 2)
      return 2;
    
    if(table[0][1] == 2 && table[1][1] == 2 && table[2][1] == 2)
      return 2;
    
    if(table[0][2] == 2 && table[1][2] == 2 && table[1][2] == 2)
      return 2;
    
    if(table[0][0] == 2 && table[0][1] == 2 && table[0][2] == 2)
      return 2;
    
    if(table[1][0] == 2 && table[1][1] == 2 && table[1][2] == 2)
      return 2;
    
    if(table[2][0] == 2 && table[2][1] == 2 && table[2][2] == 2)
      return 2;
    
    if(table[0][0] == 2 && table[1][1] == 2 && table[2][2] == 2)
      return 2;
    
    if(table[1][2] == 2 && table[2][1] == 2 && table[3][0] == 2)
      return 2;
	
	
	
	
	
	return 0;
}
  
  public int checkFull()
  {
	  
	
  	for(int r = 0;r<table.length;r++)
	{
		for(int c = 0;c<table[r].length;c++)
		{
			if(table[r][c] == 0)
			{
				return 4;
			}
			
		}
	}
	return 3;

    
    
  }


	public void drawMe(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect(100,100,200,200);
		g.drawRect(100,300,200,200);
		g.drawRect(100,500,200,200);
		g.drawRect(300,100,200,200);
		g.drawRect(300,300,200,200);
		g.drawRect(300,500,200,200);
		g.drawRect(500,100,200,200);
		g.drawRect(500,300,200,200);
		g.drawRect(500,500,200,200);
		
		//this.printTable();
		
		g.drawString("Player 1 Score: " + onescore,100,750);
		g.drawString("Player 2 Score: " + twoscore,600,750);
		
		for(int r = 0;r<table.length;r++)
		{
			for(int c = 0;c<table[r].length;c++)
			{
				if(table[r][c] == 1)
				{
					g.drawString("X",(c+1)*200, (r+1)*200+(r+1));
					//System.out.println(turn);
				}
				if(table[r][c] == 2)
				{
					g.drawString("O",(c+1)*200, (r+1)*200+(r+1));
					//System.out.println(turn);
				}
				else{
					g.drawString("",(c+1)*200, (r+1)*200+(r+1));
				}
			}
		}
		
		if(this.checkTicTacToe() == 1)
		{
			if(first == 1){
				g.drawString("Player 1 Wins!",350,375);
				onescore++;
				System.out.println("A");
			}
			else{
				g.drawString("Player 2 Wins!",350,375);
				twoscore++;
				System.out.println("B");
			}
			
		}
		else if(this.checkTicTacToe() == 2)
		{
			if(first == 2){
				g.drawString("Player 2 Wins!",350,375);
				twoscore++;
				System.out.println("B");
			}
			else{
				g.drawString("Player 1 Wins!",350,375);
				onescore++;
				System.out.println("A");
			}
		}
		else if(this.checkFull() == 3)
		{
			g.drawString("TIE!",350,375);
		}
	}
	
	public void reset()
	{
		//g.setColor(Color.white);
		//g.fillRect(0,0,800,800);
		for(int r = 0;r<table.length;r++)
		{
			for(int c = 0;c<table[r].length;c++)
			{
				table[r][c] = 0;
			}
		}
		
		turn = 0;
		newG = false;
	}
	
	public boolean getNewG()
	{
		return newG;
	}
	
	public void setNewG(boolean change)
	{
		newG = change;
	}
	
	public void setFirst(int x){
		first = x;
	}
	
	public int getTurn(){
		return turn;
	}
	
}
