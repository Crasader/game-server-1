package entity.event;

/**
 * Created by Ansh on 2017/2/28.
 */
public interface Event<T> {
    int getId();

    void setId(int id);

    T getData();

    void setData(T data);

    boolean isEvent(int id);
}
