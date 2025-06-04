package com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sinensia.hexagonal.infrastrucure.adapter.out.jpa.entities.ProductoPL;

public interface ProductoPLRepository extends JpaRepository<ProductoPL, Long> {

	List<ProductoPL> findByFamiliaId(Long idFamilia);
	
	List<ProductoPL> findByPrecioBetweenOrderByPrecio(double min, double max);
	
	List<ProductoPL> findByDescatalogadoTrue();
	
	List<ProductoPL> findByFechaAltaBetweenOrderByFechaAlta(Date desde, Date hasta);
	
	// ***************************************************************************

	@Query("""
			SELECT UPPER(CONCAT(p.nombre, ' [',  p.familia.nombre, ']')), p.precio
				FROM ProductoPL p
			
			""")
	List<Object[]> getDTO1();
	
	// ***************************************************************************
	
	@Query("""	
			SELECT 
			 	UPPER(p.nombre), 
			 	CASE
			 		WHEN p.descripcion IS NULL THEN ''
			 		ELSE
			 			CASE 
			     			WHEN LENGTH(p.descripcion) < 25 THEN p.descripcion 
			     			ELSE CONCAT(SUBSTRING(p.descripcion, 1, 22), '...') 
			 			END
			 	END		
				FROM ProductoPL p
			
			""")
	List<Object[]> getDTO2();

	// ***************************************************************************	
	
	@Modifying
	@Query("""
			UPDATE ProductoPL p
			   SET p.precio = p.precio + (p.precio * :incremental) / 100 
			WHERE p.familia = :familia
			""")
	
	void updatePrecios(Long[] productos, double incremental);

	// ***************************************************************************
	
	@Query("""	
			SELECT f, COUNT(p) 
			FROM FamiliaPL f LEFT JOIN ProductoPL p ON p.familia = f
			GROUP BY f	
		    """)
	List<Object[]> getEstadisticaNumeroProductosByFamilia();
	
	// ***************************************************************************
	
	@Query("""
			SELECT f, ROUND(AVG(p.precio), 2)
			FROM FamiliaPL f LEFT JOIN ProductoPL p ON p.familia = f
			GROUP BY f
			""")
	List<Object[]> getEstadisticaPrecioMedioProductosByFamilia();

	// ***************************************************************************
	
	@Modifying
	@Query(""" 
			UPDATE ProductoPL p
			   SET p.descatalogado = true
			 WHERE p.id = :idProducto
			""")
	void descatalogarProducto(Long idProducto);

}
