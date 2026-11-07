package project02i4;

import java.util.Comparator;

/** Orders songs by title, then artist, then duration. */
public class CompSongByTitle implements Comparator<Song> {

    @Override
    public int compare(Song first, Song second) {
        int comparison = first.getTitle().compareToIgnoreCase(second.getTitle());
        if (comparison != 0) {
            return comparison;
        }

        comparison = first.getArtist().compareToIgnoreCase(second.getArtist());
        if (comparison != 0) {
            return comparison;
        }

        return first.compareTo(second);
    }
}
