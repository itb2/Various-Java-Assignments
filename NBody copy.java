import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

import princeton.StdAudio;
import princeton.StdDraw;

public class NBody {
    public static final double G = 6.67E-11;
    
    /**
     * Displays file chooser for browsing in the project directory. and opens
     * the selected file
     *
     * @return a new Scanner that produces values scanned from the selected
     *         file. null if file could not be opened or was not selected
     */
    public static Scanner openFileFromDialog() {
        Scanner scan = null;
        System.out.println("Opening file dialog.");
        JFileChooser openChooser = new JFileChooser(System.getProperties()
                                                    .getProperty("user.dir"));
        int retval = openChooser.showOpenDialog(null);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = openChooser.getSelectedFile();
            try {
                scan = new Scanner(file);
                System.out.println("Opening: " + file.getName() + ".");
            }
            catch (FileNotFoundException e) {
                System.out.println("Could not open selected file.");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("File open canceled.");            
        }
        return scan;
    }

    /**
     * returns Euclidean distance between (x1, y1) and (x2, y2)
     *
     * @param x1
     *            x-coordinate of point 1
     * @param y1
     *            y-coordinate of point 1
     * @param x2
     *            x-coordinate of point 2
     * @param y2
     *            y-coordinate of point 2
     * @return Euclidean distance between (x1, y1) and (x2, y2)
     */
    public double distance(double x1, double y1, double x2, double y2) {         
    	//TODO: Complete distance
        //r = srt((x2-x1)^2 + (y2-y1)^2)
    	double xchange = Math.pow((x2-x1), 2);  
        double ychange = Math.pow((y2-y1), 2);
    	double r = Math.sqrt(xchange + ychange);
    	//return r
    	return r;
    }
    
    
    /**
     * return the magnitude of the gravitational force between two bodies of
     * mass m1 and m2 that are distance r apart
     *
     * @param m1
     *            mass of body 1 in kg
     * @param m2
     *            mass of body 2 in kg
     * @param r
     *            distance in m
     * @return force between m1 and m2 that are distance r apart
     */
    public double force(double m1, double m2, double r) {
        //TODO: Complete force
    	// f = (Gravity*m1*m2)/r^2
    	double f = (G*m1*m2)/(Math.pow(r, 2));
    	
    	
    	//return f
        return f;
    }


    /**
     * Returns the x positions and y positions of bodies
     * @param totalTime The total amount of universe time to run for
     * @param timeStep The value of delta t in the equations to calculate position
     * @param info The scanner with info about the initial conditions of the
     * bodies
     * @return an array whose first element is the x positions of the bodies,
     * and whose second element is the y positions of the bodies at time
     * t = totalTime
     */
    public double[][] positions(Scanner info, int totalTime,
                                int timeStep) {
        //TODO: Complete positions
    	int N = info.nextInt();
    	double[][] output = new double[2][N]; //Replace 0 with the number of
                                       //bodies, read from the file
                                       //^ should return  the  N value which is the first integer.. follows after "data/
    	
    	double radius = info.nextDouble();
    	//mass[i], px[i], py[i], vx[i], vy[i],image[i]
		double[] px = new double[N];
    	double[] py = new double[N];
    	double[] vx = new double[N];
    	double[] vy = new double[N];
    	double[] mass = new double[N];
    	//image[i]
    	String[] image = new String[N];
    	//for/while loop for N times iteration through i values. 
    	for (int i = 0; i < N ;i++){
    		// add the info from the i-th line to the variables defined outside the loop
    	
    		px[i] = info.nextDouble();
        	py[i] = info.nextDouble();
        	vx[i] = info.nextDouble();
        	vy[i] = info.nextDouble();
        	mass[i] = info.nextDouble();
        	image[i] = info.next();
    	}
    	
    	StdDraw.setXscale(-radius,radius);
    	StdDraw.setYscale(-radius,radius);
    	
    	for (int k=0; k< totalTime; k+= timeStep){
    		StdDraw.picture(0, 0, "data/starfield.jpg");
    		for (int i=0; i < N; i++){
    			StdDraw.picture(px[i], py[i], "data/" + image[i]);
    			double netFx = 0;   //initializes the net force in the x and y directions to zero
				double netFy = 0; 
    			for (int j=0; j< N; j++){
    				double f;
    				double d = distance(px[i], py[i], px[j], py[j]); // sets return value from distance method to a value
    				double[] Fx = new double[N];
    				double[] Fy = new double[N];
    				if (i==j){
    					f= 0;   //if the two planets are equal the force should be zero
    					Fx[j]=0;
    					Fy[j]=0;
    				}else{
    					f= force (mass[i], mass[j],d); //force given two planets
    					Fx[j] = f*((px[j]-px[i])/d); // Net force in x direction
        				Fy[j] = f*((py[j]-py[i])/d); // net force in y direction
    				}   				
    				netFx += Fx[j];
    				netFy += Fy[j];
    				
    			}
    	
				double Ax = netFx/mass[i];
   	    		double Ay = netFy/mass[i]; // acceleration x and y
   	    		
	    		vx[i] = (vx[i] + timeStep *Ax);
	    		vy[i] = (vy[i] + timeStep * Ay); //updated velocity x and y
	    		
	    		px[i] =( px[i] + timeStep*vx[i]);
	    		py[i] = (py[i] + timeStep*vy[i]); //this is the updated position
	    			
        	
    		}
    		StdDraw.show(30);//make it stay on each pixel a little longer
    	}

    	output[0] = px;
        output[1] = py;
       return output;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner info;
        int time, dt;
//        if (args.length == 3){
//            info = new Scanner(new File("data/"+args[0]));
//            time = Integer.parseInt(args[1]);
//            dt = Integer.parseInt(args[2]);
//        }
//        else{
        	info = new Scanner(new File("data/"+ "planets.txt"));//info =  openFileFromDialog();
            time = 1000000;//set to this time to answer analysis questions
            dt = 25000;
        //}
        
        //StdAudio.play("superman.mid");
        NBody myNBody = new NBody();
        double[][] result = myNBody.positions(info, time, dt);
        //StdDraw.clear();
        StdAudio.close();
        System.out.println(Arrays.toString(result[0]));
        System.out.println(Arrays.toString(result[1]));
        
    }
}

