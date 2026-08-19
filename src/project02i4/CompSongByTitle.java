package project02i4;

import java.util.Comparator;

/**
 *
 * @author frank
 */

// This class helps sort a collection of Song objects first by title, 
// then by artist, and finally by duration if necessary.
public class CompSongByTitle implements Comparator<Song> {
    
    @Override
    public int compare(Song s1, Song s2) {
        // Compare titles first
        int num = s1.getTitle().compareToIgnoreCase(s2.getTitle()); 
        if (num != 0) {
            return num; 
        }
        
        // If titles are the same, compare artists alphabetically
        num = s1.getArtist().compareToIgnoreCase(s2.getArtist());
        if (num != 0) {
            return num; 
        }
        
        // If title and artist are the same, compare duration using compareTo from Song class
        return s1.compareTo(s2); 
        
    }
}

