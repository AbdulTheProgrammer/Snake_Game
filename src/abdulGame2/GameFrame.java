package abdulGame2;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class GameFrame extends JFrame // this class allows the applet to be run as an application by embedding it onto a Jframe
{
    public GameFrame()
    {
        setTitle("Snake");        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,500);
        setResizable(false);
        setVisible(true); 
        SnakeApplet s = new SnakeApplet();
        s.init();
        getContentPane().add(s); 
    }
    public static void main (String args) { 
    	new GameFrame();
    }
}
