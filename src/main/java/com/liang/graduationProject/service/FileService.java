package com.liang.graduationProject.service;

import com.liang.graduationProject.model.User;
import com.liang.graduationProject.response.DataResult;
import com.liang.graduationProject.service.GenericService;
import com.liang.graduationProject.utils.CommonConsts;
import com.liang.graduationProject.utils.HeadUtils;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/5/3.
 */
@Service("fileService")
public class FileService extends GenericService{

    public DataResult uploadHead(String loginId, String head) {
        DataResult data = new DataResult();
        User user = this.getDaoFacade().getUserRepository().findByUserId(loginId);
        if ( null == user){
            data.setErrorCode("400");
            data.setReason("该用户不存在，请注册");
            return data;
        }
        String path = CommonConsts.HEAD_URL+""+loginId+".jpg";
        if (HeadUtils.checkSize(head,CommonConsts.MAX_SIZE)){
            System.out.println("path:"+path);
            HeadUtils.saveHeadForBase64(path,head);
            data.setErrorCode("200");
            data.setReason("上存图像成功");
            String headUrl = CommonConsts.SET_HEAD_URL+""+loginId+".jpg";
            user.setHead(headUrl);
            System.out.println("user.setHead:"+user.getHead());
            this.getDaoFacade().getUserRepository().save(user);
            data.setResult(headUrl);
        }else {
            data.setReason("图片过大");
            data.setErrorCode("400");
        }
        return data;
    }
}
