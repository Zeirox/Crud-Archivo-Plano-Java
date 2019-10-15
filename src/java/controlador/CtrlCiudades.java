/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CiudadesDAO;
import modelo.CiudadesVO;

/**
 *
 * @author master
 */
@WebServlet(name = "CtrlCiudades", urlPatterns = {"/ciudades/agregar", "/ciudades/buscar", "/ciudades/actualizar", "/ciudades/eliminar"})
public class CtrlCiudades extends HttpServlet {

  Gson json = new Gson();
  CiudadesVO ciuVO = new CiudadesVO();
  CiudadesDAO ciuDAO = new CiudadesDAO();
  ArrayList<CiudadesVO> arrCiuVO = new ArrayList<>();

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String path = request.getServletPath();
    arrCiuVO = ciuDAO.readData("");
    boolean trig = false;

    switch (path) {

      case "/ciudades/agregar":
        ciuVO = setDataCiudad(request);

        for (CiudadesVO c : arrCiuVO) {
          if (ciuVO.getCiudad().equals(c.getCiudad())) {
            out.print("La ciudad con el nombre " + c.getCiudad() + " ya existe");
            trig = true;
          }
        }

        if (!trig) {
          if (ciuDAO.agregarCiudad(ciuVO, "agregar")) {
            out.print("¡Ciudad agregada con exito!");
          } else {
            out.print("Error al agregar la ciudad");
          }
        }
        break;
      case "/ciudades/buscar":
        String cadena = request.getParameter("buscar");
        String opcion = request.getParameter("opcion");
        
        if (!cadena.isEmpty()) {
          arrCiuVO = ciuDAO.buscarCiudad(arrCiuVO, cadena, opcion);
        }

        if (arrCiuVO.size() == 0) {
          out.print(false);
        } else {
          out.print(json.toJson(arrCiuVO));
        }

        break;
      case "/ciudades/actualizar":
        ciuVO = new CiudadesVO();
        ciuVO = setDataCiudad(request);
        if (ciuDAO.agregarCiudad(ciuVO, "actualizar")) {
          out.print("¡Registro actualizado con exito!");
        }
        break;
      case "/ciudades/eliminar":
        ciuVO = new CiudadesVO();
        ciuVO.setCodigo(request.getParameter("codDelete"));
        
        if (ciuDAO.agregarCiudad(ciuVO, "eliminar")) {
          out.print("¡Registro eliminado con exito!");
        }
        break;
    }
  }

  public CiudadesVO setDataCiudad(HttpServletRequest request) {

    Format format = new SimpleDateFormat("yyyy-mm-dd");
    String today = format.format(new Date());

    ciuVO.setCodigo(request.getParameter("codigo").toUpperCase());
    ciuVO.setCiudad(request.getParameter("ciudad"));
    ciuVO.setPais(request.getParameter("pais"));
    ciuVO.setClima(request.getParameter("clima"));
    ciuVO.setFechaCreacion(today);

    return ciuVO;

  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
