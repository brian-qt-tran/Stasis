import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {
	private Timer timer;
	private Player player;
	private Projectile missile;
	private ArrayList<Ball> balls;
	private final int DELAY = 10;
	private final int BALLCOUNT = 3;
	private int score = 0;
	private int shotsFired = 0;
	private boolean gameOver = false;
	private static final String ballImagePath = "resources/Stick Figure.jpeg";
	private static final String playerImagePath = "resources/chipbag.jpg";
	private static final String projectileImagePath = "resources/chips.jpg";

public Game(int x, int y) {
	addKeyListener(new TAdapter());
	setFocusable(true);
	setBackground(Color.BLACK);
	
	player = new Player(new int[]{x/2,3*y/4}, x, playerImagePath);//revise this position later
	missile = new Projectile(projectileImagePath);
	balls = new ArrayList<Ball>();
	for (int i=0;i<BALLCOUNT;i++) {
		balls.add(new Ball(ballImagePath));
	}
	timer = new Timer(DELAY, this);
	timer.start();
}

//Override
public void paintComponent(Graphics g) {
	super.paintComponent(g);
	doDrawing(g);
	Toolkit.getDefaultToolkit().sync();
}

private void doDrawing(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	int[] playerPos = player.getPos();
	int[] missilePos = missile.getPos();
	
	g2d.drawImage(player.getImage(), playerPos[0], playerPos[1], this);
	g2d.setColor(Color.WHITE);
	g2d.drawString("Score: " + score, 10, 20);
	for (Ball ballToDraw : balls) {
		int[] ballPos = ballToDraw.getPos();
		g2d.drawImage(ballToDraw.getImage(), ballPos[0], ballPos[1], this);
	}
	if (missile.getFired()) {
		g2d.drawImage(missile.getImage(), missilePos[0], missilePos[1], this);
	}
	
	if (gameOver) {
		g2d.drawString("Game Over!", GameRun.xBound/2, GameRun.yBound/2);
	}
}

//Override
public void actionPerformed(ActionEvent e) {
	if (gameOver) {
		timer.stop();
	}
	moveAll();
	collisionCheck();
	repaint();
}

private void moveAll() {
	player.Move();
	missile.Move();
	for (Ball ball : balls) {
		ball.Move();
	}
}

private void collisionCheck() {
	Rectangle playerBounds = player.getBounds();
	Rectangle missileBounds = missile.getBounds();
	for (Ball ball : balls) {
		Rectangle ballBounds = ball.getBounds();
		if (playerBounds.intersects(ballBounds)) {
			gameOver = true;
		}
		if (missileBounds.intersects(ballBounds) && missile.getFired()) {
			ball.Freeze();
			missile.Explode();
			score += 10;
		}
	}
}

private class TAdapter extends KeyAdapter {
	
	//Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
	
	//Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			if (missile.Fire(player.getPos())){
				shotsFired++;
				if (shotsFired % BALLCOUNT == 0) {
					player.speedUp();
				}
			}
	}
}

}
