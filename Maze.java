public class Maze {

    private Coordinate[][] maze;
    private int rows;
    private int cols;
    private Coordinate entrance;
    private Coordinate exit;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        int i, j;
        for(i = 0; i < this.rows; i++) {
            for(j = 0; j < this.cols; j++) {
                maze[i][j] = new Coordinate(i, j);
            }
        }
    }

    public Coordinate getCoordinate(int x, int y) {
        return this.maze[x][y];
    }

    public void setCoordinateData(int x, int y, String data) {
        this.maze[x][y].setData(data);
    }

    public void findEntranceExit() {
        int i, j;

        for(i = 0; i < this.rows; i++) {
            for(j = 0; j < this.cols; j++) {
                if(maze[0][i].getData() == "G") {
                    this.entrance = this.maze[i][j];
                } else if(maze[rows - 1][i].getData() == "S") {
                    this.exit = this.maze[i][j];
                }
            }
        }
    }

    public Coordinate getEntrance() {
        return this.entrance;
    }

    public Coordinate getExit() {
        return this.exit;
    }

    public boolean isValidLocation(int x, int y) {
        if((x <= (rows - 1) && x >= 0) &&
           (y <= (cols - 1) && y >= 0) &&
            this.maze[x][y].getData() == ".") {
            
            return true;
        }
        return false;
    }

    public boolean isExit(int x, int y) {
        if(this.exit.getX() == x && this.exit.getY() == y) {
            return true;
        }
        return false;
    }

    public boolean isExplored(int x, int y) {
        return maze[x][y].getIsVisited();
    }

    public void setExplored(int x, int y) {
        maze[x][y].setIsVisited();
    }
}