package ver2;

import java.util.ArrayList;

public class DFSMazeBot {

    private static int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    private ArrayList<Coordinate> path;

    public DFSMazeBot() {
        this.path = new ArrayList<Coordinate>();
    }

    /* solve()
     * This method solves the maze
     * */
    public ArrayList<Coordinate> solve(Maze maze) {
        if(!this.explore(maze, maze.getEntrance().getX(), maze.getEntrance().getY(), path)) {
            this.path = null;
        }
        return this.path;
    }

    
    private boolean explore(Maze maze, int x, int y, ArrayList<Coordinate> path) {
        if(!maze.isValidLocation(x, y) || maze.isExplored(x, y)) {
            return false;
        }

        System.out.print("Coord added to explore: ");
        maze.printCoord(maze.getCoordinate(x, y));
        System.out.println();
        
        path.add(maze.getCoordinate(x, y));
        maze.setExplored(x, y); 

        if(maze.isExit(x, y)) {
            return true;
        }

        for(int[] direction : DIRECTIONS) {
        	
        	if (maze.isInBounds(x+direction[0], y+direction[1])) {
	        	Coordinate coordinate = this.getNextCoordinate(maze, x, y, direction[0], direction[1]);
	            
	            System.out.print("Next coord: ");
	            maze.printCoord(coordinate);
	            System.out.println();
	            
	            if(explore(maze, coordinate.getX(), coordinate.getY(), path)) {
	                return true;
	            }
        	}
        }

        // Discards the explored Coordinates that don't lead to the goal
        // path.remove(path.size() - 1);
        return false;
    }

    public Coordinate getNextCoordinate(Maze maze, int x, int y, int i, int j) {
        return maze.getCoordinate(x + i, y + j);
    }

    public void displayPath(Maze maze) {

        Maze exploredMaze = new Maze(maze);
    	this.solve(maze);

    	int n = this.path.size();
    	int x, y;
    	Coordinate coord;
    	
    	for (int i = 0; i < n; i++) {
    		coord = this.path.get(i);
    		x = coord.getX();
    		y = coord.getY();
    		maze.getCoordinate(x, y).setData(Integer.toString(i));
    	}

        maze.displayMazeWBorder();
    	exploredMaze.displayMazeWBorder();
    }
}