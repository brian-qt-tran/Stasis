import javax.swing.ImageIcon;


public class Projectile extends Actor{
	
	private boolean fired;
	
	public Projectile(String imagePath) {
		super(new int[]{0,0},new double[]{0.0,0.0}, imagePath);
		fired = false;
	}
	
	public boolean Fire(int[] position) {
		if (!fired) {
			pos[0] = position[0];
			pos[1] = position[1];
			vec[0] = 0;
			vec[1] = -1;
			fired = true;
			return true;
		}
		return false;
	}
	
	public void Explode() {
		fired = false;
	}
	
	public boolean getFired() {
		return fired;
	}
	
	public void Move() {
		if (pos[1] <= 0)
			fired = false;
		else if (fired) {
			pos[0] += speed*vec[0];
			pos[1] += speed*vec[1];
		}
	}
	
}
