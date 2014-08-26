package abdulGame2;
import java.awt.Rectangle;

public class SnakeEngine {

	private Rectangle snake[]; 
	private static int count;
	private int x, y, mx, my;
	private final int HEIGHT = 20, LENGTH = 20, SPEED = 20;

	SnakeEngine() { 
		count = 0;
		snake = new Rectangle [5];
		y = 240;
		x = 240;
		mx = 20;
		my = 0;
		for (int i = 4 ; i >= 0 ; i--)
		{
			snake [i] = new Rectangle (x + mx * i, y + my * i, LENGTH, HEIGHT);
		}
	}
	public void update() { 
		for (int i = snake.length - 1 ; i >= 1 ; i--)
		{
			snake [i] = snake [i - 1];
		}
	}
	public void moveSnake ()
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
	public void maneuverHead() {
		snake [0] = new Rectangle (x, y, LENGTH, HEIGHT);
	}
	public boolean checkBounds ()
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
	public void growSnake ()
	{
		Rectangle[] temp = new Rectangle [snake.length + 1];
		System.arraycopy (snake, 0, temp, 0, snake.length);
		snake = temp;
		snake [snake.length - 1] = new Rectangle (snake [snake.length - 2].x + mx, snake [snake.length - 2].y + my, LENGTH, HEIGHT);
	}
	public int getMx() {
		return mx;
	}
	public void setMx(int mx) {
		this.mx = mx;
	}
	public int getMy() {
		return my;
	}
	public void setMy(int my) {
		this.my = my;
	}
	public Rectangle[] getSnake() {
		return snake;
	}
}
