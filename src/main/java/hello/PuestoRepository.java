package hello;

import hello.entities.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by octavioruiz on 23/04/15.
 */
public interface PuestoRepository extends JpaRepository<Puesto,Long> {

}