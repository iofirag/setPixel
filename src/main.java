/*This application was created by Ofir Aghai & Vidran Abdovich , 22/4/2014
 * as part of a Graphics programming course.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;


public class main {
	// Window size
	static final int WIDTH = 900;	//x
    static final int HEIGHT = 500;	//y
    
    
    //panel
    static myJPanel pane;
   
    //History object draw
	static List<Shape> shapeList=new ArrayList<>();
	static int zoom=100;	// 100%
	// Mouse variables
	static Point pointPressed =null;
	static Point pointRelease =null;
	static int lastDrag_x=0;
	static int lastDrag_y=0;
	static boolean MouseExitFromWindow=false;
	
	// item & color user choose
	static int itemChecked = 1;		//	Objects:
									//		1= Line		(default)   
									//		2= Circle   
									//		3= Polygon	
									//		4= Bezier curve
									//  
									//	Transforms:
									//		5= Shift
									//		6= Scale   
									//		7= Rotating	
									//		8= Mirroring
									//		9= Cut
	static Color color = Color.black;
		  
	//bezier variables
	static List<Point> bezierPoints=new ArrayList<>();
		
	//poligon variable
	static int poligon_vertex =0;
	
	public static void main(String[] args) {
		//see all the properties in java
		System.getProperties().list(System.out);
		
        /* java window - the container managed the frame */
        JFrame frame = new JFrame("Direct draw demo");       
        
        /* specific frame */
        pane = new myJPanel(WIDTH, HEIGHT);
        
        //******************************************************************

        frame.add(pane);
        // to show at least the panel data
        frame.pack();
        /* Resizability */
        frame.setResizable(true);     
        /* close all app in close button */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      	//Create the menu bar.
        JMenuBar menuBar = new JMenuBar();
        
        //File Menu
        JMenu fileMenu = new JMenu ("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem openFile = new JMenuItem("Open file..");
        //objectItem_line.setMnemonic(KeyEvent.VK_F1);
        openFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component c = null;
				String pathString = promptForFile(c);
				Path path = Paths.get(pathString);
				try {
					pane.fillCanvas(Color.white);
					List <String>lines = null;
					//parse can open .TXT format that saved in type UTF-8
					lines = Files.readAllLines(path, StandardCharsets.US_ASCII);
					parseLines_ToObjects(lines);	//save all objects in shapeList
					
					for (Shape s :shapeList){
						s.draw();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			private void parseLines_ToObjects(List<String> lines) {
				//run on every line in file
				for (String line : lines){
					String numOfObject_string = line.substring(0, 1);  	//take the first character
					line = line.substring(2);			//delete from line the [first and second] character at start of the line
					
					int objectNumber = Integer.parseInt(numOfObject_string);	//identify wich object is it
					char[] charArray = line.toCharArray();	//make from line char array[]
					
					Shape ob;
					int psikCntr=0;
					StringBuilder buff_string=new StringBuilder();
					int numBuff_x=-999999;	//still not initialize
					int numBuff_y=-999999;	//still not initialize
					
					Color color = null;
					List<Point> pointsBuff=new ArrayList<>();
					
					switch (objectNumber){
					// Line
					case 1: 
						for (char c :line.toCharArray()){
							if(c == ',' || c == '.'){
								psikCntr++;
								switch (psikCntr){
								case 1:	//x0 point
								case 3:	//x1 point
									numBuff_x = Integer.parseInt(buff_string.toString());
									break;
								case 2:	//y0 point
								case 4:	//y1 point
									numBuff_y = Integer.parseInt(buff_string.toString());
									pointsBuff.add(new Point(numBuff_x, numBuff_y));
									break;
								case 5:	//color
									try {
									    Field field = Class.forName("java.awt.Color").getField(buff_string.toString());
									    color = (Color)field.get(null);
									} catch (Exception e) {
									    color = null; // Not defined
									}
									break;
								}
								buff_string=new StringBuilder();	//init
							}else{
								buff_string.append(c);	//build the number
							}
						}
						ob = new Line(color, pointsBuff);	//create instance
						shapeList.add(ob);					//add to list
						psikCntr=0;							//init
						break;
					// Circle
					case 2:	
						int radius = 0;
						for (char c :line.toCharArray()){
							if(c == ',' || c == '.'){
								psikCntr++;
								switch (psikCntr){
								case 1:	//x0 point
									numBuff_x = Integer.parseInt(buff_string.toString());
									break;
								case 2:	//y0 point
									numBuff_y = Integer.parseInt(buff_string.toString());
									pointsBuff.add(new Point(numBuff_x, numBuff_y));
									break;
								case 3:	//Radius
									radius = Integer.parseInt(buff_string.toString());
									break;
								case 4:	//Color
									try {
									    Field field = Class.forName("java.awt.Color").getField(buff_string.toString());
									    color = (Color)field.get(null);
									} catch (Exception e) {
									    color = null; // Not defined
									}
									break;
								}
								buff_string=new StringBuilder();	//init
							}else{
								buff_string.append(c);	//build the number
							}
						}
						ob = new Circle(color, pointsBuff, radius);	//create instance
						shapeList.add(ob);					//add to list
						psikCntr=0;							//init
						break;
					//Poligon
					case 3:
						int vertex = 0;
						for (char c :line.toCharArray()){
							if(c == ',' || c == '.'){
								psikCntr++;
								switch (psikCntr){
								case 1:	//x0 point
								case 3:	//x1 point
									numBuff_x = Integer.parseInt(buff_string.toString());
									break;
								case 2:	//y0 point
								case 4:	//y1 point
									numBuff_y = Integer.parseInt(buff_string.toString());
									pointsBuff.add(new Point(numBuff_x, numBuff_y));
									break;
								case 5:	//color
									vertex = Integer.parseInt(buff_string.toString());
									break;
								case 6:	//color
									try {
									    Field field = Class.forName("java.awt.Color").getField(buff_string.toString());
									    color = (Color)field.get(null);
									} catch (Exception e) {
									    color = null; // Not defined
									}
									break;
								}
								buff_string=new StringBuilder();	//init
							}else{
								buff_string.append(c);	//build the number
							}
						}
						ob = new Poligon(color, pointsBuff, vertex);	//create instance
						shapeList.add(ob);					//add to list
						psikCntr=0;							//init
						break;
					//Bezier
					case 4:
						for (char c :line.toCharArray()){
							if(c == ',' || c == '.'){
								psikCntr++;
								switch (psikCntr){
								case 1:	//A.x point
								case 3:	//B.x point
								case 5:	//C.x point
								case 7:	//D.x point
									numBuff_x = Integer.parseInt(buff_string.toString());
									break;
								case 2:	//A.y point
								case 4:	//B.y point
								case 6:	//C.y point
								case 8:	//D.y point
									numBuff_y = Integer.parseInt(buff_string.toString());
									pointsBuff.add(new Point(numBuff_x, numBuff_y));
									break;
								case 9:	//color
									try {
									    Field field = Class.forName("java.awt.Color").getField(buff_string.toString());
									    color = (Color)field.get(null);
									} catch (Exception e) {
									    color = null; // Not defined
									}
									break;
								}
								buff_string=new StringBuilder();	//init
							}else{
								buff_string.append(c);	//build the number
							}
						}
						ob = new Bezier(color, pointsBuff);	//create instance
						shapeList.add(ob);					//add to list
						psikCntr=0;	
						break;
						
						
					default:
						System.out.println("line error.");
						break;
					}
				}
			}
		});
        JMenuItem saveFile = new JMenuItem("Save file");
        saveFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
				File file;
				FileWriter fw;
				BufferedWriter bw = null;
				try {
					// Open the file
					file = new File(System.getProperty("user.dir")+"/"+System.currentTimeMillis()+".txt");
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
					
					//open streaming
					fw = new FileWriter(file.getAbsoluteFile());
					bw = new BufferedWriter(fw);
					
					for (Shape s :shapeList){
						bw.write(s.toString()+'\n');
					}
					bw.close();
					System.out.println("Done");
		 
				} catch (IOException e) {
					e.printStackTrace();
					try {
						bw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        
//************************************************
        JMenu editMenu = new JMenu ("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        	// Clear Screen button
           JMenuItem unDo_menu = new JMenuItem("Undo");
           unDo_menu.addActionListener(new ActionListener() {
   			@Override
   			public void actionPerformed(ActionEvent e) {
//   				if (shapeList.size()>0){
//   					pane.fillCanvas(Color.white);
//   					shapeList.remove(shapeList.size()-1);
//   					for (Shape s : shapeList){
//   						s.draw();
//   					}
//   				}
   				if (shapeList.size()>0){
   					Shape s = shapeList.get(shapeList.size()-1);
   					s.setColor(Color.WHITE);
   					s.draw();
   					shapeList.remove(shapeList.size()-1);
   				}
   			}
   		});
           // Clear Screen button
           JMenuItem clearScreen_menu = new JMenuItem("Clear Screen");
           clearScreen_menu.addActionListener(new ActionListener() {
   			@Override
   			public void actionPerformed(ActionEvent e) {

   				pane.fillCanvas(Color.white);
   				shapeList.clear();
   				System.out.println( shapeList.toString() );
   			}
   		});
           editMenu.add(unDo_menu);
           editMenu.add(clearScreen_menu);
           
 //************************************************
        // Objects Menu
        JMenu objectsMenu = new JMenu ("Objects");
        objectsMenu.setMnemonic(KeyEvent.VK_O);
        JMenuItem objectItem_line = new JMenuItem("Line");
        objectItem_line.setMnemonic(KeyEvent.VK_F1);
        objectItem_line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked=1;
			}
		});
        JMenuItem objectItem_circle = new JMenuItem("Circle");
        objectItem_circle.setMnemonic(KeyEvent.VK_F2);
        objectItem_circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked=2;
			}
		});
        JMenuItem objectItem_poligon = new JMenuItem("Poligon");
        objectItem_poligon.setMnemonic(KeyEvent.VK_F3);
        objectItem_poligon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked=3;

				//custom title, custom icon
				String result = JOptionPane.showInputDialog("Enter poligon vertex:");
				poligon_vertex= Integer.parseInt(result);
			}
		});
        JMenuItem objectItem_bezier = new JMenuItem("Bezier Curve");
        objectItem_bezier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked=4;
			}
		});
        objectsMenu.add(objectItem_line);
        objectsMenu.add(objectItem_circle);
        objectsMenu.add(objectItem_poligon);
        objectsMenu.add(objectItem_bezier);
        
