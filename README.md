# Java Playlist Manager

A command-line playlist management application written in Java. I originally created this project in 2024 while learning object-oriented programming, then revisited and refactored it to improve the structure, input handling, and maintainability.

## Features

- Create and remove playlists
- Add and delete songs
- Sort songs by title, artist, or duration
- Simulate playing a playlist in the console
- Save playlist data to a text file and load it again when the program starts
- Validate menu input and song durations
- Remove songs by restricted artists before saving

> **Note:** This project does not play audio files. Playback is simulated with console output.

## Concepts Demonstrated

- Object-oriented programming and composition
- Instance and static members
- Java collections (`List` and `LinkedList`)
- `Comparable` and `Comparator`
- Iterators
- File I/O
- Exception handling
- Input validation
- Method overriding (`equals`, `hashCode`, and `toString`)

## Project Structure

```text
src/project02i4/
├── Project02i4.java          # Program entry point
├── PlaylistApplication.java # Menus and user interaction
├── PlaylistOfPlaylists.java # Collection of playlists
├── Playlist.java            # Playlist behavior and song collection
├── Song.java                # Song model
├── IOHandling.java          # Saving and loading playlist data
├── CompSongByTitle.java     # Sort songs by title
└── CompSongByArtist.java    # Sort songs by artist
```

## Running the Project

From the project root, compile the source files:

```bash
javac -d bin src/project02i4/*.java
```

Then run the application:

```bash
java -cp bin project02i4.Project02i4
```

The included `Playlists.txt` file is used to store playlist data between runs.

## Background

This project began as a Java programming assignment in 2024. I later returned to it to clean up the code and refactor parts of the application while reviewing the Java concepts used throughout the project.
