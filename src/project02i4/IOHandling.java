package project02i4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/** Handles saving and loading playlist data. */
public class IOHandling {

    public static void writePlaylistsToFile(PlaylistOfPlaylists playlistOP, String fileName) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            writer.print(playlistOP.toCSV());
        } catch (IOException e) {
            System.out.println("File could not be opened for writing: " + e.getMessage());
        }
    }

    public static PlaylistOfPlaylists turnFileIntoPlaylistOfPlaylists(String fileName) {
        StringBuilder fileAsString = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileAsString.append(line).append("\n");
            }
            return PlaylistOfPlaylists.fromCSV(fileAsString.toString());
        } catch (IOException e) {
            System.out.println("File could not be opened. Starting with an empty playlist collection.");
            return new PlaylistOfPlaylists();
        }
    }
}
