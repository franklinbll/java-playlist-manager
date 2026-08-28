package project02i4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author frank
 */


public class Playlist {
    
    private String name; // Title of the playlist
    private List<Song> songList; // List of songs in the playlist

    // Constructor to initialize a Playlist object with a name
    public Playlist(String name) {
        this.name = name; 
        songList = new ArrayList<>(); 
    }
    public String getName() {
        return name;
    }
    
    // Adds a song to the playlist
    public void addSong(Song newSong) {
        songList.add(newSong); 
    }
    
    // Removes a song from the playlist
    public void removeSong(Song s) {
        songList.remove(s); 
    }
    
    // Removes a song at a specific index from the playlist
    public void removeSongAtIndex(int i) {
        if (i >= 0 && i < songList.size()) {
            songList.remove(i); 
        }
    }
    
    // Removes all restricted songs from the playlist
    public void removeSongsByIsRestricted() {
        
        Iterator<Song> iterator = songList.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            // If song is restricted, print a message and removes it from the playlist
            if (song.isRestricted()) {
                System.out.println("Due to licensing restrictions, " + song.getTitle()
                + " by "+ song.getArtist() + " has been excluded from the collection.");
                iterator.remove(); // Use iterator to remove
            }
        }
    }
    
    // Gets the total duration of the playlist
    public String getDuration() {
        int totalSeconds = 0;  // Temp variable to hold total duration 
        
        // Loop through each song in the list and accumulate its duration
        for (Song song : songList) {
            totalSeconds = totalSeconds + song.getDurationInSeconds(); 
        }
        
        // Calculates hours, minutes, and seconds
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
    
        // Format hours, minutes, and seconds with leading zeros if necessary
        String stringHours = (hours < 10) ? "0" + hours : Integer.toString(hours);
        String stringMinutes = (minutes < 10) ? "0" + minutes : Integer.toString(minutes);
        String stringSeconds = (seconds < 10) ? "0" + seconds : Integer.toString(seconds);
       
        return "(" + stringHours + ":" + stringMinutes + ":" + stringSeconds + ")"; 
    }
    
    // Prints the name and duration of the playlist
    public void printNameAndDuration() {
        System.out.println("Playlist: " + getName() + ". Duration: " + getDuration());
    }
    
    // Sorts the songs in the playlist by title
    public void sortByTitle() {
        Collections.sort(songList, new CompSongByTitle());
    }
    
    // Sorts the songs in the playlist by artist
    public void sortByArtist() {
        Collections.sort(songList, new CompSongByArtist());
    }
    
    // Sorts the songs in the playlist by duration
    public void sortByDuration() {
        Collections.sort(songList);
    }
    
    // Plays all the songs in the playlist
    public void play() {
        for (Song song : songList) {
            song.play();
        }
    }
    
    // Gets a song from the playlist by index
    public Song getSongByIndex(int i) {
        return songList.get(i);
    }
    
    // Gets the number of songs in the playlist
    public int getNumberOfSongs() {
        return songList.size();
    }
    
    // Converts the playlist to CSV format
    public String toCSV() {
        String playlist = name + ","; 
        
        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);
            playlist = playlist + song.getTitle() + "," + song.getArtist() + "," + song.getDuration();  
            if (i < songList.size() - 1) {
                playlist = playlist + ","; 
            }
        }
        return playlist;  
    }
    
    // Creates a Playlist object from a CSV string
    public static Playlist fromCSV(String line) {
        if (line == null || line.isBlank()) {
            return null;
        }

        String[] elements = line.split(",", -1);
        if (elements.length == 0 || elements[0].isBlank()) {
            return null;
        }

        Playlist result = new Playlist(elements[0]);
        for (int i = 1; i + 2 < elements.length; i += 3) {
            try {
                result.addSong(new Song(elements[i], elements[i + 1], elements[i + 2]));
            } catch (IllegalArgumentException e) {
                System.out.println("Skipped a song with invalid data in playlist: " + elements[0]);
            }
        }
        return result;
    }

    // Represents the playlist as a string
    public String toString() { 
        
        String playlist = name + ":\n"; //Adds the name of the playlist 
        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i); 
            playlist = playlist + (i + 1) + ". " + song; //Adds correspoding song (toString) preceded by an index
            
            // Appends a message for restricted songs
            if (song.isRestricted()) {
                playlist = playlist + " (THIS SONG WILL BE DELETED WHEN THE PROGRAM ENDS)";
            }
            
            if (i < songList.size() - 1) {
                playlist = playlist  + "," + "\n"; // Adds a comma and a newline character after every song but the last one
            }
        }
        return playlist + "\n"; // Returns the completed String playlist
    }
}
