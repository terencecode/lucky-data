package com.isep.lucky_data.utils.converter;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * One way converter used to create an object from another one.
 *
 * @param <D> DTO representation's type
 * @param <E> Domain representation's type
 */
public class OneWayConverter<D, E> {

    private final Function<E, D> fromEntity;

    /**
     * Create a new converter instance.
     *
     * @param fromEntity Function that converts given domain entity into the dto
     *                   entity.
     */
    public OneWayConverter(final Function<E, D> fromEntity) {
        this.fromEntity = fromEntity;
    }

    /**
     * Convert specified domain entity to corresponding dto entity.
     *
     * @param entity domain entity
     * @return The DTO representation - the result of the converting function
     * application on domain entity.
     */
    public D convertFromEntity(final E entity) {
        return fromEntity.apply(entity);
    }

    /**
     * Convert specified domain entities to corresponding dto entities.
     *
     * @param entities collection of domain entities
     * @return List of domain representation of provided entities retrieved by
     * mapping each of them with the conversion function
     */
    public Collection<D> createFromEntities(final Collection<E> entities) {
        return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }

}
