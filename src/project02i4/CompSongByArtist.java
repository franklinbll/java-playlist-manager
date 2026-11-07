package project02i4;

import java.util.Comparator;

/** Orders songs by artist, then title, then duration. */
public class CompSongByArtist implements Comparator<Song> {

    @Override
    public int compare(Song first, Song second) {
        int comparison = first.getArtist().compareToIgnoreCase(second.getArtist());
        if (comparison != 0) {
            return comparison;
        }

        comparison = first.getTitle().compareToIgnoreCase(second.getTitle());
        if (comparison != 0) {
            return comparison;
        }

        return first.compareTo(second);
    }
}
