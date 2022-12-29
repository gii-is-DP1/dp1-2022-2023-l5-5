package org.springframework.samples.petclinic.square;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.board.Board;


public class SquareTest {
	
	@Test
	public void testCreateSquare() {
		Square square = new Square(3, 4, new Board(4, 4, 4, 4, null));
		assertEquals(square.getFila(), 3);
		assertEquals(square.getColumna(), 4);
		assertEquals(square.getValor(), 0);
		assertEquals(square.isCovered(), true);
		assertEquals(square.isMine(), false);
	}
	
	@Test
	public void testIncreaseValue() {
		Square square = new Square(2, 1, new Board(4, 4, 4, 4, null));
		square.increaseValue();
		assertEquals(square.getValor(), 1);
	}
	

}
