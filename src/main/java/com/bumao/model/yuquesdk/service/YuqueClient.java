package com.bumao.model.yuquesdk.service;

import com.alibaba.fastjson.JSONObject;
import com.bumao.model.yuquesdk.config.YuqueConfig;
import com.bumao.model.yuquesdk.domain.HttpRespVo;
import com.bumao.model.yuquesdk.domain.UserSerializer;
import com.bumao.model.yuquesdk.exception.YuqueException;
import org.apache.http.client.methods.HttpGet;

public class YuqueClient extends HttpClientHolder{
//    protected YuqueConfig config;
    private String yuqueApiBase = "https://www.yuque.com/api/v2";
    /**
     * 获取单个用户信息-by id
     * @param Id
     */
    public UserSerializer getUserInfoById(String Id) throws YuqueException {

        HttpGet httpGet = mkGet(yuqueApiBase+"/users/"+Id);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);

        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");

        UserSerializer user = JSONObject.toJavaObject(json,UserSerializer.class);
//        System.out.println(user);
        return user;
    }

    /**
     * 获取单个用户信息-by login
     * @param Login
     * @return
     * @throws YuqueException
     */
    public UserSerializer getUserInfoByLogin(String Login) throws YuqueException {
        return this.getUserInfoById(Login);
    }
}
