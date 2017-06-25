package com.liang.graduationProject.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.model.Manager;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by admin on 2017/4/18.
 */
@Service(value = "managerService")
public class ManagerService extends GenericService{
    public DataResult managerLogin(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String managerName = jsonObject.getString("managerName");
        String password = jsonObject.getString("password");
        try {
            Manager manager = this.getDaoFacade().getManagerRepository().findByManagerName(managerName);
            if ( null == manager) {
                data.setErrorCode("400");
                data.setReason("该管理员不存在");
                return data;
            }else {
                if (password.equals(manager.getPassword())) {
                    data.setErrorCode("200");
                    data.setReason("login successfully");
                    manager.setPassword("");
                    data.setResult(manager);
                }else {
                    data.setErrorCode("400");
                    data.setReason("Incorrect password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public DataResult addOrAuthManager(String managerId,String manager) {
        DataResult data = new DataResult();
        Manager manager1 = this.getDaoFacade().getManagerRepository().findOne(managerId);
        Manager newManager = JSON.parseObject(manager,Manager.class);
        if (manager1.getManagerLevel() >= newManager.getManagerLevel()) {
            data.setErrorCode("400");
            data.setReason("权限不足");
        }else {
            //增加管理员
            if ( null == newManager.getManagerId() || "".equals(newManager.getManagerId())) {
                newManager.setManagerId(null);
                Manager manager2 = this.getDaoFacade().getManagerRepository().findByManagerName(newManager.getManagerName());
                if (null != manager2) {
                    data.setErrorCode("400");
                    data.setReason("该管理员已存在");
                    return data;
                }
                if (newManager.getManagerLevel() == 3) {
                    if ( null == newManager.getSectionName() || "".equals(newManager.getSectionName().trim())) {
                        data.setReason("请给该管理员分配版块的管理权限");
                        data.setErrorCode("400");
                        data.setResult(newManager);
                        return data;
                    }
                }
                Manager manager3 =this.getDaoFacade().getManagerRepository().save(newManager);
                data.setErrorCode("200");
                data.setReason("添加成功");
                data.setResult(manager3);
            }else {
                //修改管理员权限
                Manager manager2 = this.getDaoFacade().getManagerRepository().findOne(newManager.getManagerId());
                if ( null == manager2) {
                    data.setReason("该管理员不存在");
                    data.setErrorCode("400");
                    return data;
                }
                Manager manager3 = this.getDaoFacade().getManagerRepository().findByManagerName(newManager.getManagerName());
                if (null != manager3) {
                    data.setErrorCode("400");
                    data.setReason("该管理员已存在");
                    return data;
                }
                manager2.setManagerLevel(newManager.getManagerLevel());
                this.getDaoFacade().getManagerRepository().save(newManager);
                data.setErrorCode("200");
                data.setReason("权限修改成功");
                data.setResult(newManager);
            }
        }

        return data;
    }

    public DataResult deleteManager(String deleteManagerId, String managerId) {
        DataResult data = new DataResult();
        Manager deleteManager = this.getDaoFacade().getManagerRepository().findOne(deleteManagerId);
        Manager manager = this.getDaoFacade().getManagerRepository().findOne(managerId);
        if (deleteManagerId.trim().equals(managerId.trim())) {
            data.setErrorCode("400");
            data.setReason("不能删除自己");
            return null;
        }
        if (deleteManager.getManagerLevel() > manager.getManagerLevel()) {
            data.setErrorCode("400");
            data.setReason("权限不足");
        }else {
            this.getDaoFacade().getManagerRepository().delete(manager.getManagerId());
            data.setReason("删除成功");
            data.setErrorCode("200");
        }
        return data;
    }

    public DataResult updateManager(String manager) {
        DataResult data = new DataResult();
        Manager manager1 = JSON.parseObject(manager,Manager.class);
        Manager manager2 = this.getDaoFacade().getManagerRepository().findOne(manager1.getManagerId());
        if ( null == manager) {
            data.setErrorCode("400");
            data.setReason("该管理员不存在");
        }else {
            if ( null != manager1.getManagerName() && !"".equals(manager1.getManagerName().trim())) {
                if ( manager1.getManagerName().trim().equals(manager2.getManagerName().trim())) {
                    if ( null != manager1.getPassword() && "".equals(manager1.getPassword().trim())) {
                        manager2.setPassword(manager1.getPassword());
                    }
                }else {
                    Manager manager3 = this.getDaoFacade().getManagerRepository().findByManagerName(manager1.getManagerName());
                    if (null != manager3) {
                        data.setErrorCode("400");
                        data.setReason("该管理员名字已存在");
                        return data;
                    }
                    manager2.setManagerName(manager1.getManagerName());
                }
            }

            this.getDaoFacade().getManagerRepository().save(manager2);
            data.setReason("修改成功");
            data.setErrorCode("200");
            data.setResult(manager2);
        }
        return data;
    }

    public DataResult queryManagerList(String managerId) {
        DataResult data = new DataResult();
        Manager manager = this.getDaoFacade().getManagerRepository().findOne(managerId);
        List<Manager> managerList = new ArrayList<Manager>();
        data.setErrorCode("200");
        data.setReason("查询成功");
        switch (manager.getManagerLevel()) {
            case 1:
                data.setResult(this.getDaoFacade().getManagerRepository().findAll());
                break;
            case 2:
                managerList.addAll(this.getDaoFacade().getManagerRepository().findByManagerLevel(3));
                data.setResult(managerList);
                break;
            case 3:
                managerList.addAll(this.getDaoFacade().getManagerRepository().findByManagerLevel(3));
                data.setResult(managerList);
        }
        return data;
    }
}
