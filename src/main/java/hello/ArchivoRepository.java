package hello;

import hello.entities.Archivo;
import org.springframework.data.repository.CrudRepository;

/**
 * Proyecto Hermes HR
 * User: paumedina
 * Date: 30/11/14
 * Time: 20:40
 */
public interface ArchivoRepository extends CrudRepository<Archivo, Long> {
}
