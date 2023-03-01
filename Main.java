package ver2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		int size;
		size = mapSize("C:\\Users\\nicol\\OneDrive\\Desktop\\map.txt");
		Maze m = new Maze(size);
		fileToMaze("C:\\Users\\nicol\\OneDrive\\Desktop\\map.txt", m, size);
		if (m != null)
			m.displayMaze();
	}

	public static int mapSize(String fileName) {
		int n=0;
		char ch='\0';
		String s = "";
		
		try {
    		//load data from file
    		FileReader fr = new FileReader(fileName);
    		
    		//get size (n)
    		

    		//there is extra \n's for some reason
//    		if (fr.ready()) {
//	    		while (ch != '\n') {
//	    			ch = (char)fr.read();
//	    			if (ch != '\n')
//	    				s = s + ch;
//	    			
//	    			System.out.print(" |ch: "+ch+"| ");
//	    		} 
//    		}
//    		    		
//    		System.out.print(" |s: "+s+"| ");
//    		size = Integer.valueOf(s);    		
    		
    		//======================================================================OVER HERE
    		//can only read 1 digit, for some reason idk why it wont read properly in a while loop
    		if (fr.ready()) {
    			n = Character.getNumericValue((char)fr.read());
    		}
    		
//    		System.out.println("size: "+size);
	        fr.close();
    		
    	} catch (IOException e) {}
		
		return n;
	}
	
	//This function reads the text file and loads the data into the object Maze
    public static void fileToMaze(String fileName, Maze m, int size) {
    	    	
    	try {
    		//load data from file
    		FileReader fr = new FileReader(fileName);
    		
    		char ch, dump;
    		
    		fr.read(); //for the size
    		fr.read(); //skipping \n
    		fr.read();


    		
    		//reading the rest of the file
    		
    		for (int i=0; i<size; i++) {
    			for (int j=0; j<size; j++) {
    				
    				ch = (char)fr.read();
//    				m.printCoord(m.getCoordinate(i, j));
//    				System.out.print(ch);
    				if (ch != '\n') {
    					m.getCoordinate(i, j).setData(ch);
    				}
    				else {
    					fr.read();
    				}
    			}
    			fr.read();
    		}
    		
	        fr.close();
    		
    	} catch (IOException e) {}
    	
//    	return m;
    }
}
