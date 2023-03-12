package org.project.db.entity;

import java.io.Serializable;

/**

 The abstract base class for all entities in the system.

 An entity is a business object that has an identity and can be
 persisted to a database.

 <p>This class implements the {@code Serializable} interface, which
 allows instances of this class to be serialized and deserialized.</p>
 */
public abstract class Entity implements Serializable {
    /**

     The serial version UID for this class.
     */
    private static final long serialVersionUID = 8466257860808346236L;
    /**

     The unique identifier of this entity.
     */
    private int id;
    /**

     Gets the unique identifier of this entity.
     @return the id of this entity
     */
    public int getId() {
        return id;
    }
    /**

     Sets the unique identifier of this entity.
     @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
