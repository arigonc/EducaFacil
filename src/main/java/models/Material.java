package models;

import java.util.Date;

public class Material {
	private int id_material;
	private String material;
	private Date cadastro;
	private int id_aula;

	public Material(int id_material, String material, Date cadastro, int id_aula) {
		super();
		this.id_material = id_material;
		this.material = material;
		this.cadastro = cadastro;
		this.id_aula = id_aula;
	}

	public int getId_material() {
		return id_material;
	}

	public void setId_material(int id_material) {
		this.id_material = id_material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public int getId_aula() {
		return id_aula;
	}

	public void setId_aula(int id_aula) {
		this.id_aula = id_aula;
	}

}
