import java.io.File;
import java.util.Scanner;
import java.io.IOException;


import javax.sound.sampled.*;


public class MusicPlayer {
	
	private int songIndex = 0;
	private Clip clip;
	
	public String CURRENTSONG;

    public void load() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		
    	File tracks = new File("C:\\Users\\Jack\\eclipse-workspace\\Music app\\tracks");
		File tracksList[] = tracks.listFiles();
		
		index(tracksList);
		
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(tracksList[songIndex]);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		CURRENTSONG = tracksList[songIndex].getName();
    	
	}

	public void stop() {
		clip.stop();
	}
	
	public void play() {
		clip.start();
	}
	
	public int index (File tracksList[]) {
			
		if (songIndex == -1) {
				songIndex = (tracksList.length)-1;
		}
		
		else if (songIndex >= tracksList.length) {
				songIndex = 0;
		} 
		return songIndex;
		}
		
	
	public void playNext() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if (clip.isActive()) {
			stop();
			songIndex = songIndex + 1;
			load();
			play();
		} else {
			stop();
			songIndex = songIndex + 1;
			load();
		}
	}
	
	public void playPrev() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if (clip.isActive()) {
			stop();
			songIndex = songIndex - 1;
			load();
			play();
		} else {
			stop();
			songIndex = songIndex - 1;
			load();
		}
	}
	
	public void reset() { 
		clip.setMicrosecondPosition(0);
	}

	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

		MusicPlayer player = new MusicPlayer();	
		System.out.println("Java rudimentary music player...");		
	/*	try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		
		player.load();
		
		while(!userInput.equals("quit")) {
			
			System.out.println("----------------------");
			System.out.println("Selected Track: "+player.CURRENTSONG);
			System.out.println("play -- play song");
			System.out.println("stop -- stop song");
			System.out.println("reset -- reset song");
			System.out.println("next -- next song");
			System.out.println("last -- previous song");
			System.out.println("quit -- quit song");
			System.out.print("Enter: ");
			
			userInput = scanner.next();
			userInput = userInput.toLowerCase();
			
			switch(userInput) {
			   case ("play"): player.play();
			   break;
			   case ("stop"): player.stop();
			   break;
			   case ("next"): player.playNext();			   
			   break;
			   case ("last"): player.playPrev();
			   break;
			   case ("reset"): player.reset();
			   break;
			   case ("quit"): player.stop();
			   break;
			   default: System.out.println("invalid response");
		}
			
		
	}
		scanner.close();
		System.out.println("Exiting music player...");

		
		}
	
	 
	

}
