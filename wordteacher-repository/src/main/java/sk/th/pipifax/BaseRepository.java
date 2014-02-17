package sk.th.pipifax;

import java.io.Serializable;

import java.io.Serializable;

import javax.persistence.EntityExistsException;

/**
 * Central Interface to provide a unified interface for all repositories. This
 * interface defines generic methods that can be used for any Entity to be
 * stored, removed and loaded from the underlying persistence technology.
 * Although this interface doesn't make any assumptions about the underlying
 * persistence technology, this interface assumes that the concrete persistence
 * technology does provide any form of change detection. Therefore there is not
 * dedicated update() method available inside this repository. The application
 * developer has to provide additional methods in the concrete sub-interface if
 * the underlying persistence technology doesn't support any automatic change
 * detection (e.g. plain JDBC)
 *
 * @param <T>
 *            the type handled by the repository service. This type must be
 *            known to the underlying persistence technology
 * @param <K>
 *            the primary key type for the repository service.
 * @since 1.0.0
 */
public interface BaseRepository<T, K extends Serializable> {

    /**
     * Finder method to load a object by it's primary key. The primary key Type
     * is derived by the type parameter K. This method returns null if the
     * object can't be found, so in contrast to
     * {@link #getById(java.io.Serializable)} the client must be prepared to
     * accept null return values. This is in particular useful if the client
     * doesn't know if the instance with the specified key does exist.
     *
     * @param id
     *            the primary key for the object to be found
     *
     * @return the object with the primary key or null if the object can't be
     *         found
     */
    T findById(K id);

    /**
     * Finder method to load a object by it's primary key. In contrast to
     * {@link #findById(java.io.Serializable)} this method throws a persistence
     * technology dependent exception if the object with the specified primary
     * does not exist. Clients should use this methods if they expect a object
     * to be available with the particular primary key. In all the other cases
     * they expect a exception to be thrown. The client should use the
     * {@link #findById(java.io.Serializable)} method if he doesn't know if the
     * particular object exists.
     *
     * @param id
     *            the primary key for the object to be found
     *
     * @return the object with the primary key or a
     * @link{java.lang.RuntimeException if the object can't be found
     */
    T getById(K id);

    /**
     * Deletes the particular instance from the repository. This method assumes
     * that object that is passed in as a parameter already exists in the
     * repository. It is up to the concrete persistence technology to remove the
     * particular object with all its dependent relations from the underlying
     * persistent store.
     *
     * @param entity
     *            that should be deleted
     */
    void remove(T entity);

    /**
     * Stores the particular instance into the repository. This method should be
     * called only once in the entire life cycle of the object and <b>NOT</b>
     * multiple times. It throws an <code>EntityExistsException</code> if the
     * particular object already exists inside the repository.
     *
     * @param entity
     *            the entity to be stored into the repository.
     * @throws javax.persistence.EntityExistsException
     *             if the particular object already exists inside the repository
     */
    void store(T entity);
}