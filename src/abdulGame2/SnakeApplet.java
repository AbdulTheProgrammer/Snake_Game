package abdulGame2;
// The "SnakeBETA" class.


import javax.swing.*;

import java.awt.Image;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class SnakeApplet extends JApplet implements KeyListener, Runnable
{
	private static final long serialVersionUID = 1L;
	final int DELAY = 150;
	Image image; 
	int score;
	Graphics second;
	boolean moveX, moveY, turn, eat, pause, loss, start; 
	Thread thread; // main game thread
	SnakeFood f;
	SnakeEngine s;

	@Override 
	public void init () // intial set-up of screen
	{
		resize(500,500);
		start = false;
		setUp();
		setFocusable(true);
		setBackground(Color.BLACK);
		thread = new Thread (this); //activate thread

	}
	public void setUp() { // set up intial game settings
		score = 0;
		moveX = true; 
		moveY = false;
		turn = false;
		loss = false;
		eat = true;
		pause = false;
		addKeyListener (this); 
		s = new SnakeEngine();
		f = new SnakeFood(s.getSnake());

	}



	public synchronized void run ()// method which executes the thread
	{
		while (true)
		{
			if (!pause && !loss) 
			{
				s.update();

				if (!turn) // if snake is not turning move snake body
				{
					s.moveSnake ();
				}	
				s.maneuverHead(); //move snake head sepearately 
				repaint ();
				if (f.checkCollision (s.getSnake() [0])) // check to see if snake head has collided with snake food
				{
					score++; // update user score
					s.growSnake (); // make snake longer
					f = new SnakeFood(s.getSnake()); // generate a new food for snake to consume

				}
				turn = false;
				loss = s.checkBounds (); // win/lose check

			}
			if (pause) { 
				repaint(); //update screen
			}

			try
			{
				Thread.sleep (DELAY); // thread delay 
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void update(Graphics g) {  // double buffering method for drawing game elements 

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		for (int i = 0 ; i < s.getSnake().length ; i++) // display snake array onto screen
		{
			g2.fill (s.getSnake() [i]);
		}
		g2.setFont(new Font("times new roman", Font.BOLD, 17));
		g2.drawString("Score: " + score*150, 20, 20);
		g2.setColor(Color.RED);
		g2.fill (f.getFood());
		if (!start) { 
			g2.setColor(Color.GREEN); 
			g2.drawString("Welcome to Snake", 150, 120);
			g2.drawString("Press the direction keys to move", 150, 140);
			g2.drawString("Press S to start and P to pause", 150, 160);
		}
		g2.setFont(new Font("times new roman", Font.BOLD, 20));
		if (loss) { 
			g2.setColor(Color.GREEN);
			g2.drawString("You Lost! :(", 220, 220);
		}
		if (pause) { 
			g2.setColor(Color.GREEN);
			g2.drawString(" Paused", 220, 220);
		}
	}

	@Override
	public void paint(Graphics g) // method for preparing screen image in memory and displaying 
	{
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());

		update (second); // send to update method
		g.drawImage(image, 0, 0, this); // display screen image
	}



	@Override
	public void keyReleased (KeyEvent ke)
	{
	}

	@Override
	public void keyPressed (KeyEvent ke) // method for reading user keystrokes to move snake or pause game
	{
		int key = ke.getKeyCode ();
		if (key == KeyEvent.VK_S && !start) { // must press s to start game
			thread.start();
			start = true;
		}
		if (!pause && !loss)
		{
			if (key == KeyEvent.VK_LEFT && !moveX)
			{
				s.setMx(-20);
				s.setMy(0);
				moveX = true;
				moveY = false;
				turn = true;
				repaint ();
				s.moveSnake();
			}
			else if (key == KeyEvent.VK_RIGHT && !moveX)
			{
				s.setMx(20);
				s.setMy(0);
				moveX = true;
				moveY = false;
				turn = true;
				repaint ();
				s.moveSnake();
			}
			else if (key == KeyEvent.VK_UP && !moveY)
			{

				s.setMy(-20);
				s.setMx(0);
				moveY = true;
				moveX = false;
				turn = true;
				repaint ();
				s.moveSnake();
			}
			else if (key == KeyEvent.VK_DOWN && !moveY)
			{

				s.setMy(20);
				s.setMx(0);
				moveY = true;
				moveX = false;
				turn = true;
				repaint ();
				s.moveSnake();
			}
		}
		if (key == KeyEvent.VK_P && !pause) // used for pausing game
		{
			pause = true;
		}
		else if (key == KeyEvent.VK_P && pause)
		{
			pause = false;
		}
	}

	@Override
	public void keyTyped (KeyEvent ke)
	{
	}
}
