<%@ attribute name="size" required="true" rtexprvalue="true" 
 description="Size of the piece to show" %>
 <%@ attribute name="casilla" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.casilla.Casilla"
 description="Casilla to be rendered" %>
 
 image = document.getElementById('${casilla.type}-${casilla.color}');
 ctx.drawImage(image,${casilla.getPositionXInPixels(size)},${casilla.getPositionYInPixels(size)},${size},${size});