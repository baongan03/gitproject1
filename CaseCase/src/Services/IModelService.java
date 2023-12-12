package Services;

import Models.Show;

import java.util.List;

public interface IModelService<T>{
    void create(T t);

    void create(Show show);

    List<T> getAll();
    abstract void update(T t);
    public void delete(long id);
    public long nextId();
    public T findById(long id);
}
