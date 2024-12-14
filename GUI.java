import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GUI implements ActionListener {
	
	static MusicPlayer player;
	private JLabel label;
	private JLabel label2;
	private JFrame frame;
	private JPanel panel;
	private JButton JPlay;
	private JButton JPause;
	private JButton JNext;
	private JButton JLast;
	private JButton JReset;
	private JSlider JSlider;
	private JLabel playbackStart;
	private JLabel playbackEnd;


	public GUI() {
		
		frame = new JFrame();
		
		JPlay = new JButton("Play");
		JPlay.addActionListener(this);
		JPlay.setBounds(170, 210, 75, 25);
		
	    JPause = new JButton("Stop");
		JPause.addActionListener(this);
		JPause.setBounds(170, 234, 75, 25);
		
		JNext = new JButton("Next");
		JNext.addActionListener(this);
		JNext.setBounds(245, 210, 60, 75);
		
		JLast = new JButton("Previous");
		JLast.addActionListener(this);
		JLast.setBounds(110, 210, 60, 75);

		JReset = new JButton("Reset");
		JReset.addActionListener(this);
		JReset.setBounds(170, 259, 75, 25);

		label = new JLabel("Java rudimentary music player");
		label.setBounds(100, 100, 300, 25);
		label.setFont(new Font(null,Font.ITALIC,10));

		label2 = new JLabel(player.volume());
		label2.setBounds(100, 120, 300, 25);
		label2.setFont(new Font(null,Font.ITALIC,10));
		
		playbackStart = new JLabel("0:00");
		playbackStart.setBounds(90, 300, 300, 25);
		playbackStart.setFont(new Font(null,Font.ITALIC,10));
		
		playbackEnd = new JLabel(player.fDuration);
		playbackEnd.setBounds(320, 300, 300, 25);
		playbackEnd.setFont(new Font(null,Font.ITALIC,10));
		
		
		// Slider
		JSlider = new JSlider(0,player.clip.getFrameLength(),0);
		JSlider.setBounds(110,300,205,25); 
		JSlider.addMouseListener(new MouseAdapter() {
			
			/*
			public void mousePressed(MouseEvent e) {
				player.stop();
			}
			*/
			public void mouseReleased(MouseEvent e) {
				JSlider source = (JSlider) e.getSource();
				int pos = source.getValue();
				player.clip.setFramePosition(pos);
				System.out.println(pos);
				if (player.clip.isActive()) {
					player.play();

				} else {
					label.setText("Now playing "+player.CURRENTFILE);

				}
			}

		});


	    panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(null);
		panel.add(JPlay);
		panel.add(JPause);
		panel.add(JNext);
		panel.add(JLast);
		panel.add(JReset);
		panel.add(label);
		panel.add(label2);
		panel.add(playbackStart);
		panel.add(playbackEnd);
		panel.add(JSlider);

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
			label.setText("Now playing "+player.CURRENTFILE);
			playbackEnd.setText(player.fDuration);
			JSlider.setMaximum(player.clip.getFrameLength());
			label2.setText(player.volume());

		}
		if(e.getSource()==JPause) {
			player.stop();
		}
		if(e.getSource()==JNext) {
			try {
				JSlider.setValue(0);
				player.playNext();
				label.setText("Now playing "+player.CURRENTFILE);
				playbackEnd.setText(player.fDuration);
				JSlider.setMaximum(player.clip.getFrameLength());

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
				JSlider.setValue(0);
				player.playPrev();
				label.setText("Now playing "+player.CURRENTFILE);
				playbackEnd.setText(player.fDuration);
				JSlider.setMaximum(player.clip.getFrameLength());

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
			JSlider.setValue(0);
		}
		
	}

}
