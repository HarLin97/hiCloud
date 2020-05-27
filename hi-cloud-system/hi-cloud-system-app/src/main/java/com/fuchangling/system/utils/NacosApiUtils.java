package com.fuchangling.system.utils;

import com.fuchangling.system.pojo.dto.NacosNamespaceDTO;
import com.fuchangling.system.pojo.dto.NacosResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * nacos相关操作api
 *
 * @author harlin
 */
public class NacosApiUtils {

    @Value("#{spring.cloud.nacos.server-addr}")
    private String nacosUrl;

    public String getNacosUrl() {
        return nacosUrl;
    }

    private final String NACOS_LOGIN_URL = "http://" + getNacosUrl() + "/nacos/v1/auth/login";

    @Autowired
    private RestTemplate restTemplate;

    public String login() {
        return null;
    }


    /**
     * 向目的URL发送post请求
     *
     * @param url    目的url
     * @param params 发送的参数
     * @return ResultVO
     */
    public static NacosResultDTO sendPostRequest(String url, MultiValueMap<String, String> params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<NacosResultDTO> response = client.exchange(url, method, requestEntity, NacosResultDTO.class);

        return response.getBody();
    }

    public static void main(String[] args) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "nacos");
        params.add("password", "nacos");
        params.add("namespaceId", "");
//        System.out.println(sendPostRequest("http://localhost:8848/nacos/v1/auth/login", params).getData());
        System.out.println(sendGetRequest("http://localhost:8848/nacos/v1/console/namespaces?namespaceId=", null, NacosNamespaceDTO.class));
    }

    public static Object sendGetRequest(String url, MultiValueMap<String, String> params, Class clazz) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.GET;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWNvcyIsImF1dGgiOiIiLCJleHAiOjE1ODg1OTUwNTh9.qV7OpPFbOPbQrDGw-x2XBh48zt6dX3fDPmk8Vor0MHs");
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity response = client.exchange(url, method, requestEntity, clazz);

        return response.getBody();
    }
}
