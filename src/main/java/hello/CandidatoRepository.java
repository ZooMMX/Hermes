package hello;

import hello.entities.Candidato;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by octavioruiz on 26/04/15.
 */
public interface CandidatoRepository extends CrudRepository<Candidato, String> {
}
