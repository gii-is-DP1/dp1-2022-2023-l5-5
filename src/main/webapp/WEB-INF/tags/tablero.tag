<%@ attribute name="tablero" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.tablero.Tablero"
 description="Tablero to be rendered" %>
 <%@ attribute name="tableroImage" required="false" rtexprvalue="true" 
 description="Background image to be rendered" %>
<canvas id="canvas" filas="${tablero.anchuraTotal}" columnas="${tablero.alturaTotal}" width="${tablero.widthTotal}" height="${tablero.heightTotal}"></canvas>
<img id="source" src="/${tableroImage}" style="display:none">
<img id="bandera" src="/resources/images/banderaBuscaminas.png" style="display:none">
<img id="bomba" src="/resources/images/bombaBuscaminas.jpg" style="display:none">
<script>
function drawTablero(){ 
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var image = document.getElementById('source');
    ctx.drawImage(image, 0, 0, ${tablero.anchuraTotal}, ${tablero.alturaTotal});     
    <jsp:doBody/>
}
window.onload = drawTablero();
</script>