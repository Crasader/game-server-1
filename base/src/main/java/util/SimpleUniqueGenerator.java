package util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by veininguo on 2017/1/30.
 */
public class SimpleUniqueGenerator implements UniqueIDGeneratorService {
    private static final AtomicLong ID = new AtomicLong(0L);
    private static final String NAME = "unique_id_";

    @Override
    public Object generate() {
        return NAME + ID.incrementAndGet();
    }

    @Override
    public Object generateFor(Class<?> clazz) {
        return clazz.getSimpleName() + ID.incrementAndGet();
    }
}
