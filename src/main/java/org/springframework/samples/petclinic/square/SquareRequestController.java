package org.springframework.samples.petclinic.square;


import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/board/{boardId}/squares?row={row}&&column={column}")
public class SquareRequestController {

	private final SquareService squareService;

    @Autowired
	public SquareRequestController(SquareService squareService) {
		this.squareService = squareService;
	}

	@GetMapping()
	public Square value(@PathParam("row") int row, @PathParam("column") int column, 
			@PathVariable("boardId") int boardId) {
		return this.squareService.findByPosition(boardId, row, column);
	}






}
