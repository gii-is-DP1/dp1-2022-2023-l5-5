<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<petclinic:layout pageName="board">
<!--  	<style>
	body {
		text-align: center;
		font-family: calibri;
		margin: 0;
		padding: 0;
		user-select: none;
		background: lightsteelpink;
	  }

	  #titulo{
		  font-size: 35px;
		  font-weight: bolder;
		  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
		  background-color: goldenrod;
		  border-style: ridge;
		  border-width: 4px;
	  }

	  #numBanderas, #screen{
		font-size: large;
	  }

	</style>  -->
	
	<div id="title">MINESWEEPER</div>
	<br>
	<div>
		<p>Choose the difficulty:</p>
		<button class="emerald" onclick="redirect(1)">Easy</button>
		<button class="emerald" onclick="redirect(2)">Medium</button>
		<button class="emerald" onclick="redirect(3)" >Difficult</button>
		
<!-- 		<input type="radio" id="easy" name="difficulty" value="Easy" checked /> <label for="easy">Easy</label><br>
		<input type="radio" id="medium" name="difficulty" value="Medium" /> <label for="medium">Medium</label><br>
		<input type="radio" id="difficult" name="difficulty" value="Difficult" /> <label for="difficult">Difficult</label><br>
	 -->
	</div>
	<div id=flagsNumber></div>
	<div id="screen">00 : 00</div>
<!-- Cuadro de mensaje de victoria o derrota que se muestra cuando se acaba la partida
	<div id="mensajeFinPartida" style="color:aliceblue; font-family: 'Courier New', Courier, monospace; font-size: 20px; border-style: ridge; 
	border-width: 10px; background-color: #2a6478; border-color: #b8c9d0; width: 600px; margin-left: 23%;"></div>
	<br> -->
	
	<script> 
	//Este script va a ser para funciones para el cronometro y más cosas...
	</script>
	
	<canvas id= "canvas" onclick="start()">
	
	<script>
	
	/* function click */
	
 	function createBoard(difficulty) {
		document.getElementById("mensajeFinPartida").style.display = "none";
		axios.get(`/boards/new/`+difficulty.toString())
		.then(board=>{
			console.log(board);
			renderBoard(board);
			const squareSide = 550/board.data.rowsNumber;
			document.getElementById("canvas").addEventListener("click", function(){
			let column = Math.floor((event.pageX-canvas.offsetLeft)/squareSide);
			let row = Math.floor((event.pageY-canvas.offsetTop)/squareSide);
			let id = board.data.id;
			let url = '/boards/'+id.toString()+'/click/'+row.toString()+'/'+column.toString();
			axios.get(url)
			.then(boardT=>{
				renderBoard(boardT);
			})
			.catch(function(error){console.log(error)});
			});
			document.getElementById("canvas").addEventListener("contextmenu", function(event){
			//Elimina la funcionalidad en el canvas del boton derecho de desplegar el menu para poner una bandera
			event.preventDefault();
			let column = Math.floor((event.pageX-canvas.offsetLeft)/squareSide);
			let row = Math.floor((event.pageY-canvas.offsetTop)/squareSide);
			let id = board.data.id;
			let url = '/boards/'+id.toString()+'/clickDerecho/'+row.toString()+'/'+column.toString();
			console.log(url);
			axios.get(url)
			.then(boardT=>{
				renderBoard(boardT);
			})
			.catch(function(error){console.log(error)});
			});
		}).catch(function (error) {
        		console.log(error);
          	});
	} 
	
 	//Refresca el tablero cuando se cambia la dificultad para borrar los datos del tablero anterior
	function redirect(dificultad){
		window.location.href = "http://localhost:8080/board/new?dificulty="difficulty.toString();
	}

	//Obtenemos el parametro de dificultad de la URL
	var queryString = window.location.search;
	var urlParams = new URLSearchParams(queryString);
	var anuncioParam = urlParams.get('dificulty');
	window.onload = createBoard(anuncioParam);
	function renderBoard(board){
		/* actualizarBanderas(); */
		/* finPartida(); */
		var array2D = [];
		for (let i=0;i<board.data.rowsNumber;i++){
			const a = []
			for (let j=0;j<board.data.columnsNumber;j++){
				let c = "";
				if(board.data.squares[j+board.data.columnsNumber*i].isCovered){
					c = "X";
				} else if(board.data.squares[j+board.data.columnsNumber*i].isMine){
					c = "*";
				} else{
					c = board.data.squares[j+board.data.columnsNumber*i].valor.toString();
				}
				a.push(c);
			}
			array2D.push(a);
		}
	
	
	 	//Importamos la imagen a cada casilla correspondiendo con su valor
		const canvas = document.getElementById('canvas');
		var ctx = canvas.getContext('2d');
		var elementsList = [];
		canvas.width = 550;
		canvas.height = 550;
		canvas.style = "border:2px solid #000000;";
		const squareSide = 550/array2D.length;
		for (let i = 0; i < array2D.length; i++) {
			for (let j = 0; j < array2D[i].length; j++) {
			    let x = j * squareSide;
			    let y = i * squareSide;
 			    squareColor = 'lightgrey';
				let img = new Image();
			    if (array2D[i][j] === 'X') img.src = "resources/images/covered_square.png";
			    if (array2D[i][j] === '*') img.src = "resources/images/mine.png";
			    if (array2D[i][j] === '0') img.src = "resources/images/uncovered_square.png";			
				if (array2D[i][j] === '1') img.src = "resources/images/one.png";
				if (array2D[i][j] === '2') img.src = "resources/images/two.png"
				if (array2D[i][j] === '3') img.src = "resources/images/three.png";
				if (array2D[i][j] === '4') img.src = "resources/images/four.png";
				if (array2D[i][j] === '5') img.src = "resources/images/five.png";
				if (array2D[i][j] === '6') img.src = "resources/images/six.png";
				if (board.data.squares[j+board.data.columnsNumber*i].isFlag) img.src = "resources/images/flag.png";
				if(board.data.squares[j+board.data.columnsNumber*i].isWrong) img.src = "resources/images/wrong.png";
					
				const square = new Path2D();
			    square.rect(x, y, squareSide, squareSide); 
			    ctx.strokeStyle = 'black';
			    ctx.stroke(square);
			    img.onload = function(){
					ctx.drawImage(img,x,y,squareSide,squareSide);
				}
			    ctx.fill(square);
				ctx.fillStyle = squareColor;
			    ctx.fill(square);
			    elementsList.push(square);  
			}
		}
	</script>
	
	</canvas>
	
	
</petclinic:layout>