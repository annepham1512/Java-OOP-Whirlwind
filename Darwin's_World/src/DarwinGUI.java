/**<CODE>DarwinGUI</CODE> provides a graphical user interface to simulate a
*a world of creatures in. It provides the user with easy access to information
*as well as a way to step through each turn of the simulation.
*@author Sean Shappell and Tim Wahls
*@author Minor changes by John MacCormick
*@author Dickinson College
*@version 1.1
**/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.*;
import java.io.*;
import javax.swing.text.*;

public class DarwinGUI extends JFrame {
  //public constants for the simulation
  /**The number of turns the simulation will run**/
  public static final int NUMTURNS = 200;
  /**The width of the simulation world**/
  public static final int XSIZE = 21;
  /**The height of the simulation world**/
  public static final int YSIZE = 21;


  //private constants
  // The size of the creature
  private static final int CSIZE = 20;
  // allow some padding when drawing a creature
  private static final int DIM = DarwinGUI.CSIZE/2 - 2; 
  //the width of the world canvas
  private static final int WORLDX = (XSIZE*CSIZE)+(CSIZE*2);
  //the height of the world canvas
  private static final int WORLDY = (YSIZE*CSIZE)+(CSIZE*2);
  //the width of the world panel
  private static final int WPX = WORLDX+CSIZE;
  //the height of the world panel
  private static final int WPY = WORLDY+CSIZE;
  //default creature files
  private static String [] DCRITTERS = {"src/Flytrap.txt","src/Food.txt", 
                                        "src/Hop.txt", "src/Rover.txt"};
  //default number of creatures
  private static int NUMCRITTERS = 10;
  //maximum delay for "continue" option
  private static int MAXDELAY = 1000;
  //min delay for "continue" option
  private static int MINDELAY = 0;
  //default delay for "continue" option
  private static int DEFAULTDELAY = 100;
  
  //swing components
  private JTable turnData;
  private JPanel turnPanel;
  private JPanel endPanel;
  private JScrollPane endSP;
  private JTextPane endTextArea;
  private JButton simulate;
  private JButton addCreatures;
  private JButton addDefCreatures;
  private JButton finishRun;
  private JButton start;
  private JButton stop;
  private Canvas worldCanvas;
  private JPanel worldPanel;
  private javax.swing.Timer timer = null;
  private JSlider delaySlider;
  
  //button click state
  
  //list of all the creatures and objects in the world
  private ArrayList<Creature> critters;
  private ArrayList<Creature> newCritters;      // created via infection
  private ArrayList<Creature> removedCritters;  // replaced by infection
  private ArrayList<Wall> walls;
  
  //the turn in the simulation
  private int turn;
  // the total number of creatures (living and dead)
  private int numCreatures;
  // the delay for animation
  private int delay;
  
  /**Creates a new DarwinGUI**/
  public DarwinGUI(boolean visible) {
    initComponents();
    critters = new ArrayList<Creature>();
    newCritters = new ArrayList<Creature>();
    removedCritters = new ArrayList<Creature>();
    walls = new ArrayList<Wall>();
    start();
    delay = DEFAULTDELAY;
    if (visible) {
        display();
    }
  }
  
  /** Creates a new DarwinGUI **/
  public DarwinGUI() {
      this(true);
  }
  
