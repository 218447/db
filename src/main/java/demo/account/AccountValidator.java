package demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class AccountValidator implements Validator {

    @Autowired
    AccountDAO accountDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        Account account = (Account) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "password.required");
        Account checker = accountDAO.findByUsername(account.getUsername());

        if (checker != null) {
            e.rejectValue("username", "username.exists");
        }
    }
}

