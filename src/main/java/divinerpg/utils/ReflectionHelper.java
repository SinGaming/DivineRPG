package divinerpg.utils;

import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectionHelper {

    private static final HashMap<Class, HashMap<String, Field>> mappedFields = new HashMap<>();
    private static final HashMap<Class, HashMap<String, List<Method>>> mappedMethods = new HashMap<>();

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

    public static <T> List<T> getDeclaredFieldsValues(Object storage, Class<T> clazz) {

        List<Field> fields = new ArrayList<>();

        {
            Arrays.stream(ArrayUtils.addAll(storage.getClass().getDeclaredFields(), storage.getClass().getFields()))
                    .distinct()
                    .filter(x -> x.getType().equals(clazz))
                    .forEach(field -> {
                        if (!field.isAccessible())
                            field.setAccessible(true);

                        fields.add(field);
                    });
        }

        List<T> results = new ArrayList<>();

        for (Field field : fields) {
            try {
                results.add((T) field.get(storage));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    public static <T> Object callMethod(Object main, Action action, String methodName, Object... params) {
//        try {
//            Method method = find(searchingClass, methodName, Arrays.stream(params).map(x -> x.getClass()).toArray());
//            return method.invoke(main, params);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }

        return null;
    }


    private static Field find(Class clazz, String field) throws NoSuchFieldException {
        HashMap<String, Field> fields = mappedFields.computeIfAbsent(clazz, key -> new HashMap<>());
        if (fields.containsKey(field))
            return fields.get(field);

        Field declaredField = null;

        try {
            // search in declared fields
            declaredField = clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            if (declaredField == null) {
                // in superclasses
                declaredField = clazz.getField(field);
            }
        }


        if (!declaredField.isAccessible())
            declaredField.setAccessible(true);

        fields.put(field, declaredField);
        return declaredField;
    }

    private static Method find(Class clazz, String methodName, Class... params) throws NoSuchMethodException {
        // find by class
        HashMap<String, List<Method>> methods = mappedMethods.computeIfAbsent(clazz, key -> new HashMap<>());
        // find by name
        List<Method> sameNameList = methods.computeIfAbsent(methodName, name -> new ArrayList<>());

        // if present
        if (!sameNameList.isEmpty()) {
            // same params
            Optional<Method> result = sameNameList.stream().filter(x -> x.getParameterTypes().equals(params)).findFirst();
            // if already present, return
            if (result.isPresent())
                return result.get();
        }

        // Only searching in declared!!!!
        Method result = clazz.getDeclaredMethod(methodName, params);

        // remember
        sameNameList.add(result);
        return result;
    }
}
