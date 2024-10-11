package ownPest;

/**<CODE>OwnVirtualPestGUI</CODE> provides a graphical user interface for a
* virtual pest.
*@author Tim Wahls
*@author Dickinson College
*@version 1.0
**/

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.text.*;
import javax.swing.border.*;

public class OwnVirtualPestGUI extends JFrame {

  // the pest that inhabits this GUI
  private OwnVirtualPest thePest;
  // show the state or action and sound?
  private boolean showState;
  
  //swing components
  private JButton [] action;
  private JPanel statePanel;
  private JScrollPane stateSP;
  private JTextPane stateTextArea;
  private Document stateDoc;
  private JLabel pestImage;
  private javax.swing.Timer timer = null;
  private JPanel actionPanel;
  private JLabel soundLabel;
  private JTextArea soundArea;
  private JLabel actionLabel;
  private JTextArea actionArea;

  // how often to try creating random action
  private static int DELAY = 1000;
  // proportion of the time to ignore timeout
  private static double IGNORE = 0.8;
  
  /**Creates a new OwnVirtualPestGUI
    * @param v the virtual pest to inhabit the GUI
    * @param showState whether or not to show the state of the pest **/
 
  public OwnVirtualPestGUI(OwnVirtualPest v, boolean showState) {
    thePest = v;
    this.showState = showState;
    initComponents();
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } 
    catch (Exception e) { 
      System.out.println(e);
    }
    setVisible(true);
  }
  
  /** Creates a new OwnVirtualPestGUI inhabited by Fred Fish, the default
   * OwnVirtualPest.  Fred's state is explicitly shown.
   */
  public OwnVirtualPestGUI(OwnVirtualPest v) {
      this(v, true);
  }
  
  private void scrollDown() {
    int h = 20;
    Rectangle r = stateTextArea.getVisibleRect();
    r.y += h;
    stateTextArea.scrollRectToVisible(r);
  }
  
  private void initComponents() {
    
    //initialize all swing components
    statePanel = new JPanel();
    stateTextArea = new JTextPane();
    stateSP = new JScrollPane();
    action = new JButton [thePest.getNumActions()];
    
    //set they layout for the main window
    getContentPane().setLayout(new GridBagLayout());
    
    //set the properties of the main window
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle(thePest.getName() + " the Virtual Pest");
    setResizable(false);
    
    //add a listener to the main window to listen for quit clicks
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        exitForm(evt);
      }
    });
   
    //set the properties of the state information panel
    statePanel.setLayout(new BoxLayout(statePanel, BoxLayout.Y_AXIS));
    statePanel.setBorder(new CompoundBorder(new 
      TitledBorder(thePest.getName() + "'s Current State"), 
    new EmptyBorder(new Insets(5, 
                               5, 
                               5, 
                               5))));
    statePanel.setFocusable(false);
    statePanel.setMinimumSize(new Dimension(238, 195));
    
    //set the properties of the end information scroll panel
    stateSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    stateSP.setAlignmentX(Component.LEFT_ALIGNMENT);
    stateSP.setMinimumSize(new Dimension(218, 161));
    
    //set the properties of the end information text area
    stateTextArea.setEditable(false);
    stateTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
    stateTextArea.setAlignmentY(Component.LEFT_ALIGNMENT);
    stateTextArea.setMinimumSize(new Dimension(200, 158));
    stateTextArea.setPreferredSize(new Dimension(200, 158));
    
    //add the end information text area to the end information scroll panel
    stateSP.setViewportView(stateTextArea);
    
    statePanel.setVisible(showState);
    
    //add the end information scroll panel to the end information panel
    statePanel.add(stateSP);
    
    //set they layout of the state panel
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = thePest.getNumActions() + 2;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new Insets(10, 10, 10, 5);
    
    //add the state information panel to the main window
    getContentPane().add(statePanel, gridBagConstraints);

    // write initial state
    stateDoc = stateTextArea.getDocument();
    try {
      stateDoc.insertString(stateDoc.getLength(), 
                            thePest.getState() + "\n", null);
    } catch (BadLocationException e) {
      System.out.println(e);
    }
         
    // create and add the pet sound and action area
    actionPanel = new JPanel();
    actionPanel.setLayout(new GridBagLayout());
    gridBagConstraints.gridy = thePest.getNumActions() + 1; 
    actionPanel.setMinimumSize(new Dimension(200, 60));
    actionPanel.setPreferredSize(new Dimension(200, 60));

    getContentPane().add(actionPanel, gridBagConstraints);
    
    soundLabel = new JLabel();
    soundLabel.setText("Pest sound:");
    soundArea = new JTextArea();
    actionLabel = new JLabel();
    actionLabel.setText("Pest action:");
    actionArea = new JTextArea();
        
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(10, 10, 0, 10);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    actionPanel.add(soundLabel, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    actionPanel.add(soundArea, gridBagConstraints);
    
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    actionPanel.add(actionLabel, gridBagConstraints);
    
    gridBagConstraints.gridx = 1;
    actionPanel.add(actionArea, gridBagConstraints);
        
    soundArea.setText(thePest.getPestSound());
    actionArea.setText(thePest.getPestAction());
    

    //set the layout for the action buttons
    gridBagConstraints.gridx = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    //set the properties for the action buttons
    for (int j = 0; j < thePest.getNumActions(); j++) {
      final int i = j;
      action[i] = new JButton();
      action[i].setText(thePest.getAction(i + 1));
      action[i].setAlignmentY(Component.LEFT_ALIGNMENT);
      action[i].setHorizontalTextPosition(SwingConstants.CENTER);
 
      //add an action listener to listen for a button click
      action[i].addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        thePest.doAction(i + 1);
        pestImage.setIcon(new ImageIcon(thePest.getFile()));
        try {
          stateDoc.insertString(stateDoc.getLength(), 
                                thePest.getState() + "\n", null);
          scrollDown();
        } catch (BadLocationException e) {
          System.out.println(e);
        }
        soundArea.setText(thePest.getPestSound());
        actionArea.setText(thePest.getPestAction());
      }});
      gridBagConstraints.gridy = i;
      //add each button to the main window
      getContentPane().add(action[i], gridBagConstraints);
    }

    // add the pet image
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
//    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.gridheight = thePest.getNumActions();
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    pestImage = new JLabel(new ImageIcon(thePest.getFile()));
    gridBagConstraints.insets = new Insets(10, 10, 10, 0);
    getContentPane().add(pestImage, gridBagConstraints);

    //pack the main window
    pack();

    // start the timer
    timer = new javax.swing.Timer(DELAY, new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          if (Math.random() > IGNORE) {
            String st = thePest.getState();
            thePest.doAction(0);
            pestImage.setIcon(new ImageIcon(thePest.getFile()));
            if (! thePest.getState().equals(st)) {
              try {
                stateDoc.insertString(stateDoc.getLength(), 
                                      thePest.getState() + "\n", null);
                scrollDown();
              } catch (BadLocationException e) {
                System.out.println(e);
              }
              soundArea.setText(thePest.getPestSound());
              actionArea.setText(thePest.getPestAction());
            } 
          }
        }
      });
    timer.start();
  }
  
  //quit the application
  private void exitForm(WindowEvent evt) {
    System.exit(0);
  }

  public static void main(String args[]) {
 
    //create and show the GUI  
    new OwnVirtualPestGUI(new OwnVirtualPest("Fred", 1), false);
  }
}
