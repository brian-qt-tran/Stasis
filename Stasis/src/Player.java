import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player extends Actor {
	private int maxX;
	
	public Player(int[] position, int width, String imagePath) {
		super(position,new double[]{0.0,0.0}, imagePath);
		maxX = width - image.getWidth(null) - 5;
	}
	
	public void Move(){
		//determine how to move the player
		if (!(vec[0] + pos[0] <= 0) && !(vec[0]>0 && pos[0] > maxX)) {
			pos[0] += speed*vec[0];
			pos[1] += speed*vec[1];
		}
	}
	
	public void speedUp() {
		speed++;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			vec[0] = -1.0;
			break;
		case KeyEvent.VK_RIGHT:
			vec[0] = 1.0;
			break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			vec[0] = 0.0;
			break;
		case KeyEvent.VK_RIGHT:
			vec[0] = 0.0;
			break;
		}
	}
}
