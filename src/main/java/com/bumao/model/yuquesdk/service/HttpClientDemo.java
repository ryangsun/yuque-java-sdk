package com.bumao.model.yuquesdk.service;

import com.bumao.model.yuquesdk.domain.*;
import com.bumao.model.yuquesdk.exception.YuqueException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HttpClientDemo extends HttpClientHolder {
    @Test
    public void doGetTestOne() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("https://www.yuque.com/api/v2/repos/u1486894/blog/docs/dbi9ym");
        httpGet.setHeader("X-Auth-Token","yuque-token");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException!!!!");
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void doGetTestTwo() throws YuqueException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("https://www.yuque.com/api/v2/repos/u1486894/blog/docs/dbi9ym");

        //设置权限头信息
        httpGet.setHeader("X-Auth-Token","yuque-token");
        //发送请求
        HttpRespVo vo = doRequest(httpGet);
//        System.out.println(vo.getHttpStatusLine());
//        System.out.println(vo.getHttpContent());
//        log.info("vo={}",vo);
//        System.out.println(vo);
    }
    @Test
    public void TestGetUser() throws YuqueException {
        YuqueClient client = new YuqueClient("yuque-token");

        UserSerializer user = client.getUserInfoById("1757692");
        System.out.println(user);

        UserSerializer user1 = client.getUserInfoByLogin("u1486894");
        System.out.println(user1);

        UserSerializer user2 = client.getCurrUserInfo();
        System.out.println(user2);
    }

    @Test
    public void TestGroup() throws YuqueException {
        YuqueClient client = new YuqueClient("yuque-token");

//        System.out.println("--获取某个用户的加入的组织列表 by Id--");
//        List<GroupSerializer> groupList = client.getJoinedGroupById("1757692");
//        System.out.println(groupList);
//
//        System.out.println("--获取某个用户的加入的组织列表 by Login--");
//        List<GroupSerializer> groupList1 = client.getJoinedGroupByLogin("u1486894");
//        System.out.println(groupList1);
//
//        System.out.println("--获取公开组织列表--");
//        List<GroupSerializer> groupList3 = client.getPublicGroup();
//        System.out.println(groupList3);
//        System.out.println(groupList3.size());
//
//        List<GroupSerializer> groupList2 = client.getPublicGroup(999);
//        System.out.println(groupList2);
//        System.out.println(groupList2.size());
//
//        System.out.println("--获取单个组织的详细信息--");
//        GroupSerializer group = client.getGroupInfoById("firstGroup");
//        System.out.println(group);

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
//        List<GroupUserSerializer> groupUserSerializerList = client.getGroupUserById("firstGroup");
//        System.out.println(groupUserSerializerList);

//        System.out.println("--增加或更新组织成员--");
//        GroupUserSerializer UpgroupUserSerializer = client.updateGroupUserbyId("firstGroup","zhangxin_ux",1);
//        System.out.println(UpgroupUserSerializer);

        System.out.println("--删除组织成员--");
        GroupUserSerializer DelgroupUserSerializer = client.deleteGroupUserbyId("firstGroup","zhangxin_ux");
        System.out.println(DelgroupUserSerializer);


    }

    @Test
    public void TestRepo() throws YuqueException {
        YuqueClient client = new YuqueClient("yuque-token");

        System.out.println("--获取某个用户的知识库列表 by Id--");
        List<BookSerializer> bookSerializerList = client.listUserReposById("u1486894","all",0);
        System.out.println(bookSerializerList);

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
//        BookSerializer bookSerializerDetail = client.getRepoDetailById("20113349");
//        System.out.println(bookSerializerDetail);

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

        System.out.println("--获取一个仓库的文档列表 by nameSpace--");
        List<DocSerializer> docSerializerList = client.listDocByNameSpace("20113349");
        System.out.println(docSerializerList);

//        System.out.println("--获取单篇文档的详细信息 by slug--");
//        DocSerializer docSerializer = client.getDocDetailBySlug("u1486894/nn3k9e","helloword1");
//        System.out.println(docSerializer);

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

        System.out.println("--删除文档 by repoId--");
        DocSerializer docSerializerDel = client.deleteDocByRepoId("20113349","46939326");
        System.out.println(docSerializerDel);
    }
}
