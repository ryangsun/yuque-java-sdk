package com.bumao.model.yuquesdk.service;

import com.alibaba.fastjson.JSONObject;
import com.bumao.model.yuquesdk.vo.HttpRespVo;
import com.bumao.model.yuquesdk.exception.YuqueException;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Data
public class HttpClientHolder {
    private String yuqueToken = null;

    void HttpClientHolder(String Token){
        this.yuqueToken = Token;
    }

    /**
     * 造GET
     * @param URI
     * @return
     */
    public HttpGet buildHttpGet(String URI) throws YuqueException {
        return this.buildHttpGet(URI,null);
    }
    public HttpGet buildHttpGet(String URI, Map<String, String> para) throws YuqueException {
        HttpGet httpGet = null;
        try {
            URIBuilder builder = new URIBuilder(URI);
            if (para != null) {
                Set<String> set = para.keySet();
                for (String key : set) {
                    builder.setParameter(key, para.get(key));
                }
            }
            httpGet = new HttpGet(builder.build());
        }catch (URISyntaxException e){
            throw new YuqueException(e.getMessage(),e);
        }
        return httpGet;
    }

    /**
     * 造POST
     * @param URI
     * @param map
     * @return
     * @throws YuqueException
     */
    public HttpPost buildHttpPost(String URI,Map<String,String> map) throws YuqueException {
        HttpPost httpPost = null;
        try {
            URIBuilder builder = new URIBuilder(URI);
            if(map!=null) {
                List<NameValuePair> pairs = new ArrayList<>();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                builder.setParameters(pairs);
            }
            httpPost = new HttpPost(builder.build());
        }catch (URISyntaxException e){
            throw new YuqueException(e.getMessage(),e);
        }
        return httpPost;
    }
    /**
     * 造PUT
     * @param URI
     * @param map
     * @return
     * @throws YuqueException
     */
    public HttpPut buildHttpPut(String URI, Map<String,String> map) throws YuqueException {
        HttpPut httpPut = null;
        try {
            URIBuilder builder = new URIBuilder(URI);
            if(map!=null) {
                List<NameValuePair> pairs = new ArrayList<>();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                builder.setParameters(pairs);
            }
            httpPut = new HttpPut(builder.build());
        }catch (URISyntaxException e){
            throw new YuqueException(e.getMessage(),e);
        }
        return httpPut;
    }
    /**
     * 造HttpDelete
     * @param URI
     * @param map
     * @return
     * @throws YuqueException
     */
    public HttpDelete buildHttpDelete(String URI, Map<String,String> map) throws YuqueException {
        HttpDelete httpDelete = null;
        try {
            URIBuilder builder = new URIBuilder(URI);
            if(map!=null) {
                List<NameValuePair> pairs = new ArrayList<>();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                builder.setParameters(pairs);
            }
            httpDelete = new HttpDelete(builder.build());
        }catch (URISyntaxException e){
            throw new YuqueException(e.getMessage(),e);
        }
        return httpDelete;
    }
    /**
     * 发送http请求
     * @param httpRequestBase
     * @return
     */
    public HttpRespVo doRequest(HttpRequestBase httpRequestBase) throws YuqueException {
        //设置权限头信息
        httpRequestBase.setHeader("X-Auth-Token",this.yuqueToken);
        //解决cookies报错问题
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpRequestBase.setConfig(defaultConfig);

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 响应模型
        CloseableHttpResponse response = null;
        HttpEntity responseEntity = null;
        HttpRespVo vo = new HttpRespVo();
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpRequestBase);
            // 从响应模型中获取响应实体
            responseEntity = response.getEntity();
            vo.setHttpStatusLine(response.getStatusLine().toString());
            vo.setHttpCode(response.getStatusLine().getStatusCode());
            if (responseEntity != null) {
                vo.setHttpContent(EntityUtils.toString(responseEntity));
            }
            if( !vo.getHttpCode().equals(200) ){
                Map<String,String> digMap = dig400(vo.getHttpContent());
                if(digMap==null){
                    throw new YuqueException(
                        vo.getHttpCode(),
                        vo.getHttpStatusLine(),
                        vo.getHttpContent(),
                        "unknow Message",
                        "unknow Code",
                        -1
                    );
                }else{
                    throw new YuqueException(
                        vo.getHttpCode(),
                        vo.getHttpStatusLine(),
                        vo.getHttpContent(),
                        digMap.get("message"),
                        digMap.get("code"),
                        Integer.parseInt(digMap.get("status")==null?"-1":digMap.get("status"))
                    );
                }
            }
        } catch (ClientProtocolException e) {
            throw new YuqueException(e.getMessage(),e);
        } catch (ParseException e) {
            throw new YuqueException(e.getMessage(),e);
        } catch (IOException e) {
            throw new YuqueException(e.getMessage(),e);
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
                throw new YuqueException(e.getMessage(),e);
            }
        }
        return vo;
    }
    /**
     * 分析返回的非标json
     * @param content
     * @return
     */
    private Map<String,String> dig400(String content){
        Map<String,String> map = new HashMap<>();
        try {
            JSONObject json = JSONObject.parseObject(content);
            map.put("message",json.getString("message"));
            map.put("code",json.getString("code"));
            map.put("status",json.getString("status"));
            return map;
        }catch (Exception e){
            return null;
        }

    }
}
