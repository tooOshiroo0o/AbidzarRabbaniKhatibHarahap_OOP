package Repository;

import java.util.*;

public abstract class BaseRepository<T, ID> {
    protected Map<ID, T> map = new HashMap<>();
    protected List<T> list = new ArrayList<>();

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(map.get(id));
    }

    public List<T> findAll() {
        // return defensive copy
        return List.copyOf(list);
    }

    public abstract void save(T entity);

    protected abstract ID getId(T entity);
}
