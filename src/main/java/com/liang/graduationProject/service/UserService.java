package com.liang.graduationProject.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liang.graduationProject.model.Manager;
import com.liang.graduationProject.model.User;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.GenericService;
import com.liang.graduationProject.utils.FastJsonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
@Service(value = "userService")
public class UserService extends GenericService{

    public DataResult checkName(String jsonData) {
        DataResult data = new DataResult();
        JSONObject json = FastJsonUtils.textToJson(jsonData);
        String type = json.getString("type");
        String userName = json.getString("userName");
        if (type.equals("phoneUser")) {
            User user = this.getDaoFacade().getUserRepository().findByPhone(userName);
            if(user == null) {
                data.setErrorCode("200");
                data.setReason("");
            }else {
                data.setErrorCode("400");
                data.setReason("该电话已注册");
            }
        }else if(type.equals("emailUser")) {
            User user = this.getDaoFacade().getUserRepository().findByEmail(userName);
            if(user == null) {
                data.setErrorCode("200");
                data.setReason("");
            }else {
                data.setErrorCode("400");
                data.setReason("该邮箱已注册");
            }
        }else {
            data.setErrorCode("400");
            data.setReason("请求出错");
        }
        return data;
    }

    public DataResult UserRegister(String jsonData) {
        DataResult data = new DataResult();
        JSONObject json = FastJsonUtils.textToJson(jsonData);
        String type = json.getString("type");
        String userName = json.getString("userName");
        String password = json.getString("password");
        User user = new User();
        user.setPassword(password);
        System.out.println("type.equals(\"phoneUser\"): "+type.equals("phoneUser")+"        type.equals(\"emailUser\"): "+type.equals("emailUser"));
        if (type.equals("phoneUser")) {
            User user1 = this.getDaoFacade().getUserRepository().findByPhone(userName);
            if(user1 == null) {
                user.setPhone(userName);
                user.setRegisterTime(new Date());
                this.getDaoFacade().getUserRepository().save(user);
                user = this.getDaoFacade().getUserRepository().findByPhone(userName);
                user.setPassword("");
                data.setErrorCode("200");
                data.setResult(user);
                data.setReason("注册成功");
            }else {
                data.setErrorCode("400");
                data.setReason("该电话已注册");
            }

        }else if(type.equals("emailUser")) {
            User user1 = this.getDaoFacade().getUserRepository().findByEmail(userName);
            if(user1 == null) {
                user.setEmail(userName);
                user.setRegisterTime(new Date());
                this.getDaoFacade().getUserRepository().save(user);
                user = this.getDaoFacade().getUserRepository().findByEmail(userName);
                user.setPassword("");
                data.setResult(user);
                data.setErrorCode("200");
                data.setReason("注册成功");
            }
            else {
                data.setErrorCode("400");
                data.setReason("该电话已注册");
            }
        }else {
            data.setErrorCode("400");
            data.setReason("注册失败");
        }
        return data;
    }

    public DataResult userLogin(String jsonData) {
        DataResult data = new DataResult();
        JSONObject json = FastJsonUtils.textToJson(jsonData);
        String type = json.getString("type");
        String userName = json.getString("userName");
        String password = json.getString("password");
        if (type.equals("phoneUser")) {
            User user =  this.getDaoFacade().getUserRepository().findByPhone(userName);
            if (user == null) {
                data.setErrorCode("400");
                data.setReason("该用户不存在");
            }else if(user != null) {
                if(!password.equals(user.getPassword())) {
                    data.setErrorCode("400");
                    data.setReason("密码错误");
                }else if (password.equals(user.getPassword())) {
                    user.setPassword("");
                    data.setErrorCode("200");
                    data.setReason("登入成功");
                    data.setResult(user);
                }
            }

        }else if(type.equals("emailUser")) {
            User user =  this.getDaoFacade().getUserRepository().findByEmail(userName);
            if (user == null) {
                data.setErrorCode("400");
                data.setReason("该用户不存在");
            }else if(user != null) {
                if(!password.equals(user.getPassword())) {
                    data.setErrorCode("400");
                    data.setReason("密码错误");
                }else if (password.equals(user.getPassword())) {
                    data.setErrorCode("200");
                    data.setReason("登入成功");
                    data.setResult(user);
                }
            }

        }else {
            data.setErrorCode("400");
            data.setReason("登入失败");
        }
        return data;
    }

    public DataResult userUpdate(String jsonData) {
        DataResult data = new DataResult();
        JSONObject json = FastJsonUtils.textToJson(jsonData);
        String loginId = json.getString("loginId").trim();
        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);
        User user = this.getDaoFacade().getUserRepository().findByUserId(loginId);
        User user1 = json.getObject("user",User.class);
        User user2 = this.getDaoFacade().getUserRepository().findByUserId(user1.getUserId());
        if ( null == user2) {
            data.setErrorCode("400");
            data.setReason("该用户不存在");
            return data;
        }