//************************************************
        
        // Color Menu
        JMenu colorMenu = new JMenu("Color");
        JMenuItem colorMenu_black = new JMenuItem("");
        colorMenu_black.setPreferredSize(new Dimension(40, 20));
        colorMenu_black.setBackground(Color.BLACK);
        colorMenu_black.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.BLACK;
			}
		});
        JMenuItem colorMenu_green = new JMenuItem("");
        colorMenu_green.setPreferredSize(new Dimension(40, 20));
        colorMenu_green.setBackground(Color.GREEN);
        colorMenu_green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.GREEN;
			}
		});
        JMenuItem colorMenu_blue = new JMenuItem("");
        colorMenu_blue.setPreferredSize(new Dimension(40, 20));
        colorMenu_blue.setBackground(Color.BLUE);
        colorMenu_blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.BLUE;
			}
		});
        JMenuItem colorMenu_white = new JMenuItem("");
        colorMenu_white.setPreferredSize(new Dimension(40, 20));
        colorMenu_white.setBackground(Color.WHITE);
        colorMenu_white.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.WHITE;
			}
		});
        JMenuItem colorMenu_yellow = new JMenuItem("");
        colorMenu_yellow.setPreferredSize(new Dimension(40, 20));
        colorMenu_yellow.setBackground(Color.YELLOW);
        colorMenu_yellow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.YELLOW;
			}
		});
        JMenuItem colorMenu_red = new JMenuItem("");
        colorMenu_red.setPreferredSize(new Dimension(40, 20));
        colorMenu_red.setBackground(Color.RED);
        colorMenu_red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = Color.RED;
			}
		});
        //Build Color menu.
        colorMenu.add(colorMenu_black);
        colorMenu.add(colorMenu_red);
        colorMenu.add(colorMenu_green);
        colorMenu.add(colorMenu_blue);
        colorMenu.add(colorMenu_white);
        colorMenu.add(colorMenu_yellow);
        
