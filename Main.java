package ver2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		int size;
		size = mapSize("C:\\Users\\nicol\\OneDrive\\Desktop\\map.txt");
		Maze m = new Maze(size);
		fileToMaze("C:\\Users\\nicol\\OneDrive\\Desktop\\map.txt", m, size);
		System.out.println(size);
		if (m != null)
			m.displayMaze();
	}

	public static int mapSize(String fileName) {
		int n=0;
		String s = "";
		
		try {
    		//load data from file
			
		    BufferedReader br = new BufferedReader(new FileReader(fileName));
		    s = br.readLine().trim();
    		n = Integer.valueOf(s);    		
	        br.close();
    		
    	} catch (IOException e) {
    		System.out.println("An error occured");
    		e.printStackTrace();}
		
		return n;
	}
	
	//This function reads the text file and loads the data into the object Maze
    public static void fileToMaze(String fileName, Maze m, int size) {
    	    	
    	String str = "";
		int n;
		
		try {
		    BufferedReader br = new BufferedReader(new FileReader(fileName));
		    
		    //getting size
		    str = br.readLine().trim();
    		n = Integer.valueOf(str);
    		
    		//setting char data per coordinate of map
    		for (int i=0; i<n; i++) {
    			str = br.readLine().trim();
    			for (int j=0; j<n; j++) {
    				m.setCoordinateData(i, j, str.charAt(j));
    			}
    		}
    		
	        br.close();
    		
    	} catch (IOException e) {
    		System.out.println("An error occured");
    		e.printStackTrace();}
    }
}
