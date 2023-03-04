package ver2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		// Gets size of map from file
		int size;
		size = mapSize("C:\\Users\\Kieffer Dy\\Desktop\\maze.txt");
		
		// Transfers data from file to object Maze
		Maze m = new Maze(size);
		fileToMaze("C:\\Users\\Kieffer Dy\\Desktop\\maze.txt", m);
		
		// Displays the maze
		System.out.println("Size: "+size);
		if (m != null)
			m.displayMazeWBorder();
		
		m.displayMazeWCoord();
		m.findEntranceExit();
		
		DFSMazeBot dfs = new DFSMazeBot();
		System.out.println("Attempting to solve the maze with DFS...");
		dfs.displayPath(m);

		/* UNDER CONSTRUCTION
		BFSMazeBot bfs = new BFSMazeBot();
		System.out.println("Attempting to solve the maze with BFS...");
		bfs.displayPath(m);
		*/
	}
	
	/* mapSize()
	 * This method gets the size of the map from the file
	 * @param String fileName = name of file
	 * @return int n = size of the map
	 * */
	public static int mapSize(String fileName) {
		int n = 0;
		String s = "";
		
		try {
    		// Loads data from file
		    BufferedReader br = new BufferedReader(new FileReader(fileName));
		    s = br.readLine().trim();
    		n = Integer.valueOf(s);    		
	        br.close();
    		
    	} catch (IOException e) {
    		System.out.println("An error occured");
    		e.printStackTrace();}
		
		return n;
	}
	
	/* fileToMaze()
	 * This function reads the text file
	 * and loads the data into the object Maze
	 * @param String fileName = name of file
	 * @param Maze m = maze
	 * */
    public static void fileToMaze(String fileName, Maze m) {
    	    	
    	String str = "";
		int n;
		
		try {
		    BufferedReader br = new BufferedReader(new FileReader(fileName));
		    
		    // Getting size
		    str = br.readLine().trim();
    		n = Integer.valueOf(str);
    		
    		// Setting String data per coordinate of map
    		for (int i=0; i<n; i++) {
    			str = br.readLine().trim();
    			for (int j=0; j<n; j++) {
					System.out.println(Character.toString(str.charAt(j)));
    				m.setCoordinateData(i, j, Character.toString(str.charAt(j)));
    			}
    		}
	        br.close();
    		
    	} catch (IOException e) {
    		System.out.println("An error occured.");
    		e.printStackTrace();
		}
    }
}