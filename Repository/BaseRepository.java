package Repository;
import java.util.HashMap;
import java.util.ArrayList;


public class BaseRepository {
}

abstract class BaseRepository<T, ID> {
    public HashMap<ID, T>;
    public ArrayList<T>;

    public <state>  T findById(ID id) {
        return findById(id);
    }
    public <state> T findAll() {
        return ;
    }

    public abstract void save(T entity);
    public abstract void getId(T entity);

}