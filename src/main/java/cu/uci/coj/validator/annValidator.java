/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.model.entities.Announcement;

@Component
public class annValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Announcement.class.isAssignableFrom(clazz);
    }

    /**
     *
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "general.error.empty");
    }
}