//************************************************
        
        // Transforms
        JMenu transformsMenu = new JMenu ("Transforms");
        JMenuItem transShifting = new JMenuItem("Shift");		//הזזה
        transShifting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked = 5;
			}
		});
        JMenuItem transScaling = new JMenuItem("Scaling");			//סילום
        transScaling.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked = 6;
			}
		});
        JMenuItem transRotating = new JMenuItem("Rotating");	//סיבוב
        transRotating.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked = 7;
			}
		});
        JMenuItem transMirroring = new JMenuItem("Mirroring");	//שיקוף
        //transMove.setMnemonic(KeyEvent.VK_F1);
        transMirroring.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked = 8;
			}
		});
        JMenuItem transCut = new JMenuItem("Cut");				//גזירה
        //transMove.setMnemonic(KeyEvent.VK_F1);
        transCut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemChecked = 9;
			}
		});
        transformsMenu.add(transShifting);
        transformsMenu.add(transScaling);
        transformsMenu.add(transRotating);
        transformsMenu.add(transMirroring);
        transformsMenu.add(transCut);
        
        


//************************************************
        
        // Help
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem instructions = new JMenuItem("Instructions");
        instructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
        helpMenu.add(instructions);
        helpMenu.add(about);
        
//*********************************************
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(objectsMenu);
        menuBar.add(colorMenu);
        menuBar.add(transformsMenu);
        menuBar.add(helpMenu);

        pane.setLayout(new BorderLayout());
        frame.add(menuBar, BorderLayout.NORTH);
