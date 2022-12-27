package com.mcp.infrastructure.common.domain.validation;



import com.mcp.infrastructure.common.domain.util.IdCardUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: KG
 * @description: 身份证号码校验
 * @date: Created in 5:56 下午 2020/11/3
 * @modified by:
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IdentityCardNumber.IdentityCardNumberValidator.class)
public @interface IdentityCardNumber {

    String message() default "身份证号码不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class IdentityCardNumberValidator implements ConstraintValidator<IdentityCardNumber, Object> {

        @Override
        public void initialize(IdentityCardNumber identityCardNumber) {
        }

        @Override
        public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
            return IdCardUtils.isValid(o.toString());
        }
    }
}







