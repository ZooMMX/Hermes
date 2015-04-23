package hello;

import hello.entities.Prueba;
import hello.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Proyecto Omoikane: SmartPOS 2.0
 * User: octavioruizcastillo
 * Date: 21/11/14
 * Time: 13:35
 */
public interface PruebaRepository extends CrudRepository<Prueba, Long> {

}
