package com.sinensia.donpollo.integration.model;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="PRODUCTOS")
public class ProductoPL {

	@Id
	@Column(name="CODIGO")
	@GeneratedValue(generator = "PRODUCTO_SEQ")
	private Long id;
	
	private String nombre;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	private double precio;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="ID_FAMILIA")
	private FamiliaPL familia;
	
	private boolean descatalogado;
	
	public ProductoPL() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public FamiliaPL getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaPL familia) {
		this.familia = familia;
	}

	public boolean isDescatalogado() {
		return descatalogado;
	}

	public void setDescatalogado(boolean descatalogado) {
		this.descatalogado = descatalogado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoPL other = (ProductoPL) obj;
		return Objects.equals(id, other.id);
	}

}
