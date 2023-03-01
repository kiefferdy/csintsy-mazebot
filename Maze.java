package ver2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze {

//    private Coordinate[][] maze;
    private ArrayList<ArrayList<Coordinate> > maze = new ArrayList<ArrayList<Coordinate>>();

    private int size;
    private Coordinate entrance;
    private Coordinate exit;

    public Maze(int size) {
        this.size = size;
        this.maze = createMaze(size);
        
    }
    
    public ArrayList<ArrayList<Coordinate>> createMaze(int n){
        int i, j;
        for(i = 0; i < n; i++) {
        	maze.add(new ArrayList<Coordinate>());
            for(j = 0; j < n; j++) {
            	maze.get(i).add(new Coordinate(i,j));

            }
        }

		return maze;
    }

   
    public void findEntranceExit() {
        int i, j;

        for(i = 0; i < this.size; i++) {
            for(j = 0; j < this.size; j++) {
                if(maze.get(0).get(j).getData() == 'G') {
                    this.entrance = this.maze.get(i).get(j);
                } else if(maze.get(size-1).get(i).getData() == 'S') {
                    this.exit = this.maze.get(i).get(j);
                }
            }
        }
    }
    
    public boolean isValidLocation(int x, int y) {
        if((x <= (size - 1) && x >= 0) &&
           (y <= (size - 1) && y >= 0) &&
            this.maze.get(x).get(y).getData() == '.') {
            
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
    
    public void printCoord(Coordinate c) {
    	System.out.print(" ("+c.getX()+", "+c.getY()+") ");
    }
    
    public void displayMaze() {
    	int i=0, j=0;
    	for(i = 0; i < this.size; i++) {
            for(j = 0; j < this.size; j++) {
                System.out.print(this.getCoordinate(i, j).getData());
            }
            System.out.println();
        }
    }
    
    public void dispMazeWCoord() {
    	for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                printCoord(this.getCoordinate(i, j));
                System.out.print(": "+ this.getCoordinate(i, j).getData() + " ");
            }
            System.out.println();
        }
    }
    
    //GETTERS & SETTERS
    
    public Coordinate getCoordinate(int x, int y) {
        return this.maze.get(x).get(y);
    }

    public void setCoordinateData(int x, int y, char data) {
        this.maze.get(x).get(y).setData(data);
    }

    public Coordinate getEntrance() {
        return this.entrance;
    }

    public Coordinate getExit() {
        return this.exit;
    }

    
    

//    public int getRows() {
//		return rows;
//	}
//
//	public void setRows(int rows) {
//		this.rows = rows;
//	}
//
//	public int getCols() {
//		return cols;
//	}
//
//	public void setCols(int cols) {
//		this.cols = cols;
//	}

    public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
    
	public boolean isExplored(int x, int y) {
        return maze.get(x).get(y).getIsVisited();
    }

	public void setExplored(int x, int y) {
    	maze.get(x).get(y).setIsVisited();
    }
}