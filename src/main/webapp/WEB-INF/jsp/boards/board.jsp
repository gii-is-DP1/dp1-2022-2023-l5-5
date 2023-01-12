<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<petclinic:layout pageName="board">
	
	<style>
	  #flagsNumber, #crono{
		font-size: large;
	  }
	</style>
	
	
	<div class= "col-2 text-center">
	<div id="title" style= "font-weight: bold; font-size: 30">MINESWEEPER</div>
	<br>
	<img name="1" id="1" src="/resources/images/one.png" style= "display:None;">
	<img name="2" id="2" src="/resources/images/two.png" style= "display:None;">
	<img name="3" id="3" src="/resources/images/three.png" style= "display:None;">
	<img name="4" id="4" src="/resources/images/four.png" style= "display:None;">
	<img name="5" id="5" src="/resources/images/five.png" style= "display:None;">
	<img name="6" id="6" src="/resources/images/six.png" style= "display:None;">
	<img name="7" id="7" src="/resources/images/seven.png" style= "display:None;">
	<img name="flag" id="F" src="/resources/images/flag.png" style= "display:None;">
	<img name="wrong" id="W" src="/resources/images/wrong.png" style= "display:None;">
	<img name="mine" id="*" src="/resources/images/mine.png" style= "display:None;">

	<div id=flagsNumber></div>
	<div id="crono">00 : 00</div>
	
	<!-- Cuadro de mensaje cuando tienes un game in progress -->
	<div id="gameInProgressMessage" style="display:None; color:black; font-family: 'Courier New', Courier, monospace; font-size: 20px; border-style: ridge; 
	border-width: 10px; background-color: #9da5a8; border-color: #b8c9d0; width: 600px; margin-left: 23%;"></div>
	<br> 
	
	<!-- Cuadro de mensaje de victoria o derrota que se muestra cuando se acaba la partida -->
	<div id="gameOverMessage" style="display:None; color:black; font-family: 'Courier New', Courier, monospace; font-size: 20px; border-style: ridge; 
	border-width: 10px; background-color: #9da5a8; border-color: #b8c9d0; width: 600px; margin-left: 23%;"></div>
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
	</div>
	<script>

 	function click(squareSide, board){
		let column = Math.floor((event.pageX-canvas.offsetLeft)/squareSide);
		let row = Math.floor((event.pageY-canvas.offsetTop)/squareSide);
		let id = board.data.id;
		let url='/boards/'+id.toString()+'/click/'+row.toString()+'/'+column.toString();
		axios.get(url)
		.then(boardT=>{
			renderBoard(boardT);
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
	

	//Obtenemos el parametro de dificultad de la URL
	var queryString = window.location.search;
	var urlParams = new URLSearchParams(queryString);
	var difficultyParam = urlParams.get('dificulty');
	window.onload = createBoard(difficultyParam);
	
	function renderBoard(board){
		updateFlags();
		gameOver();
		var array2D = [];
		for (let i=0;i<board.data.rowsNumber;i++){
			const a = []
			for (let j=0;j<board.data.columnsNumber;j++){
				let c = "";
				if(board.data.squares[j+board.data.columnsNumber*i].isCovered){
					c = "X";
				} else if(board.data.squares[j+board.data.columnsNumber*i].isFlag){
					c = "F";
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
			 	if (array2D[i][j] === 'X') squareColor = 'lightgrey';
			 	else if (array2D[i][j] === '0') squareColor = 'white';			
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
	

	
		//Comprueba por cada click si se ha perdido o ganado la partida, si se ha ganado o perdido, para el crono y pone el mensaje, si no, no hace nada
		function gameOver(){
			if(board.data.gameStatus == "LOST"){
				document.getElementById("gameOverMessage").style.display = "block";
				document.getElementById("gameOverMessage").innerHTML = "&#x1f6a9 You have lost the game <br> The duration of the game played has been: "+document.getElementById("crono").innerHTML+"&#x1f4a3"
				+"\n Click on the board to play again";
				//Paramos el cronometro de la vista cuando perdemos
				stop();
				document.getElementById("canvas").addEventListener("click", function(){
					if(confirm("You have lost the game! Do you want to play another game?")){
						window.location.href = "http://localhost:8080/board/game?dificulty=1";
					}else{
						window.location.href = "http://localhost:8080/players/myprofile";
					};
				});
			}
			else if(board.data.gameStatus == "WON"){
				document.getElementById("gameOverMessage").style.display = "block";
				document.getElementById("gameOverMessage").innerHTML = "&#x1f6a9 CONGRATULATIONS! You have completed the board <br> The duration of the game played has been: "+document.getElementById("crono").innerHTML+"&#x1f4a3"
				+"\n Click on the board to play again";
				stop();	
				document.getElementById("canvas").addEventListener("click", function(){
						if(confirm("CONGRATULATIONS YOU HAVE WON THE GAME! Do you want to play another game?")){
							window.location.href = "http://localhost:8080/board/game?dificulty=1";
						}else{
							window.location.href = "http://localhost:8080/players/myprofile";
						};
				});
			}
		}
		

		function updateFlags(){
			document.getElementById("flagsNumber").innerHTML = "&#x1f6a9 "+ board.data.flagsNumber;
		}

	}
	</script>
	</canvas>
	
</petclinic:layout>