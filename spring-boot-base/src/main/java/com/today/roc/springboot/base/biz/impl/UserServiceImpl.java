package com.today.roc.springboot.base.biz.impl;

import com.today.roc.springboot.base.biz.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年03月01日 15:14*
 * log.info()
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户注册
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean register() {
        return Boolean.TRUE;
    }

}
