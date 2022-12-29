package org.springframework.samples.petclinic.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(classes= {Service.class, Configuration.class}))
public class BoardTest {

	
	@Test
	public void testCreateBoard() {
		Board b = new Board(6, 6, 6, 6, null);
		int filas = (int) Math.sqrt(b.squares.size());
		assertEquals(filas, 6);
		assertEquals(b.squares.size(), 36);
		assertEquals(b.getFlagsNumber(), 6);
	}
	
	@Test
	public void testPrintBoard() {
		Board b0 = new Board(3, 3, 0, 3, null);
		String bp0 = b0.toString();
		assertEquals(bp0, "000\n000\n000\n");
	}
	
	@Test
	public void testSquaresXBoard() {
		Board b0 = new Board(6, 5, 10, 3, null);
		Board b1 = new Board(12, 12, 5, 5, null);
		assertEquals(b0.getSquares().size(), 30);
		assertEquals(b1.getSquares().size(), 144);
	}
}
