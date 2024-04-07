import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GUI implements ActionListener {
	
	static MusicPlayer player;
	private JLabel label;
	private JFrame frame;
	private JPanel panel;
	private JButton JPlay;
	private JButton JPause;
	private JButton JNext;
	private JButton JLast;
	private JButton JReset;

	public GUI() {
		
		frame = new JFrame();
		
		JPlay = new JButton("Play");
		JPlay.addActionListener(this);
		
	    JPause = new JButton("Stop");
		JPause.addActionListener(this);
		
		JNext = new JButton("Next");
		JNext.addActionListener(this);
		
		JLast = new JButton("Previous");
		JLast.addActionListener(this);
		
		JReset = new JButton("Reset");
		JReset.addActionListener(this);
		
		label = new JLabel("Java rudimentary music player");
		
	    panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(JPlay);
		panel.add(JPause);
		panel.add(JNext);
		panel.add(JLast);
		panel.add(JReset);
		panel.add(label);

		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setTitle("Music app");
		//frame.pack();
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		player = new MusicPlayer();
		player.load();
		new GUI();
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==JPlay) {
			player.play();
			label.setText("Now playing "+player.CURRENTSONG); 
		}
		if(e.getSource()==JPause) {
			player.stop();
		}
		if(e.getSource()==JNext) {
			try {
				player.playNext();
				label.setText("Now playing "+player.CURRENTSONG); 
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==JLast) {
			try {
				player.playPrev();
				label.setText("Now playing "+player.CURRENTSONG); 
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==JReset) {
			player.reset();
		}
		
	}

}
