package com.meby98.specification;

import com.meby98.utils.StringUtils;
import com.meby98.dto.DateOperatorQuery;
import com.meby98.specification.exceptions.DateOperatorWrongException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

import java.time.temporal.Temporal;
import java.util.List;

import static java.util.Objects.requireNonNull;

/*
 *
 * @E entity
 * @F filter
 * @K primary key
 *
 */
public abstract class BaseSpecification<E> {

    String UNACCENT_POSTGRESQL_FUNCTION = "unaccent";

    protected Specification<E> distinct() {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return criteriaBuilder.conjunction();
        };
    }

    protected <A> Specification<E> equals(final SingularAttribute<E, A> attribute, final A value) {
        return (root, query, builder) -> buildEqual(root.get(attribute), value, builder);
    }

    protected <A> Specification<E> equals(final String attribute, final A value) {
        return (root, query, builder) -> buildEqual(root.get(attribute), value, builder);
    }

    protected <A> Specification<E> equals(final Path<A> path, final A value) {
        return (root, query, builder) -> buildEqual(path, value, builder);
    }

    protected <A> Predicate buildEqual(final Path<A> path, final A value, final CriteriaBuilder builder) {
        return builder.equal(path, value);
    }

    protected <A> Specification<E> in(final SingularAttribute<E, A> attribute,
                                      final List<A> list) {
        return (root, query, builder) -> buildIn(root.get(attribute), list);
    }

    protected <A> Specification<E> in(final String attribute, final List<A> list) {
        return (root, query, builder) -> buildIn(root.get(attribute), list);
    }

    protected <A> Specification<E> in(final Path<A> path, final List<A> list) {
        return (root, query, builder) -> buildIn(path, list);
    }

    protected <A> Predicate buildIn(final Path<A> path, final List<A> list) {
        return path.in(list);
    }

    protected Specification<E> startWith(final SingularAttribute<E, String> attribute, final String value) {
        return (root, query, builder) -> buildStartWith(root.get(attribute), value, builder);
    }

    protected Specification<E> startWith(final String attribute, final String value) {
        return (root, query, builder) -> buildStartWith(root.get(attribute), value, builder);
    }

    protected Specification<E> startWith(final Path<String> path, final String value) {
        return (root, query, builder) -> buildStartWith(path, value, builder);
    }

    protected Predicate buildStartWith(final Path<String> path, final String value, final CriteriaBuilder builder) {
        return builder.like(path, value + "%");
    }

    protected Specification<E> endWith(final SingularAttribute<E, String> attribute, final String value) {
        return (root, query, builder) -> buildEndWith(root.get(attribute), value, builder);
    }

    protected Specification<E> endWith(final String attribute, final String value) {
        return (root, query, builder) -> buildEndWith(root.get(attribute), value, builder);
    }

    protected Specification<E> endWith(final Path<String> path, final String value) {
        return (root, query, builder) -> buildEndWith(path, value, builder);
    }

    protected Predicate buildEndWith(final Path<String> path, final String value, final CriteriaBuilder builder) {
        return builder.like(path, "%" + value);
    }

    protected Specification<E> contains(final SingularAttribute<E, String> attribute, final String value) {
        return (root, query, builder) -> buildContains(root.get(attribute), value, builder);
    }

    protected Specification<E> contains(final String attribute, final String value) {
        return (root, query, builder) -> buildContains(root.get(attribute), value, builder);
    }

    protected Specification<E> contains(final Path<String> path, final String value) {
        return (root, query, builder) -> buildContains(path, value, builder);
    }

    protected Predicate buildContains(final Path<String> path, final String value, final CriteriaBuilder builder) {
        return builder.like(path, "%" + value + "%");
    }

    protected Specification<E> contains(final String attribute, final String value, final boolean ignoreCaseSensitive,
                                        final boolean ignoreAccents) {
        if (ignoreCaseSensitive && ignoreAccents) {
            return (root, query, builder) -> buildLikeUnaccentAndLowerCase(root.get(attribute), value, builder);
        } else if (ignoreAccents) {
            return (root, query, builder) -> buildLikeUnaccent(root.get(attribute), value, builder);
        } else if (ignoreCaseSensitive) {
            return (root, query, builder) -> buildLikeLowerCase(root.get(attribute), value, builder);
        } else {
            return this.contains(attribute, value);
        }
    }

    protected Specification<E> contains(final SingularAttribute<E, String> attribute, final String value,
                                        final boolean ignoreCaseSensitive, final boolean ignoreAccents) {
        if (ignoreCaseSensitive && ignoreAccents) {
            return (root, query, builder) -> buildLikeUnaccentAndLowerCase(root.get(attribute), value, builder);
        } else if (ignoreAccents) {
            return (root, query, builder) -> buildLikeUnaccent(root.get(attribute), value, builder);
        } else if (ignoreCaseSensitive) {
            return (root, query, builder) -> buildLikeLowerCase(root.get(attribute), value, builder);
        } else {
            return this.contains(attribute, value);
        }
    }

    protected Specification<E> contains(final Path<String> path, final String value,
                                        final boolean ignoreCaseSensitive, final boolean ignoreAccents) {
        if (ignoreCaseSensitive && ignoreAccents) {
            return (root, query, builder) -> buildLikeUnaccentAndLowerCase(path, value, builder);
        } else if (ignoreAccents) {
            return (root, query, builder) -> buildLikeUnaccent(path, value, builder);
        } else if (ignoreCaseSensitive) {
            return (root, query, builder) -> buildLikeLowerCase(path, value, builder);
        } else {
            return this.contains(path, "%" + value + "%");
        }
    }

    protected Predicate buildLikeLowerCase(final Path<String> path, final String value,
                                           final CriteriaBuilder builder) {
        return builder.like(builder.lower(path), "%" + value.toLowerCase() + "%");
    }

    protected Predicate buildLikeUnaccent(final Path<String> path, final String value,
                                          final CriteriaBuilder builder) {
        return builder.like(builder.function(UNACCENT_POSTGRESQL_FUNCTION, String.class
                , path), "%" + StringUtils.removeAccents(value) + "%");
    }

    protected Predicate buildLikeUnaccentAndLowerCase(final Path<String> path, final String value,
                                                      final CriteriaBuilder builder) {
        return builder.like(builder.function(UNACCENT_POSTGRESQL_FUNCTION, String.class
                , builder.lower(path)), "%" + requireNonNull(StringUtils.removeAccents(value)).toLowerCase() + "%");
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> before(final SingularAttribute<E, A> attribute,
                                                                                   final A date) {
        return (root, query, builder) -> buildBefore(root.get(attribute), date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> before(final String attribute,
                                                                                   final A date) {
        return (root, query, builder) -> buildBefore(root.get(attribute), date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> before(final Path<A> path,
                                                                                   final A date) {
        return (root, query, builder) -> buildBefore(path, date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Predicate buildBefore(final Path<A> path, final A date,
                                                                                 final CriteriaBuilder builder) {
        return builder.lessThan(path, date);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> equalsAndBefore(final SingularAttribute<E
            , A> attribute,
                                                                                            final A date) {
        return (root, query, builder) -> buildEqualsAndBefore(root.get(attribute), date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> equalsAndBefore(final String attribute,
                                                                                            final A date) {
        return (root, query, builder) -> buildEqualsAndBefore(root.get(attribute), date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> equalsAndBefore(final Path<A> path,
                                                                                            final A date) {
        return (root, query, builder) -> buildEqualsAndBefore(path, date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Predicate buildEqualsAndBefore(final Path<A> path,
                                                                                          final A date,
                                                                                          final CriteriaBuilder builder) {
        return builder.lessThanOrEqualTo(path, date);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> after(final SingularAttribute<E, A> attribute,
                                                                                  final A date) {
        return (root, query, builder) -> buildAfter(root.get(attribute), date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> after(final String attribute,
                                                                                  final A date) {
        return (root, query, builder) -> buildAfter(root.get(attribute), date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> after(final Path<A> path,
                                                                                  final A date) {
        return (root, query, builder) -> buildAfter(path, date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Predicate buildAfter(final Path<A> path, final A date,
                                                                                final CriteriaBuilder builder) {
        return builder.greaterThan(path, date);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> equalsAndAfter(final SingularAttribute<E,
            A> attribute,
                                                                                           final A date) {
        return (root, query, builder) -> buildEqualsAndAfter(root.get(attribute), date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> equalsAndAfter(final String attribute,
                                                                                           final A date) {
        return (root, query, builder) -> buildEqualsAndAfter(root.get(attribute), date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> equalsAndAfter(final Path<A> path,
                                                                                           final A date) {
        return (root, query, builder) -> buildEqualsAndAfter(path, date, builder);
    }

    protected <A extends Temporal & Comparable<? super A>> Predicate buildEqualsAndAfter(final Path<A> path,
                                                                                         final A date,
                                                                                         final CriteriaBuilder builder) {
        return builder.greaterThanOrEqualTo(path, date);
    }

    protected <A extends Temporal & Comparable<? super A>> Specification<E> dateOperatorSpec(final SingularAttribute<E, A> attribute,
                                                                                             final DateOperatorQuery<A> dateOperatorQuery) {
        switch (dateOperatorQuery.getDateOperator()) {
            case LET -> {
                return this.equalsAndBefore(attribute, dateOperatorQuery.getDate());
            }
            case GT -> {
                return this.after(attribute, dateOperatorQuery.getDate());
            }
            case GET -> {
                return this.equalsAndAfter(attribute, dateOperatorQuery.getDate());
            }
            case LT -> {
                return this.before(attribute, dateOperatorQuery.getDate());
            }
            case E -> {
                return this.equals(attribute, dateOperatorQuery.getDate());
            }
            default ->
                    throw new DateOperatorWrongException("DATE OPERATOR ENUM WRONG OR METHOD NOT IMPLEMENTED YET: '" +
                            dateOperatorQuery.getDateOperator().getValue() + "' IN ATTRIBUTE: '" + attribute.getName());
        }
    }

    protected <A extends Temporal & Comparable<? super A>> Predicate getDateOperatorPredicate(final Path<A> attribute,
                                                                                              final DateOperatorQuery<A> dateOperatorQuery,
                                                                                              CriteriaBuilder cb) {
        return switch (dateOperatorQuery.getDateOperator()) {
            case LET -> cb.lessThanOrEqualTo(attribute, dateOperatorQuery.getDate());
            case GT -> cb.greaterThan(attribute, dateOperatorQuery.getDate());
            case GET -> cb.greaterThanOrEqualTo(attribute, dateOperatorQuery.getDate());
            case LT -> cb.lessThan(attribute, dateOperatorQuery.getDate());
            case E -> cb.equal(attribute, dateOperatorQuery.getDate());
        };
    }

    protected <T> Path<T> getObjectPath(final Path<?> root, final String field) {
        final var path = field.split("\\.");

        Path<T> property = null;
        for (String s : path) {
            if (property == null) {
                property = root.get(s);
            } else {
                property = property.get(s);
            }
        }
        return property;
    }
}
