import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.IOException;


import javax.sound.sampled.*;


public class MusicPlayer {
	
	private int fileIndex = 0;
	public Clip clip;
	
	public String CURRENTFILE;
	public String fDuration;
	public double drtMilSecs; 
	
    public void load() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		
    	// creates new file array with selected tracks
    	File tracks = new File("C:\\Users\\jacke\\eclipse-workspace\\Music app\\tracks");
		File tracksList[] = tracks.listFiles();
		
		index(tracksList);
		
		// grabs audio file from tracklist in given index and gets its audio stream and format
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(tracksList[fileIndex]);
		AudioFormat format = audioStream.getFormat();
		
		// converts the framelength divided by the framerate of the audio file into the duration of the file in seconds (double).
		long frames = audioStream.getFrameLength();
		double durationInSeconds = (frames+0.0) / format.getFrameRate();
		
		// turns audiostream file into clip
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		
		// class variable declarations, where fDuration is durationInSeconds converted into minutes then formatted with its remaining seconds.
		CURRENTFILE = tracksList[fileIndex].getName();
		fDuration = (((long) durationInSeconds / 60) % 60)+":"+(long)durationInSeconds%60;
		drtMilSecs = durationInSeconds*Math.pow(10, 3);
    	
	}

	public void stop() {
		clip.stop();
	}
	
	
	public void play() {
		clip.start();
	}
	
	public void setFrame(int framePos) {
		clip.setFramePosition(framePos);
	}
	
	public int index (File tracksList[]) {
			
		if (fileIndex == -1) {
			    fileIndex = (tracksList.length)-1;
		}
		else if (fileIndex >= tracksList.length) {
			    fileIndex = 0;
		} 
		return fileIndex;

	}
		
	public void playNext() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if (clip.isActive()) {
			stop();
			fileIndex = fileIndex + 1;
			load();
			play();
		} else {
			stop();
			fileIndex = fileIndex + 1;
			load();
		}
	}
	
	public void playPrev() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if (clip.isActive()) {
			stop();
			fileIndex = fileIndex - 1;
			load();
			play();
		} else {
			stop();
			fileIndex = fileIndex - 1;
			load();
		}
	}
	
	public void reset() { 
		clip.setMicrosecondPosition(0);
	}
	
	public String volume() {
		String clipVolume;
		clipVolume = String.valueOf(clip.getLevel());
		return clipVolume;
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
			System.out.println("Selected Track: "+player.CURRENTFILE+" "+player.clip.getFrameLength());
			System.out.println("play -- play song");
			System.out.println("stop -- stop song");
			System.out.println("reset -- reset song");
			System.out.println("next -- next song");
			System.out.println("last -- previous song");
			System.out.println("quit -- quit player");
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
