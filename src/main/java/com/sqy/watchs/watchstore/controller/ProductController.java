package com.sqy.watchs.watchstore.controller;


import com.sqy.watchs.watchstore.pojo.RespData;
import com.sqy.watchs.watchstore.pojo.entity.Product;
import com.sqy.watchs.watchstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends HoshiController {
    @Autowired
    private ProductService productService;

    /**
     * 增加商品
     *
     * @param product
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(Product product) {
        return $(respData -> {
            productService.save(product);
            String id = product.getId();
            if (id == null && id.equals("")) {
                respData.success(false).msg("添加失败");
            } else {
                respData.success(true).data(id).msg("添加成功");
            }
        });
    }

    /**
     * 根据ID删除商品
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del")
    public RespData<Boolean> del(String id) {
        return $(booleanRespData -> {
            if (id == null) {
                booleanRespData.success(false).msg("未发现该ID");
            } else {
                productService.removeById(id);
                booleanRespData.success(true).data(true).msg("已删除");
            }
        });
    }

    /**
     * 更加ID更改商品
     * @param product
     * @return
     */
    @PutMapping("/update")
    public RespData<Boolean> update(Product product) {
        return $(booleanRespData -> {
            if (product.getId() == null && product.getId().equals("")) {
                booleanRespData.success(false).msg("未找到更改ID");
            } else {
                Product proID = productService.getById(product.getId());
                proID.setName(product.getName());
                proID.setDecimal(product.getDecimal());
                proID.setIntroduction(product.getIntroduction());
                productService.updateById(proID);
                booleanRespData.success(true).data(true).msg("更改成功");
            }
        });
    }
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public RespData<List<Product>> search(@RequestParam(name = "search",required = false,defaultValue = "") String key,
                                          @PathVariable int pageIndex, @PathVariable int pageSize){
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<Product> productList = productService.listSearch(key);
            listRespData.success(true).data(productList);
        });
    }
}
