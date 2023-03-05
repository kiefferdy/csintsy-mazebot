package ver2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Asks user for system preferences
		Scanner sc = new Scanner(System.in);
		String filepath = "";
		String animateChoice = "";

		do {
			System.out.print("Enter the filepath of the maze file: ");
			filepath = sc.nextLine();
		} while(!isValidFilePath(filepath));

		do {
			System.out.print("Would you like to see the animation? (Y/N): ");
			animateChoice = sc.nextLine();
		} while(!animateChoice.equalsIgnoreCase("Y") && !animateChoice.equalsIgnoreCase("N"));

		sc.close();

		// Gets size of map from file
		int size;
		size = mapSize(filepath);
		
		// Transfers data from file to object Maze
		Maze maze = new Maze(size);
		Maze duplicateMaze = new Maze(size);
		fileToMaze(filepath, maze);
		fileToMaze(filepath, duplicateMaze);
		
		// Displays the maze
		System.out.println("Maze size: " + size);
		if (maze != null)
			maze.displayMazeWBorder();
		
		maze.displayMazeWCoord();
		maze.findEntranceExit();
		duplicateMaze.findEntranceExit();
		
		DFSMazeBot dfs = new DFSMazeBot();
		DFSMazeBot dfsFinalPath = new DFSMazeBot();
		dfsFinalPath.showFinalPathOnly();
		if(animateChoice.equalsIgnoreCase("Y"))
			dfs.enableAnimation();
		
		System.out.println("Attempting to solve the maze with DFS...");
		dfs.solve(maze);
		dfs.displayPath(maze);
		
		dfsFinalPath.solve(duplicateMaze);
		System.out.println();
		System.out.println("============ FINAL PATH TAKEN ============");
		dfsFinalPath.displayPath(duplicateMaze);
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
    		System.out.println("An error occured.");
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
    		for (int i = 0; i < n; i++) {
    			str = br.readLine().trim();
    			for (int j = 0; j < n; j++) {
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

	public static boolean isValidFilePath(String filepath) {
		try {
			Paths.get(filepath);
		} catch(InvalidPathException ex) {
			return false;
		}
		return true;
	}
}