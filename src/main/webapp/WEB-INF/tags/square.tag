 <%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the square to show" %>
 <%@ attribute name="square" required="true" rtexprvalue="true" type="org.springframework.samples.minesweeper.square.Square"
 description="Square to be rendered" %>
 <script>
 var canvas = document.getElementById("canvas");
 var ctx = canvas.getContext("2d");
 var image = document.getElementById('${square.type}');
 ctx.drawImage(image,${square.getPositionYInPixels(size)},${square.getPositionXInPixels(size)},${size},${size});
 </script>
