package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.Role;
import com.gongsibao.uc.base.IRoleService;

@Service
public class RoleService extends PersistableService<Role> implements IRoleService {

    public RoleService(){
        super();
        this.type=Role.class;
    }
}