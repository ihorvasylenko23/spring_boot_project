package mate.academy.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object firstObj = FieldValueUtil.getFieldValue(value, firstFieldName);
        Object secondObj = FieldValueUtil.getFieldValue(value, secondFieldName);

        if (firstObj == null && secondObj == null) {
            return true;
        }

        if (firstObj != null) {
            return firstObj.equals(secondObj);
        }

        return false;
    }
}
