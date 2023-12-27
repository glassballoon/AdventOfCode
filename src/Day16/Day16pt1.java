package Day16;

import Utils.Grid2D;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day16pt1 {

    public static final char emptySpace = '.';
    public static final char rightAngleMirror = '/';
    public static final char leftAngleMirror = '\\';
    public static final char upSplitter = '|';
    public static final char sideSplitter = '-';

    public static void main(String[] args) {

//        Path filePath = Paths.get("src/Input/Day16TestInput.txt");
        Path filePath = Paths.get("src/Input/Day16Input.txt");

        try {
            var lines = Files.readAllLines(filePath);

            var grid = new Grid2D();
            grid.loadGrid(lines);

            Tile[] tiles = new Tile[grid.getSize()]; // auto initialised to false
            for (int i = 0; i < tiles.length; i++)
                tiles[i] = new Tile();

            List<Beam> beams = new ArrayList<Beam>();

            beams.add(new Beam(0, BeamDirection.RIGHT));

            while (beams.size() > 0) {

                var newBeams = new ArrayList<Beam>();

                for (Beam beam : beams) {

                    // has this tile been accessed via the same direction before? then don't do this beam and remove
                    int currentLocation = beam.getCurrentLocation();
                    int nextLocation = -1;
                    BeamDirection currentDirection = beam.getDirection();
                    BeamDirection nextDirection = beam.getDirection();

                    Tile currentTile = tiles[currentLocation];

                    if (currentTile.isBeenVisited()) {
                        if ((currentDirection == BeamDirection.RIGHT && currentTile.isRightDirection())
                                || (currentDirection == BeamDirection.LEFT && currentTile.isLeftDirection())
                                || (currentDirection == BeamDirection.UP && currentTile.isUpDirection())
                                || (currentDirection == BeamDirection.DOWN && currentTile.isDownDirection())) {
                            beam.setCurrentLocation(-1);
                            continue;
                        }
                    }

                    if (grid.getCharacterAt(currentLocation) == rightAngleMirror) {
                        if (currentDirection == BeamDirection.RIGHT) nextDirection = BeamDirection.UP;
                        else if (currentDirection == BeamDirection.DOWN) nextDirection = BeamDirection.LEFT;
                        else if (currentDirection == BeamDirection.LEFT) nextDirection = BeamDirection.DOWN;
                        else if (currentDirection == BeamDirection.UP) nextDirection = BeamDirection.RIGHT;
                    } else if (grid.getCharacterAt(currentLocation) == leftAngleMirror) {
                        if (currentDirection == BeamDirection.RIGHT) nextDirection = BeamDirection.DOWN;
                        else if (currentDirection == BeamDirection.DOWN) nextDirection = BeamDirection.RIGHT;
                        else if (currentDirection == BeamDirection.LEFT) nextDirection = BeamDirection.UP;
                        else if (currentDirection == BeamDirection.UP) nextDirection = BeamDirection.LEFT;
                    } else if (grid.getCharacterAt(currentLocation) == upSplitter) {
                        if (currentDirection == BeamDirection.RIGHT || beam.getDirection() == BeamDirection.LEFT) {
                            nextDirection = BeamDirection.DOWN;
                            newBeams.add(new Beam(grid.getLocationAbove(currentLocation), BeamDirection.UP));
                        }
                    } else if (grid.getCharacterAt(currentLocation) == sideSplitter) {
                        if (currentDirection == BeamDirection.UP || beam.getDirection() == BeamDirection.DOWN) {
                            nextDirection = BeamDirection.RIGHT;
                            newBeams.add(new Beam(grid.getLocationToLeft(currentLocation), BeamDirection.LEFT));
                        }
                    }

                    if (nextDirection == BeamDirection.RIGHT)
                        nextLocation = grid.getLocationToRight(currentLocation);
                    else if (nextDirection == BeamDirection.DOWN)
                        nextLocation = grid.getLocationBelow(currentLocation);
                    else if (nextDirection == BeamDirection.LEFT)
                        nextLocation = grid.getLocationToLeft(currentLocation);
                    else if (nextDirection == BeamDirection.UP)
                        nextLocation = grid.getLocationAbove(currentLocation);

                    currentTile.setBeenVisited(true);
                    if (currentDirection == BeamDirection.RIGHT)
                        currentTile.setRightDirection(true);
                    else if (currentDirection == BeamDirection.LEFT)
                        currentTile.setLeftDirection(true);
                    else if (currentDirection == BeamDirection.UP)
                        currentTile.setUpDirection(true);
                    else if (currentDirection == BeamDirection.DOWN)
                        currentTile.setDownDirection(true);

                    beam.setCurrentLocation(nextLocation);
                    beam.setDirection(nextDirection);
                }

                beams.addAll(newBeams);
                newBeams.clear();
                beams.removeIf(b -> b.getCurrentLocation() == -1);
            }

            int sum = Arrays.asList(tiles).stream().filter(t -> t.isBeenVisited()).toList().size();
            System.out.println(sum);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}