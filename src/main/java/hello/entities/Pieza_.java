package hello.entities;

import hello.TipoPieza;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Date;

/**
 * Proyecto Hermes HR
 * User: paumedina
 * Date: 05/12/14
 * Time: 19:56
 */
@javax.persistence.metamodel.StaticMetamodel(Pieza.class)
public class Pieza_ {
    public static volatile SingularAttribute<Pieza,Long> id;
    public static volatile SingularAttribute<Pieza,String> descripcion;
    public static volatile SingularAttribute<Pieza, TipoPieza> tipoPieza;
    public static volatile SingularAttribute<Pieza,String> nombreSap;
    public static volatile SingularAttribute<Pieza,String> universalCode;
    public static volatile SingularAttribute<Pieza,String> cliente;
    public static volatile SingularAttribute<Pieza, Long> workOrderNo;
    public static volatile SingularAttribute<Pieza,Date> workOrderDate;
    public static volatile SingularAttribute<Pieza,Boolean> enabled;
}
