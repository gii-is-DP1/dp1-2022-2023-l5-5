<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<petclinic:layout pageName="board">
	
	<div id="title" style= "font-weight: bold; font-size: 30">MINESWEEPER</div>
	<br>
	<img name="1" id="1" src="/resources/images/one.png" style= "display:None;">
	<img name="2" id="2" src="/resources/images/two.png" style= "display:None;">
	<img name="3" id="3" src="/resources/images/three.png" style= "display:None;">
	<img name="4" id="4" src="/resources/images/four.png" style= "display:None;">
	<img name="5" id="5" src="/resources/images/five.png" style= "display:None;">
	<img name="6" id="6" src="/resources/images/six.png" style= "display:None;">
	<div>
		<p>Choose the difficulty:</p>
		<a href="http://localhost:8080/board/game?dificulty=1" class="btn btn-primary" >Easy</a>
		<a href="http://localhost:8080/board/game?dificulty=2" class="btn btn-primary">Medium</a>
		<a href="http://localhost:8080/board/game?dificulty=3" class="btn btn-primary">Difficult</a>		
	</div>
	<br>
	
	
	<div id=flagsNumber></div>
	<div id="crono">00 : 00</div>
	
	<!-- Cuadro de mensaje de victoria o derrota que se muestra cuando se acaba la partida -->
	<div id="gameOverMessage" style="color:aliceblue; font-family: 'Courier New', Courier, monospace; font-size: 20px; border-style: ridge; 
	border-width: 10px; background-color: #2a6478; border-color: #b8c9d0; width: 600px; margin-left: 23%;"></div>
	<br> 
	
	<script> 
	//Cronometro
	crono = document.getElementById("crono");
	res = document.getElementById("res");
	var inProgress = false; 
	var acumularTime = 0; 
	//Funcion que comienza el cronometro cuando clicas en algun lugar del canvas
	function start () {
		if (inProgress == false) { 
		timeInicial = new Date();
		control = setInterval(chronometer,10);
		inProgress = true;
		}
	}

	function chronometer () { 
		timeActual = new Date();
		acumularTime = timeActual - timeInicial;
		acumularTime2 = new Date();
		acumularTime2.setTime(acumularTime); 
		ss = Math.round(acumularTime2.getSeconds());
		mm = acumularTime2.getMinutes();
		if (ss < 10) {ss = "0"+ss;} 
		if (mm < 10) {mm = "0"+mm;}
		crono.innerHTML = mm+" : "+ss;
		document.getElementById
		console.log("Chronometer started");
	}

	function stop () { 
		if (inProgress == true) {
		clearInterval(control);
		inProgress = false;
		}     
	}      
	
	function reset () {
		document.getElementById("res").innerHTML = "";
		if (inProgress == true) {
			clearInterval(control);
			inProgress = false;
		}
		acumularTime = 0;
		crono.innerHTML = "00 : 00";
	}
	</script>
	
	<canvas id= "canvas" onclick="start()">
	
	<script>

 	function click(squareSide, board){
		let column = Math.floor((event.pageX-canvas.offsetLeft)/squareSide);
		let row = Math.floor((event.pageY-canvas.offsetTop)/squareSide);
		let id = board.data.id;
		let url='/boards/'+id.toString()+'/click/'+row.toString()+'/'+column.toString();
		axios.get(url)
		.then(boardT=>{
			renderTablero(boardT);
		})
		.catch(function(error){console.log(error)});
	} 
	

 	function createBoard(difficulty) {

		
		axios.get('/boards/new/'+difficulty.toString())

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
			let url = '/boards/'+id.toString()+'/rightClick/'+row.toString()+'/'+column.toString();
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
 	function redirect(difficulty){
			window.location.href = "http://localhost:8080/board/game?dificulty=" +difficulty.toString();
	}
	

	//Obtenemos el parametro de dificultad de la URL
	var queryString = window.location.search;
	var urlParams = new URLSearchParams(queryString);
	var difficultyParam = urlParams.get('dificulty');
	window.onload = createBoard(difficultyParam);
	
	function renderBoard(board){
		/* actualizarBanderas(); */
		gameOver();
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
					c = board.data.squares[j+board.data.columnsNumber*i].value.toString();
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
			    /* if (array2D[i][j] === 'X') img.src = "resources/images/covered_square.png";
			    if (array2D[i][j] === '*') img.src = "resources/images/mine.png";
			    if (array2D[i][j] === '0') img.src = "resources/images/uncovered_square.png";			
				if (array2D[i][j] === '1') img.src = "resources/images/one.png";
				if (array2D[i][j] === '2') img.src = "resources/images/two.png"
				if (array2D[i][j] === '3') img.src = "resources/images/three.png";
				if (array2D[i][j] === '4') img.src = "resources/images/four.png";
				if (array2D[i][j] === '5') img.src = "resources/images/five.png";
				if (array2D[i][j] === '6') img.src = "resources/images/six.png";
				if (board.data.squares[j+board.data.columnsNumber*i].isFlag) img.src = "resources/images/flag.png";
				if (board.data.squares[j+board.data.columnsNumber*i].isWrong) img.src = "resources/images/wrong.png";
 */					
			 	if (array2D[i][j] === 'X') squareColor = 'lightgrey';
			 	else if (array2D[i][j] === '*') img.src = "data:image/jpeg;base64,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";				
				else if (array2D[i][j] === '0') squareColor = 'white';			
				else if (board.data.squares[j+board.data.columnsNumber*i].isFlag) img.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAmVBMVEW5ubn8/Px1dXUAAAD+AAD///9vb2+xsbHAwMC9vb23vb7ReHdpaWkHBwe2traioqJ7e3uoqKi0wsLWdnaxw8DHl5flUE/sOjrPiIi8ra/1GhvwMjPRg4G2v77pSEjiXFvab3DKkpHCoKGDg4PApaT0JCbfYmP3BQWVdXbbAACGAQDOAQNsAQC5AgFPAAHvAQGgAQA2AAC4e3fXcfv7AAADeklEQVR4nO3daW+bQBgE4K13fUBSGrB7pC09kia9z///4wqJ43DsAgtY3nc089lBPIJoMspKVk+ws0zUqW/huFkm59jCAriAFpZAaOEdEFl4DwQW7oG4wgcgrPAARBU+AkGFFSCmsAqEFNaAiMI6EFDYAOIJm0A4YQuIJmwDwYQWIJbQBoQSWoFIQjsQSOgA4ghdwA7hUlZcQLdwuREV5QJ2CRMlKOvVCOGpb9orFFIYfiikMPxQSGH4oZDC8BOaMI7nvmJgwuzt2dyXDEqYX740Z3M/xJCEu60xyML8041BFsb5rTHIwuz5Z4MszJMXxiALs1fGIAvLikAWxtnWGGRhdnVjkIV5fGuagRI+VgSmMK5UBKSwVhGAwkZF4Al3W4cPRLhfEbDCw4pAFVorAkiY2ysCR7hzVASK0F0RGMLWikAT5q0VgSXsqQj5wr6KkC6MVV9FCBe6VgSKcFBFSBZ2rAgIYeeKABAOrwihQo+KECnMfSpConDAihAt9K0IacKBK0Kw8Or1eKAM4eU7dKHKPqILVRy/BxeWk37kL6MYYfGqfkAXFqX4BlxYnsNDF6o88S4OYcIRxSFOqHLP4pAnLE+U+BSHRGHxl7hHcYgUehWHUGHxGIcWh1RheXxmWHHIFQ4tDslCFecDikO0cFBxCBcOWBzihb3FIV/YtzgQhN2LA0LYWRwgwo7FgSJ0FweO0LU4gISO4oASWhcHltC2ONCE7eLAEzb/xwEoLB7jl6/gwuhCf4MXav0dXqh/wAu1/gkv1L9+owu1/gMv1H//RTNfPTShvqDQNxRSOEconBYKKZwjFE4LhRTOEQqnhUIK5wiF00IhhXOEwmmhkMI5QuG0UEjhHKFwWiik0DeRJZuacGP7iBzh+pkl6aoiXKW2j6ylCGvvo08mvLsUUkghhRRSSCGFFFKIK4xcXwN+PVJ47fxe8RMJU/3UkZFC5+XS0wijdCTEP2nv60shhRRSSCGFFFJIIYUUUkghhRSOFioK5Qtthw2Ok95bCeK0yXFDIYXhh0IKww+FFAafaIwwWUtK6i1cJucrUXEBXcIC6PwRYbELgYB2IRLQKoQC2oRYQIsQDNgWogFbQjhgU4gHbAgBgXUhIrAmhARWhZjAihAU+ChEBR6EsMAHIS5wLwQG3guRgXdCaGApxAYWQnDgQqEDFwoduPgPr9MYhw8NclcAAAAASUVORK5CYII=";
				else if (board.data.squares[j+board.data.columnsNumber*i].isWrong) img.src = "https://pngimage.net/wp-content/uploads/2018/06/red-x-mark-png-5.png"; 				
				else img=document.getElementById(array2D[i][j]);
				
				const square = new Path2D();
			    square.rect(x, y, squareSide, squareSide); 
			    ctx.strokeStyle = 'black';
			    ctx.stroke(square);
			    //img.onload = function(){
					ctx.drawImage(img, x, y, squareSide, squareSide);
				///}
				if(array2D[i][j] === 'X' || array2D[i][j] === '0'){
			    	ctx.fill(square);
					ctx.fillStyle = squareColor;
			    	ctx.fill(square);
				}
			    elementsList.push(square);  
			}
		}
	

	
		function gameOver(){
			if(board.data.gameStatus == "LOST"){
				document.getElementById("gameOverMessage").style.display = "block";
				document.getElementById("gameOverMessage").innerHTML = "&#x1f6a9 OHHH..has perdido la partida <br> La duracion de la partida ha sido: "+document.getElementById("screen").innerHTML+"&#x1f4a3";
				//Paramos el cronometro de la vista cuando perdemos
				stop();
				document.getElementById("canvas").addEventListener("click", function(){
					if(confirm("OHHH HAS PERDIDO... Desea jugar otra partida?")){
						window.location.href = "http://localhost:8080/board/game?dificulty=2";
					}else{
						window.location.href = "http://localhost:8080/players/myprofile";
					};
				});
			}
			if(board.data.gameStatus == "WON"){
				document.getElementById("gameOverMessage").style.display = "block";
				document.getElementById("gameOverMessage").innerHTML = "&#x1f6a9 ENHORABUENA! Has completado el tablero<br> La duracion de la partida ha sido: "+document.getElementById("screen").innerHTML+"&#x1f4a3";
				stop();	
				document.getElementById("canvas").addEventListener("click", function(){
						if(confirm("ENHORABUENA HAS GANADO LA PARTIDA!! Deseas jugar otra?")){
							window.location.href = "http://localhost:8080/board/game?dificulty=2";
						}else{
							window.location.href = "http://localhost:8080/players/myprofile";
						};
				});
			}
		}
		
		function endMessage(){
			if(board.data.gameStatus == "WON"){
				if(confirm("ENHORABUENA HAS GANADO!! <br> Has completado el tablero en:"
				+document.getElementById("screen").innerHTML+"<br> Desea jugar otra partida?")){
					window.location.href = "http://localhost:8080/board/game?dificulty=2";
				}else{
					window.location.href = "http://localhost:8080/players/myprofile";
				};
			}

			if(board.data.gameStatus == "LOST"){
				if(confirm("OHH HAS PERDIDO... Has completado el tablero en:"
				+document.getElementById("screen").innerHTML+"Desea jugar otra partida?")){
					window.location.href = "http://localhost:8080/board/game?dificulty=2";
				}else{
					window.location.href = "http://localhost:8080/players/myprofile";
				};
			}
		}

		function updateFlags(){
			document.getElementById("flagsNumber").innerHTML = "&#x1f6a9 "+ board.data.flagsNumber;
		}

	}
	</script>
	</canvas>
	
</petclinic:layout>