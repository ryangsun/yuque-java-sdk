package com.bumao.model.yuquesdk.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumao.model.yuquesdk.domain.*;
import com.bumao.model.yuquesdk.exception.YuqueException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YuqueClient extends HttpClientHolder{

    private final String yuqueApiBase = "https://www.yuque.com/api/v2";

    public YuqueClient(String Token){
        super.setYuqueToken(Token);
    }

    /**
     * 获取单个用户信息-by id
     * @param Id
     */
    public UserSerializer getUserInfoById(String Id) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/users/"+Id);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
//        System.out.println(json);
        //转user
        UserSerializer user = JSONObject.toJavaObject(json,UserSerializer.class);

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

    /**
     * 获取当前 Token 对应的用户的个人信息
     * @return
     * @throws YuqueException
     */
    public UserSerializer getCurrUserInfo() throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/user");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        UserSerializer user = JSONObject.toJavaObject(json,UserSerializer.class);

        return user;
    }

    /**
     * 获取某个用户的加入的组织列表 by Id
     * @param userId
     * @return
     * @throws YuqueException
     */
    public List<GroupSerializer> getJoinedGroupById(String userId) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/users/"+userId+"/groups");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);
        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
//        System.out.println(jsonArray);
        //转user
        List<GroupSerializer> groupSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), GroupSerializer.class);
//        System.out.println(groupSerializerList);

        return groupSerializerList;
    }

    /**
     * 获取某个用户的加入的组织列表 by Login
     * @param Login
     * @return
     * @throws YuqueException
     */
    public List<GroupSerializer> getJoinedGroupByLogin(String Login) throws YuqueException {
        return this.getJoinedGroupById(Login);
    }

    /**
     * 获取公开组织列表
     * @return
     * @throws YuqueException
     */
    public List<GroupSerializer> getPublicGroup() throws YuqueException {
        return this.getPublicGroup(1);
    }
    public List<GroupSerializer> getPublicGroup(Integer offset) throws YuqueException {
        Map<String,String> para = new HashMap<String, String>();
        if(offset==null||offset<1){
            offset = 1;
        }
        para.put("offset",offset.toString());

        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/groups",para);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);
        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
//        System.out.println(jsonArray);
        //转user
        List<GroupSerializer> groupSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), GroupSerializer.class);
        return groupSerializerList;
    }

    /**
     * 获取单个组织的详细信息
     * @param groupId
     * @return
     * @throws YuqueException
     */
    public GroupSerializer getGroupInfoById(String groupId) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/groups/"+groupId);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
//        System.out.println(json);
        //转user
        GroupSerializer group = JSONObject.toJavaObject(json,GroupSerializer.class);

        return group;
    }

    /**
     * 创建 Group
     * @param createPo
     * @return
     * @throws YuqueException
     */
    public GroupSerializer createGroup(GroupCreatePo createPo) throws YuqueException {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(createPo);
        }catch (Exception e){
            throw new YuqueException(e.getMessage(),e);
        }
        HttpPost httpPost = buildHttpPost(yuqueApiBase+"/groups",map);
        HttpRespVo vo = doRequest(httpPost);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        GroupSerializer group = JSONObject.toJavaObject(json,GroupSerializer.class);
        return group;
    }

    /**
     * 更新组织 by Id
     * @param groupId
     * @param updatePo
     * @return
     * @throws YuqueException
     */
    public GroupSerializer updateGroupById(String groupId,GroupCreatePo updatePo) throws YuqueException {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(updatePo);
        }catch (Exception e){
            throw new YuqueException(e.getMessage(),e);
        }
        HttpPut httpPut = buildHttpPut(yuqueApiBase+"/groups/"+groupId,map);
        HttpRespVo vo = doRequest(httpPut);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        GroupSerializer group = JSONObject.toJavaObject(json,GroupSerializer.class);
        return group;
    }

    /**
     * 更新组织 by Login
     * @param Login
     * @param updatePo
     * @return
     * @throws YuqueException
     */
    public GroupSerializer updateGroupByLogin(String Login,GroupCreatePo updatePo) throws YuqueException {
        return updateGroupById(Login,updatePo);
    }

    /**
     * 删除组织 by Id
     * @param groupId
     * @throws YuqueException
     */
    public GroupSerializer deleteGroupById(String groupId) throws YuqueException {
        HttpDelete httpDelete = buildHttpDelete(yuqueApiBase+"/groups/"+groupId,null);
        HttpRespVo vo = doRequest(httpDelete);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        GroupSerializer group = JSONObject.toJavaObject(json,GroupSerializer.class);
        return group;
    }

    /**
     * 删除组织 by Login
     * @param Login
     * @return
     * @throws YuqueException
     */
    public GroupSerializer deleteGroupByLogin(String Login) throws YuqueException {
        return this.deleteGroupById(Login);
    }

    /**
     * 获取组织成员信息 by groupId
     * @param groupId
     * @return
     * @throws YuqueException
     */
    public List<GroupUserSerializer> getGroupUserById(String groupId) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/groups/"+groupId+"/users");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);

        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
        //转user
        List<GroupUserSerializer> groupUserSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), GroupUserSerializer.class);
//        System.out.println(groupUserSerializerList);
        return groupUserSerializerList;
    }

    /**
     * 获取组织成员信息 by Login
     * @param Login
     * @return
     * @throws YuqueException
     */
    public List<GroupUserSerializer> getGroupUserByLogin(String Login) throws YuqueException {
        return this.getGroupUserById(Login);
    }

    /**
     * 增加或更新组织成员 by groupId
     * @param groupId
     * @param userLogin
     * @param roleId 0 - 管理员, 1 - 普通成员
     * @return
     * @throws YuqueException
     */
    public GroupUserSerializer updateGroupUserbyId(String groupId,String userLogin,Integer roleId) throws YuqueException {
        Map<String, String> map = new HashMap<>();
        map.put("role",roleId.toString());

        HttpPut httpPut = buildHttpPut(yuqueApiBase+"/groups/"+groupId+"/users/"+userLogin,map);
        HttpRespVo vo = doRequest(httpPut);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        GroupUserSerializer groupUser = JSONObject.toJavaObject(json,GroupUserSerializer.class);
        return groupUser;
    }

    /**
     * 增加或更新组织成员 by groupLogin
     * @param groupLogin
     * @param userLogin
     * @param roleId 0 - 管理员, 1 - 普通成员
     * @return
     * @throws YuqueException
     */
    public GroupUserSerializer updateGroupUserbyLogin(String groupLogin,String userLogin,Integer roleId) throws YuqueException {
        return this.updateGroupUserbyId(groupLogin,userLogin,roleId);
    }

    /**
     * 删除组织成员 by groupId
     * @param groupId
     * @param userLogin
     * @return
     * @throws YuqueException
     */
    public GroupUserSerializer deleteGroupUserbyId(String groupId,String userLogin) throws YuqueException {
        HttpDelete httpDelete = buildHttpDelete(yuqueApiBase+"/groups/"+groupId+"/users/"+userLogin,null);
        HttpRespVo vo = doRequest(httpDelete);
//        System.out.println(vo);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        GroupUserSerializer groupUser = JSONObject.toJavaObject(json,GroupUserSerializer.class);
        return groupUser;
    }

    /**
     * 删除组织成员 by groupLogin
     * @param groupLogin
     * @param userLogin
     * @return
     * @throws YuqueException
     */
    public GroupUserSerializer deleteGroupUserbyLogin(String groupLogin,String userLogin) throws YuqueException {
        return this.deleteGroupUserbyId(groupLogin,userLogin);
    }
}