        if ( null != manager) {
            if ( null != user1.getNickName() && !"".equals(user1.getNickName().trim()))
                user2.setNickName(user1.getNickName().trim());
            if ( null != user1.getPassword() && !"".equals(user1.getPassword().trim()))
                user2.setPassword(user1.getPassword().trim());
            if ( null != user1.getSex() && !"".equals(user1.getSex().trim()) )
                user2.setSex(user1.getSex().trim());
            if ( null != user1.getBirth() && !"".equals(user1.getBirth().trim()))
                user2.setBirth(user1.getBirth().trim());
            if ( null != user1.getAddress() && !"".equals(user1.getBirth().trim()))
                user2.setAddress(user1.getAddress().trim());
            if ( null != user1.getQqNum() && !"".equals(user1.getQqNum().trim()))
                user2.setQqNum(user1.getQqNum().trim());
            if ( null != user1.getWechatNum() && !"".equals(user1.getWechatNum().trim()))
                user2.setWechatNum(user1.getWechatNum().trim());
            if ( null != user1.getPhone() && !"".equals(user1.getPhone().trim()))
                user2.setPhone(user1.getPhone().trim());
            if ( null != user1.getEmail() && !"".equals(user1.getEmail().trim()))
                user2.setEmail(user1.getEmail().trim());
            this.getDaoFacade().getUserRepository().save(user2);
            user1.setPassword("");
            data.setErrorCode("200");
            data.setReason("修改成功");
            data.setResult(user2);
        }else if ( null != user && user.getUserId().equals(user1.getUserId())) {
            if ( null != user1.getNickName() && !"".equals(user1.getNickName().trim()))
                user2.setNickName(user1.getNickName().trim());
            if ( null != user1.getSex() && !"".equals(user1.getSex().trim()) )
                user2.setSex(user1.getSex().trim());
            if ( null != user.getBirth() && !"".equals(user1.getBirth().trim()))
                user2.setBirth(user1.getBirth().trim());
            if ( null != user.getAddress() && !"".equals(user1.getBirth().trim()))
                user2.setAddress(user1.getAddress().trim());
            if ( null != user.getQqNum() && !"".equals(user1.getQqNum().trim()))
                user2.setQqNum(user1.getQqNum().trim());
            if ( null != user.getWechatNum() && !"".equals(user1.getWechatNum().trim()))
                user2.setWechatNum(user1.getWechatNum().trim());
            this.getDaoFacade().getUserRepository().save(user2);
            user1.setPassword("");
            data.setErrorCode("200");
            data.setReason("修改成功");
            data.setResult(user2);
        }else if ( null == manager && null == user) {
            data.setErrorCode("400");
            data.setReason("请登陆");
        }else{

        }
        return  data;
    }

    public DataResult bindingOrNot(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String userId = jsonObject.getString("userId").trim();
        String phone = jsonObject.getString("phone").trim();
        String email = jsonObject.getString("email".trim());
        if( ("".equals(phone) || null == phone )&& (null == email || "".equals(email))) {
            data.setErrorCode("400");
            data.setReason("email和phone不能全为空");
        }else if (null != phone && !"".equals(phone) && (null == email || "".equals(email))) {
            User user1 = this.getDaoFacade().getUserRepository().findByPhone(phone);
            if ( null == user1) {
                try {
                    User user = this.getDaoFacade().getUserRepository().findByUserId(userId);
                    if ( null == user) {
                        data.setErrorCode("400");
                        data.setReason("用户不存在，请登陆");
                    }else {
                        user.setPhone(phone);
                        try {
                            this.getDaoFacade().getUserRepository().save(user);
                            user.setPassword("");
                            data.setErrorCode("200");
                            data.setResult(user);
                            data.setReason("修改成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                            data.setErrorCode("400");
                            data.setReason("系统正在维护，请稍后再试");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    data.setErrorCode("400");
                    data.setReason("系统正在维护，请稍后再试");
                }
            }else {
                data.setErrorCode("400");
                data.setReason("该电话号码已被注册");
            }
        }else if ((null == phone || "".equals(phone)) && null != email && !"".equals(email)) {
            User user1 = this.getDaoFacade().getUserRepository().findByPhone(email);
            if ( null == user1) {
                try {
                    User user = this.getDaoFacade().getUserRepository().findByUserId(userId);if ( null == user)
                    {
                        data.setErrorCode("400");
                        data.setReason("用户不存在，请登陆");
                    }else {
                        user.setEmail(email);
                        try {
                            this.getDaoFacade().getUserRepository().save(user);
                            user.setPassword("");
                            data.setErrorCode("200");
                            data.setResult(user);
                            data.setReason("修改成功");
                        } catch (Exception e) {
                            e.printStackTrace();
                            data.setErrorCode("400");
                            data.setReason("系统正在维护，请稍后再试");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    data.setErrorCode("400");
                    data.setReason("系统正在维护，请稍后再试");
                }
            }else {
                data.setErrorCode("400");
                data.setReason("该邮箱已被注册");
            }
        } else if (null != phone && null != email && !"".equals(email) && !"".equals(phone)) {
            try {
                User user = this.getDaoFacade().getUserRepository().findByUserId(userId);
                if ( null == user) {
                    data.setErrorCode("400");
                    data.setReason("用户不存在，请登陆");
                }else {
                      user.setPhone(phone);
                      user.setEmail(email);
                    try {
                        this.getDaoFacade().getUserRepository().save(user);
                        user.setPassword("");
                        data.setErrorCode("200");
                        data.setReason("绑定成功");
                        data.setResult(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                        data.setErrorCode("400");
                        data.setReason("系统正在维护，请稍后再试");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                data.setErrorCode("400");
                data.setReason("系统正在维护，请稍后再试");
            }
        }
        else {
            data.setErrorCode("400");
            data.setReason("非正常请求");
        }
        return data;
    }

    public DataResult updatePassword( String phone, String authCode,String newPassword) {
        DataResult data = new DataResult();

        try {
            User user = this.getDaoFacade().getUserRepository().findByPhone(phone);
            if ( null == user) {
                data.setErrorCode("400");
                data.setReason("账号不存在，请注册");
            }else {
                if ( authCode.equals(user.getAuthCode())) {
                    user.setPassword(newPassword);
                    this.getDaoFacade().getUserRepository().save(user);
                    user.setPassword("");
                    data.setReason("修改成功");
                    data.setErrorCode("200");
                    data.setResult(user);
                }else {
                    data.setErrorCode("400");
                    data.setReason("验证码错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setReason("系统正在维护，请稍后再试，或者联系网站管理员");
            data.setErrorCode("400");
        }

        return data;
    }

    public DataResult queryUserPage(String jsonData) {
        DataResult data = new DataResult();
        JSONObject jsonObject = JSON.parseObject(jsonData);
        String loginId = jsonObject.getString("loginId").trim();
        int pageIndex = Integer.parseInt(jsonObject.getString("pageIndex").trim());
        int pageSize = Integer.parseInt(jsonObject.getString("pageSize").trim());
        if ( 0 == pageIndex || "".equals(pageIndex+"")) {
            pageIndex = 1;
        }
        if ( 0 == pageSize || "".equals(pageSize+"")) {
            pageSize = 8;
        }
        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);
        if ( null == manager) {
            data.setErrorCode("400");
            data.setReason("请注册登陆");
        }else if ( manager.getManagerLevel() == 3) {
            data.setErrorCode("400");
            data.setReason("您的权限不足");
        }else if ( manager.getManagerLevel() == 2 || manager.getManagerLevel() == 1) {
            PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
            Page<User> userPage = this.getDaoFacade().getUserRepository().findAll(pageRequest);
            data.setErrorCode("200");
            data.setReason("查询成功");
            data.setResult(userPage);
        }else {
            data.setErrorCode("300");
            data.setReason("未解之错");
        }
        return data;
    }

    public DataResult getUser(String loginId, String userId) {
        DataResult data = new DataResult();

        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);
        if ( null == manager) {
            data.setErrorCode("400");
            data.setReason("请注册登陆");
        }else if ( manager.getManagerLevel() == 3 || manager.getManagerLevel() == 2) {
            data.setErrorCode("400");
            data.setReason("您的权限不足");
        }else if ( manager.getManagerLevel() == 1 ) {
            User user = this.getDaoFacade().getUserRepository().findByUserId(userId);
            data.setErrorCode("200");
            data.setReason("查询成功");
            data.setResult(user);
        }else {
            data.setErrorCode("300");
            data.setReason("未解之错");
        }
        return data;
    }

    public DataResult deleteUser( String loginId, String userId) {
        DataResult data = new DataResult();

        Manager manager = this.getDaoFacade().getManagerRepository().findOne(loginId);
        if ( null == manager) {
            data.setErrorCode("400");
            data.setReason("请注册登陆");
        }else if ( manager.getManagerLevel() == 3 || manager.getManagerLevel() == 2) {
            data.setErrorCode("400");
            data.setReason("您的权限不足");
        }else if ( manager.getManagerLevel() == 1 ) {
            User user = this.getDaoFacade().getUserRepository().findByUserId(userId);
            if ( null == user) {
                data.setReason("该用户不存在");
                data.setErrorCode("400");
            }else {
                this.getDaoFacade().getUserRepository().delete(user);
                data.setErrorCode("200");
                data.setReason("删除成功");
            }
        }else {
            data.setErrorCode("300");
            data.setReason("未解之错");
        }
        return data;
    }
}
