package ord.boka.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener{
	
	public final static int WIDTH = 500, HEIGHT = 500;
	JButton singlePlayerButton, multiPlayerButton;
	
	public Menu(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		setBorder(BorderFactory.createEmptyBorder(HEIGHT/4, WIDTH/5, HEIGHT/4, WIDTH/5));
		setLayout(new GridLayout(0, 1,HEIGHT/4,WIDTH/4));
		singlePlayerButton = new JButton("Singleplayer");
        singlePlayerButton.setActionCommand("singlePlayer");
        singlePlayerButton.addActionListener(this);
        add(singlePlayerButton);
        multiPlayerButton = new JButton("Multiplayer");
        multiPlayerButton.setActionCommand("multiPlayer");
        add(multiPlayerButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("singlePlayer".equals(e.getActionCommand())){
			Main.startGame();
		}
	}
}
