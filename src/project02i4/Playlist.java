package project02i4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/** Represents a named collection of songs. */
public class Playlist {

    private final String name;
    private final List<Song> songList;

    public Playlist(String name) {
        this.name = name;
        songList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSong(Song newSong) {
        songList.add(newSong);
    }

    public void removeSong(Song song) {
        songList.remove(song);
    }

    public void removeSongAtIndex(int index) {
        if (index >= 0 && index < songList.size()) {
            songList.remove(index);
        }
    }

    public void removeSongsByIsRestricted() {
        Iterator<Song> iterator = songList.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.isRestricted()) {
                System.out.println("Due to licensing restrictions, " + song.getTitle()
                        + " by " + song.getArtist() + " has been excluded from the collection.");
                iterator.remove(); // Remove safely while iterating over the list.
            }
        }
    }

    public String getDuration() {
        int totalSeconds = 0;

        for (Song song : songList) {
            totalSeconds += song.getDurationInSeconds();
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        String stringHours = (hours < 10) ? "0" + hours : Integer.toString(hours);
        String stringMinutes = (minutes < 10) ? "0" + minutes : Integer.toString(minutes);
        String stringSeconds = (seconds < 10) ? "0" + seconds : Integer.toString(seconds);

        return "(" + stringHours + ":" + stringMinutes + ":" + stringSeconds + ")";
    }

    public void printNameAndDuration() {
        System.out.println("Playlist: " + getName() + ". Duration: " + getDuration());
    }

    public void sortByTitle() {
        Collections.sort(songList, new CompSongByTitle());
    }

    public void sortByArtist() {
        Collections.sort(songList, new CompSongByArtist());
    }

    public void sortByDuration() {
        Collections.sort(songList);
    }

    public void play() {
        for (Song song : songList) {
            song.play();
        }
    }

    public Song getSongByIndex(int index) {
        return songList.get(index);
    }

    public int getNumberOfSongs() {
        return songList.size();
    }

    public String toCSV() {
        StringBuilder playlist = new StringBuilder();
        playlist.append(name).append(",");

        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);
            playlist.append(song.getTitle()).append(",")
                    .append(song.getArtist()).append(",")
                    .append(song.getDuration());
            if (i < songList.size() - 1) {
                playlist.append(",");
            }
        }
        return playlist.toString();
    }

    // Each line starts with a playlist name, followed by title/artist/duration triples.
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

    @Override
    public String toString() {
        StringBuilder playlist = new StringBuilder();
        playlist.append(name).append(":\n");
        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);
            playlist.append(i + 1).append(". ").append(song);

            if (song.isRestricted()) {
                playlist.append(" (THIS SONG WILL BE DELETED WHEN THE PROGRAM ENDS)");
            }

            if (i < songList.size() - 1) {
                playlist.append(",\n");
            }
        }
        return playlist.append("\n").toString();
    }
}
