package project02i4;

import java.util.Scanner;

/**
 * Handles the console menus and user interaction for the playlist manager.
 */
public class PlaylistApplication {

    private final Scanner scan = new Scanner(System.in);
    private PlaylistOfPlaylists currentPlaylists;

    public void run() {
        addRestrictedArtists();
        currentPlaylists = IOHandling.turnFileIntoPlaylistOfPlaylists("Playlists.txt");

        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = readInt("Choose an option (Enter a number): ", 1, 5);
            System.out.println();

            switch (choice) {
                case 1:
                    openPlaylist();
                    break;
                case 2:
                    addPlaylist();
                    break;
                case 3:
                    removePlaylist();
                    break;
                case 4:
                    showLicensingInformation();
                    break;
                case 5:
                    saveAndExit();
                    running = false;
                    break;
                default:
                    break;
            }
        }

        scan.close();
    }

    private void addRestrictedArtists() {
        Song.addRestrictedArtist("Eagles");
        Song.addRestrictedArtist("The Beatles");
        Song.addRestrictedArtist("Led Zeppelin");
    }

    private void showMainMenu() {
        System.out.println("THESE ARE YOUR PLAYLISTS: ");
        System.out.println(currentPlaylists);
        System.out.println("MAIN MENU");
        System.out.println("1. Open Existing Playlist.");
        System.out.println("2. Add Playlist.");
        System.out.println("3. Remove Playlist.");
        System.out.println("4. See Licensing Information.");
        System.out.println("5. End Program.");
    }

    private void openPlaylist() {
        if (currentPlaylists.getNumberOfPlaylists() == 0) {
            System.out.println("There are no playlists to open. Add a playlist first.\n");
            return;
        }

        int playlistNumber = readInt(
                "What playlist do you want to open? (Enter a number): ",
                1,
                currentPlaylists.getNumberOfPlaylists());

        Playlist selectedPlaylist = currentPlaylists.getPlaylistByIndex(playlistNumber - 1);

        System.out.println();
        System.out.println("Here is your selected playlist!");
        System.out.println(selectedPlaylist);

        System.out.println("What do you want to do with it?");
        System.out.println("1. Play playlist.");
        System.out.println("2. Edit playlist.");
        System.out.println("3. Go back to main.");

        int choice = readInt("Enter your choice (number): ", 1, 3);
        System.out.println();

        switch (choice) {
            case 1:
                selectedPlaylist.play();
                System.out.println();
                break;
            case 2:
                editPlaylist(selectedPlaylist);
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    private void editPlaylist(Playlist playlist) {
        System.out.println("EDIT OPTIONS:");
        System.out.println("1. Add song.");
        System.out.println("2. Delete song.");
        System.out.println("3. Sort by title.");
        System.out.println("4. Sort by artist.");
        System.out.println("5. Sort by duration.");

        int choice = readInt("Enter your choice (number): ", 1, 5);
        System.out.println();

        switch (choice) {
            case 1:
                addSongs(playlist);
                break;
            case 2:
                deleteSongs(playlist);
                break;
            case 3:
                playlist.sortByTitle();
                showSortedPlaylist(playlist, "TITLE");
                break;
            case 4:
                playlist.sortByArtist();
                showSortedPlaylist(playlist, "ARTIST");
                break;
            case 5:
                playlist.sortByDuration();
                showSortedPlaylist(playlist, "DURATION");
                break;
            default:
                break;
        }
    }

    private void addSongs(Playlist playlist) {
        boolean keepAdding = true;

        while (keepAdding) {
            System.out.println("That's great! Enter the name of the new song: ");
            String title = scan.nextLine();

            System.out.println("Who created it?: ");
            String artist = scan.nextLine();

            String duration;
            do {
                System.out.println("What's its duration? ***Enter in format (mm:ss) ***");
                duration = scan.nextLine();

                if (!Song.isValidDuration(duration)) {
                    System.out.println("Invalid duration. Use mm:ss (for example, 03:45).");
                }
            } while (!Song.isValidDuration(duration));

            Song newSong = new Song(title, artist, duration);
            playlist.addSong(newSong);

            System.out.println("This is gonna be the song that will be added: "
                    + title + " by " + artist);
            System.out.println();

            System.out.println("Do you want to keep adding songs?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            int choice = readInt("Enter a number: ", 1, 2);
            System.out.println();

            if (choice == 2) {
                keepAdding = false;
                System.out.println("Back to main menu...");
                System.out.println();
            }
        }
    }

    private void deleteSongs(Playlist playlist) {
        if (playlist.getNumberOfSongs() == 0) {
            System.out.println("You cannot delete songs from an empty playlist. Back to main...");
            System.out.println();
            return;
        }

        boolean keepDeleting = true;

        while (keepDeleting) {
            System.out.println(playlist);

            int songNumber = readInt(
                    "What song do you want to delete? (Enter a number): ",
                    1,
                    playlist.getNumberOfSongs());

            playlist.removeSongAtIndex(songNumber - 1);
            System.out.println();

            if (playlist.getNumberOfSongs() == 0) {
                System.out.println("The playlist is now empty. Back to main menu...");
                System.out.println();
                return;
            }

            System.out.println("Do you want to keep deleting songs?");
            System.out.println("1. Yes");
            System.out.println("2. No");

            int choice = readInt("Enter a number: ", 1, 2);
            System.out.println();

            if (choice == 2) {
                keepDeleting = false;
                System.out.println("Back to main menu...");
                System.out.println();
            }
        }
    }

    private void showSortedPlaylist(Playlist playlist, String sortType) {
        System.out.println("HERE IS YOUR NEW SORTED PLAYLIST! (BY " + sortType + ")");
        System.out.println(playlist);
        System.out.println("BACK TO THE MAIN MENU...");
        System.out.println();
    }

    private void addPlaylist() {
        while (true) {
            System.out.println("What will be the name of the playlist you want to add? ");
            String name = scan.nextLine();

            if (name.isBlank()) {
                System.out.println("A white space is not a name!");
                continue;
            }

            if (currentPlaylists.isInListOfPlaylists(name)) {
                System.out.println("You already have a playlist with that name. Try again!");
                continue;
            }

            currentPlaylists.addPlaylist(new Playlist(name));
            System.out.println("Your new playlist has been added successfully. Back to the main menu...");
            System.out.println();
            return;
        }
    }

    private void removePlaylist() {
        if (currentPlaylists.getNumberOfPlaylists() == 0) {
            System.out.println("There are no playlists to remove.\n");
            return;
        }

        int playlistNumber = readInt(
                "What playlist do you want to remove? (Enter a number): ",
                1,
                currentPlaylists.getNumberOfPlaylists());

        Playlist playlist = currentPlaylists.getPlaylistByIndex(playlistNumber - 1);
        System.out.print(playlist.getName() + " has been removed. ");

        currentPlaylists.removePlaylistByIndex(playlistNumber - 1);

        System.out.println("Back to main menu...");
        System.out.println();
    }

    private void showLicensingInformation() {
        System.out.println("We don't have the rights to store tracks by these artists: ");
        System.out.println(Song.getRestrictedArtists());
        System.out.println("All songs by these creators will be deleted when the program ends.");
        System.out.println("We apologize for the inconvenience. Our teams are working to get their rights.");
        System.out.println("BACK TO MAIN...");
        System.out.println();
    }

    private void saveAndExit() {
        currentPlaylists.removeRestrictedSongs();
        IOHandling.writePlaylistsToFile(currentPlaylists, "Playlists.txt");
    }

    /**
     * Reads a whole line, converts it to an integer, and keeps asking until
     * the user enters a number inside the requested range.
     */
    private int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String input = scan.nextLine();

            try {
                int number = Integer.parseInt(input);

                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException e) {
                // The message below handles non-numeric input too.
            }

            System.out.println("That is not a valid option. Enter a number from "
                    + min + " to " + max + ".");
        }
    }
}