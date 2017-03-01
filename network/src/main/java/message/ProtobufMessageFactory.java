package message;

import com.google.protobuf.Message;
import com.google.protobuf.Parser;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by veininguo on 2017/1/24.
 */
public final class ProtobufMessageFactory {
    private static final Map<Integer, Parser<?>> messageParsers = new LinkedHashMap<>();

    public static void registerMessage(int id, Class<? extends Message> clazz) {
        if (clazz == null) {
            throw new NullPointerException();
        }

        try {
            Method method = clazz.getMethod("parser");
            if (method != null) {
                Object object = method.invoke(null);
                if (object instanceof Parser) {
                    Parser<?> parser = (Parser) object;
                    messageParsers.put(id, parser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T extends Message> T createMessage(int id, byte[] data) {
        Parser<?> parser = messageParsers.get(id);
        if (parser == null) {
            return null;
        }

        try {
            @SuppressWarnings("unchecked")
            T message = (T) parser.parseFrom(data);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

