package ver2;

import java.util.ArrayList;

public class Maze implements Cloneable{

//    private Coordinate[][] maze;
    private ArrayList<ArrayList<Coordinate> > maze = new ArrayList<ArrayList<Coordinate>>();

    private int size;
    private Coordinate entrance;
    private Coordinate exit;

    public Maze(int size) {
        this.size = size;
        this.maze = createMaze(size);
        
    }
    
    /* createMaze() 
     * This method initializes the maze and adds Coordinates for each x & y
     * @param int n = size of maze
     * @return maze = maze
     * */
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

   
    /* findEntranceExit()
     * This method finds the entrance (start) and exit (goal) coordinates of the maze
     * This method does not return anything but updates the variables for entrance and exit
     * */
    public void findEntranceExit() {
        int i, j;
        char ch;
        Coordinate coord;
        
        for(i = 0; i < this.size; i++) {
            for(j = 0; j < this.size; j++) {
            	coord = maze.get(i).get(j);
            	ch = coord.getData();
                if(ch == 'S') {
                    this.entrance = coord;
                } else if(ch == 'G') {
                    this.exit = coord;
                }
            }
        }
    }
    
    public boolean isInBounds(int x, int y) {
    	
    	if ( ((x >= 0) && (x < (this.size))) &&
       		 ((y >= 0) && (y < (this.size))) ){
       		
    		System.out.print("valid location: ");
           	printCoord(this.maze.get(x).get(y));
           	System.out.println();
       		
           	return true;
       	}
           
       System.out.print("invalid loc (out of bounds): ");
       System.out.print(" ("+x+", "+y+") ");
       System.out.println();
       
       return false;
    }
    
    /* isValidLocation
     * This method checks if the coordinate is a valid location to explore
     * @param int x = x coordinate
     * @param int y = y coordinate
     * @return true if the location is valid
     * @return false if the location is invalid
     * */
    public boolean isValidLocation(int x, int y) {
    	if (isInBounds(x,y)){
    		char ch = this.maze.get(x).get(y).getData();
    		if (ch == '.' || ch == 'G' || ch == 'S') {
    			
    			System.out.print("valid location: ");
            	printCoord(this.maze.get(x).get(y));
            	System.out.println();
            	
                return true;
    		}
    		else {
    			System.out.print("invalid loc (wall): ");
    			printCoord(this.maze.get(x).get(y));
            	System.out.println();
            	return false;
    		}
    	}
        return false;
    }
    
    public boolean isExit(int x, int y) {
        if(this.exit.getX() == x && this.exit.getY() == y) {
            return true;
        }
        return false;
    }
    
    //DISPLAY METHODS
    
    public void printCoord(Coordinate c) {
    	System.out.print(" ("+c.getX()+", "+c.getY()+") ");
    }
    
    public void displayMaze() {
    	int i=0, j=0;
    	
    	System.out.println();
    	for(i = 0; i < this.size; i++) {
            for(j = 0; j < this.size; j++) {
                System.out.print(" "+this.getCoordinate(i, j).getData()+" ");
            }
            System.out.println();
        }
    	System.out.println();
    }
    
    public void displayMazeWBorder() {
    	int i=0, j=0;
    	
    	System.out.println();
    	for (i=0; i<this.size*3+2; i++)
    		System.out.print("=");
    	System.out.println();
    	for(i = 0; i < this.size; i++) {
    		System.out.print("|");
            for(j = 0; j < this.size; j++) {
                System.out.print(" "+this.getCoordinate(i, j).getData()+" ");
            }
            System.out.println("|");
        }
    	for (i=0; i<this.size*3+2; i++)
    		System.out.print("=");
    	System.out.println();
    }
    
    public void dispMazeWCoord() {
    	System.out.println();
    	for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                printCoord(this.getCoordinate(i, j));
                System.out.print(": "+ this.getCoordinate(i, j).getData() + " ");
            }
            System.out.println();
        }
    	System.out.println();
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
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}