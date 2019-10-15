<%-- 
    Document   : crear-ciudad
    Created on : 13/10/2019, 04:38:22 PM
    Author     : master
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <form id="frm-crearciudad">
      <div>
        <label>Código</label>
        <input type="text" name="codigo" class="codigo" placeholder="Código" required>
      </div>
      <div>
        <label>Ciudad</label>
        <input type="text" name="ciudad" class="nombre" placeholder="Nombre de la ciudad" required>
      </div>
      <div>
        <label>Páis</label>
        <input type="text" name="pais" class="pais" placeholder="Páis" required>
      </div>
      <div>
        <label>Clima</label>
        <input type="text" name="clima" class="clima" placeholder="Clima" required>
      </div>
      <div class="accionprincipal">
        <button type="button" class="btn-agregar">Agregar</button>
      </div>
    </form>

    <div>
      <select name="busqueda" class="busqueda">
        <option value="">Buscar por...</option>
        <option value="codigo">Código</option>
        <option value="ciudad">Ciudad</option>
        <option value="pais">País</option>
        <option value="clima">Clima</option>
      </select>
        
      <input type="text" class="txt-buscar" placeholder="Buscar" >
      <button type="button" class="btn-buscar">Buscar</button>
      <button type="button" class="btn-all">All</button>
    </div>
    
    <table>
      <thead>
        <tr>
          <th>CÓDIGO</th>
          <th>CIUDAD</th>
          <th>PAIS</th>
          <th>CLIMA</th>
          <th>FECHA DE CREACION</th>
          <th colspan="2">ACCIONES</th>
        </tr>
      </thead>
      <tbody class="listaciudades"></tbody>
    </table>
    <div>
      <span class="sinresultados"></span>
    </div>

    <script src="src/js/jquery-3.4.1.js" type="text/javascript"></script>
    <script src="src/js/script.js" type="text/javascript"></script>
  </body>
</html>
