package com.miaoshaproject.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;


    //实现校验方法并返回校验结果
    public ValidationResult validate(Object Bean){
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationsSet = validator.validate(Bean);
        if(constraintViolationsSet.size()>0){
            //有错误
            validationResult.setHasErrors(true);
            constraintViolationsSet.forEach(constraintViolations->{
                String errMsg = constraintViolations.getMessage();
                String propertyName = constraintViolations.getPropertyPath().toString();
                validationResult.getErrMsgMap().put(propertyName,errMsg);
            });

        }
        return validationResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    //将hibernate validator通过工厂的初始化方式使其实例化
        this.validator= Validation.buildDefaultValidatorFactory().getValidator();
    }
}
