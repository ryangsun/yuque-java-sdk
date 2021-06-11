package com.bumao.model.yuquesdk.service;

import com.alibaba.fastjson.JSONObject;
import com.bumao.model.yuquesdk.domain.*;
import com.bumao.model.yuquesdk.exception.YuqueException;
import com.bumao.model.yuquesdk.po.SearchPo;
import com.bumao.model.yuquesdk.vo.BookDetailVo;
import com.bumao.model.yuquesdk.vo.DocDetailVo;
import com.bumao.model.yuquesdk.vo.GroupDetailVo;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class HttpClientDemo extends HttpClientHolder {
    @Test
    public void TestGetUser() throws YuqueException {
        YuqueClient client = new YuqueClient("aaa");

//        System.out.println("--获取某个用户信息 by Id--");
//        UserDetailSerializer userDetailSerializer = client.getUserInfoById("1757692");
//        System.out.println(userDetailSerializer);

        System.out.println("--获取某个用户信息 by Login--");
        UserDetailSerializer user1 = client.getUserInfoByLogin("zhangxin_ux");
        System.out.println(user1);
        System.out.println(JSONObject.toJSONString(user1) );
//
//        System.out.println("--获取当前用户信息--");
//        UserDetailSerializer user2 = client.getCurrUserInfo();
//        System.out.println(user2);
    }

    @Test
    public void TestGroup() throws YuqueException {
        YuqueClient client = new YuqueClient("yuque-token");

//        System.out.println("--获取某个用户的加入的组织列表 by Id--");
//        List<GroupSerializer> groupList = client.listJoinedGroupById("1757692");
//        System.out.println(groupList);
//
//        System.out.println("--获取某个用户的加入的组织列表 by Login--");
//        List<GroupSerializer> groupList1 = client.listJoinedGroupByLogin("u1486894");
//        System.out.println(groupList1);
//
        System.out.println("--获取公开组织列表--");
        List<GroupSerializer> groupList3 = client.listPublicGroup();
        System.out.println(groupList3);
        System.out.println(groupList3.size());
//
//        List<GroupSerializer> groupList2 = client.listPublicGroup(99);
//        System.out.println(groupList2);
//        System.out.println(groupList2.size());

        System.out.println("--获取单个组织的详细信息--");
        GroupDetailVo groupDetailVo = client.getGroupInfoById("antv");
        System.out.println(groupDetailVo.getData());
        System.out.println(groupDetailVo.getAbilities());

//        System.out.println("--创建组织--");
//        GroupCreatePo createPo = new GroupCreatePo();
//        createPo.setName("firstGroup1");
//        createPo.setLogin("firstGroup2");
//        createPo.setDescription("firstGroup");
//        try {
//            GroupSerializer newGroup = client.createGroup(createPo);
//            System.out.println(newGroup);
//        }catch (YuqueException e){
//            System.out.println(e);
//            System.out.println("创建组织错误：httpCode="+e.getHttpCode()+",code="+e.getOrigCode()+",message="+e.getOrigMsg());
//        }

//        System.out.println("--更新组织--");
//        GroupCreatePo updatePo = new GroupCreatePo();
//        updatePo.setName("first被更新");
//        updatePo.setLogin("firstGroup2");
//        updatePo.setDescription("1234567890");
//        try {
//            GroupSerializer newGroup = client.updateGroupById("21807875",updatePo);
//            System.out.println(newGroup);
//        }catch (YuqueException e){
//            System.out.println(e);
//            if(e.getHttpCode().equals(400)) {
//                System.out.println("更新组织错误：httpCode=" + e.getHttpCode() + ",code=" + e.getOrigCode() + ",message=" + e.getOrigMsg());
//            }
//        }

//        System.out.println("--删除组织--");
//        try {
//            client.deleteGroupById("21807875");
//        }catch (YuqueException e){
//            System.out.println(e);
//        }

//        System.out.println("--获取组织成员信息--");
//        List<GroupUserSerializer> groupUserSerializerList = client.listGroupUserById("firstGroup");
//        System.out.println(groupUserSerializerList);

//        System.out.println("--增加或更新组织成员--");
//        GroupUserSerializer UpgroupUserSerializer = client.updateGroupUserbyId("firstGroup","zhangxin_ux",1);
//        System.out.println(UpgroupUserSerializer);

//        System.out.println("--删除组织成员--");
//        GroupUserSerializer DelgroupUserSerializer = client.deleteGroupUserbyId("firstGroup","zhangxin_ux");
//        System.out.println(DelgroupUserSerializer);

    }

    @Test
    public void TestRepo() throws YuqueException {
        YuqueClient client = new YuqueClient("yuque-token");

//        System.out.println("--获取某个用户的知识库列表 by Id--");
//        List<BookSerializer> bookSerializerList = client.listUserReposById("u1486894","all",0);
//        System.out.println(bookSerializerList);

//        System.out.println("--获取某个团队的知识库列表 by Id--");
//        List<BookSerializer> groupbookSerializerList = client.listGroupReposById("firstgroup","all",0);
//        System.out.println(groupbookSerializerList);

//        System.out.println("--往团队创建知识库 by Id--");
//        RepoCreatePo createPo = new RepoCreatePo();
//        createPo.setDescription("介绍");
//        createPo.setName("名称");
//        createPo.setPublic_id(1);
//        createPo.setSlug("abc");
//        createPo.setType("Book");
//        BookSerializer bookSerializer = client.createGroupRepoById("firstgroup",createPo);
//        System.out.println(bookSerializer);

//        System.out.println("--往自己下面创建知识库 by Id--");
//        RepoCreatePo cPo = new RepoCreatePo();
//        cPo.setDescription("介绍");
//        cPo.setName("名称");
//        cPo.setPublic_id(1);
//        cPo.setSlug("abc");
//        cPo.setType("Book");
//        BookSerializer bookSerializer = client.createUserRepoById("u1486894",cPo);
//        System.out.println(bookSerializer);
//
//        System.out.println("--获取知识库详情 by Id--");
//        BookDetailVo bookDetailVo = client.getRepoDetailById("20113349");
//        System.out.println(bookDetailVo.getData());//repo
//        System.out.println(bookDetailVo.getAbilities());//Abilities

//        System.out.println("--更新知识库信息 by repoId--");
//        RepoCreatePo upPo = new RepoCreatePo();
//        upPo.setDescription("介绍");
//        upPo.setName("名称");
//        upPo.setPublic_id(0);
//        upPo.setSlug("abc");
//        upPo.setType("Book");
//        BookSerializer bookSerializerUp = client.updateRepoById("20167466",upPo);
//        System.out.println(bookSerializerUp);

//        System.out.println("--删除知识库 by repoId--");
//        BookSerializer bookSerializerDel = client.deleteRepoById("u1486894/slug");
//        System.out.println(bookSerializerDel);

    }
    @Test
    public void TestToc() throws YuqueException {
        YuqueClient client = new YuqueClient("yuque-token");

        System.out.println("--获取某仓库的目录 by nameSpace--");
        List<TocSerializer> tocSerializerList = client.listTocByNameSpace("u1486894/nn3k9e");
        System.out.println(tocSerializerList);
    }
    @Test
    public void TestDoc() throws YuqueException {
        YuqueClient client = new YuqueClient("yuque-token");

//        System.out.println("--获取一个仓库的文档列表 by nameSpace--");
//        List<DocSerializer> docSerializerList = client.listDocByNameSpace("20113349");
//        System.out.println(docSerializerList);

//        System.out.println("--获取单篇文档的详细信息 by slug--");
//        DocDetailVo docDetailVo = client.getDocDetailBySlug("u1486894/nn3k9e","hgg9lg");
//        System.out.println(docDetailVo.getData());//文章
//        System.out.println(docDetailVo.getAbilities());//Abilities

//        System.out.println("--创建文档 by nameSpace--");
//        DocCreatePo createPo = new DocCreatePo();
//        createPo.setTitle("helloword");
//        createPo.setSlug("helloword1");
//        createPo.setPublic_id(1);
//        createPo.setFormat("markdown");
//        createPo.setBody("#helloword");
//        DocSerializer docSerializerCreate = client.createDocByNameSpace("u1486894/nn3k9e",createPo);
//        System.out.println(docSerializerCreate);

//        System.out.println("--更新文档 by nameSpace--");
//        DocCreatePo upPo = new DocCreatePo();
//        upPo.setTitle("helloword");
//        upPo.setSlug("helloword1");
//        upPo.setPublic_id(1);
//        upPo.setFormat("markdown");
//        upPo.setBody("# helloword11111111");
//        DocSerializer docSerializerUp = client.updateDocByNameSpace("u1486894/nn3k9e","46939700",upPo);
//        System.out.println(docSerializerUp);

//        System.out.println("--删除文档 by repoId--");
//        DocSerializer docSerializerDel = client.deleteDocByRepoId("20113349","46939326");
//        System.out.println(docSerializerDel);
    }

    @Test
    public void TestMaster() throws YuqueException {
        YuqueClient client = new YuqueClient("yuque-token");

        System.out.println("--搜索--");
        SearchPo searchPo = new SearchPo();
        searchPo.setType("doc");
        searchPo.setQ("世界上最好的语言");
        JSONObject obj = client.listSearch(searchPo);
        System.out.println(obj);
    }
}
