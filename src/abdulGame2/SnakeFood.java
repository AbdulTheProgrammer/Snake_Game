package abdulGame2;
import java.awt.Rectangle;


public class SnakeFood {

	private int food_x = 1; 
	private int food_y = 1;
	private Rectangle foodRect;
	private final int FOODLENGTH = 20;
	SnakeFood(Rectangle snake[]) { 
		int count = 0;
		while (count != snake.length)
		{
			while (food_x % 20 != 0 || food_y % 20 != 0 )
			{
				food_x = (int) (Math.random () * 500);
				food_y = (int) (Math.random () * 500);
			}

			foodRect = new Rectangle (food_x, food_y, FOODLENGTH, FOODLENGTH);
			for (int i = 0 ; i < snake.length ; i++)
			{
				if (!snake [i].intersects (foodRect))
				{
					count++;
				}
			}
		}

	}
	public boolean checkCollision (Rectangle snake)
	{

		if (snake.intersects (getFood()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int getFood_x() {
		return food_x;
	}

	public int getFood_y() {
		return food_y;
	}
	public Rectangle getFood() {
		return foodRect;
	}
}
