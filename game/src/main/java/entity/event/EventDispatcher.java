package entity.event;

/**
 * Created by Ansh on 2017/2/28.
 */
public interface EventDispatcher {
    void addHandler(int eventId, EventHandler handler);

    void removeHandler(int eventId, EventHandler handler);

    void fireEvent(Event event);
}
