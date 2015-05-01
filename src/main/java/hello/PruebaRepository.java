package hello;

import hello.entities.Prueba;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Proyecto Hermes HR
 * User: paumedina
 * Date: 21/11/14
 * Time: 13:35
 */
public interface PruebaRepository extends CrudRepository<Prueba, Long> {

    @Query("SELECT pr FROM Candidato c JOIN c.puesto puesto JOIN puesto.pruebas pr WHERE c.username = ?1 AND NOT EXISTS (SELECT pa FROM PruebaAsignada pa WHERE pa.candidato.username = ?1 AND pa.prueba = pr)")
    List<Prueba> findPruebaCandidato(String username);
}
