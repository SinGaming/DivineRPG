package divinerpg.utils;

import cpw.mods.modlauncher.api.INameMappingService;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReflectionHelper {

    private static final HashMap<Class, HashMap<String, Field>> mappedFields = new HashMap<>();

    /**
     * Gets field value from storage class
     *
     * @param storage     - storage object. Pass null to static classes
     * @param actualClazz - IF you know actual class where field lives
     * @param fieldName   - field name
     * @param clazz       - field class
     * @return field value
     */
    public static <T> T getFieldValue(@Nullable Object storage, @Nonnull Class actualClazz, String fieldName, Class<T> clazz) {
        T result = null;

        try {
            Field declaredField = find(actualClazz, fieldName);

            if (declaredField.getType().equals(clazz)) {
                result = (T) declaredField.get(storage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Gets field value from storage class
     *
     * @param storage   - storage object
     * @param fieldName - field name
     * @param clazz     - field class
     * @param <T>       - type of field
     * @return field value
     */
    public static <T> T getFieldValue(Object storage, String fieldName, Class<T> clazz) {
        return getFieldValue(storage, storage.getClass(), fieldName, clazz);
    }

    /**
     * Searches all fields of declared type of class and it's superclasses
     *
     * @param storage - storage object
     * @param clazz   - foeld class
     * @param <T>     - type of field
     * @return - list of finded declared fields
     */
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

    /**
     * Try to set value in field
     *
     * @param storage   - sotage object
     * @param fieldName - field name
     * @param value     - new value
     * @return success of operation
     */
    public static boolean setValue(Object storage, String fieldName, Object value) {
        try {
            Field field = find(storage.getClass(), fieldName);

            // need to remove final modifier
            if ((field.getModifiers() & Modifier.FINAL) != 0) {
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }

            // if static field, don't need an storage object
            if ((field.getModifiers() & Modifier.STATIC) != 0 && storage != null) {
                storage = null;
            }

            field.set(storage, value);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Searching field of class by name. When finded, store it in map for fast access I hope
     *
     * @param clazz - storage object class
     * @param field - field name
     * @return - finded field
     * @throws NoSuchFieldException - when no field declared
     */
    private static Field find(Class clazz, String field) throws NoSuchFieldException {
        HashMap<String, Field> fields = mappedFields.computeIfAbsent(clazz, key -> new HashMap<>());
        if (fields.containsKey(field))
            return fields.get(field);

        Field declaredField = null;



        try {
            // search in declared fields
            declaredField = ObfuscationReflectionHelper.findField(clazz, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (declaredField == null) {
                // in superclasses
                declaredField = clazz.getField(ObfuscationReflectionHelper.remapName(INameMappingService.Domain.FIELD, field));
            }
        }


        if (!declaredField.isAccessible())
            declaredField.setAccessible(true);

        fields.put(field, declaredField);
        return declaredField;
    }
}
