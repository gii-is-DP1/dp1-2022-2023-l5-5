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

	  #title{
		  font-size: 35px;
		  font-weight: bolder;
		  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
		  background-color: goldenrod;
		  border-style: ridge;
		  border-width: 4px;
	  }

	  #flagsNumber, #crono{
		font-size: large;
	  }

	</style>  -->
	
	<div id="title">MINESWEEPER</div>
	<br>
	<div>
		<p>Choose the difficulty:</p>
		<button class="emerald" onclick="redirect(1)">Easy</button>
		<button class="emerald" onclick="redirect(2)">Medium</button>
		<button class="emerald" onclick="redirect(3)">Difficult</button>
		
	</div>
	
	<!-- 		<input type="radio" id="easy" name="difficulty" value="Easy" checked /> <label for="easy">Easy</label><br>
		<input type="radio" id="medium" name="difficulty" value="Medium" /> <label for="medium">Medium</label><br>
		<input type="radio" id="difficult" name="difficulty" value="Difficult" /> <label for="difficult">Difficult</label><br>
	 -->
	
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
		let url = '/boards/'+id.toString()+'/click/' + row.toString() + '/' + column.toString();
		axios.get(url)
		.then(boardT=>{
			renderTablero(boardT);
		})
		.catch(function(error){console.log(error)});
	}
	 
	 
 	function createBoard(difficulty) {

		document.getElementById("gameOverMessage").style.display = "none";
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
			 	if (array2D[i][j] === '*') img.src = "data:image/jpeg;base64,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";				
				if (array2D[i][j] === '0') squareColor = 'white';			
				if (array2D[i][j] === '1') img.src = "data:image/png;base64,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";
				if (array2D[i][j] === '2') img.src = "data:image/png;base64,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";	
				if (array2D[i][j] === '3') img.src = "data:image/png;base64,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";	
				if (array2D[i][j] === '4') img.src = "data:image/png;base64,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";	
				if (array2D[i][j] === '5') img.src = "data:image/png;base64,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";
				if (array2D[i][j] === '6') img.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAe1BMVEX///8zMzMdHR3MzMwhISEvLy8mJiYwMDAsLCzX19ckJCQcHByRkZE1NTUZGRl6enpBQUFQUFD09PTk5OTq6uqjo6NVVVWcnJywsLC8vLw6OjpcXFzGxsZISEhvb29hYWGDg4N1dXXm5uYQEBCNjY23t7eqqqrS0tKYmJigxempAAAKgUlEQVR4nO2da3urKhCFU0XAe6K5aBI1vaTp//+Fp7HZbQqDlwCKPa6Pu915eAuBAWYWi0WbEjffxNW22D+ZpH2xreJN7iat7W+hs4OD7yNMHULGZmJEiEMx8v1DYD9Oacehj52xUVrkYD+M7Ye67yNE1LSOg0UoCj/6duQ6QHgaeF8iGAXrPoA7D4/d5t7C3q4z37FAYzf3IaHi2IkvKSPTZxeRnKjs8HV0l9MboD/CS7cN8N345aFZDn5vBgz8Kc2gkIgfNAHG1tgNVCArFgM+T3MOZYWehT34NwA/EQW9GPyFIfolC/wuvvtjt0uhfGBGdZviUIo9hCyThJCHqbjBBHPrYrIUrYMOjlAVZPnRNknHPAsqFAlXb2fJRjelIJKhqNi1hgmjyd0VSNCTuPz9q8dIwFdexml7Z11KAWP0OwwvwN6ODuZ234/cA9g9TnH/SztoJaQoG6vRPZWB3Yju9otrDxrIq3S8NvdUuoKmEe9n1x8AP0dlwyeapxIYhfh73U+An4pjO0MFxdTo34rxwXchO9dOQMB6hz9uPwu5aIauRm3sY1px0w0Jv35i8/2LpjPJ/CgFOL6OimOOPZrKMvFbGbcu0noblXCDlB7GbuuDOrB9RcLrXGNzuyY0hUgGksuNU/86TLnFkE5vHv2nku3Eekk8sCEpMj3YFuvCdqLz+YVL2EH6O2KdmLgdhJ8sXJYQd7/gME879ivnu4uc7dhoqvPMVS67YKB8sWGp0ditlBLbX3jDrfe0GruRUqpYnHhRMd9N3Hjsb7zYtc+pFlsmovGmGbH9U8Zs5sl2UTCEn1/NKYudOEmxYBOBULebYlN1ZKea/YL5hyfrodwUY2Rzly8z4dQ0E86E5msmnAnN10w4E5qvmXAmNF8z4UyoXsk6dfMsO5/Pao5pDSJM7ewjrrZLgiJU61XJAZEZhOn5bRtiC2FKnZ9zMLpV8dnjEyb5KYwQdvhESEdJ7sDIhMm58ixRMdUfIDw+h6JEwj9BeN4Le+8vECabvdVWyDFpwmyP2gtVJkzobjvwTZgwiV+7VVJNlfDSuRRuooQ71LkUbpKESdWjzmiKhC+9ijUnSOg+NUQwf4Hw2LMadXKEdqdFcMKEl96GBWpSywcjTLsBOhghP4ooxRhve3kiiDQU4UvY9h0k1EM+PgRZfnmRdWC510CEybJ5FiUUkcPGVtJpjAYifG5cBx20j3MddFcNQ8jnzN+JRkWmclgyGoTQbaiYdlClN8d6CMIErmWshVa6zyqHIAygUr9a1NsooWjSAIRAbc5NnpoVr1kDEHJ1K98ffFKC0CL9hIKS6c/PbXFVUST9hEtBtOYPlJirnXAj+Bb6Q+VW6yZMBF1o6Z9Eb9JNeIYXe++kCqBVuglX4GLvLJUBtEozoQ1PpBYTqK1TfQujZkK2YOVL3zUqibt5Xu3xaxRFr/6+enuX9ubkpZfwBexC8jVG17sqjLyfa21Csecvy1wxpF7CdzAiRdel/lhSD7g+vLpWxkrrxvUSbqF5hniLRU594aaf0NdSYT2gVsIU3Nnj97Rqsbmj0UnZWNVKuAEHKTk1+Tn9+zOE5ykQCnYVnY72iaqdh07CtZzdm7dSMlJ1Eh4lDe3oSkUcoJMQ8IDpiVgoQNRJyDkXPIBoNmHTIWlHKbic0Uh4UWG7iKTdDjQSwiFbX3my4Y1GwlOvK22RpE05NBIKTxH7CUmed2gk3CuyWEZyC78+wqR1rXAoQhgjBO2i7oTfDCWENxY/omh1yi9uejlm5d5q+HMQR6oT9RFy7j4M3+lukkyOq4YID0kdjusj5Gxu7uVX7D4+EyfbyGWd6CPMxIQEulRbh8K5V8oCSB8hvP2tATHotLHmTQBv+nY7NItQvLN4FVxrp1rKEvQRQhaotcQOhcKB/Sqxi9JHKAracIONP3yALGeRo4+Qt5esRUhDfwguAaSMnPQRPsOEzY0VZG04EjVeg/dh825ItOPCDzWh1tDfQ9J8r5YKhqkl8c6YNsI3cC51mp1QE8GGRMJedOjVgrY4SnNunDfCx+9O9BFyloy12rZCcM/L+I0NHXnThtXwKvjvYiYhYCj91G7CKJhM0eP3NPoIXXimaQkxBXsuiaBGH+H6kdWCd8i7EZo4SgFj96tocxAtCL7NJITD6BbXZc4e9va/TFwtBBvElkdABHsuI1f8x5YLQbxuZNS2SF+hthLuUaJfgrPECHmsCVfpvF17Aqcav+njwNdfWqPZRukkhHcX9NTwXwQX4zJHUToJc7C5hDQMU8Gm0pKwwdVJmMDzoifuEOh9m6siiXN9rfk0gq2QuL2C9V7mEEMvoSDIFO+g4DDoSaruRCuhcMcuiKNFR6yeTLKi3txEwZkpoWCT4d2I7Bswegm5hwlucvZA/J0CRkq15F57GyUL+rNbQi7QTEV1tGRv6A1pLVEnPjnssyAZFd0f9nghfQRC0YLxdM0g3fwM1fwgLtaXWQwHIGy4zSfea3F6P+fZLkaWODNFtgBMe93TR1NmlEO9lidg5Y0VBqhdk8tQbNyKmEFog9vErvLkkmkGIVx8SDzureBRwiEqnUUvC7eL4JdJECb7B3P4iPSXcCDCxfpBREtFIe0wrhGPIap5VnIg54+0PyLx1VTNDOVPk2x75kRTrMq8aTAXpVNLNddvIWV2CwM6YZ3DzquGE0kv9N8a0s0see5mCUlQodDwZFjPPXvb5Kp74/Oo0lr9oZ0hjwe/0fSLWsuN2kLg4d093bfQF6RZOl5UKTdUGsWD9lg7eN+bkxOHYitabTSU5Y/llLzOg3K1/+RC/nULvF+VO012SqP6eScvqeteXDdNp+71NapmwpnQfM2EM6H5mglnQvM1E86E5ut/Sfj331YvmCMGmeI/A8QmZpFisWUIvaF8OPUoYw7byXZRMYeaMuWNBohNJXMqLsezrczFcLFJSzTmCwLQ2I2UEjvR4A2fMylV8D+2uKysz4nTZd2P2AStSYkrhvPdRcISStv8jCmudtpPABeylkoek8U5rNSFDVyqLlXy4Mso4jIH67XP5mzIJMqOxpXLZSvVCSwJl1Aul4c8ojg3LhLWFwh8XYeaxI/Bxb+NcissA0p4kVJv34EEvDvxr5CRr3tQkEI3vHhLHxLefgQUR6p5QGtQARmD3yViUP0RanFDME7PEMT3RSVUvYKm1YslAHi3EQQrHfFqOtNNuoLSk7y7PIEdlNhL5apXBlQG5u38NkOF3X6iwxSiG/cAFrIwOwjBAzgUlaaH4ZdSkHgVMYeGouxsioqduR3p7gpRYhm33iXCQgkHR6gKsvxom6RjngUVioT2iw5fJu82JaBR7CFkmaSW4huCgYH3rsLM2RT5YPlUIGkbb5AswaFvLFHuYpSQ0I0Diu0mqKaYOv4LA9Vq9FMJeqXYmyjit1y8vPd8Tds0Obi1CNXt9eS7acLLDgFYUkZT7UYnKrsl5x6Lac6pqOh+Q7/zpjdUcb9K/nWAej//PqYIRkHfzP/kI0TNHv/G6Pp628dD2fF2HPrGLx4O9sP48RygxA4Ovl+Xg5jWnYTU5Rv+IbBlixsSN9/E1bZgU4vG1b7YVvEm7/BG5H8jDKrq/5RinwAAAABJRU5ErkJggg==";
				if (board.data.squares[j+board.data.columnsNumber*i].isFlag) img.src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAmVBMVEW5ubn8/Px1dXUAAAD+AAD///9vb2+xsbHAwMC9vb23vb7ReHdpaWkHBwe2traioqJ7e3uoqKi0wsLWdnaxw8DHl5flUE/sOjrPiIi8ra/1GhvwMjPRg4G2v77pSEjiXFvab3DKkpHCoKGDg4PApaT0JCbfYmP3BQWVdXbbAACGAQDOAQNsAQC5AgFPAAHvAQGgAQA2AAC4e3fXcfv7AAADeklEQVR4nO3daW+bQBgE4K13fUBSGrB7pC09kia9z///4wqJ43DsAgtY3nc089lBPIJoMspKVk+ws0zUqW/huFkm59jCAriAFpZAaOEdEFl4DwQW7oG4wgcgrPAARBU+AkGFFSCmsAqEFNaAiMI6EFDYAOIJm0A4YQuIJmwDwYQWIJbQBoQSWoFIQjsQSOgA4ghdwA7hUlZcQLdwuREV5QJ2CRMlKOvVCOGpb9orFFIYfiikMPxQSGH4oZDC8BOaMI7nvmJgwuzt2dyXDEqYX740Z3M/xJCEu60xyML8041BFsb5rTHIwuz5Z4MszJMXxiALs1fGIAvLikAWxtnWGGRhdnVjkIV5fGuagRI+VgSmMK5UBKSwVhGAwkZF4Al3W4cPRLhfEbDCw4pAFVorAkiY2ysCR7hzVASK0F0RGMLWikAT5q0VgSXsqQj5wr6KkC6MVV9FCBe6VgSKcFBFSBZ2rAgIYeeKABAOrwihQo+KECnMfSpConDAihAt9K0IacKBK0Kw8Or1eKAM4eU7dKHKPqILVRy/BxeWk37kL6MYYfGqfkAXFqX4BlxYnsNDF6o88S4OYcIRxSFOqHLP4pAnLE+U+BSHRGHxl7hHcYgUehWHUGHxGIcWh1RheXxmWHHIFQ4tDslCFecDikO0cFBxCBcOWBzihb3FIV/YtzgQhN2LA0LYWRwgwo7FgSJ0FweO0LU4gISO4oASWhcHltC2ONCE7eLAEzb/xwEoLB7jl6/gwuhCf4MXav0dXqh/wAu1/gkv1L9+owu1/gMv1H//RTNfPTShvqDQNxRSOEconBYKKZwjFE4LhRTOEQqnhUIK5wiF00IhhXOEwmmhkMI5QuG0UEjhHKFwWiik0DeRJZuacGP7iBzh+pkl6aoiXKW2j6ylCGvvo08mvLsUUkghhRRSSCGFFFKIK4xcXwN+PVJ47fxe8RMJU/3UkZFC5+XS0wijdCTEP2nv60shhRRSSCGFFFJIIYUUUkghhRSOFioK5Qtthw2Ok95bCeK0yXFDIYXhh0IKww+FFAafaIwwWUtK6i1cJucrUXEBXcIC6PwRYbELgYB2IRLQKoQC2oRYQIsQDNgWogFbQjhgU4gHbAgBgXUhIrAmhARWhZjAihAU+ChEBR6EsMAHIS5wLwQG3guRgXdCaGApxAYWQnDgQqEDFwoduPgPr9MYhw8NclcAAAAASUVORK5CYII=";
				if (board.data.squares[j+board.data.columnsNumber*i].isWrong) img.src = "https://pngimage.net/wp-content/uploads/2018/06/red-x-mark-png-5.png"; 				
				const square = new Path2D();
			    square.rect(x, y, squareSide, squareSide); 
			    ctx.strokeStyle = 'black';
			    ctx.stroke(square);
			    img.onload = function(){
					ctx.drawImage(img, x, y, squareSide, squareSide);
				}
			    ctx.fill(square);
				ctx.fillStyle = squareColor;
			    ctx.fill(square);
			    elementsList.push(square);  
			}
		}
	}
	
	/* function gameOver(){
		if(board.data.gameStatus == "LOST"){
			document.getElementById("gameOverMessage").style.display = "block";
			document.getElementById("gameOverMessage").innerHTML = "&#x1f6a9 OHHH..has perdido la partida <br> La duracion de la partida ha sido: "+document.getElementById("crono").innerHTML+"&#x1f4a3";
			//Paramos el cronometro de la vista cuando perdemos
			stop();
			document.getElementById("canvas").addEventListener("click", function(){
				if(confirm("OHHH HAS PERDIDO... Desea jugar otra partida?")){
					window.location.href = "http://localhost:8080/board/new?dificulty=2";
				}else{
					window.location.href = "http://localhost:8080/players/myprofile";
				};
			});
		}
		if(board.data.gameStatus == "WON"){
			document.getElementById("gameOverMessage").style.display = "block";
			document.getElementById("gameOverMessage").innerHTML = "&#x1f6a9 ENHORABUENA! Has completado el tablero<br> La duracion de la partida ha sido: "+document.getElementById("crono").innerHTML+"&#x1f4a3";
			stop();	
			document.getElementById("canvas").addEventListener("click", function(){
					if(confirm("ENHORABUENA HAS GANADO LA PARTIDA!! Deseas jugar otra?")){
						window.location.href = "http://localhost:8080/board/new?dificulty=2";
					}else{
						window.location.href = "http://localhost:8080/players/myprofile";
					};
			});
		}
	} */
	</script>
	
	</canvas>
	
	
</petclinic:layout>