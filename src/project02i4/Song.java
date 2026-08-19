package project02i4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Represents a song in a playlist. */
public class Song implements Comparable<Song> {

    private String title;
    private String artist;
    private String duration;
    private static final List<String> restrictedArtists = new ArrayList<>();

    public Song(String title, String artist, String duration) {
        setTitle(title);
        setArtist(artist);
        setDuration(duration);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    /** Sets duration in mm:ss format. */
    public void setDuration(String duration) {
        if (!isValidDuration(duration)) {
            throw new IllegalArgumentException("Duration must be in mm:ss format with seconds from 00 to 59.");
        }
        this.duration = duration;
    }

    public static boolean isValidDuration(String duration) {
        if (duration == null || !duration.matches("\\d+:\\d{2}")) {
            return false;
        }
        String[] parts = duration.split(":");
        try {
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);
            return minutes >= 0 && seconds >= 0 && seconds <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getDuration() { return duration; }

    public static void addRestrictedArtist(String restrictedA) {
        if (restrictedA != null && !restrictedArtists.contains(restrictedA)) {
            restrictedArtists.add(restrictedA);
        }
    }

    public static String getRestrictedArtists() {
        StringBuilder result = new StringBuilder();
        for (String artist : restrictedArtists) {
            result.append("* ").append(artist).append("\n");
        }
        return result.toString();
    }

    public boolean isRestricted() {
        return restrictedArtists.contains(getArtist());
    }

    public int getDurationInSeconds() {
        String[] parts = duration.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public void play() {
        System.out.println(getTitle() + " by " + getArtist() + " is playing.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Song)) return false;
        Song otherSong = (Song) obj;
        return Objects.equals(title, otherSong.title)
                && Objects.equals(artist, otherSong.artist)
                && getDurationInSeconds() == otherSong.getDurationInSeconds();
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, getDurationInSeconds());
    }

    @Override
    public int compareTo(Song other) {
        return Integer.compare(this.getDurationInSeconds(), other.getDurationInSeconds());
    }

    @Override
    public String toString() {
        return getTitle() + " by " + getArtist() + " (" + getDuration() + ")";
    }
}