/**End menu's*******************************************************/
        
        // visibility 
        frame.setVisible(true);
          
        // Mouse Listeners 
        frame.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println("mouseWheelMoved");
				
				switch (itemChecked){
				case 6:
					if (e.getWheelRotation()>0 ){
						System.out.println("mouseWheelMoved UP");	//zoom-out
						if (zoom>1){	//zoom=1 is minimal
							zoom--;
							System.out.println("zoom= "+zoom);
							pane.fillCanvas(Color.white);
							for (Shape s : shapeList){
								for (int i=0; i<s.getPoints().size(); i++){
									s.getPoints().get(i).x = s.getPoints().get(i).x/(zoom/100);
									s.getPoints().get(i).y = s.getPoints().get(i).y/(zoom/100);
								}
								s.draw();
							}
						}
					}else{
						System.out.println("mouseWheelMoved DOWN");	//zoom-in
						zoom++;
						System.out.println("zoom= "+zoom);
						pane.fillCanvas(Color.white);
		   				for (Shape s : shapeList){
		   					for (int i=0; i<s.getPoints().size(); i++){
								s.getPoints().get(i).x = s.getPoints().get(i).x*(zoom/100);
								s.getPoints().get(i).y = s.getPoints().get(i).y*(zoom/100);
							}
							s.draw();
		   				}
		   				//System.out.println( shapeList.toString() );
					}
					break;
				}
			}
		});
        frame.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Released	(x="+(e.getX()-8)+", y="+(e.getY()-53)+")");
				pointRelease= new Point(e.getPoint().x-8, e.getPoint().y-53 );

				switch (itemChecked){
				// Draw a line
				case 1: 
					List<Point> linePoints=new ArrayList<>();
					linePoints.add(new Point((int)pointPressed.getX(), (int)pointPressed.getY()));	//start

					if (WIDTH - pointRelease.getX() >=0 && HEIGHT - pointRelease.getY() >=0 ){
  						linePoints.add(new Point((int)pointRelease.getX(),(int)pointRelease.getY()));	//end
  						Line line = new Line(color, linePoints);  
  						line.draw();
  						shapeList.add(line);	//history
					}
  					else{ 
  						linePoints.add(new Point((int)lastDrag_x,(int)lastDrag_y));	//end	
  						Line line = new Line(color, linePoints);  
  						line.draw();
  						shapeList.add(line);	//history
  					}
  					break;
				//Draw a circle
				case 2:
					List<Point> circlePoints=new ArrayList<>();
					circlePoints.add(new Point(pointRelease.x, pointRelease.y));
					circlePoints.add(new Point(pointPressed.x, pointPressed.y));
					
					Circle circle = new Circle(color, circlePoints);  
					circle.draw();
					shapeList.add(circle);	//history
					break;
				//Draw a polygon
				case 3:
					List<Point> polygonPoints=new ArrayList<>();					
					polygonPoints.add(new Point(pointPressed.x,pointPressed.y));
					polygonPoints.add(new Point(pointRelease.x,pointRelease.y));
					
					Poligon poligon = new Poligon(color, polygonPoints, poligon_vertex);  
					poligon.draw();
					shapeList.add(poligon);	//history
					break;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Pressed		(x="+(e.getX()-8)+", y="+(e.getY()-53)+")");
				pointPressed= new Point(e.getPoint().x-8, e.getPoint().y-53 );
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Exited		(x="+(e.getX()-8)+", y="+(e.getY()-53)+")");
				//MouseExitFromWindow=true;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Entered		(x="+(e.getX()-8)+", y="+(e.getY()-53)+")");
				//MouseExitFromWindow=false;
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked		(x="+(e.getX()-8)+", y="+(e.getY()-53)+")");
				
				if ((itemChecked==4) && (bezierPoints.size()<=4)){ //shape 4 = bezier curve
					pane.putSuperPixel(e.getX()-8,e.getY()-53,color);	//put big pixel + Fix
					bezierPoints.add(new Point(e.getX()-8,e.getY()-53));	//save point to list + Fix
					
					if(bezierPoints.size()==4 ) {
						Bezier bezier = new Bezier(color, bezierPoints);  
						bezier.draw();
						shapeList.add(bezier);	//history

						bezierPoints.clear();	//init
						
					}
				}	
				
			}
		});
        
        // mouse motion 
        frame.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("Moved		(x="+(e.getX()-8)+", y="+(e.getY()-53)+")");
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("Dragged		(x="+(e.getX()-8)+", y="+(e.getY()-53)+")");
				lastDrag_x= (int) e.getPoint().getX()-8;		//Fix
				lastDrag_y= (int) e.getPoint().getY()-53;		//Fix
				
				//shift transformation
				switch (itemChecked) {
				case 5:
					int dragDx = lastDrag_x - pointPressed.x;
					int dragDy = lastDrag_y - pointPressed.y;
					pointPressed.x = lastDrag_x;
					pointPressed.y = lastDrag_y;
					//first ,clear the canvas 
					pane.fillCanvas(Color.white);
					for (int i=0; i<shapeList.size(); i++){
						//Change current shape cordinations
						for ( int j=0; j < shapeList.get(i).getPoints().size(); j++ ){
							shapeList.get(i).getPoints().get(j).x += dragDx;
							shapeList.get(i).getPoints().get(j).y += dragDy;
						}
						//Draw current shape
						shapeList.get(i).draw();
						pane.repaint();
					}
					break;
				}
			}
		});
        
    }
	
	public static String promptForFile( Component parent ){
	    JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
	    fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
	    if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION )
	    {
	        return fc.getSelectedFile().getAbsolutePath();
	    }
	    return null;
	}
}