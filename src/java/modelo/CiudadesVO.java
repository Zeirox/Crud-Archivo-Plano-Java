/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author master
 */
public class CiudadesVO {

  private String codigo;
  private String ciudad;
  private String pais;
  private String clima;
  private String fechaCreacion;

  public CiudadesVO() {
  }

  public CiudadesVO(String codigo, String ciudad, String pais, String clima, String fechaCreacion) {
    this.codigo = codigo;
    this.ciudad = ciudad;
    this.pais = pais;
    this.clima = clima;
    this.fechaCreacion = fechaCreacion;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getCiudad() {
    return ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getClima() {
    return clima;
  }

  public void setClima(String clima) {
    this.clima = clima;
  }

  public String getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(String fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

}
