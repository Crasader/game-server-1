package entity.core;

import entity.event.EventHandler;

/**
 * Created by Ansh on 2017/2/28.
 */
public interface Component extends EventHandler {
    void setParent(Entity entity);

    Entity getParent();

    void initializer();

    void destroy();
}
