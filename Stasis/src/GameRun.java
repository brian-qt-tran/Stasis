import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameRun extends JFrame {
	public static final int xBound = 1200;
	public static final int yBound = 800;
	
	public GameRun() {
		add(new Game(xBound, yBound));
		setSize(xBound,yBound);
		setResizable(false);
		setTitle("Stasis");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				GameRun game = new GameRun();
				game.setVisible(true);
			}
		});
	}

}
