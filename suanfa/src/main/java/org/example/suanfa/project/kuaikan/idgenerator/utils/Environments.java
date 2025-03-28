package org.example.suanfa.project.kuaikan.idgenerator.utils;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.suanfa.project.kuaikan.database.Environment;
import org.example.suanfa.project.kuaikan.database.Settings;

/**
 * Created by XuFan on 2019/5/27
 */
@Slf4j
public class Environments {

    private final static CloseableHttpClient DEFAULT_CLIENT = HttpClients.custom()
            .setDefaultRequestConfig(RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(2000).build())
            .build();

    private static String zkAddress;

    static {
        init();
    }

    private static void init() {
        zkAddress = System.getProperty("id-generator.zk");
        if (StringUtils.isEmpty(zkAddress)) {
            Environment env = Settings.getEnvironment();
            String providerDomain = env.isProd() ? "http://openapi-inner.quickcan.com" : "http://openapi.quickcan.cn";
            zkAddress = get(providerDomain + "/inner/id_generator/env/zk_address");
        }
        log.info("id generator zookeeper:{}", zkAddress);
    }

    public static String getZkAddress() {
        return zkAddress;
    }

    public static String getServiceName() {
        return StringUtils.defaultIfBlank(Settings.getServiceName(), "unknown");
    }

    private static String get(String uri) {
        HttpGet httpGet = new HttpGet(uri);
        return requestWithRetry(httpGet, 1);
    }

    private static String requestWithRetry(HttpUriRequest request, int retryTimes) {

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = DEFAULT_CLIENT.execute(request);
            StatusLine status = httpResponse.getStatusLine();
            HttpEntity entity = httpResponse.getEntity();
            String response = entity == null ? null : EntityUtils.toString(entity);
            if (status == null || status.getStatusCode() != HttpStatus.SC_OK) {
                log.error("httpResponse status is:{},request:{},message:{}", status, request, response);
            }
            return response;
        } catch (Exception e) {
            log.error("http request exception,request:{},will retry {} times", request, retryTimes, e);
            if (retryTimes > 0) {
                return requestWithRetry(request, retryTimes - 1);
            }
        } finally {
            HttpClientUtils.closeQuietly(httpResponse);
        }
        return null;
    }
}
