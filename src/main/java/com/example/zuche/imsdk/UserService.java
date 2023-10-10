package com.example.zuche.imsdk;

import com.easemob.im.server.EMException;
import com.easemob.im.server.EMService;
import com.easemob.im.server.model.EMUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private EMService service;

    private void createUser() {
        try {
            EMUser user = service.user().create("chengzhang0516cs", "Cq520221").block();
        } catch (EMException e) {
            e.getErrorCode();
            e.getMessage();
        }

    }
}
