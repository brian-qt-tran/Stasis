import javax.swing.ImageIcon;
import java.lang.Math;


public class Ball extends Actor{
	private boolean frozen;
	private long freezeTime;
	private int maxX, maxY;
	
	public Ball(String imagePath) {
		super(imagePath);
		maxX = GameRun.xBound - image.getWidth(null) - 5;
		maxY = GameRun.yBound - image.getHeight(null) - 30;
		pos = new int[]{(int)((maxX)*Math.random()), (int)((maxY/2)*Math.random())};
		vec = normalize(new double[] {Math.random(), Math.random()});
		frozen = false;
	}
	
	private double[] normalize(double[] vector) {
		double vectorLength = Math.sqrt(Math.pow(vector[0],(double)2) + Math.pow(vector[1],(double)2));
		return new double[]{vector[0]/vectorLength, vector[1]/vectorLength};
	}
	
	public void Move() {
		if (!frozen) {
			pos[0] += Math.round(speed*vec[0]);
			pos[1] += Math.round(speed*vec[1]);
			//bounce the ball if it goes out of bounds
			if (pos[0] <=0 || pos[0] >= maxX) {
				vec[0] *= -1;
			}
			if (pos[1] <=0 || pos[1] >= maxY) {
				vec[1] *= -1;
			}
		} else if (System.currentTimeMillis() - freezeTime > 3000) {
			frozen = false; 
		}
	}
	
	public void Freeze() {
		frozen = true;
		freezeTime = System.currentTimeMillis();
		speed++;
		vec = normalize(new double[] {Math.random(), Math.random()});
	}
}
