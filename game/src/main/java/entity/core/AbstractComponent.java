package entity.core;

import entity.event.Event;

/**
 * Created by Ansh on 2017/2/28.
 */
public class AbstractComponent implements Component {
    private Entity owner;

    @Override
    public void setParent(Entity owner) {
        this.owner = owner;
    }

    @Override
    public Entity getParent() {
        return this.owner;
    }

    @Override
    public void initializer() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onEvent(Event event) {

    }

    protected void sendEvent(Event event) {
        this.owner.fireEvent(event);
    }

    protected void addInternalHandler(int eventId) {
        this.owner.addHandler(eventId, this);
    }

    protected void removeInternalHandler(int eventId) {
        this.owner.removeHandler(eventId, this);
    }
}
