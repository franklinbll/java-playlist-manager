package project02i4;

import java.util.Comparator;

/**
 *
 * @author frank
 */

// This class is designed to sort songs primarily by artist, then by title alphabetically, 
// and finally by duration if both artist and title are the same
public class CompSongByArtist implements Comparator<Song>{
    
       @Override
    public int compare(Song s1, Song s2){
        
        // Compares artist first
        int num = s1.getArtist().compareToIgnoreCase(s2.getArtist());
        if (num!=0) {
            return num; 
            
        }
        
        // If authors are the same, compare titles alphabetically
        num = s1.getTitle().compareToIgnoreCase(s2.getTitle()); 
        if (num!=0) {
            return num; 
        }
        
       
        // If title and artist are the same, compare duration using compareTo from Song class
        return s1.compareTo(s2); 
        
    }
    
}
