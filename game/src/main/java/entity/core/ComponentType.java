package entity.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ansh on 2017/2/28.
 */
public class ComponentType {
    private static Map<Class<? extends Component>, ComponentType> assignedComponentTypes = new ConcurrentHashMap<>();
    private static AtomicInteger typeIndex = new AtomicInteger(0);

    public static ComponentType getFor(Class<? extends Component> componentType) {
        ComponentType type = assignedComponentTypes.get(componentType);

        if (type == null) {
            type = new ComponentType();
            assignedComponentTypes.putIfAbsent(componentType, type);
        }

        return assignedComponentTypes.get(type);
    }

    public static int getIndexFor(Class<? extends Component> componentType) {
        return getFor(componentType).getIndex();
    }

    private final int index;

    public ComponentType() {
        this.index = typeIndex.incrementAndGet();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentType that = (ComponentType) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return index;
    }
}
