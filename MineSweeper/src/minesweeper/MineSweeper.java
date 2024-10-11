package minesweeper;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MineSweeper {

    private static int level = MineSweeperBoard.BEGINNER_LEVEL;
    private static boolean gameOn = false;

    private static JFrame window;

    private static final ImageIcon EMPTY_ICON = new ImageIcon(MineSweeper.class
            .getResource("icons/empty.jpg"));
    private static final ImageIcon FLAG_ICON = new ImageIcon(MineSweeper.class
            .getResource("icons/flag.jpg"));

    private static MineSweeperBoard theBoard;
    private static MineSweeperPanel thePanel;
    private static MineSweeperMenuHandler menuHandler;

    public static void main(String[] args) {

        window = new JFrame("Minesweeper");

        // Handle closing the window.
        // This is the old way but is is compatible with 
        // old JDK's.
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        makeNewBoard();
        window.setJMenuBar(getMenuBar());
        window.setResizable(false);
        
        window.pack();
        window.setVisible(true);
    }

    public static void startGame() {
        gameOn = true;
    }

    public static void endGame() {
        gameOn = false;
    }

    public static void gameWasWon() {
        endGame();
        revealBoard();
        JOptionPane.showMessageDialog(window, "Way to go, you won!", "Winner!",
                JOptionPane.INFORMATION_MESSAGE);
        menuHandler.resetMenus();
    }

    public static void gameWasLost() {
        endGame();
        revealBoard();
        JOptionPane.showMessageDialog(window, "Too bad, you lose!", "Looser!",
                JOptionPane.INFORMATION_MESSAGE);
        menuHandler.resetMenus();
    }

    public static void startGameOnClick() {
        menuHandler.actLikeStart();
    }

    public static void reStartGameOnClick() {
        menuHandler.actLikeStart();
        makeNewBoard();
    }

    public static boolean isGameOn() {
        return gameOn;
    }

    public static void setLevel(int newLevel) {
        level = newLevel;
    }

    public static int getLevel() {
        return level;
    }

    public static void revealBoard() {
        theBoard.revealBoard();
        thePanel.revealBoard();
    }

    public static void makeNewBoard() {
        theBoard = new MineSweeperBoard(level);

        /*
         * Check to see if the board was created. If not the one-arg constructor
         * has not yet been implemented to create a board using the default
         * no-arg constructor. This allows students to see the GUI without
         * having done anything but the default constructor and a few accessors.
         */
        try {
            theBoard.getRows();
        }
        catch (NullPointerException e) {
            theBoard = new MineSweeperBoard();
        }

        Container thePane = window.getContentPane();

        // Get Rid of old panel if one exists!
        thePane.removeAll();

        thePanel = new MineSweeperPanel(theBoard);
        thePane.add(thePanel);
        window.pack();
        window.repaint();
    }

    private static JMenuBar getMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu levelMenu = new JMenu("Level");

        menuHandler = new MineSweeperMenuHandler(levelMenu, gameMenu);

        JMenuItem startGame = new JMenuItem("Start Game");
        startGame.addActionListener(menuHandler);
        startGame.setBackground(new Color(155, 153, 156));

        JMenuItem restartGame = new JMenuItem("Re-Start Game");
        restartGame.setEnabled(false);
        restartGame.addActionListener(menuHandler);
        restartGame.setBackground(new Color(155, 153, 156));

        JMenuItem revealGame = new JMenuItem("Reveal board");
        revealGame.addActionListener(menuHandler);
        revealGame.setBackground(new Color(155, 153, 156));

        JMenuItem exitGame = new JMenuItem("Exit");
        exitGame.setBackground(new Color(155, 153, 156));
        exitGame.addActionListener(menuHandler);

        gameMenu.add(startGame);
        gameMenu.add(restartGame);
        gameMenu.add(revealGame);
        gameMenu.add(exitGame);

        JMenuItem beginnerLevel = new JMenuItem("Beginner");
        beginnerLevel.addActionListener(menuHandler);
        beginnerLevel.setIcon(FLAG_ICON);
        beginnerLevel.setBackground(new Color(155, 153, 156));

        JMenuItem intermediateLevel = new JMenuItem("Intermediate");
        intermediateLevel.addActionListener(menuHandler);
        intermediateLevel.setIcon(EMPTY_ICON);
        intermediateLevel.setBackground(new Color(155, 153, 156));

        JMenuItem expertLevel = new JMenuItem("Expert");
        expertLevel.addActionListener(menuHandler);
        expertLevel.setIcon(EMPTY_ICON);
        expertLevel.setBackground(new Color(155, 153, 156));

        levelMenu.add(beginnerLevel);
        levelMenu.add(intermediateLevel);
        levelMenu.add(expertLevel);

        menuBar.add(gameMenu);
        menuBar.add(levelMenu);

        return (menuBar);
    }

    /*
     * Handle events on the menus of the main minesweeper window.
     */
    static class MineSweeperMenuHandler implements ActionListener {

        private JMenu levelMenu;
        private JMenu gameMenu;

        public MineSweeperMenuHandler(JMenu lm, JMenu gm) {
            levelMenu = lm;
            gameMenu = gm;
        }

        public void resetMenus() {
            levelMenu.setEnabled(true);
            gameMenu.getItem(0).setEnabled(true);
            gameMenu.getItem(1).setEnabled(false);
            gameMenu.getItem(2).setEnabled(false);
        }

        public void actLikeStart() {
            levelMenu.setEnabled(false);
            gameMenu.getItem(0).setEnabled(false);
            gameMenu.getItem(1).setEnabled(true);
            gameMenu.getItem(2).setEnabled(true);
            MineSweeper.startGame();
            //MineSweeper.makeNewBoard();
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Beginner")) {
                levelMenu.getItem(0).setIcon(FLAG_ICON);
                levelMenu.getItem(1).setIcon(EMPTY_ICON);
                levelMenu.getItem(2).setIcon(EMPTY_ICON);
                MineSweeper.setLevel(MineSweeperBoard.BEGINNER_LEVEL);
                MineSweeper.makeNewBoard();
            }
            else if (e.getActionCommand().equals("Intermediate")) {
                levelMenu.getItem(0).setIcon(EMPTY_ICON);
                levelMenu.getItem(1).setIcon(FLAG_ICON);
                levelMenu.getItem(2).setIcon(EMPTY_ICON);
                MineSweeper.setLevel(MineSweeperBoard.INTERMEDIATE_LEVEL);
                MineSweeper.makeNewBoard();
            }
            else if (e.getActionCommand().equals("Expert")) {
                levelMenu.getItem(0).setIcon(EMPTY_ICON);
                levelMenu.getItem(1).setIcon(EMPTY_ICON);
                levelMenu.getItem(2).setIcon(FLAG_ICON);
                MineSweeper.setLevel(MineSweeperBoard.EXPERT_LEVEL);
                MineSweeper.makeNewBoard();
            }
            else if (e.getActionCommand().equals("Start Game")) {
                levelMenu.setEnabled(false);
                gameMenu.getItem(0).setEnabled(false);
                gameMenu.getItem(1).setEnabled(true);
                gameMenu.getItem(2).setEnabled(true);
                MineSweeper.startGame();
                MineSweeper.makeNewBoard();
            }
            else if (e.getActionCommand().equals("Re-Start Game")) {
                levelMenu.setEnabled(false);
                gameMenu.getItem(0).setEnabled(false);
                gameMenu.getItem(1).setEnabled(true);
                gameMenu.getItem(2).setEnabled(true);
                MineSweeper.startGame();
                MineSweeper.makeNewBoard();
            }
            else if (e.getActionCommand().equals("Reveal board")) {
                levelMenu.setEnabled(true);
                gameMenu.getItem(0).setEnabled(true);
                gameMenu.getItem(1).setEnabled(false);
                gameMenu.getItem(2).setEnabled(false);
                MineSweeper.endGame();
                MineSweeper.revealBoard();
            }
            else if (e.getActionCommand().equals("Exit")) {
                System.exit(0);
            }
        }
    }

    /*
     * The main GUI component that is placed into the window.  This panel
     * holds all of the buttons and also implements the mouse listener
     * that responds to the clicks on the buttons.
     */
    static class MineSweeperPanel extends JPanel implements MouseListener {

        private static final long serialVersionUID = 1L;

        private MineSweeperBoard theBoard;

        public MineSweeperPanel(MineSweeperBoard b) {

            theBoard = b;

            int rows = b.getRows();
            int cols = b.getColumns();

            setLayout(new GridLayout(rows, cols));
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    JButton but = new MineSweeperButton(r, c);
                    add(but);
                    but.addMouseListener(this);
                }
            }
        }

        public void revealBoard() {
            // iterate through all of the components
            // turn them into MineSweeperButtons and
            // set their icons according to the board.
            Component[] c = getComponents();
            for (int i = 0; i < c.length; i++) {
                MineSweeperButton b = (MineSweeperButton) c[i];
                b.setRevealIcon(theBoard.getCell(b.getRow(), b.getCol()));
            }
            repaint();
        }

        public void mouseClicked(MouseEvent e) {

            try {
                if (theBoard.gameLost() || theBoard.gameWon()) {
                    MineSweeper.reStartGameOnClick();
                }
                else {
                    if (!MineSweeper.isGameOn()) {
                        MineSweeper.startGameOnClick();
                    }

                    MineSweeperButton b = (MineSweeperButton) (e.getSource());
                    int row = b.getRow();
                    int col = b.getCol();

                    int mod = e.getModifiers();
                    boolean button1 = (mod & InputEvent.BUTTON1_MASK) != 0;
                    boolean button3 = (mod & InputEvent.BUTTON3_MASK) != 0;
                    
					/*
					 * Need to explicitly check control down to get right button
					 * press on a mac that uses ctrl+click for right button.
					 */
                    if (button1 && !e.isControlDown()) {
						// 1.4.1 stuff: e.getButton() == MouseEvent.BUTTON1) {
						theBoard.uncoverCell(row, col);

						// Update the entire board because the bonus questions
						// require it.
						Component[] c = getComponents();
						for (int i = 0; i < c.length; i++) {
							MineSweeperButton tb = (MineSweeperButton) c[i];
							tb.setPlayingIcon(theBoard.getCell(tb.getRow(), tb.getCol()));
						}

						// b.setPlayingIcon(theBoard.getCell(row, col));
					} else if (button3 || (button1 && e.isControlDown())) {
                        // 1.4.1 stuff: e.getButton() == MouseEvent.BUTTON3) {
                        theBoard.flagCell(row, col);
                        b.setPlayingIcon(theBoard.getCell(row, col));
                    }

                    repaint();
                    
                    if (theBoard.gameWon()) {
                        MineSweeper.gameWasWon();
                    }
                    else if (theBoard.gameLost()) {
                        MineSweeper.gameWasLost();
                    }
                }
            }
            catch (ClassCastException e2) {
            }
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
    }
    
    /*
     * This class implements the behavior of the buttons including how the 
     * icons are displayed.
     */
    static class MineSweeperButton extends JButton {

        private static final long serialVersionUID = 1L;

        private static final ImageIcon EMPTY_ICON = new ImageIcon(MineSweeper.class
                .getResource("icons/empty.jpg"));
        private static final ImageIcon MINE_ICON = new ImageIcon(MineSweeper.class
                .getResource("icons/mine.jpg"));
        private static final ImageIcon HIT_MINE_ICON = new ImageIcon(MineSweeper.class
                .getResource("icons/hitMine.jpg"));
        private static final ImageIcon WRONG_MINE_ICON = new ImageIcon(MineSweeper.class
                .getResource("icons/wrongMine.jpg"));
        private static final ImageIcon FLAG_ICON = new ImageIcon(MineSweeper.class
                .getResource("icons/flag.jpg"));

        private static final ImageIcon[] NUMBER_ICONS = new ImageIcon[9];
        static {
            NUMBER_ICONS[0] = new ImageIcon(MineSweeper.class.getResource("icons/zero.jpg"));
            NUMBER_ICONS[1] = new ImageIcon(MineSweeper.class.getResource("icons/one.jpg"));
            NUMBER_ICONS[2] = new ImageIcon(MineSweeper.class.getResource("icons/two.jpg"));
            NUMBER_ICONS[3] = new ImageIcon(MineSweeper.class.getResource("icons/three.jpg"));
            NUMBER_ICONS[4] = new ImageIcon(MineSweeper.class.getResource("icons/four.jpg"));
            NUMBER_ICONS[5] = new ImageIcon(MineSweeper.class.getResource("icons/five.jpg"));
            NUMBER_ICONS[6] = new ImageIcon(MineSweeper.class.getResource("icons/six.jpg"));
            NUMBER_ICONS[7] = new ImageIcon(MineSweeper.class.getResource("icons/seven.jpg"));
            NUMBER_ICONS[8] = new ImageIcon(MineSweeper.class.getResource("icons/eight.jpg"));
        }

        private ImageIcon curIcon;
        private int myRow;
        private int myCol;

        public MineSweeperButton(int row, int col) {
            super(EMPTY_ICON);

            myRow = row;
            myCol = col;

            setBorder(BorderFactory.createRaisedBevelBorder());
            setBackground(new Color(155, 153, 156));
        }

        public int getRow() {
            return myRow;
        }

        public int getCol() {
            return myCol;
        }

        public void setPlayingIcon(int iconNumber) {
            switch (iconNumber) {
            case MineSweeperBoard.COVERED_CELL:
                curIcon = EMPTY_ICON;
                break;
            case MineSweeperBoard.MINE:
                curIcon = EMPTY_ICON;
                break;
            case MineSweeperBoard.FLAG:
                curIcon = FLAG_ICON;
                break;
            case MineSweeperBoard.FLAGGED_MINE:
                curIcon = FLAG_ICON;
                break;
            case MineSweeperBoard.UNCOVERED_MINE:
                curIcon = MINE_ICON;
                break;
            default:
                curIcon = NUMBER_ICONS[iconNumber];
            }

            if (curIcon != EMPTY_ICON && curIcon != FLAG_ICON) {
                setBorder(BorderFactory.createLoweredBevelBorder());
            }

            setIcon(curIcon);
            repaint();
        }

        public void setRevealIcon(int iconNumber) {
            switch (iconNumber) {
            case MineSweeperBoard.COVERED_CELL:
                curIcon = EMPTY_ICON;
                break;
            case MineSweeperBoard.MINE:
                curIcon = MINE_ICON;
                break;
            case MineSweeperBoard.FLAG:
                curIcon = WRONG_MINE_ICON;
                break;
            case MineSweeperBoard.FLAGGED_MINE:
                curIcon = FLAG_ICON;
                break;
            case MineSweeperBoard.UNCOVERED_MINE:
                curIcon = HIT_MINE_ICON;
                break;
            default:
                curIcon = NUMBER_ICONS[iconNumber];
            }

            setBorder(BorderFactory.createLoweredBevelBorder());
            setIcon(curIcon);
            repaint();
        }
    }
}
