package com.bumao.model.yuquesdk.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumao.model.yuquesdk.domain.*;
import com.bumao.model.yuquesdk.exception.YuqueException;
import com.bumao.model.yuquesdk.po.DocCreatePo;
import com.bumao.model.yuquesdk.po.GroupCreatePo;
import com.bumao.model.yuquesdk.po.RepoCreatePo;
import com.bumao.model.yuquesdk.po.SearchPo;
import com.bumao.model.yuquesdk.vo.BookDetailVo;
import com.bumao.model.yuquesdk.vo.DocDetailVo;
import com.bumao.model.yuquesdk.vo.GroupDetailVo;
import com.bumao.model.yuquesdk.vo.HttpRespVo;
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
    public UserDetailSerializer getUserInfoById(String Id) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/users/"+Id);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转JSONObject
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转实体
        UserDetailSerializer user = JSONObject.toJavaObject(json,UserDetailSerializer.class);

        return user;
    }

    /**
     * 获取单个用户信息-by login
     * @param Login
     * @return
     * @throws YuqueException
     */
    public UserDetailSerializer getUserInfoByLogin(String Login) throws YuqueException {
        return this.getUserInfoById(Login);
    }

    /**
     * 获取当前 Token 对应的用户的个人信息
     * @return
     * @throws YuqueException
     */
    public UserDetailSerializer getCurrUserInfo() throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/user");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转JSONObject
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转实体
        UserDetailSerializer user = JSONObject.toJavaObject(json,UserDetailSerializer.class);

        return user;
    }

    /**
     * 获取某个用户的加入的组织列表 by Id
     * @param userId
     * @return
     * @throws YuqueException
     */
    public List<GroupSerializer> listJoinedGroupById(String userId) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/users/"+userId+"/groups");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);
        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
        //转实体
        List<GroupSerializer> groupSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), GroupSerializer.class);

        return groupSerializerList;
    }

    /**
     * 获取某个用户的加入的组织列表 by Login
     * @param Login
     * @return
     * @throws YuqueException
     */
    public List<GroupSerializer> listJoinedGroupByLogin(String Login) throws YuqueException {
        return this.listJoinedGroupById(Login);
    }

    /**
     * 获取公开组织列表
     * @return
     * @throws YuqueException
     */
    public List<GroupSerializer> listPublicGroup() throws YuqueException {
        return this.listPublicGroup(0);
    }
    public List<GroupSerializer> listPublicGroup(Integer offset) throws YuqueException {
        Map<String,String> para = new HashMap<String, String>();
        if(offset==null||offset<1){
            offset = 0;
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
    public GroupDetailVo getGroupInfoById(String groupId) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/groups/"+groupId);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        JSONObject abli = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("abilities");
        //转实体
        GroupDetailSerializer group = JSONObject.toJavaObject(json,GroupDetailSerializer.class);
        GroupAbilities groupAbilities = JSONObject.toJavaObject(abli,GroupAbilities.class);

        GroupDetailVo groupDetailVo = new GroupDetailVo();
        groupDetailVo.setAbilities(groupAbilities);
        groupDetailVo.setData(group);

        return groupDetailVo;
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
    public List<GroupUserSerializer> listGroupUserById(String groupId) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/groups/"+groupId+"/users");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);
        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
        //转实体
        List<GroupUserSerializer> groupUserSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), GroupUserSerializer.class);

        return groupUserSerializerList;
    }

    /**
     * 获取组织成员信息 by Login
     * @param Login
     * @return
     * @throws YuqueException
     */
    public List<GroupUserSerializer> listGroupUserByLogin(String Login) throws YuqueException {
        return this.listGroupUserById(Login);
    }

    /**
     * 增加或更新组织成员 by groupId
     * @param groupId
     * @param userLogin
     * @param roleId 0 - 管理员, 1 - 普通成员
     * @return
     * @throws YuqueException
     */
    public GroupUserSerializer updateGroupUserById(String groupId,String userLogin,Integer roleId) throws YuqueException {
        Map<String, String> map = new HashMap<>();
        map.put("role",roleId.toString());

        HttpPut httpPut = buildHttpPut(yuqueApiBase+"/groups/"+groupId+"/users/"+userLogin,map);
        HttpRespVo vo = doRequest(httpPut);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转实体
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
    public GroupUserSerializer updateGroupUserByLogin(String groupLogin,String userLogin,Integer roleId) throws YuqueException {
        return this.updateGroupUserById(groupLogin,userLogin,roleId);
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
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转实体
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
    public GroupUserSerializer deleteGroupUserByLogin(String groupLogin,String userLogin) throws YuqueException {
        return this.deleteGroupUserbyId(groupLogin,userLogin);
    }

    /**
     * 获取某个用户的知识库列表 by Id
     * @param userId
     * @param type
     * @param offset
     * @return
     * @throws YuqueException
     */
    public List<BookSerializer> listUserReposById(String userId,String type,Integer offset) throws YuqueException {
        Map<String,String> para = new HashMap<String, String>();
        if(offset==null||offset<1){
            offset = 0;
        }
        para.put("offset",offset.toString());
        if(type==null){
            type = "all";
        }
        para.put("type",type);

        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/users/"+userId+"/repos",para);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
        //转实体
        List<BookSerializer> bookSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), BookSerializer.class);
        return bookSerializerList;
    }

    /**
     * 获取某个用户的知识库列表 by Login
     * @param Login
     * @param type
     * @param offset
     * @return
     * @throws YuqueException
     */
    public List<BookSerializer> listUserReposByLogin(String Login,String type,Integer offset) throws YuqueException{
        return this.listUserReposById(Login,type,offset);
    }

    /**
     * 获取某个团队的知识库列表 by Id
     * @param groupId
     * @param type
     * @param offset
     * @return
     * @throws YuqueException
     */
    public List<BookSerializer> listGroupReposById(String groupId,String type,Integer offset) throws YuqueException {
        Map<String,String> para = new HashMap<>();
        if(offset==null||offset<1){
            offset = 0;
        }
        para.put("offset",offset.toString());
        if(type==null){
            type = "all";
        }
        para.put("type",type);

        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/groups/"+groupId+"/repos",para);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
        //转实体
        List<BookSerializer> bookSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), BookSerializer.class);
        return bookSerializerList;
    }

    /**
     * 获取某个团队的知识库列表 by Login
     * @param groupLogin
     * @param type
     * @param offset
     * @return
     * @throws YuqueException
     */
    public List<BookSerializer> listGroupReposByLogin(String groupLogin,String type,Integer offset) throws YuqueException {
        return this.listGroupReposById(groupLogin,type,offset);
    }

    /**
     * 往团队创建知识库 by Id
     * @param groupId
     * @param createPo
     * @return
     * @throws YuqueException
     */
    public BookSerializer createGroupRepoById(String groupId, RepoCreatePo createPo) throws YuqueException {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(createPo);
            map.put("public",createPo.getPublic_id().toString());
            map.remove("public_id");

        }catch (Exception e){
            throw new YuqueException(e.getMessage(),e);
        }
        HttpPost httpPost = buildHttpPost(yuqueApiBase+"/groups/"+groupId+"/repos",map);
        HttpRespVo vo = doRequest(httpPost);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        BookSerializer bookSerializer = JSONObject.toJavaObject(json,BookSerializer.class);
        return bookSerializer;
    }

    /**
     * 往团队创建知识库 by Login
     * @param groupLogin
     * @param createPo
     * @return
     * @throws YuqueException
     */
    public BookSerializer createGroupRepoByLogin(String groupLogin,RepoCreatePo createPo) throws YuqueException {
        return this.createGroupRepoById(groupLogin,createPo);
    }

    /**
     * 往自己下面创建知识库 by Id
     * @param userId
     * @param createPo
     * @return
     * @throws YuqueException
     */
    public BookSerializer createUserRepoById(String userId,RepoCreatePo createPo) throws YuqueException {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(createPo);
            map.put("public",createPo.getPublic_id().toString());
            map.remove("public_id");

        }catch (Exception e){
            throw new YuqueException(e.getMessage(),e);
        }
        HttpPost httpPost = buildHttpPost(yuqueApiBase+"/users/"+userId+"/repos",map);
        HttpRespVo vo = doRequest(httpPost);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        BookSerializer bookSerializer = JSONObject.toJavaObject(json,BookSerializer.class);
        return bookSerializer;
    }

    /**
     * 往自己下面创建知识库 by Login
     * @param userLogin
     * @param createPo
     * @return
     * @throws YuqueException
     */
    public BookSerializer createUserRepoByLogin(String userLogin,RepoCreatePo createPo) throws YuqueException {
        return this.createUserRepoById(userLogin,createPo);
    }

    /**
     * 获取知识库详情 by repoId
     * @param repoId
     * @return
     * @throws YuqueException
     */
    public BookDetailVo getRepoDetailById(String repoId) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/repos/"+repoId);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);
        //转jsonArr
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        JSONObject abli = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("abilities");
        //转实体
        BookDetailSerializer bookDetailSerializer =  JSONObject.toJavaObject(json,BookDetailSerializer.class);
        BookAbilities bookAbilities =  JSONObject.toJavaObject(abli,BookAbilities.class);

        BookDetailVo bookDetailVo = new BookDetailVo();
        bookDetailVo.setAbilities(bookAbilities);
        bookDetailVo.setData(bookDetailSerializer);

        return bookDetailVo;
    }

    /**
     * 获取知识库详情 by nameSpace
     * @param nameSpace
     * @return
     * @throws YuqueException
     */
    public BookDetailVo getRepoDetailByNameSpace(String nameSpace) throws YuqueException {
        return this.getRepoDetailById(nameSpace);
    }

    /**
     * 更新知识库信息 by repoId
     * @param repoId
     * @param updatePo
     * @return
     * @throws YuqueException
     */
    public BookSerializer updateRepoById(String repoId,RepoCreatePo updatePo) throws YuqueException {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(updatePo);
            map.put("public",updatePo.getPublic_id().toString());
            map.remove("public_id");

        }catch (Exception e){
            throw new YuqueException(e.getMessage(),e);
        }
        HttpPut httpPut = buildHttpPut(yuqueApiBase+"/repos/"+repoId,map);
        HttpRespVo vo = doRequest(httpPut);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        BookSerializer bookSerializer = JSONObject.toJavaObject(json,BookSerializer.class);
        return bookSerializer;
    }

    /**
     * 更新知识库信息 by nameSpace
     * @param nameSpace
     * @param updatePo
     * @return
     * @throws YuqueException
     */
    public BookSerializer updateRepoByNameSpace(String nameSpace,RepoCreatePo updatePo) throws YuqueException {
        return this.updateRepoById(nameSpace,updatePo);
    }
    /**
     * 删除知识库 by Id
     * @param repoId
     * @throws YuqueException
     */
    public BookSerializer deleteRepoById(String repoId) throws YuqueException {
        HttpDelete httpDelete = buildHttpDelete(yuqueApiBase+"/repos/"+repoId,null);
        HttpRespVo vo = doRequest(httpDelete);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        BookSerializer bookSerializer = JSONObject.toJavaObject(json,BookSerializer.class);

        return bookSerializer;
    }

    /**
     * 删除知识库 by nameSpace
     * @param nameSpace
     * @return
     * @throws YuqueException
     */
    public BookSerializer deleteRepoByNameSpace(String nameSpace) throws YuqueException {
        return this.deleteRepoById(nameSpace);
    }

    /**
     * 获取某仓库的目录 by nameSpace
     * @param nameSpace
     * @return
     * @throws YuqueException
     */
    public List<TocSerializer> listTocByNameSpace(String nameSpace) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/repos/"+nameSpace+"/toc");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
        //转实体
        List<TocSerializer> tocSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), TocSerializer.class);
        return tocSerializerList;
    }

    /**
     * 获取一个仓库的文档列表 by NameSpace
     * @param nameSpace
     * @return
     * @throws YuqueException
     */
    public List<DocSerializer> listDocByNameSpace(String nameSpace) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/repos/"+nameSpace+"/docs");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转jsonArr
        JSONArray jsonArray = JSONObject.parseObject(vo.getHttpContent()).getJSONArray("data");
        //转实体
        List<DocSerializer> docSerializerList = JSONObject.parseArray(jsonArray.toJSONString(), DocSerializer.class);
        return docSerializerList;
    }

    /**
     * 获取一个仓库的文档列表 by repoId
     * @param repoId
     * @return
     * @throws YuqueException
     */
    public List<DocSerializer> listDocByRepoId(String repoId) throws YuqueException {
        return this.listDocByNameSpace(repoId);
    }

    /**
     * 获取单篇文档的详细信息 by slug
     * @param nameSpace
     * @param slug
     * @return docSerializer
     * @throws YuqueException
     */
    public DocDetailVo getDocDetailBySlug(String nameSpace,String slug) throws YuqueException {
        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/repos/"+nameSpace+"/docs/"+slug);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        JSONObject abli = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("abilities");
        //转实体
        DocDetailSerializer docDetailSerializer = JSONObject.toJavaObject(json,DocDetailSerializer.class);
        DocAbilities abilities = JSONObject.toJavaObject(abli,DocAbilities.class);

        DocDetailVo docDetailVo = new DocDetailVo();
        docDetailVo.setData(docDetailSerializer);
        docDetailVo.setAbilities(abilities);

        return docDetailVo;
    }

    /**
     * 创建文档 by nameSpace
     * @param nameSpace
     * @param createPo
     * @return
     * @throws YuqueException
     */
    public DocSerializer createDocByNameSpace(String nameSpace, DocCreatePo createPo) throws YuqueException {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(createPo);
            map.put("public",createPo.getPublic_id().toString());
            map.remove("public_id");

        }catch (Exception e){
            throw new YuqueException(e.getMessage(),e);
        }
        HttpPost httpPost = buildHttpPost(yuqueApiBase+"/repos/"+nameSpace+"/docs",map);
        HttpRespVo vo = doRequest(httpPost);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转实体
        DocSerializer docSerializer = JSONObject.toJavaObject(json,DocSerializer.class);
        return docSerializer;
    }

    /**
     * 创建文档 by repoId
     * @param repoId
     * @param createPo
     * @return
     * @throws YuqueException
     */
    public DocSerializer createDocByRepoId(String repoId, DocCreatePo createPo) throws YuqueException {
        return this.createDocByNameSpace(repoId,createPo);
    }

    /**
     * 更新文档 by nameSpace
     * @param nameSpace
     * @param docId
     * @param updatePo
     * @return
     * @throws YuqueException
     */
    public DocSerializer updateDocByNameSpace(String nameSpace,String docId,DocCreatePo updatePo) throws YuqueException {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(updatePo);
            map.put("public",updatePo.getPublic_id().toString());
            map.remove("public_id");

        }catch (Exception e){
            throw new YuqueException(e.getMessage(),e);
        }
        HttpPut httpPut = buildHttpPut(yuqueApiBase+"/repos/"+nameSpace+"/docs/"+docId,map);
        HttpRespVo vo = doRequest(httpPut);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转实体
        DocSerializer docSerializer = JSONObject.toJavaObject(json,DocSerializer.class);
        return docSerializer;
    }

    /**
     * 更新文档 by repoId
     * @param repoId
     * @param docId
     * @param updatePo
     * @return
     * @throws YuqueException
     */
    public DocSerializer updateDocByRepoId(String repoId,String docId,DocCreatePo updatePo) throws YuqueException {
        return this.updateDocByNameSpace(repoId,docId,updatePo);
    }

    /**
     * 删除文档 by repoId
     * @param repoId
     * @param docId
     * @return
     * @throws YuqueException
     */
    public DocSerializer deleteDocByRepoId(String repoId,String docId) throws YuqueException {
        HttpDelete httpDelete = buildHttpDelete(yuqueApiBase+"/repos/"+repoId+"/docs/"+docId,null);
        HttpRespVo vo = doRequest(httpDelete);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent()).getJSONObject("data");
        //转user
        DocSerializer docSerializer = JSONObject.toJavaObject(json,DocSerializer.class);
        return docSerializer;
    }

    public JSONObject listSearch(SearchPo searchPo) throws YuqueException {
        Map<String,String> para = new HashMap<>();
        if(searchPo.getType()==null){
            throw new YuqueException("type不能为null");
        }
        para.put("type",searchPo.getType());
        if(searchPo.getQ()==null){
            throw new YuqueException("q不能为null");
        }
        para.put("q",searchPo.getQ());
        if(searchPo.getOffset()!=null){
            para.put("offset",searchPo.getOffset().toString());
        }
        if( searchPo.getRelated()!=null && searchPo.getRelated() ){
            para.put("related","true");
        }

        HttpGet httpGet = buildHttpGet(yuqueApiBase+"/search",para);
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
        //转json
        JSONObject json = JSONObject.parseObject(vo.getHttpContent());
        return json;
    }
}
