package com.miaoshaproject.service;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);
    void register(UserModel userModel) throws BusinessException;
    /*
        telephone:用户手机号
        password:用户加密后的密码
     */
    UserModel validateLogin(String telephone,String encrptPassword) throws BusinessException;
}
