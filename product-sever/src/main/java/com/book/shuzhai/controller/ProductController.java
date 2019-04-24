package com.book.shuzhai.controller;

import com.alibaba.fastjson.JSON;
import com.book.shuzhai.entity.Comment;
import com.book.shuzhai.entity.Product;
import com.book.shuzhai.feign.RedisFeignClient;
import com.book.shuzhai.service.IProductService;
import com.book.shuzhai.utils.Base64;
import com.book.shuzhai.utils.ServerResponse;
import com.sun.xml.internal.ws.spi.db.DatabindingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.print.attribute.standard.Severity;
import java.util.*;

@RestController
@Api(tags = "商品服务product-server")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private RedisFeignClient redisFeignClient;

    /**
     * 主图存储位置
     */
    private static String mainUrl = "C:\\Users\\22341\\Desktop\\picture\\shuzhai-img\\main-img\\";

    /**
     * 主图存储位置
     */
    private static String subUrl = "C:\\Users\\22341\\Desktop\\picture\\shuzhai-img\\sub-img\\";

    /**
     * 主图存储位置
     */
    private static String thumbnailUrl = "C:\\Users\\22341\\Desktop\\picture\\shuzhai-img\\thumbnail-img\\";
    /**
     * 获取商品列表
     * @return
     */
    @GetMapping("/product")
    @ApiOperation(value = "商品列表")
    @CrossOrigin
    public ServerResponse<List<Product>> getProductList(){
        // 进行添加redis数据库，加快访问速度
        String jsonList = redisFeignClient.toGetJsonList("all-product-list");
        List<Product> allProductList = JSON.parseArray(jsonList, Product.class);
        if(allProductList != null){
            return ServerResponse.createBySuccess(allProductList);
        }
        List<Product> productList = productService.getProductList();
        if(productList == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        redisFeignClient.toSaveJsonList("all-product-list", JSON.toJSON(productList).toString());
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
        // 先查询redis中是否存储
        String categoryProduct = redisFeignClient.toGetJsonList("product-category-" + categoryId);
        List<Product> products = JSON.parseArray(categoryProduct, Product.class);
        if(products != null){
            return ServerResponse.createBySuccess(products);
        }
        List<Product> productList = productService.getProductListByCategory(categoryId);
        if(productList == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        // 将分类的商品进行存储缓存
        redisFeignClient.toSaveJsonList("product-category-" + categoryId, JSON.toJSON(productList).toString());
        return ServerResponse.createBySuccess(productList);
    }

    /**
     * 添加商品
     * @return
     */
    @ApiOperation(value = "商品添加")
    @PostMapping("/product")
    @CrossOrigin
    public ServerResponse<String> addProduct(@RequestBody Product product){
        // 在后台进行对数据库的更新的时候需要对redis也进行同步更新，保证数据的一致性
        // 主图存储路径
        String mainImgFilePath = mainUrl + UUID.randomUUID().toString() + ".jpg";
        // 将图片主图进行存储
        Base64.GenerateImage(product.getMainImg(),mainImgFilePath);
        // 附图处理
        String allSubImgUrl = "";
        String[] subSplit = product.getSubImg().split(",");
        for(int i = 0; i<subSplit.length; i++){
            // 附图存储路径
            String subImgFilePath = subUrl + UUID.randomUUID().toString() + ".jpg";
            Base64.GenerateImage(subSplit[i],subImgFilePath);
            if(i != subSplit.length-1){
                allSubImgUrl += subImgFilePath + ",";
            }else{
                allSubImgUrl += subImgFilePath;
            }
        }
        // 缩略图处理
        String allThumbnailImgUrl = "";
        String[] thumbnailSplit = product.getThumbnail().split(",");
        for(int j = 0; j<thumbnailSplit.length; j++){
            // 附图存储路径
            String thumbnailImgFilePath = thumbnailUrl + UUID.randomUUID().toString() + ".jpg";
            Base64.GenerateImage(thumbnailSplit[j],thumbnailImgFilePath);
            if(j != subSplit.length-1){
                allThumbnailImgUrl += thumbnailImgFilePath + ",";
            }else{
                allThumbnailImgUrl += thumbnailImgFilePath;
            }
        }
        product.setMainImg(mainImgFilePath);// 设置主图
        product.setSubImg(allSubImgUrl);// 设置附图
        product.setThumbnail(allThumbnailImgUrl);// 设置缩略图
        product.setPricing(product.getPrice());// 设置定价
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        System.out.println(product);
        int result = productService.addProduct(product);
        if(result == 1){
            return ServerResponse.createBySuccessMessage("图书成功上架！");
        }
        return ServerResponse.createByErrorMessage("系统错误，请稍后再试！");
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
        // 进行删除时，需要对redis中数据也进行更新
      return "言念君子" + id;
    }

    @ApiOperation(value="搜索商品", notes="根据用户的输入框进行模糊查询")
    @ApiImplicitParam(name = "search", value = "用户搜索框输入search", required = true, dataType = "String")
    @GetMapping("/product/search")
    @CrossOrigin
    public ServerResponse getProduct(@RequestParam String search){
        List<Product> productList = productService.getProductBySearch(search);
        if(productList == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return ServerResponse.createBySuccess(productList);
    }

    @ApiOperation(value="添加商品评论", notes="添加用户评论输入信息")
    @ApiImplicitParam(name = "map", value = "用户评论信息map", required = true, dataType = "Map")
    @PostMapping("/product/comment")
    @CrossOrigin
    public ServerResponse<String> addComment(@RequestParam(value = "userId", required = true) String userId,
                                             @RequestParam(value = "productId", required = true) String productId,
                                             @RequestParam(value = "detail", required = true) String detail){
        int result = productService.addComment(new Comment(Long.parseLong(userId), Long.parseLong(productId), detail, new Date()));
        if(result != 1){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试！");
        }
        return ServerResponse.createBySuccessMessage("评论添加成功");
    }

    @ApiOperation(value="获取用户评论", notes="获取该书籍的全部评论")
    @ApiImplicitParam(name = "productId", value = "书籍id", required = true, dataType = "String")
    @GetMapping("/product/comment/{productId}")
    @CrossOrigin
    public ServerResponse getComment(@PathVariable String productId){
        List<Comment> comment = productService.getCommentByProduct(Long.parseLong(productId));
        if(comment == null){
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试！");
        }
        return ServerResponse.createBySuccess(comment);
    }
}
