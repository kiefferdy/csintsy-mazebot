package ver2;

import java.util.ArrayList;
import java.util.LinkedList;

public class BFSMazeBot {

    private static int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    private ArrayList<Coordinate> path;

    public BFSMazeBot() {
        this.path = new ArrayList<Coordinate>();
    }

    private ArrayList<Coordinate> backtrack(Coordinate cur) {
        Coordinate iter = cur;

        while(iter != null) {
            path.add(iter);
            iter = iter.getParent();
        }
        return path;
    }

    public ArrayList<Coordinate> solve(Maze maze) {

        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        Coordinate start = maze.getEntrance();
        nextToVisit.add(start);

        while(!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            if(!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) {
                continue;
            }

            if(maze.isExit(cur.getX(), cur.getY())) {
                return backtrack(cur);
            }

            for(int[] direction : DIRECTIONS) {
                nextToVisit.add(maze.getCoordinate(cur.getX() + direction[0], cur.getY() + direction[1]));
                maze.getCoordinate(cur.getX() + direction[0], cur.getY() + direction[1]).setParent(cur);
                maze.setExplored(cur.getX(), cur.getY());
            }
        }

        this.path = null;
        return this.path;
    }

    public void displayPath(Maze m) {
    	int n = this.path.size(), x, y;
    	Coordinate coord;
    	solve(m);
    	for (int i=0; i<n; i++) {
    		coord = this.path.get(i);
    		x = coord.getX();
    		y = coord.getY();
    		m.getCoordinate(x, y).setData(Integer.toString(i));
    	}
    	m.displayMazeWBorder();
    }

    //GETTERS & SETTERS
	public ArrayList<Coordinate> getPath() {
		return path;
	}

	public void setPath(ArrayList<Coordinate> path) {
		this.path = path;
	}
    
    
}