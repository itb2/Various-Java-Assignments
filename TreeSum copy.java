import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * CompSci 201: Tree Summing Extra Credit
 * determines whether there exists a root-to-leaf path whose nodes sum
 * to a specified integer
 *
 * Name:
 * Resources Used:
 * Collaborators:
 * Time Spent:

 */

public class TreeSum {
	// chooser for selecting input file
    private static javax.swing.JFileChooser ourChooser = new javax.swing.JFileChooser(System
            .getProperties().getProperty("user.dir")); 
    
    /**
     * Brings up chooser for user to select a file
     * @return Scanner for user selected file, null if file not found
     */
    public static Scanner getScanner(){       
        int retval = ourChooser.showOpenDialog(null);
        
        if (retval == JFileChooser.APPROVE_OPTION){
            File f = ourChooser.getSelectedFile();	
            Scanner s;
            try {
                s = new Scanner(f);
            } catch (FileNotFoundException e) {
                return null;
            }
            return s;
	    }
        return null;
    }


	public static void main(String[] args) {
		Scanner in = null;
	    if (args.length == 1) { // use command-line arguments for testing/grading
	    		// first argument should be filename
	    		try {
					in = new Scanner(new File(args[0]));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.exit(0);
				} 
	    }
	    else { // prompt user for file
	    		in = getScanner();
	    }
	    // TODO complete TreeSum
	}
}
