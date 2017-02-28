package util;

/**
 * Created by veininguo on 2017/1/30.
 */
public interface UniqueIDGeneratorService {
    Object generate();

    Object generateFor(Class<?> clazz);
}