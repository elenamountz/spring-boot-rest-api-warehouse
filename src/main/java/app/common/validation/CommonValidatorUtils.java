package app.common.validation;

import app.exception.FieldNotUniqueException;
import app.exception.MissingRequiredFieldException;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

/**
 * Component about some most used validation utilities. Implementation is based on
 * generic classes and types, in order to be specified on runtime. This technique helps
 * us to avoid boilerplate code and multiple different implementations for the exact
 * same functionality.
 *
 * @param <T>
 *     Class of the object that we check the validity.
 * @param <S>
 *     Class of the object maybe found on db.
 *
 * In most cases <T> is a DTO class, <S> is an entity class and these utilities
 * are used before save/update in order to throw custom exceptions.
 */
@Component
public class CommonValidatorUtils<T, S> {

    public void validateUniqueField(String fieldName, T checkingObj, S foundObj) {
        // Use BeanWrapper to get the value of a field that is specified on runtime
        BeanWrapperImpl checkingObjWrapper = new BeanWrapperImpl(checkingObj);
        Object checkingObjId = checkingObjWrapper.getPropertyValue("id");
        String checkingObjFieldValue = checkingObjWrapper.getPropertyValue(fieldName).toString();

        BeanWrapperImpl foundObjWrapper = new BeanWrapperImpl(foundObj);
        Object foundObjId = foundObjWrapper.getPropertyValue("id");

        // Save case
        if (checkingObjId == null) {
            throw new FieldNotUniqueException(checkingObjFieldValue);
        }

        // Update case
        if(checkingObjId != null) {
            // Trying to update an object, using a taken(not unique) value
            if(checkingObjId != foundObjId) {
                throw new FieldNotUniqueException(checkingObjFieldValue);
            }
        }
    }

    public void validateRequiredField(String fieldLabel, T fieldValue) {
        if(fieldValue == null)
            throw new MissingRequiredFieldException(fieldLabel);
    }

}
