package com.bumao.model.yuquesdk.service;

import com.bumao.model.yuquesdk.config.YuqueConfig;
import com.bumao.model.yuquesdk.domain.HttpRespVo;
import com.bumao.model.yuquesdk.exception.YuqueException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientHolder {
    protected YuqueConfig config;

    private String yuqueApiBase = null;
    private String yuqueToken = null;
    public HttpGet mkGet(String URI){
        // 创建Get请求
        HttpGet httpGet = new HttpGet(URI);
        //设置权限头信息
        httpGet.setHeader("X-Auth-Token","EwIAnaYbkUp8hwfOumMEee3swog5lFOOlNLwvaon");
        return httpGet;
    }
    /**
     * 发送http请求
     * @param httpRequestBase
     * @return
     */
    public HttpRespVo doRequest(HttpRequestBase httpRequestBase) throws YuqueException {
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
                new YuqueException.Builder()
                        .setHttpCode(vo.getHttpCode())
                        .setHttpStatusLine(vo.getHttpStatusLine())
                        .setOrigContent(vo.getHttpContent())
                        .setCustomErrorMsg("返回代码非200")
                        .build();
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
}
