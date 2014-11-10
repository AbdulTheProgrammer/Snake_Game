package abdulGame2;
import java.awt.Rectangle;

public class SnakeEngine {

	private Rectangle snake[]; // snake array 
	private static int count; 
	private int x, y, mx, my;
	private final int HEIGHT = 20, LENGTH = 20, SPEED = 20;

	SnakeEngine() { 
		count = 0;
		snake = new Rectangle [5]; // intial size of snake is 5 rectangles long
		y = 240;
		x = 240;
		mx = 20;
		my = 0;
		for (int i = 4 ; i >= 0 ; i--)
		{
			snake [i] = new Rectangle (x + mx * i, y + my * i, LENGTH, HEIGHT);
		}
	}
	public void update() { //method for the normal movement of the snake by moving the position of each element of the array to the next upper value (i.e. rectangle 5 is to 4)
		for (int i = snake.length - 1 ; i >= 1 ; i--)
		{
			snake [i] = snake [i - 1];
		}
	}
	public void moveSnake () //method for altering the coordinates of the snake for movement
	{
		if ( mx == -20)
		{
			x -= SPEED;
		}
		if ( mx == 20)
		{
			x += SPEED;
		}
		if ( my == -20)
		{
			y -= SPEED;
		}
		if (my == 20)
		{
			y += SPEED;
		}


	}
	public void maneuverHead() { // method that re-declares the first element (head) of the snake array according to changes made by the user
		snake [0] = new Rectangle (x, y, LENGTH, HEIGHT);
	}
	public boolean checkBounds () // method to check if the snake crashes into the edges of the screen or with itself 
	{
		count++;
		if ( count < 4) { 
			return false;
		}
		if (snake [0].x < 0 || snake [0].x > 499 || snake [0].y < 0 || snake [0].y > 499)
		{
			return true;
		}
		for (int i = snake.length - 1 ; i > 0 ; i--)
		{
			if (snake [0].intersects (snake [i]))
			{
				return true;
			}
		}
		return false;
	}
	public void growSnake () // method to make the snake array larger after the snake eats its food
	{
		Rectangle[] temp = new Rectangle [snake.length + 1];
		System.arraycopy (snake, 0, temp, 0, snake.length);
		snake = temp;
		snake [snake.length - 1] = new Rectangle (snake [snake.length - 2].x + mx, snake [snake.length - 2].y + my, LENGTH, HEIGHT);
	}
	//accessor methods
	public int getMx() {
		return mx;
	}
		public int getMy() {
		return my;
	}
	public Rectangle[] getSnake() {
		return snake;
	}
	//mutator functions
	public void setMx(int mx) {
		this.mx = mx;
	}
	public void setMy(int my) {
		this.my = my;
	}

}
