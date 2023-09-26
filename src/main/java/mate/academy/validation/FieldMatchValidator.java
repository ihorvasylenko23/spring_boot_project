package mate.academy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        field = constraintAnnotation.first();
        fieldMatch = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object firstObj = getFieldValue(value, field);
        Object secondObj = getFieldValue(value, fieldMatch);

        if (firstObj == null || secondObj == null) {
            return firstObj == secondObj;
        }

        return firstObj.equals(secondObj);
    }

    private Object getFieldValue(Object object, String fieldName) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(object);
        return wrapper.getPropertyValue(fieldName);
    }
}