  private void initComponents() {
    //layout manager
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    
    //initialize all swing components
    worldPanel = new JPanel();
    turnPanel = new JPanel();
    turnData = new JTable();
    endPanel = new JPanel();
    endSP = new JScrollPane();
    endTextArea = new JTextPane();
    simulate = new JButton();
    addCreatures = new JButton();
    addDefCreatures = new JButton();
    finishRun = new JButton();
    start = new JButton();
    stop = new JButton();
    delaySlider = new JSlider(JSlider.HORIZONTAL, MINDELAY, MAXDELAY, 
                              MAXDELAY - DEFAULTDELAY);
    worldCanvas = new DarwinWorld(this);
    
    //set they layout for the main window
    getContentPane().setLayout(new GridBagLayout());
    
    //set the properties of the main window
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Darwin Simulator");
    setResizable(false);
    
    //add a listener to the main window to listen for quit clicks
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        exitForm(evt);
      }
    });
    
    //set the properties of the world panel
    worldPanel.setBorder(new CompoundBorder(new TitledBorder("Darwin's World"), 
    new EmptyBorder(new Insets(1, 
                               1, 
                               1, 
                               1))));
    worldPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    worldPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
    worldPanel.setFocusable(false);
    worldPanel.setMaximumSize(new Dimension(WPX, WPY));
    worldPanel.setMinimumSize(new Dimension(WPX, WPY));
    
    //set they layour of the world panel
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 9;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new Insets(10, 10, 10, 5);
    
    //add the world panel to the main window
    getContentPane().add(worldPanel, gridBagConstraints);
    
    //set the world canvas properties
    worldCanvas.setSize(WORLDX,WORLDY);
    
    //add the world canvas to the world panel
    worldPanel.add(worldCanvas);
    
    //set the turn panel properties
    turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.Y_AXIS));
    turnPanel.setBorder(new CompoundBorder(new TitledBorder("Turn"), 
    new EmptyBorder(new Insets(3, 
                               3, 
                               3, 
                               3))));
    turnPanel.setAlignmentY(Component.LEFT_ALIGNMENT);
    turnPanel.setMaximumSize(new Dimension(200, 200));
    turnPanel.setMinimumSize(new Dimension(200, 75));
    turnPanel.setName("Turn");
    turnPanel.setPreferredSize(new Dimension(200, 75));
    
    //set the turn data table properties
    turnData.setBackground(new Color(204, 204, 204));
    turnData.setModel(new DefaultTableModel(
    new Object [][] {
      {"Turn", null},
      {"Creatures", null},
//      {"Living Creatures", null}
    },
    new String [] {
      "", ""
    }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.Integer.class
      };
      boolean[] canEdit = new boolean [] {
        false, false
      };
      
      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }
      
      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    turnData.setFocusable(false);
    turnData.setMinimumSize(new Dimension(150, 48));
    turnData.setRequestFocusEnabled(false);
    turnData.setRowSelectionAllowed(false);
    turnData.setShowHorizontalLines(false);
    turnData.setShowVerticalLines(false);
    
    //add the turn data table to the turn panel
    turnPanel.add(turnData);
  
    //set the layout of the turn panel
    gridBagConstraints = new GridBagConstraints();
