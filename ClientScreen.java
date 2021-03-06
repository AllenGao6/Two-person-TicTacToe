import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent; 
 
 
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
public class ClientScreen extends JPanel implements MouseListener,ActionListener {
 
    private JTextField textInput;
    private ObjectOutputStream out;
    private JTextArea convoArea;
    private JScrollPane scrollPaneConvo;
    private String chatMessage = "";
    private String notificationText;
	private Game game;
	private JButton newgame;
	public int turn;
	private Game gam;
	private boolean XO;
	private boolean gameOver;
	
	
    public ClientScreen()
    {

         
		game = new Game();
		turn = 0;
		//ai = false;
		notificationText = "";
        addMouseListener(this);
		
		newgame = new JButton("New Game");
		newgame.setBounds(300,0,200,80);
		newgame.addActionListener(this);
		 
		
		 
        this.setFocusable(true);
        this.setLayout(null);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
		
		g.setColor(Color.blue);
        g.fillRect(0,0,800,800);
 
        //Draw Box
 
        //Draw Text
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString(notificationText, 50, 200);
		game.drawMe(g);
		if(XO == true){
			if(game.checkTicTacToe() == 1)
			{
				this.add(newgame);
				game.setNewG(false);
				gameOver = true;
				this.playWin();
			}
			else if(game.checkTicTacToe() == 2){
				
				this.add(newgame);
				game.setNewG(false);
				gameOver = true;
				this.playLose();
				
			}
			else if(game.checkFull() == 3){
				this.add(newgame);
				game.setNewG(false);
				gameOver = true;
				this.playTie();
				//this.playLose();
			}
			else{
			}
		}
		else{
			if(game.checkTicTacToe() == 1)
			{
				this.add(newgame);
				game.setNewG(false);
				gameOver = true;
				this.playLose();
			}
			else if(game.checkTicTacToe() == 2){
				
				this.add(newgame);
				game.setNewG(false);
				gameOver = true;
				this.playWin();
				
			}
			else if(game.checkFull() == 3){
				this.add(newgame);
				game.setNewG(false);
				gameOver = true;
				this.playTie();
				//this.playLose();
			}
			else{
			}
		}
    }
 
    public Dimension getPreferredSize() {
        return new Dimension(800,800);
    }
    
	public void playX() {
 
        try {
            URL url = this.getClass().getClassLoader().getResource("sound/x.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
	
	public void playO() {
 
        try {
            URL url = this.getClass().getClassLoader().getResource("sound/o.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
	
	public void playWin() {
 
        try {
            URL url = this.getClass().getClassLoader().getResource("sound/win.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
	
	public void playLose() {
 
        try {
            URL url = this.getClass().getClassLoader().getResource("sound/lose.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    } 
	
	public void playTie() {
 
        try {
            URL url = this.getClass().getClassLoader().getResource("sound/tie.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    } 
	
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newgame){
			//System.out.println("new");
			game.reset();
			this.remove(newgame);
			
			XO = false;
			gameOver = false;
				try {
					out.reset();
					out.writeObject(game);
					//repaint();    
				}
			 catch (UnknownHostException ex) {
				System.exit(1);
			} 
			catch (IOException ex){
				System.exit(1);
			}
		}
		repaint();
    }
 
    public void poll() throws IOException{
         
        String hostName = "localhost"; 
        int portNumber = 1024;
        Socket serverSocket = new Socket(hostName, portNumber);
		out = new ObjectOutputStream(serverSocket.getOutputStream());
        ObjectInputStream  in = new ObjectInputStream (serverSocket.getInputStream());
        //repaint();
        //listens for inputs
		while (true) {  
				//listen
				try {
					game = (Game)(in.readObject());
					repaint();
					if(!game.getNewG())
						this.remove(newgame);
			} catch (UnknownHostException e) {
				System.err.println("Host unkown: " + hostName);
				System.exit(1);
			} catch (ClassNotFoundException e) {
				System.err.println("Couldn't get I/O for the connection to " +
					hostName);
				System.exit(1);
			} 
			}
		
				
		
        
    }
	
	public void mousePressed(MouseEvent e) 
    {
 
        //Print location of x and y
        //System.out.println( "X: " + e.getX() + ", Y: " + e.getY() );
 
		turn = game.getTurn();
 
        //Check if mouse pressed position is in the brown box
        if( e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 100 && e.getY() <= 300 )
        {
            game.insertXO(1,1);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
        else if(e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 300 && e.getY() <= 500)
        {
            game.insertXO(2,1);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
		else if(e.getX() >= 100 && e.getX() <= 300 && e.getY() >= 500 && e.getY() <= 700)
        {
            game.insertXO(3,1);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
		else if(e.getX() >= 300 && e.getX() <= 500 && e.getY() >= 100 && e.getY() <= 300)
        {
            game.insertXO(1,2);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
		else if(e.getX() >= 300 && e.getX() <= 500 && e.getY() >= 300 && e.getY() <= 500)
        {
            game.insertXO(2,2);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
		else if(e.getX() >= 300 && e.getX() <= 500 && e.getY() >= 500 && e.getY() <= 700)
        {
            game.insertXO(3,2);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
		else if(e.getX() >= 500 && e.getX() <= 700 && e.getY() >= 100 && e.getY() <= 300)
        {
            game.insertXO(1,3);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
		else if(e.getX() >= 500 && e.getX() <= 700 && e.getY() >= 300 && e.getY() <= 500)
        {
            game.insertXO(2,3);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
		else if(e.getX() >= 500 && e.getX() <= 700 && e.getY() >= 500 && e.getY() <= 700)
        {
            game.insertXO(3,3);
			if(turn%2==0)
			{
				this.playX();
				XO = true;
				game.setFirst(2);
			}
			else
			{
				this.playO();
				XO = false;
			}
			
        }
		
		try {    
                out.reset();
				out.writeObject(game);
               // repaint();    
            }
        catch (UnknownHostException ex) {
			System.exit(1);
		} 
		catch (IOException ex){
			System.exit(1);
		}
		
        repaint();
    }
	
	public void mouseReleased(MouseEvent e) {}
 
    public void mouseEntered(MouseEvent e) {}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mouseClicked(MouseEvent e){}
}