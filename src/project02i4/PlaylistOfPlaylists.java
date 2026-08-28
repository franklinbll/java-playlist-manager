package project02i4;

import java.util.ArrayList;
import java.util.List;

/** Represents a collection of playlists. */
public class PlaylistOfPlaylists {

    private final List<Playlist> playlistList;

    public PlaylistOfPlaylists() {
        playlistList = new ArrayList<>();
    }

    public boolean isInListOfPlaylists(String name) {
        for (Playlist playlist : playlistList) {
            if (playlist.getName().equals(name)) return true;
        }
        return false;
    }

    public void addPlaylist(Playlist playlist) {
        if (playlist != null) playlistList.add(playlist);
    }

    public void removePlaylistByName(String name) {
        playlistList.removeIf(playlist -> playlist.getName().equals(name));
    }

    public void removePlaylistByIndex(int i) {
        if (i >= 0 && i < playlistList.size()) playlistList.remove(i);
    }

    public Playlist getPlaylistByName(String name) {
        for (Playlist playlist : playlistList) {
            if (playlist.getName().equals(name)) return playlist;
        }
        return null;
    }

    public int getNumberOfPlaylists() { return playlistList.size(); }

    public Playlist getPlaylistByIndex(int i) {
        if (i < 0 || i >= playlistList.size()) return null;
        return playlistList.get(i);
    }

    public void removeRestrictedSongs() {
        for (Playlist playlist : playlistList) playlist.removeSongsByIsRestricted();
    }

    public String toCSV() {
        StringBuilder result = new StringBuilder();
        for (Playlist playlist : playlistList) result.append(playlist.toCSV()).append("\n");
        return result.toString();
    }

    public static PlaylistOfPlaylists fromCSV(String data) {
        PlaylistOfPlaylists result = new PlaylistOfPlaylists();
        if (data == null || data.isBlank()) return result;

        String[] lines = data.split("\\R");
        for (String line : lines) {
            if (line.isBlank()) continue;
            Playlist playlist = Playlist.fromCSV(line);
            if (playlist != null) result.addPlaylist(playlist);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < playlistList.size(); i++) {
            Playlist playlist = playlistList.get(i);
            result.append(i + 1).append(". ").append(playlist.getName()).append(" ")
                    .append(playlist.getDuration()).append("\n");
        }
        return result.toString();
    }
}
