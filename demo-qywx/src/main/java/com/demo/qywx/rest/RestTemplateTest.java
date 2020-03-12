package com.demo.qywx.rest;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jjxiek
 * @since 2019/11/26 9:40
 */
public class RestTemplateTest {
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testGet_product1_simple() {
        String url = "http://localhost:8080/rest/get_product1";
        //方式一：GET 方式获取 JSON 串数据
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("get_product1返回结果：" + result);
        Assert.hasText(result, "get_product1返回结果为空");

        //方式二：GET 方式获取 JSON 数据映射后的 Product 实体对象
        Product product = restTemplate.getForObject(url, Product.class);
        System.out.println("get_product1返回结果：" + product);
        Assert.notNull(product, "get_product1返回结果为空");

        //方式三：GET 方式获取包含 Product 实体对象 的响应实体 ResponseEntity 对象,用 getBody() 获取
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url, Product.class);
        System.out.println("get_product1返回结果：" + responseEntity);
        Assert.isTrue(responseEntity.getStatusCode().equals(HttpStatus.OK), "get_product1响应不成功");
    }
    @Test
    public void testGet_product1() {
        String url = "http://localhost:8080/rest/get_product1";
        //....

        //方式一： 构建请求实体 HttpEntity 对象，用于配置 Header 信息和请求参数
        MultiValueMap header = new LinkedMultiValueMap();
        header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Object> requestEntity = new HttpEntity<>(header);
        //方式二： 执行请求获取包含 Product 实体对象 的响应实体 ResponseEntity 对象,用 getBody() 获取
        ResponseEntity<Product> exchangeResult = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Product.class);
        System.out.println("get_product1返回结果：" + exchangeResult);
        Assert.isTrue(exchangeResult.getStatusCode().equals(HttpStatus.OK), "get_product1响应不成功");

        //方式三： 根据 RequestCallback 接口实现类设置Header信息,用 ResponseExtractor 接口实现类读取响应数据
        String executeResult = restTemplate.execute(url, HttpMethod.GET, request -> {
            request.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        }, (clientHttpResponse) -> {
            InputStream body = clientHttpResponse.getBody();
            byte[] bytes = new byte[body.available()];
            body.read(bytes);
            return new String(bytes);
        }); // 备注：这里使用了 Java8 特性：Lambda 表达式语法，若未接触 Lambda 表达式后可以使用匿名内部类代替实现
        System.out.println("get_product1返回结果：" + executeResult);
        Assert.hasText(executeResult, "get_product1返回结果为空");
    }

    @Test
    public void testGet_product2() {
        String url = "http://localhost:8080/rest/get_product2?id={id}";

        //方式一：将参数的值存在可变长度参数里，按照顺序进行参数匹配
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url, Product.class, 101);
        System.out.println(responseEntity);
        Assert.isTrue(responseEntity.getStatusCode().equals(HttpStatus.OK), "get_product2 请求不成功");
        Assert.notNull(responseEntity.getBody().getId(), "get_product2  传递参数不成功");

        //方式二：将请求参数以键值对形式存储到 Map 集合中，用于请求时URL上的拼接
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("id", 101);
        Product result = restTemplate.getForObject(url, Product.class, uriVariables);
        System.out.println(result);
        Assert.notNull(result.getId(), "get_product2  传递参数不成功");
    }

    /**
     * 发送 Content-Type 为 application/x-www-form-urlencoded 的 POST 请求
     */
    @Test
    public void testPost_product1() {
        String url = "http://localhost:8080/rest/post_product1";
        Product product = new Product(201, "Macbook", BigDecimal.valueOf(10000));
        // 设置请求的 Content-Type 为 application/x-www-form-urlencoded
        MultiValueMap<String, String> header = new LinkedMultiValueMap();
        header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

        //方式二： 将请求参数值以 K=V 方式用 & 拼接，发送请求使用
        String productStr = "id=" + product.getId() + "&name=" + product.getName() + "&price=" + product.getPrice();
        HttpEntity<String> request = new HttpEntity<>(productStr, header);
        ResponseEntity<String> exchangeResult = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("post_product1: " + exchangeResult);
        Assert.isTrue(exchangeResult.getStatusCode().equals(HttpStatus.OK), "post_product1 请求不成功");

        //方式一： 将请求参数以键值对形式存储在 MultiValueMap 集合，发送请求时使用
        MultiValueMap<String, Object> map = new LinkedMultiValueMap();
        map.add("id", (product.getId()));
        map.add("name", (product.getName()));
        map.add("price", (product.getPrice()));
        HttpEntity<MultiValueMap> request2 = new HttpEntity<>(map, header);
        ResponseEntity<String> exchangeResult2 = restTemplate.exchange(url, HttpMethod.POST, request2, String.class);
        System.out.println("post_product1： " + exchangeResult2);
        Assert.isTrue(exchangeResult.getStatusCode().equals(HttpStatus.OK), "post_product1 请求不成功");
    }

    /**
     * 发送 Content-Type 为 application/json 的 POST 请求
     */
    @Test
    public void testPost_product2() {
        String url = "http://localhost:8080/rest/post_product2";

        // 设置请求的 Content-Type 为 application/json
        MultiValueMap<String, String> header = new LinkedMultiValueMap();
        header.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(MediaType.APPLICATION_JSON_VALUE));
        // 设置 Accept 向服务器表明客户端可处理的内容类型
        header.put(HttpHeaders.ACCEPT, Arrays.asList(MediaType.APPLICATION_JSON_VALUE));
        // 直接将实体 Product 作为请求参数传入，底层利用 Jackson 框架序列化成 JSON 串发送请求
        HttpEntity<Product> request = new HttpEntity<>(new Product(2, "Macbook", BigDecimal.valueOf(10000)), header);
        ResponseEntity<String> exchangeResult = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        System.out.println("post_product2: " + exchangeResult);
        Assert.isTrue(exchangeResult.getStatusCode().equals(HttpStatus.OK), "post_product2 请求不成功");
    }

    // DELETE 方法请求
    @Test
    public void testDelete() {
        String url = "http://localhost:8080/product/delete/{id}";
        restTemplate.delete(url, 101);
    }

    // PUT 方法请求
    @Test
    public void testPut() {
        String url = "http://localhost:8080/product/update";
        Map<String, ?> variables = new HashMap<>();
        MultiValueMap<String, String> header = new LinkedMultiValueMap();
        header.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        Product product = new Product(101, "iWatch", BigDecimal.valueOf(2333));
        String productStr = "id=" + product.getId() + "&name=" + product.getName() + "&price=" + product.getPrice();
        HttpEntity<String> request = new HttpEntity<>(productStr, header);
        restTemplate.put(url, request);
    }

    @Test
    public void testUploadFile() {
        String url = "http://localhost:8080/rest/upload";
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        FileSystemResource file = new FileSystemResource(new File("material/002.txt"));
        body.add("file", file);

        MultiValueMap<String, String> header = new LinkedMultiValueMap();
        header.put(HttpHeaders.CONTENT_TYPE, Arrays.asList(MediaType.MULTIPART_FORM_DATA_VALUE));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, header);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println("upload: " + responseEntity);
        Assert.isTrue(responseEntity.getStatusCode().equals(HttpStatus.OK), "upload 请求不成功");
    }

}
