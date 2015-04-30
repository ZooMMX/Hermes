package hello.entities;

import javax.persistence.Entity;

import hello.CustomRevisionListener;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.DefaultTrackingModifiedEntitiesRevisionEntity;
import org.hibernate.envers.RevisionEntity;

/**
 * Proyecto Hermes HR
 * User: paumedina
 * Date: 21/11/14
 * Time: 19:17
 */

@Entity
@RevisionEntity(CustomRevisionListener.class)
public class Revision extends DefaultTrackingModifiedEntitiesRevisionEntity {

    private static final long serialVersionUID = 3775550420286576001L;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}