package entity.core;

import entity.event.Event;
import entity.event.EventDispatcher;
import entity.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ansh on 2017/2/28.
 */
public class Entity implements EventDispatcher {
    private static final Logger logger = LoggerFactory.getLogger(Entity.class);

    private long id;
    private Map<Integer, Component> components;
    private Map<Integer, List<EventHandler>> eventHandlers;

    public Entity() {
        this.components = new HashMap<>();
        this.eventHandlers = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean add(Component component) {
        Class<? extends Component> componentType = component.getClass();
        Component oldComponent = this.getComponent(componentType);
        if (component == oldComponent) {
            return false;
        }

        int componentTypeIndex = ComponentType.getIndexFor(componentType);
        this.components.put(componentTypeIndex, component);
        return true;
    }

    public boolean remove(Class<? extends Component> componentClass) {
        ComponentType componentType = ComponentType.getFor(componentClass);
        int componentTypeIndex = componentType.getIndex();
        Component removeComponent = this.components.get(componentTypeIndex);

        if (removeComponent != null) {
            this.components.remove(componentTypeIndex);
            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(Class<T> componentClass) {
        int index = ComponentType.getIndexFor(componentClass);
        if (this.components.containsKey(index)) {
            return (T) this.components.get(index);
        }
        return null;
    }

    @Override
    public void addHandler(int eventId, EventHandler handler) {
        if (handler == null) {
            return;
        }

        List<EventHandler> handlers = this.eventHandlers.get(eventId);
        if (handlers == null) {
            handlers = new LinkedList<>();
            this.eventHandlers.put(eventId, handlers);
        }

        handlers.add(handler);
    }

    @Override
    public void removeHandler(int eventId, EventHandler handler) {
        if (handler == null) {
            return;
        }

        List<EventHandler> handlers = this.eventHandlers.get(eventId);
        if (handlers == null) {
            return;
        }

        handlers.remove(handler);

        if (handlers.size() == 0) {
            this.eventHandlers.remove(eventId);
        }
    }

    @Override
    public void fireEvent(Event event) {
        if (event == null) {
            return;
        }

        List<EventHandler> handlers = this.eventHandlers.get(event.getId());
        if (handlers == null) {
            return;
        }

        for (EventHandler handler : handlers) {
            long startTime = System.currentTimeMillis();

            try {
                handler.onEvent(event);
            } catch (Exception e) {
                logger.error("onEvent error, {}", e);
            }

            long handleTime = System.currentTimeMillis() - startTime;
            if (handleTime > 100) {
                logger.debug("{} processing takes to long, eventId : {}, handling time : {}", handler.getClass()
                        .getSimpleName(), handleTime);
            }
        }
    }
}
