package com.demo.qywx.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.math.BigDecimal;

/**
 * @author jjxiek
 * @since 2019/11/26 9:16
 */
@RestController
@RequestMapping("/rest")
public class RestTemplateController {
    @GetMapping("/get_product1")
    public Product get_product1() {
        return new Product(1, "ProductA", BigDecimal.valueOf(6666.0));
    }

    @GetMapping("/get_product2")
    public Product get_product2(Integer id) {
        return new Product(id, "ProductC", BigDecimal.valueOf(6666.0));
    }

    @GetMapping("/get_product3")
    public String get_product3(Product product) {
        return product.toString();
    }


    @PostMapping("/post_product1")
    public String post_product1(Product product) {
        return product.toString();
    }

    @PostMapping("/post_product2")
    public String post_product2(@RequestBody Product product) {
        return product.toString();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        String result = String.format("编号为%s的产品删除成功", id);
        System.out.println(result);
        return result;
    }

    @PutMapping("/update")
    public String updateByPut(Product product) {
        String result = product.toString() + " 更新成功";
        System.out.println(result);
        return result;
    }

    @PostMapping("/upload")
    public String upload(MultipartRequest request) {
        // Spring MVC 使用 MultipartRequest 接受带文件的 HTTP 请求
        MultipartFile file = request.getFile("file");
        String originalFilename = file.getOriginalFilename();
        return "upload success filename: " + originalFilename;
    }
}
