import java.awt.Color;
import java.awt.Graphics;
 
 
public class Win
{
     
    int x;
    int y;
    
	int r;
	int g;
	int b;
	
    Color random;
	Color blue;
	
     
    public Win()
    {
        x = (int)(Math.random()*801);
        y = (int)(Math.random()*601);
        
		r = (int)(Math.random()*156+100);
        g = (int)(Math.random()*156+100);
		b = (int)(Math.random()*156+100);

		
        blue = new Color(0,0,255);
		
    }
     
    public void drawWin(Graphics g)
    {
        g.setColor(random);
        g.fillRect(x,y,2,5);
    }
    
	public void drawLose(Graphics g)
    {
        g.setColor(blue);
        g.fillRect(x,y,2,5);
    }
	
    public void move()
    {
        y++;
         
        if( y > 600)
        {
			y=0;
			x = (int)(Math.random()*801);
        } 
    }
     
     
     
}