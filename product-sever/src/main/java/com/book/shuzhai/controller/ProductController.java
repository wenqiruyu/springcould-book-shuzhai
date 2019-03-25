package com.book.shuzhai.controller;

import com.book.shuzhai.entity.Product;
import com.book.shuzhai.service.IProductService;
import com.book.shuzhai.utils.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "商品服务product-server")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 获取商品列表
     * @return
     */
    @GetMapping("/product")
    @ApiOperation(value = "商品列表")
    @CrossOrigin
    public ServerResponse<List<Product>> getProductList(){
        List<Product> productList = productService.getProductList();
        if(productList == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess(productList);
    }

    /**
     * 查询分类商品
     * @param categoryId
     * @return
     */
    @ApiOperation(value = "分类商品查询", notes = "根据分类id查询该分类下的所有商品")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "Long")
    @GetMapping("/product/category/{id}")
    @CrossOrigin
    public ServerResponse<List<Product>> getProductListByCategory(@PathVariable("id") Long categoryId){
        List<Product> productList = productService.getProductListByCategory(categoryId);
        if(productList == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess(productList);
    }

    /**
     * 添加商品
     * @return
     */
    @ApiOperation(value = "商品添加")
    @PostMapping("/product")
    @CrossOrigin
    public String addProduct(Product product){
        return null;
    }

    /**
     * 根据id获取商品
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    @ApiOperation(value = "商品信息",  notes = "根据商品id获取商品的详细信息")
    @ApiImplicitParam(name = "id", value = "商品id", required = true,dataType = "Long")
    @CrossOrigin
    public ServerResponse<Product> getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        if(product == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess(product);
    }

    /**
     * 根据id更新商品
     * @param id
     * @return
     */
    @PostMapping("/product/{id}")
    @ApiOperation(value="商品更新", notes="根据url的id来指定更新对象，并根据传过来的Product信息来更新商品详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "product", value = "商品详细实体product", required = true, dataType = "Product")
    })
    @CrossOrigin
    public String updateProductById(@PathVariable("id") Long id,@RequestBody Product product){
        return null;
    }

    /**
     * 根据id删除商品
     * @param id
     * @return
     */
    @ApiOperation(value="删除商品", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Long")
    @DeleteMapping("/product/{id}")
    @CrossOrigin
    public String deleteProductById(@PathVariable("id") Long id){
        return "言念君子" + id;
    }
}
