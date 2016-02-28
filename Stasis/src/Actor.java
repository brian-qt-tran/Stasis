import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Actor {
	protected int[] pos = new int[2]; //position of the actor
	protected double[] vec = new double[2]; //unit vector for the direction of movement
	protected int speed = 1; //speed of the actor
	protected Image image; //image file for the actor
	
	public Actor(int[] position, double[] vector, String imagePath) {
		pos[0] = position[0];
		pos[1] = position[1];
		vec[0] = vector[0];
		vec[1] = vector[1];
		ImageIcon ii = new ImageIcon(imagePath);
		image = ii.getImage();
	}
	
	public Actor(String imagePath) {
		ImageIcon ii = new ImageIcon(imagePath);
		image = ii.getImage();
	}
	
	public int[] getPos() {
		return pos;
	}
	
	public double[] getVec() {
		return vec;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(pos[0], pos[1], image.getWidth(null), image.getHeight(null));
	}
	
	abstract public void Move();
	
}
