package com.meby98.date;

import org.apache.commons.lang3.tuple.Pair;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class TemporalItems<E extends TemporalItem<T>, T extends Temporal & Comparable<? super T>> {

    private final List<E> temporalItems;

    public TemporalItems() {
        this.temporalItems = new ArrayList<>();
    }

    public TemporalItems(final List<E> temporalItems) {
        this.temporalItems = temporalItems;
    }

    public int size() {
        return temporalItems.size();
    }

    public boolean isEmpty() {
        return temporalItems.isEmpty();
    }

    public Stream<E> stream() {
        return temporalItems.stream();
    }

    public List<E> getItems() {
        return List.copyOf(temporalItems);
    }

    public List<Pair<T, T>> getDatesAsPair() {
        return getItemsSortedByStartDate().stream()
                .map(item -> Pair.of(item.getStartDate(), item.getEndDate()))
                .toList();
    }

    public List<E> getItemsSortedByStartDate() {
        return temporalItems.stream()
                .sorted(Comparator.comparing(TemporalItem::getStartDate))
                .toList();
    }

    public Optional<E> getActiveItem() {
        return temporalItems.stream()
                .filter(TemporalItem::isActive)
                .findFirst();
    }

    public Optional<E> getActiveItemAtDate(final T date) {
        return temporalItems.stream()
                .filter(item -> item.isActiveAtDate(date))
                .findFirst();
    }

    public List<E> getOldItems() {
        return temporalItems.stream()
                .filter(TemporalItem::isOld)
                .sorted(Comparator.comparing(TemporalItem::getStartDate))
                .toList();
    }

    public Optional<T> getLowestStartDate() {
        return temporalItems.stream()
                .map(TemporalItem::getStartDate)
                .min(Comparator.naturalOrder());
    }

    public Optional<T> getGreatestEndDate() {
        return temporalItems.stream()
                .map(TemporalItem::getEndDate)
                .max(Comparator.naturalOrder());
    }
}
