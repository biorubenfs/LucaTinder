package com.lucatinder.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="JUNTOS")
public class Juntos {

	private int id;
	private int id_perfil1;
	private int id_perfil2;
	
	@Id
	@GeneratedValue
	@Column(name = "ID_MATCH")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_perfil1() {
		return id_perfil1;
	}
	public void setId_perfil1(int id_perfil1) {
		this.id_perfil1 = id_perfil1;
	}
	public int getId_perfil2() {
		return id_perfil2;
	}
	public void setId_perfil2(int id_perfil2) {
		this.id_perfil2 = id_perfil2;
	}
	@Override
	public String toString() {
		return "Match [id=" + id + ", id_perfil1=" + id_perfil1 + ", id_perfil2=" + id_perfil2 + "]";
	}
		
}
