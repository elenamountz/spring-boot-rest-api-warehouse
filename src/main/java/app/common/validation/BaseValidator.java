package app.common.validation;

/**
 * @param <T>
 *     Class of the object that we want to validate.
 */
public interface BaseValidator<T> {

    void validate(T obj);
}
