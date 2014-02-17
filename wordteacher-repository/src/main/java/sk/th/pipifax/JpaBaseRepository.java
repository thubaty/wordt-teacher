package sk.th.pipifax;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * A JPA based implementation of the {@link BaseRepository} interface,
 * implementing all method with the underlying EntityManager. This class doesn't
 * make any assumptions about the type of the entity and even the type for the
 * key itself. It is up to the implementation to specify the entity and the key
 * as the type variables of this class
 *
 * @param <T>
 *            the type handled by the repository service. This type must be
 *            known to the underlying persistence technology.
 * @param <K>
 *            the primary key type for the repository service.
 * @since 1.0.0
 */
public abstract class JpaBaseRepository<T, K extends Serializable> implements
        BaseRepository<T, K> {

    /**
     * The transaction scoped entity manager, handled outside this class by
     * frameworks (Spring) or containers (EJB)
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * The entity class that will be handled by this repository
     */
    private final Class<T> entityClass;

    /**
     * Default constructor that gets the entity class through the first type
     * parameter of the concrete class.
     */
    public JpaBaseRepository() {
        this(null);
    }

    /**
     * Alternative constructor that can be used by subclasses to specify the
     * entity class instead of using type parameters.
     *
     * @param entityClass
     *            - The class object that specifies the entity type that will be
     *            handled by this application. Falls back to the default
     *            constructor semantics if the parameter is null.
     */
    public JpaBaseRepository(Class<T> entityClass) {
        this.entityClass = (entityClass != null) ? entityClass
                : determineEntityClass();
    }

    /**
     * Loads an entity through the {@link javax.persistence.EntityManager}s find
     * method using the primary key as the id and the entity class as the
     * expected type.
     *
     * @param id
     *            The primary key for the object to be found
     *
     * @return the object or null if not available with the particular primary
     *         key.
     */
    @Override
    public T findById(K id) {
        return getCurrentEntityManager().find(this.entityClass, id);
    }

    /**
     * Loads an entity through delegation to the
     * {@link #findById(java.io.Serializable)} method, but throws an
     * {@link javax.persistence.EntityNotFoundException} in case of a null
     * return value from the underlying {@link javax.persistence.EntityManager}.
     *
     * @param id
     *            The primary key for the object to be found
     *
     * @return the object
     *
     * @throws javax.persistence.EntityNotFoundException
     *             if the object doesn't exist with the particular primary key.
     */
    @Override
    public T getById(K id) {
        T result = findById(id);
        if (result == null) {
            throw new EntityNotFoundException("No Entity found for id:'" + id
                    + "'");
        }
        return result;
    }

    /**
     * Removes the object by calling the remove method on the
     * {@link javax.persistence.EntityManager}
     *
     * @param entity
     *            that should be deleted
     */
    @Override
    public void remove(T entity) {
        getCurrentEntityManager().remove(entity);
    }

    /**
     * Stores the object by calling the persist method on the
     * {@link javax.persistence.EntityManager}.
     *
     * @param entity
     *            the entity to be stored into the repository
     */
    @Override
    public void store(T entity) {
        // TODO adgy why we shouldn't allow this? JPA already makes sure that a
        // second instance of an entity cannot be passed.
        if (getCurrentEntityManager().contains(entity)) {
            throw new EntityExistsException("Entity '" + entity
                    + "' already stored in repository");
        }
        getCurrentEntityManager().persist(entity);
    }

    /**
     * @return the currently active entity manager
     */
    protected EntityManager getCurrentEntityManager() {
        return entityManager;
    }

    /**
     * Helper method to retrieve the single result of the query in a type-safe
     * way. If the result does not contain any item, <code>null</code> is
     * returned.
     *
     * @param query
     *            the query to retrieve the single result from
     *
     * @return the single result of the query, or null, if the query does not
     *         contain any item in the result
     *
     * @throws javax.persistence.NonUniqueResultException
     *             if the query contains more than one item in the result
     */
    protected T getSingleResult(TypedQuery<T> query) {
        T result;

        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }

        return result;
    }

    /**
     * Helper method to retrieve query result list in type-safe way.
     *
     * @param query
     *            the query to retrieve the result list from
     *
     * @return the query result list
     * @deprecated since 2.0: Directly use {@link TypedQuery#getResultList()}.
     */
    @Deprecated
    protected List<T> getResultList(TypedQuery<T> query) {
        return query.getResultList();
    }

    /**
     * Helper method to retrieve the single result of the query in a type-safe
     * way. If the result does not contain any item, <code>null</code> is
     * returned.
     *
     * @param query
     *            the query to retrieve the single result from
     *
     * @return the single result of the query, or null, if the query does not
     *         contain any item in the result
     *
     * @throws NonUniqueResultException
     *             if the query contains more than one item in the result
     * @deprecated since 2.0: Use {@link #getSingleResult(TypedQuery)}.
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    protected T getSingleResult(Query query) {
        List<?> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null;
        }

        if (resultList.size() > 1) {
            throw new NonUniqueResultException("Result for query '" + query
                    + "' must contain exactly one item");
        }

        Object item = resultList.get(0);

        return (T) item;
    }

    /**
     * Internal, helper method returning a pattern for the given search term
     * which might include <code>'*'</code>, replaced by <code>'%'</code>
     * characters.
     *
     * @param searchParam
     *            the search term to turn into a valid search pattern
     *
     * @return the SQL search pattern or <code>'%'</code>, if the given
     *         parameter is <code>null</code>
     */
    protected String createSearchPattern(String searchParam) {
        if (searchParam != null) {
            return "%" + searchParam.replace('*', '%') + "%";
        } else {
            return "%";
        }
    }

    /**
     * Creates a typed query using this repository's entity class.
     *
     * @param query
     *            (mandatory).
     * @return Never null.
     * @since 2.0
     */
    protected TypedQuery<T> createTypedQuery(String query) {
        return getCurrentEntityManager().createQuery(query, entityClass);
    }

    /**
     * Determines the Type Parameter for the particular BaseRepository instance.
     * This method assumes that the concrete class is not a raw-type and defines
     * the actual type parameters.
     *
     * @return The first type paramter for the particular class
     */
    @SuppressWarnings("unchecked")
    private Class<T> determineEntityClass() {
        if (!(getClass().getGenericSuperclass() instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                    "Class: "
                            + getClass().getName()
                            + " must be Parameterized. Please "
                            + "define TypeArgument while extending JpaBaseRepository (eg. MyRepo extends "
                            + "JpaBaseRepository<EntityClass>)");
        }

        ParameterizedType type = (ParameterizedType) getClass()
                .getGenericSuperclass();
        if (type.getActualTypeArguments().length == 0) {
            throw new IllegalArgumentException("No Type Parameter define for: "
                    + type);
        }
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}
