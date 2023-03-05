package ver2;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DFSMazeBot {

    // Up, right, down, left
    private static int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    private ArrayList<Coordinate> path;
    private boolean animate;
    private boolean showFinalPathOnly;

    public DFSMazeBot() {
        this.path = new ArrayList<Coordinate>();
        this.animate = false;
        this.showFinalPathOnly = false;
    }

    /* solve()
     * This method solves the maze
     * */
    public boolean solve(Maze maze) {
        if(maze.getEntrance() == null) {
            System.out.println("ERROR: No starting point found!");
            return false;
        }

        if(!this.explore(maze, maze.getEntrance().getX(), maze.getEntrance().getY(), path)) {
            System.out.println("ERROR: No path found!");
            return false;
        }

        return true;
    }
    
    private boolean explore(Maze maze, int x, int y, ArrayList<Coordinate> path) {
        if(!maze.isValidLocation(x, y) || maze.isExplored(x, y)) {
            return false;
        }

        if(!this.showFinalPathOnly && this.animate) {
            System.out.print("Exploring ");
            maze.printCoord(maze.getCoordinate(x, y));
            System.out.print("...");
        }
        
        path.add(maze.getCoordinate(x, y));
        maze.setExplored(x, y);

        if(this.animate) {
            try {
                TimeUnit.MILLISECONDS.sleep(750);
                this.displayPath(maze);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(maze.isExit(x, y)) {
            return true;
        }

        for(int[] direction : DIRECTIONS) {
        	
        	if (maze.isInBounds(x + direction[0], y + direction[1])) {
	        	Coordinate coordinate = this.getNextCoordinate(maze, x, y, direction[0], direction[1]);
	            
	            if(explore(maze, coordinate.getX(), coordinate.getY(), path)) {
	                return true;
	            }
        	}
        }

        // Discards the explored Coordinates that don't lead to the goal
        if(this.showFinalPathOnly)
            path.remove(path.size() - 1);
        
        return false;
    }

    public Coordinate getNextCoordinate(Maze maze, int x, int y, int i, int j) {
        return maze.getCoordinate(x + i, y + j);
    }

    public void displayPath(Maze maze) {

        int n = this.path.size();
        int x, y;
        Coordinate coord;
        
        for (int i = 0; i < n; i++) {
            coord = this.path.get(i);
            x = coord.getX();
            y = coord.getY();
            // maze.getCoordinate(x, y).setData(Integer.toString(i));
            maze.getCoordinate(x, y).setData("L");
        }

        maze.displayMazeWBorder();
    }

    // GETTERS & SETTERS

    public void enableAnimation() {
        this.animate = true;
    }

    public void showFinalPathOnly() {
        this.showFinalPathOnly = true;
    }

    public int getLocationsExplored() {
        return this.path.size();
    }
}