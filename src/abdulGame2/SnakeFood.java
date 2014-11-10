package abdulGame2;
import java.awt.Rectangle;


public class SnakeFood {

	private int food_x = 1; 
	private int food_y = 1;
	private Rectangle foodRect;
	private final int FOODLENGTH = 20; 
	SnakeFood(Rectangle snake[]) { //constructor generates that new food (with a new position) for the snake to eat
		int count = 0;
		while (count != snake.length)
		{
			while (food_x % 20 != 0 || food_y % 20 != 0 ) //randomizes snake food position but ensures that the snake's head can overlap fully with the food
			{
				food_x = (int) (Math.random () * 500); //randomize so that it is contained within the screen boundries
				food_y = (int) (Math.random () * 500);
			}

			foodRect = new Rectangle (food_x, food_y, FOODLENGTH, FOODLENGTH);
			for (int i = 0 ; i < snake.length ; i++) // check that the new position of the food is not already overlaping with any element of the snake array
			{
				if (!snake [i].intersects (foodRect))
				{
					count++;
				}
			}
		}

	}
	public boolean checkCollision (Rectangle snake) // check to see if snake has eaten food
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
	//accessor methods
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
