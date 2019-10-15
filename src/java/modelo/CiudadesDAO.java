/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author master
 */
public class CiudadesDAO {

  private boolean opracion = false;

  CiudadesVO ciuVO = null;
  ArrayList<CiudadesVO> arrCiuVO;

  final String NAMEDIR = "reportes";
  final String PATHDIR = "/home/master/NetBeansProjects/CrudArchivoPlano/web/src/" + NAMEDIR;
  final String NAMEFILE = "ciudades.txt";
  final String PATHFILE = PATHDIR + File.separator + NAMEFILE;

  FileReader fr = null;
  PrintWriter pw = null;
  
  String codigo;

  public CiudadesDAO() {
  }

  public boolean agregarCiudad(CiudadesVO ciuVO, String accion) {
    try {
      codigo = ciuVO.getCodigo();
      readData(accion);
      pw = new PrintWriter(PATHFILE);

      if (arrCiuVO.size() == 0) {
        if (ciuVO.getCiudad() != null) {
          pw.print(ciuVO.getCodigo() + ",");
          pw.print(ciuVO.getCiudad() + ",");
          pw.print(ciuVO.getPais() + ",");
          pw.print(ciuVO.getClima() + ",");
          pw.println(ciuVO.getFechaCreacion());
        }

      } else {
        if (ciuVO.getCiudad() != null) {
          arrCiuVO.add(ciuVO);
        }

        for (CiudadesVO c : arrCiuVO) {
          pw.print(c.getCodigo() + ",");
          pw.print(c.getCiudad() + ",");
          pw.print(c.getPais() + ",");
          pw.print(c.getClima() + ",");
          pw.println(c.getFechaCreacion());
        }
        pw.flush();
      }

      opracion = true;
    } catch (FileNotFoundException ex) {
      Logger.getLogger(CiudadesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      pw.close();
    }
    return opracion;
  }

  public ArrayList<CiudadesVO> buscarCiudad(ArrayList<CiudadesVO> arrCiu, String cadena, String opcion) {
    ArrayList<CiudadesVO> listCiud = new ArrayList<>();
    cadena = cadena.toUpperCase();
    for (CiudadesVO c : arrCiu) {
      switch (opcion) {
        case "codigo":
          if (c.getCodigo().toUpperCase().contains(cadena)) {
            listCiud.add(c);
          }
          break;
        case "ciudad":
          if (c.getCiudad().toUpperCase().contains(cadena)) {
            listCiud.add(c);
          }
          break;
        case "pais":
          if (c.getPais().toUpperCase().contains(cadena)) {
            listCiud.add(c);
          }
          break;
        case "clima":
          if (c.getClima().toUpperCase().contains(cadena)) {
            listCiud.add(c);
          }
          break;

      }

    }

    return listCiud;
  }

  public ArrayList<CiudadesVO> readData(String accion) {
    arrCiuVO = new ArrayList<>();

    try {
      File dir = new File(PATHDIR);

      if (!dir.exists()) {
        dir.mkdir();
      }

      File file = new File(PATHFILE);

      if (file.exists()) {
        fr = new FileReader(PATHFILE);
        BufferedReader br = new BufferedReader(fr);
        String cadena = br.readLine();
        String linea[];

        while (cadena != null) {
          linea = cadena.split(",");
          ciuVO = new CiudadesVO();

          ciuVO.setCodigo(linea[0]);
          ciuVO.setCiudad(linea[1]);
          ciuVO.setPais(linea[2]);
          ciuVO.setClima(linea[3]);
          ciuVO.setFechaCreacion(linea[4]);

          arrCiuVO.add(ciuVO);
          cadena = br.readLine();
        }
        if (accion.equalsIgnoreCase("actualizar")) {
          for (int i = 0; i < arrCiuVO.size(); i++) {
            if (arrCiuVO.get(i).getCodigo().equals(codigo)) {
              arrCiuVO.remove(i);
            }
          }
        } else if (accion.equalsIgnoreCase("eliminar")) {
          for (int i = 0; i < arrCiuVO.size(); i++) {
            if (arrCiuVO.get(i).getCodigo().equals(codigo)) {
              arrCiuVO.remove(i);
            }
          }
        }

      }

    } catch (FileNotFoundException ex) {
      Logger.getLogger(CiudadesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(CiudadesDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if (fr != null) {
          fr.close();
        }
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
    return arrCiuVO;
  }
}
