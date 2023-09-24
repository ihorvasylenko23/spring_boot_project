package mate.academy.validation;

import org.springframework.beans.BeanWrapperImpl;

public class FieldValueUtil {
    public static Object getFieldValue(Object object, String fieldName) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(object);
        return wrapper.getPropertyValue(fieldName);
    }
}
