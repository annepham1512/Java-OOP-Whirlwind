package minesweeper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MineSweeperBoardTest {
	private MineSweeperBoard b0;
	private MineSweeperBoard b1;
	private MineSweeperBoard b2;
	private MineSweeperBoard b3;
	private MineSweeperBoard b4;
	

	@BeforeEach
	void setUp() throws Exception {
		b0 = new MineSweeperBoard();
		b1 = new MineSweeperBoard(MineSweeperBoard.BEGINNER_LEVEL);
		b2 = new MineSweeperBoard(MineSweeperBoard.INTERMEDIATE_LEVEL);
		b3 = new MineSweeperBoard(MineSweeperBoard.EXPERT_LEVEL);
		b4 = new MineSweeperBoard(10);
	}

	@Test
	void testMineSweeperBoard() {
		assertEquals(3, b0.getRows());
		assertEquals(4, b0.getColumns());
		assertEquals(2, b0.getNumMines());
	}

	@Test
	void testMineSweeperBoardWithLevel() {
		assertEquals(5, b1.getRows());
		assertEquals(10, b1.getColumns());
		assertEquals(3, b1.getNumMines());
		assertEquals(10, b2.getRows());
		assertEquals(15, b2.getColumns());
		assertEquals(15, b2.getNumMines());
		assertEquals(15, b3.getRows());
		assertEquals(20, b3.getColumns());
		assertEquals(45, b3.getNumMines());
		assertEquals(5, b4.getRows());
		assertEquals(10, b4.getColumns());
		assertEquals(3, b4.getNumMines());
	}

	@Test
	void testGetRows() {
		assertEquals(3, b0.getRows());
		assertEquals(5, b1.getRows());
		assertEquals(10, b2.getRows());
		assertEquals(15, b3.getRows());
		assertEquals(5, b4.getRows());
	}

	@Test
	void testGetColumns() {
		assertEquals(4, b0.getColumns());
		assertEquals(10, b1.getColumns());
		assertEquals(15, b2.getColumns());
		assertEquals(20, b3.getColumns());
		assertEquals(10, b4.getColumns());
	}

	@Test
	void testGetNumMines() {
		assertEquals(2, b0.getNumMines());
		assertEquals(3, b1.getNumMines());
		assertEquals(15, b2.getNumMines());
		assertEquals(45, b3.getNumMines());
		assertEquals(3, b4.getNumMines());
		
	}

	@Test
	void testGetCell() {
		assertEquals(MineSweeperBoard.MINE, b0.getCell(0,0));
		assertEquals(MineSweeperBoard.MINE, b0.getCell(2,1));
		assertEquals(MineSweeperBoard.COVERED_CELL, b0.getCell(1,1));
	}

	@Test
	void testNumAdjMines() {
		assertEquals(2, b0.numAdjMines(1,0));
		assertEquals(1, b0.numAdjMines(2,2));
		assertEquals(0, b0.numAdjMines(2,3));
	}

	@Test
	void testUncoverCell() {
		// If flagged cell, the cell won't change
		b0.flagCell(2,3);
		b0.uncoverCell(2, 3);
		assertEquals(MineSweeperBoard.FLAG, b0.getCell(2,3));
		b0.flagCell(0,0);
		b0.uncoverCell(0, 0);
		assertEquals(MineSweeperBoard.FLAGGED_MINE, b0.getCell(0,0));
		// If covered cell, return num adj_mines
		b0.uncoverCell(2,2);
		assertEquals(b0.numAdjMines(2, 2), b0.getCell(2, 2));
		// If cell is MINE, UNCOVERED_MINE
		b0.uncoverCell(2,1);
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, b0.getCell(2,1));
		// If already uncovered, still uncovered
		b0.uncoverCell(2,2);
		assertEquals(b0.numAdjMines(2, 2), b0.getCell(2, 2));
	}

	@Test
	void testFlagCell() {
		// If COVERED_CELL, change to FLAG
		b0.flagCell(1, 1);
		assertEquals(MineSweeperBoard.FLAG, b0.getCell(1,1));
		// If MINE, change to FLAGGED_MINE
		b0.flagCell(0, 0);
		assertEquals(MineSweeperBoard.FLAGGED_MINE, b0.getCell(0,0));
		// If FLAG, change to COVERED_CELL
		b0.flagCell(2,3);
		b0.flagCell(2,3);
		assertEquals(MineSweeperBoard.COVERED_CELL, b0.getCell(2,3));
		// If FLAGGED_MINE, change back to MINE
		b0.flagCell(0,0);
		assertEquals(MineSweeperBoard.MINE, b0.getCell(0,0));
		// If already UNCOVERED_MINE, no change
		b0.uncoverCell(2,2);
		b0.flagCell(2,2);
		assertEquals(b0.numAdjMines(2, 2), b0.getCell(2, 2));
	}

	@Test
	void testRevealBoard() {
		b0.revealBoard();
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, b0.getCell(0, 0));
		assertEquals(MineSweeperBoard.UNCOVERED_MINE, b0.getCell(2, 1));
		assertEquals(b0.numAdjMines(0, 1), b0.getCell(0, 1));
		assertEquals(b0.numAdjMines(0, 2), b0.getCell(0, 2));
		assertEquals(b0.numAdjMines(0, 3), b0.getCell(0, 3));
		assertEquals(b0.numAdjMines(1, 0), b0.getCell(1, 0));
		assertEquals(b0.numAdjMines(1, 1), b0.getCell(1, 1));
		assertEquals(b0.numAdjMines(1, 2), b0.getCell(1, 2));
		assertEquals(b0.numAdjMines(1, 3), b0.getCell(1, 3));
		assertEquals(b0.numAdjMines(2, 0), b0.getCell(2, 0));
		assertEquals(b0.numAdjMines(2, 2), b0.getCell(2, 2));
		assertEquals(b0.numAdjMines(2, 3), b0.getCell(2, 3));
	}

	@Test
	void testGameLost() {
		assertEquals(false, b0.gameLost());
		b0.uncoverCell(0, 0);
		assertEquals(true, b0.gameLost());
	}

	@Test
	void testGameWon() {
		assertEquals(false,b0.gameWon());
		b0.flagCell(0, 0);
		b0.flagCell(2, 1);
		b0.uncoverCell(0, 1);
		b0.uncoverCell(0, 2);
		b0.uncoverCell(0, 3);
		b0.uncoverCell(1, 0);
		b0.uncoverCell(1, 1);
		b0.uncoverCell(1, 2);
		b0.uncoverCell(1, 3);
		b0.uncoverCell(2, 0);
		b0.uncoverCell(2, 2);
		b0.uncoverCell(2, 3);
		assertEquals(true,b0.gameWon());
	}

}
