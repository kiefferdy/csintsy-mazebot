import java.util.ArrayList;

public class DFSMazeBot {

    private static int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    private ArrayList<Coordinate> path;

    public DFSMazeBot() {
        this.path = new ArrayList<Coordinate>();
    }

    public ArrayList<Coordinate> solve(Maze maze) {
        if(!this.explore(maze, maze.getEntrance().getX(), maze.getEntrance().getY(), this.path)) {
            this.path = null;
        }
        return this.path;
    }

    private boolean explore(Maze maze, int x, int y, ArrayList<Coordinate> path) {
        if(!maze.isValidLocation(x, y) || maze.isExplored(x, y)) {
            return false;
        }

        path.add(maze.getCoordinate(x, y));
        maze.setExplored(x, y);

        if(maze.isExit(x, y)) {
            return true;
        }

        for(int[] direction : DIRECTIONS) {
            Coordinate coordinate = this.getNextCoordinate(maze, x, y, direction[0], direction[1]);

            if(explore(maze, coordinate.getX(), coordinate.getY(), path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public Coordinate getNextCoordinate(Maze maze, int x, int y, int i, int j) {
        return maze.getCoordinate(x + i, y + j);
    }

    public void engravePath(Maze maze) {
        for (Coordinate cur : this.path) {
            maze.getCoordinate(cur.getX(), cur.getY()).setData("O");
        }
    }
}