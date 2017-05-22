package demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class AccountValidator implements Validator {

    @Autowired
    UserDAO userDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        User account = (User) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "password.required");
        User checker = userDAO.findByUsername(account.getUsername());

        if (checker != null) {
            e.rejectValue("username", "username.exists");
        }
    }
}

