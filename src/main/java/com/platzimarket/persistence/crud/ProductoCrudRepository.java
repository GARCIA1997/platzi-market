package com.platzimarket.persistence.crud;

import com.platzimarket.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//Extendemos del crud y se otorga el nombre  de la tabla y el tipo de id
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    //Si quiero hacer una consulta normal  se utiliza el @query de la siguiente forma de lo contrario solo se deja lo de abajo
    //@Query (value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock , boolean estado);


}
