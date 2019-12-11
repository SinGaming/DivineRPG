package divinerpg.utils;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ReflectionHelper {

    private static final HashMap<Class, HashMap<String, Field>> mappedFields = new HashMap<>();

    public static <T> T getFieldValue(Object storage, String fieldName, Class<T> clazz) {

        T result = null;

        try {
            Field declaredField = find(storage.getClass(), fieldName);

            if (declaredField.getType().equals(clazz)) {
                result = (T) declaredField.get(storage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Field find(Class clazz, String field) throws NoSuchFieldException {
        HashMap<String, Field> fields = mappedFields.computeIfAbsent(clazz, key -> new HashMap<>());
        if (fields.containsKey(field))
            return fields.get(field);

        Field declaredField = clazz.getDeclaredField(field);
        if (!declaredField.isAccessible())
            declaredField.setAccessible(true);

        fields.put(field, declaredField);
        return declaredField;
    }
}
