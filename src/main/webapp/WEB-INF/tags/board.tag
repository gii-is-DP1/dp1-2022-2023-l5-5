<%@ attribute name="board" required="false" rtexprvalue="true" type="org.springframework.samples.minesweeper.Board"
 description="Board to be rendered" %>
<canvas id="canvas" width="${board.width}" height="${board.height}"></canvas>
<img id="source" src="${board.background}" style="display:none">
<img id="FLAG" src="resources/images/flag.png" style="display:none">
<img id="COVERED" src="resources/images/covered_square.png" style="display:none">
<img id="UNCOVERED" src="resources/images/uncovered_square.png" style="display:none">
<img id="MINE" src="resources/images/mine.png" style="display:none">
<!-- <img id="MINE-PRESSED" src="resources/images/boom-pressed.png" style="display:none">
<img id="MINE-GUESSED" src="resources/images/boom-guessed.png" style="display:none"> -->
<img id="ONE" src="resources/images/one.png" style="display:none">
<img id="TWO" src="resources/images/two.png" style="display:none">
<img id="THREE" src="resources/images/three.png" style="display:none">
<img id="FOUR" src="resources/images/four.png" style="display:none">
<img id="FIVE" src="resources/images/five.png" style="display:none">
<img id="SIX" src="resources/images/six.png" style="display:none">
<img id="SEVEN" src="resources/images/seven.png" style="display:none">
<img id="HEIGHT" src="resources/images/height.png" style="display:none">


<script>
var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var image = document.getElementById('source');
ctx.drawImage(image, 0, 0, ${board.width}, ${board.height});
</script>