/*
    gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
    gridBagConstraints.gridheight = GridBagConstraints.RELATIVE;
*/
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.NORTH;
    gridBagConstraints.insets = new Insets(10, 5, 0, 10);

    //add the turn panel to the main window
    getContentPane().add(turnPanel, gridBagConstraints);
   
    //set the properties of the end information panel
    endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.Y_AXIS));
    endPanel.setBorder(new CompoundBorder(new TitledBorder("End World State"), 
    new EmptyBorder(new Insets(5, 
                               5, 
                               5, 
                               5))));
    endPanel.setFocusable(false);
    endPanel.setMinimumSize(new Dimension(238, 195));
    
    //set the properties of the end information scroll panel
    endSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    endSP.setAlignmentX(Component.LEFT_ALIGNMENT);
    endSP.setMinimumSize(new Dimension(218, 161));
    
    //set the properties of the end information text area
    endTextArea.setEditable(false);
    endTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
    endTextArea.setAlignmentY(Component.LEFT_ALIGNMENT);
    endTextArea.setMinimumSize(new Dimension(200, 158));
    endTextArea.setPreferredSize(new Dimension(200, 158));
    
    //add the end information text area to the end information scroll panel
    endSP.setViewportView(endTextArea);
    
    //add the end information scroll panel to the end information panel
    endPanel.add(endSP);
    
    //set the layout for the end information panel
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 7;
/*
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.gridheight = GridBagConstraints.REMAINDER;
*/
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new Insets(10, 10, 10, 5);
    
    //add the end information panel to the main window
    getContentPane().add(endPanel, gridBagConstraints);
   
    //set the properties for the simulate button
    simulate.setText("Next Turn");
    simulate.setAlignmentY(Component.LEFT_ALIGNMENT);
    simulate.setHorizontalTextPosition(SwingConstants.CENTER);
    
 
    //add an action listener to listen for a button click
    simulate.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        buttonClick(evt);
      }
    });
    
    //set the layout for the simulate button
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 10;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(0, 200, 10, 200);
    
    //add the simulate button to the main window
    getContentPane().add(simulate, gridBagConstraints);
   
    //set the properties for the addCreatures button
    addCreatures.setText("Add Creatures");
    addCreatures.setAlignmentY(Component.LEFT_ALIGNMENT);
    addCreatures.setHorizontalTextPosition(SwingConstants.CENTER);
 
    //add an action listener to listen for a button click
    addCreatures.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        addCreatures(evt);
      }
    });
    
    //set the layout for the addCreatures button
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.NORTH;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(10, 50, 0, 50);
    
    //add the addCreatures button to the main window
    getContentPane().add(addCreatures, gridBagConstraints);
   
    //set the properties for the addDefCreatures button
    addDefCreatures.setText("Default Creatures");
    addDefCreatures.setAlignmentY(Component.LEFT_ALIGNMENT);
    addDefCreatures.setHorizontalTextPosition(SwingConstants.CENTER);
 
    //add an action listener to listen for a button click
    addDefCreatures.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        addDefCreatures(evt);
      }
    });
    
    //set the layout for the addDefCreatures button
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = GridBagConstraints.NORTH;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(10, 50, 0, 50);
    
    //add the addDefCreatures button to the main window
    getContentPane().add(addDefCreatures, gridBagConstraints);
   
    //set the properties for the start button
    start.setText("(Re)Start");
    start.setAlignmentY(Component.LEFT_ALIGNMENT);
    start.setHorizontalTextPosition(SwingConstants.CENTER);
 
    //add an action listener to listen for a button click
    start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        start();
      }
    });
    
    //set the layout for the start button
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = GridBagConstraints.NORTH;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(10, 50, 0, 50);
    
    //add the start button to the main window
    getContentPane().add(start, gridBagConstraints);
   
    //set the properties for the finishRun button
    finishRun.setText("Continue");
    finishRun.setAlignmentY(Component.LEFT_ALIGNMENT);
    finishRun.setHorizontalTextPosition(SwingConstants.CENTER);
    
 
    //add an action listener to listen for a button click
    finishRun.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        finishRun(evt);
      }
    });
    
    //set the layout for the continue button
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = GridBagConstraints.NORTH;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(10, 50, 0, 50);
    
    //add the finishRun button to the main window
    getContentPane().add(finishRun, gridBagConstraints);
   
    //set the properties for the stop button
    stop.setText("Pause");
    stop.setAlignmentY(Component.LEFT_ALIGNMENT);
    stop.setHorizontalTextPosition(SwingConstants.CENTER);
 
    //add an action listener to listen for a button click
    stop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (timer != null) timer.stop();
        stop.setEnabled(false);
      }
    });
    
    //set the layout for the stop button
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = GridBagConstraints.NORTH;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(10, 50, 0, 50);
    
    //add the stop button to the main window
    getContentPane().add(stop, gridBagConstraints);

    // add a change listener to the delay slider

    delaySlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            delay = MAXDELAY - (int)source.getValue();
            if (timer != null) timer.setDelay(delay);
        }
      }
    });
    Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
    labelTable.put(new Integer(MINDELAY), new JLabel("Slow"));
    labelTable.put(new Integer(MAXDELAY), new JLabel("Fast"));
    delaySlider.setLabelTable(labelTable);
    delaySlider.setPaintLabels(true);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.insets = new Insets(10, 20, 0, 20);
    getContentPane().add(delaySlider, gridBagConstraints);
    
    //pack the main window
    pack();
  }

  // update turn data
  private void updateTurn() {
      turnData.setValueAt(String.valueOf(turn),0,1);
      turnData.setValueAt(String.valueOf(numCreatures),1,1);
  }

  // add the walls to the world
  private void addWalls() {
      for (int i = 0; i <= XSIZE + 1; i++) {
         walls.add(new Wall(i, 0));
         walls.add(new Wall(i, YSIZE + 1));
      }
      for (int i = 1; i <= YSIZE; i++) {
         walls.add(new Wall(0, i));
         walls.add(new Wall(XSIZE + 1, i));
      }

      for (int i = 2; i <= XSIZE; i += 3) {
          walls.add(new Wall(i,i));
          walls.add(new Wall(XSIZE - i + 1, i));
      }
  }

  // (re)start the simulation
  private void start() {
    turn = 0;
    numCreatures = 0;
    critters.clear();
    addWalls();
    worldCanvas.repaint();
    updateTurn();
    simulate.setEnabled(true);
    finishRun.setEnabled(true);
    addCreatures.setEnabled(true);
    addDefCreatures.setEnabled(true);
    stop.setEnabled(false);
    if (timer != null) timer.stop();
  }
  
  //determines the actions to take for each click of the simulate button
  //depending on its current state
  private void buttonClick(ActionEvent evt) {
    //if the simulation is running and the number of turns is less than the max
    if (turn <= NUMTURNS) {
      //allow creatures to move etc
      execute();
      
      //update the values of turn data table
      updateTurn();
      
      //update the graphical representation of the world
      worldCanvas.repaint();

      //add one to the number of turns simulated
      turn++;
      
    } else {
      //if the simulation has completed
        simulate.setEnabled(false);
        finishRun.setEnabled(false);
        addCreatures.setEnabled(false);
        addDefCreatures.setEnabled(false);
        stop.setEnabled(false);
        printFinalStats();
        timer.stop();
    }
  }
  
  /** print number of each kind of creature remaining */
  private void printFinalStats() {
    HashMap<String, Integer> res = new HashMap<String, Integer>();
    
    Integer num;
    String species;
    for (Creature crit : critters) {
      species = crit.getSpecies();
      num = res.get(species);
      if (num == null) {
          res.put(species, new Integer(1));
      } else {
          res.put(species, new Integer(1 + num.intValue()));
      }
    }
    Document doc = endTextArea.getDocument();
    try {
      doc.insertString(doc.getLength(), "Final results:\n", null);
      for (String spec : res.keySet()) {
        doc.insertString(doc.getLength(), spec + ": " + res.get(spec) + "\n", null);
      }
      doc.insertString(doc.getLength(), "\n", null);
    } catch (BadLocationException e) {
      System.out.println(e);
    }
  }      
  
  /** add one creature, if it is not on top of a wall or another
      creature 
      @return true if creature is added, false o.w.*/
  public boolean addOneCreature(Creature c) {
    if (isEmpty(c.getXPos(), c.getYPos())) {
      critters.add(c);
      numCreatures++;
      return true;
    }
    return false;
  }
  
    /** return a random integer between low and hi (inclusive) */
    private int random(int low, int high) {
        return (int) (Math.random() * (high - low + 1) + low);
    }
  
  /** create a new Creature using data from file */
  private Creature fromFile(String fileName) throws FileNotFoundException {
      try {
          FileReader file = new FileReader(fileName);  
          BufferedReader input = new BufferedReader(file);
          String species = input.readLine();
          String color = input.readLine();
          String stringInstr = input.readLine();
          StringTokenizer strtok;
          Instruction instr;
          String opCode;
          String temp;
          int targ; // branch target
          Program program = new Program();
          while (stringInstr != null && !stringInstr.equals("")) {
              strtok = new StringTokenizer(stringInstr);
              opCode = strtok.nextToken();
              if (strtok.hasMoreTokens()) {
                  temp = strtok.nextToken();
                  try {
                      targ = Integer.parseInt(temp);
                  } catch (NumberFormatException e) {
                      targ = -1;
                  }
                  instr = new Instruction(opCode, targ);
              } else {
                  instr = new Instruction(opCode);
              }
              program.addInstruction(instr);
              stringInstr = input.readLine();
          }
          file.close();
          return new Creature(species, random(1, DarwinGUI.XSIZE), random(1, DarwinGUI.YSIZE),
                              random(0, 3), color, program);      
      } catch (IOException ioe) {
          System.out.println(ioe);
          if (ioe instanceof FileNotFoundException) {
              throw (FileNotFoundException) ioe;
          }
          return null;
      }
    }
    
  /** determines the actions to take for each click of the 
      Add Creatures button */
  private void addCreatures(ActionEvent evt) {
     String file = JOptionPane.showInputDialog("Enter creature file name: ");
     if (file != null) {
       // The file must be in the src directory
       file = "src" + File.separatorChar + file;
       int num = Integer.parseInt(JOptionPane.showInputDialog(
                    "How many should be added? "));
        try {
          for (int i = 0; i < num; i++) {
            addOneCreature(fromFile(file));
          }
        } catch (FileNotFoundException e) {
          System.out.println(e);
        }
     }
     worldCanvas.repaint();
     updateTurn();
  }
  
  /** add the set of default creatures */
  private void addDefCreatures(ActionEvent evt) {
     for (int i = 0; i < DCRITTERS.length; i++) {
       try {
           for (int j = 0; j < NUMCRITTERS; j++) {
             addOneCreature(fromFile(DCRITTERS[i]));
           }
        } catch (FileNotFoundException e) {
          System.out.println(e);
        }
     }
     worldCanvas.repaint();
     updateTurn();
  }
  
  /** run the simulation to completion */
  private void finishRun(ActionEvent evt) {
    timer = new javax.swing.Timer(delay, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          if (turn <= NUMTURNS + 1) {
            buttonClick(evt);
          } else {
            timer.stop();
          }
        }
      });
    stop.setEnabled(true);
    timer.start();
  }
  
  private int toInt(String dir) {
      if (dir.equals("up")) {
          return 0;
      } else if (dir.equals("right")) {
          return 1;
      } else if (dir.equals("down")) {
          return 2;
      } else {
          return 3;
      }
  }
  
  private void infect(Creature crit, Creature infected) {
      Creature newCrit = new Creature(crit.getSpecies(), infected.getXPos(), infected.getYPos(),
                                      toInt(infected.getDirection()), crit.getColor(), crit.getProgram());
      newCritters.add(newCrit);
      removedCritters.add(infected);
  }
    
  /** Execute the program (one step) for the specified creature */
  private void execute(Creature crit) {
        String opCode; // instruction opcode
        int targ; // instruction branch target
        int xFront;
        int yFront; // (x, y) position in front of creature
        Program program = crit.getProgram();
        int counter = crit.getCounter();
        boolean done = false; // is this creature done executing its program
                              // for the current turn?
        while (!done) {
            opCode = program.getInstruction(counter).getOpCode();
            targ = program.getInstruction(counter).getTarget();
            xFront = crit.getXFront();
            yFront = crit.getYFront();
            if (opCode.equals("hop")) {
                if (isEmpty(xFront, yFront)) {
                    crit.hop();
                }
                counter++;
                done = true;
            } else if (opCode.equals("infect")) {
                if (isCreature(xFront, yFront)) {
                    Creature infected = getCreature(xFront, yFront);
                    if (!crit.getSpecies().equals(infected.getSpecies())) {
                        infect(crit, infected);
                    }
                }
                counter++;
                done = true;
            } else if (opCode.equals("left")) {
                crit.turnLeft();
                counter++;
                done = true;
            } else if (opCode.equals("right")) {
                crit.turnRight();
                counter++;
                done = true;
            } else if (opCode.equals("ifenemy")) {
                counter++;
                if (isCreature(xFront, yFront)) {
                    Creature enemy = getCreature(xFront, yFront);
                    if (!crit.getSpecies().equals(enemy.getSpecies())) {
                        counter = targ;
                    } 
                } 
            } else if (opCode.equals("ifwall")) {
                counter++;
                if (isWall(xFront, yFront)) {
                    counter = targ;
                } 
            } else if (opCode.equals("ifempty")) {
                counter++;
                if (isEmpty(xFront, yFront)) {
                    counter = targ;
                }
            } else if (opCode.equals("ifsame")) {
                counter++;
                if (isCreature(xFront, yFront)) {
                    Creature same = getCreature(xFront, yFront);
                    if (crit.getSpecies().equals(same.getSpecies())) {
                        counter = targ;
                    }
                }
            } else if (opCode.equals("ifrandom")) {
                counter++;
                if (random(0, 1) == 0) {
                    counter = targ;
                }
            } else if (opCode.equals("go")) {
                counter = targ;
            } else {
                System.out.println("Error: invalid instruction!");
                counter = 0;
            }
        }
        crit.setCounter(counter);
    }
  
  /**
  *Iterates through all of the creatures in the simulation and 
  * executes the program of each one
  **/
  public void execute() {
    for (Creature crit : critters) {
        if (!removedCritters.contains(crit) && !newCritters.contains(crit)) {
            execute(crit);
        }
    }
    critters.removeAll(removedCritters);
    critters.addAll(newCritters);
    newCritters.clear();
    removedCritters.clear();
  }
  
  /**
  *Return the <code>ArrayList</code> containing the creatures in the
  *simulation.
  *@return
  *An <code>ArrayList</code> containing the creatures in the simulation.     
  **/   
  public ArrayList<Creature> getCreatures() {
    return critters;
  }
 
  /**
  *Return the <code>ArrayList</code> containing the walls in the simulation.
  *@return An <code>ArrayList</code> containing the walls in the simulation.     
  **/   
  public ArrayList<Wall> getWalls() {
    return walls;
  }
  
  /** get Creature at specified square<br>
      returns null if square is empty or invalid */
  public Creature getCreature(int x, int y) {
    for (Creature crit : critters) {
      if (crit.getXPos() == x && crit.getYPos() == y) {
        return crit;
      }
    }
    return null;
  }
  
  /** get Wall at specified square<br>
   * returns null if square is empty or invalid
   */
  public Wall getWall(int x, int y) {
    for (Wall wall : walls) {
      if (wall.getXPos() == x && wall.getYPos() == y) {
        return wall;
      }
    }
    return null;
  }
    
  /** @return true if square contains a creature */
  public boolean isCreature(int x, int y) {
      Creature crit = getCreature(x, y);
      return crit != null  && !removedCritters.contains(crit);
  }
    
  /** @return true if square contains a Wall */
  public boolean isWall(int x, int y) {
     return getWall(x, y) != null;
  }
    
  /** @return true if square is empty */
  public boolean isEmpty(int x, int y) {
     return !isCreature(x, y) && !isWall(x, y);
  }
  
  //quit the application
  private void exitForm(WindowEvent evt) {
    System.exit(0);
  }
  
  public void display() {
    //set the look and feel
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } 
    catch (Exception e) { 
      System.out.println(e);
    }
    setVisible(true);
  }   
  
  public static void main(String args[]) {
 
    //create and show the GUI  
    new DarwinGUI();
  }
  
  //DarwinWorld is an extension of the canvas class the provides a graphical
  //representation of the creatures' world.
  private class DarwinWorld extends Canvas {
    //the GUI with which the world is associated
    private DarwinGUI tg;
    
    //create a new creature world
    public DarwinWorld(DarwinGUI initTg) {
      tg = initTg;
      
      setBackground(Color.WHITE);
      setForeground(Color.BLACK);
    }
  
    /** return polygon at correct location and orientation for creature */
    private Polygon getPoly(Creature t) {
      int x = (t.getXPos()*CSIZE)+(DarwinGUI.CSIZE/2);
      int y = (t.getYPos()*CSIZE)+(DarwinGUI.CSIZE/2);
 
      Polygon p;
      if (t.getDirection().equals("up")) {
         int [] xpol = {x - DIM, x - DIM, x, x + DIM, x + DIM};
         int [] ypol = {y + DIM, y, y - DIM, y, y + DIM};
         p = new Polygon(xpol, ypol, 5);
      } else if (t.getDirection().equals("right")) {
         int [] xpol = {x - DIM, x - DIM, x, x + DIM, x};
         int [] ypol = {y + DIM, y - DIM, y - DIM, y, y + DIM};
         p = new Polygon(xpol, ypol, 5);
      } else if (t.getDirection().equals("down")) {
         int [] xpol = {x - DIM, x - DIM, x + DIM, x + DIM, x};
         int [] ypol = {y, y - DIM, y - DIM, y, y + DIM};
         p = new Polygon(xpol, ypol, 5);
      } else { // left
         int [] xpol = {x - DIM, x, x + DIM, x + DIM, x};
         int [] ypol = {y, y - DIM, y - DIM, y + DIM, y + DIM};
         p = new Polygon(xpol, ypol, 5);
      }
      return p;
    }
          
    
    //paints the creatures in the world based on the current world state
    public void paint(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
 
       Line2D.Double ln;

      // draw vertical grid lines
      for (int i = 2 * DarwinGUI.CSIZE; i <= DarwinGUI.XSIZE * CSIZE;
           i += CSIZE) {
           ln = new Line2D.Double(i, CSIZE, i, DarwinGUI.YSIZE * (CSIZE + 1));
           g2.draw(ln);
      }

      // draw horizontal grid lines
      for (int i = 2 * DarwinGUI.CSIZE; i <= DarwinGUI.YSIZE * CSIZE;
           i += CSIZE) {
           ln = new Line2D.Double(CSIZE, i, DarwinGUI.XSIZE * (CSIZE + 1), i);
           g2.draw(ln);
      }
        
      //get the ArrayList of all the creatures in the world
      ArrayList<Creature> creatures = tg.getCreatures();
      ArrayList<Wall> walls = tg.getWalls();
      
      //a temporary creature, Object and Wall
      Object ob;
        
      //creature coordinates
      int x;
      int y;
        
      //for every creature in the list
      for (Wall wall : walls) {
        x = wall.getXPos() * CSIZE;
        y = wall.getYPos() * CSIZE;
        g2.setPaint(Color.BLACK);
        g2.fill(new Rectangle2D.Double(x, y, DarwinGUI.CSIZE, DarwinGUI.CSIZE));
      }
      
      for (Creature crit : creatures) {
        //set the creature's color and stroke
        g2.setPaint(translateColor(crit.getColor()));
        g2.fill(getPoly(crit)); 
        
        //set the text color                             
        g2.setPaint(Color.BLACK);
        
        //draw the creature's species 
        x = (crit.getXPos()*CSIZE)+(DarwinGUI.CSIZE/2);
        y = (crit.getYPos()*CSIZE)+(DarwinGUI.CSIZE/2);
        String species =crit.getSpecies();
        if (species!=null && species.length()>0) {
            g2.drawString(String.valueOf(species.substring(0, 1)),
                      x - DIM/2, y + DIM/2);
        }
      }
  }
      
    //Returns the Color object associated with the passed color. The Color 
    //object will have an alpha value of 100.
    private Color translateColor(String color) {
      if(color.equals("black")) {
        return Color.BLACK;
      }
      else if(color.equals("blue")) {
        return new Color(Color.BLUE.getRed(),   
        Color.BLUE.getGreen(),
        Color.BLUE.getBlue(),
        100);
      }
      else if(color.equals("cyan")) {
        return new Color(Color.CYAN.getRed(),   
        Color.CYAN.getGreen(),
        Color.CYAN.getBlue(),
        100);
      }
      else if(color.equals("gray")) {
        return new Color(Color.GRAY.getRed(),   
        Color.GRAY.getGreen(),
        Color.GRAY.getBlue(),
        100);
      }
      else if(color.equals("green")) {
        return new Color(Color.GREEN.getRed(),   
        Color.GREEN.getGreen(),
        Color.GREEN.getBlue(),
        100);
      }
      else if(color.equals("magenta")) {
        return new Color(Color.MAGENTA.getRed(),   
        Color.MAGENTA.getGreen(),
        Color.MAGENTA.getBlue(),
        100);
      }
      else if(color.equals("orange")) {
        return new Color(Color.ORANGE.getRed(),   
        Color.ORANGE.getGreen(),
        Color.ORANGE.getBlue(),
        100);
      }
      else if(color.equals("pink")) {
        return new Color(Color.PINK.getRed(),   
        Color.PINK.getGreen(),
        Color.PINK.getBlue(),
        100);
      }
      else if(color.equals("red")) {
        return new Color(Color.RED.getRed(),   
        Color.RED.getGreen(),
        Color.RED.getBlue(),
        100);
      }
      else if(color.equals("yellow")) {
        return new Color(Color.YELLOW.getRed(),   
        Color.YELLOW.getGreen(),
        Color.YELLOW.getBlue(),
        100);
      }
      else {
        return new Color(Color.WHITE.getRed(),   
        Color.WHITE.getGreen(),
        Color.WHITE.getBlue(),
        100);
      }
    }
  }
}
