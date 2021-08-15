package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController extends AbstractCoreController<Brand> {


    private BrandService brandService;


    @Autowired
    public BrandController(BrandService brandService) {
        super(brandService,Brand.class);
        this.brandService = brandService;
    }


//    /**
//     * 查询所有品牌
//     * @return
//     */
//    @GetMapping
//    public Result<Brand> findAll(){
//        return new Result<Brand>(true, StatusCode.OK,"查询成功",brandService.findAll());
//    }
//
//    /**
//     * 添加品牌数据
//     * @param brand
//     * @return
//     */
//    @PostMapping
//    public Result insert(@RequestBody Brand brand){
//        brandService.insertBrand(brand);
//        return new Result(true, StatusCode.OK, "添加成功");
//    }
//
//    /**
//     * 根据ID查询品牌
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public Result<Brand> findById(@PathVariable Integer id){
//        return new Result<Brand>(true,StatusCode.OK,"查询成功",brandService.findById(id));
//    }
//
//    /**
//     * 根据ID更新品牌数据
//     * @param id
//     * @return
//     */
//    @PutMapping("/{id}")
//    public Result updateBrandById(@PathVariable Integer id,@RequestBody Brand brand){
//        brand.setId(id);
//        brandService.updateBrand(brand);
//        return new Result(true, StatusCode.OK, "修改成功");
//    }
//
//    /**
//     * 删除品牌数据
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/{id}")
//    public Result deleteBrand(@PathVariable Integer id){
//        brandService.deleteBrand(id);
//        return new Result(true, StatusCode.OK, "删除成功");
//    }
//
//    /**
//     * 品牌列表条件查询
//     * @param brand 条件查询数据
//     * @return
//     */
//    @PostMapping("/search")
//    public Result<List<Brand>> search(@RequestBody Brand brand){
//        return new Result<>(true, StatusCode.OK, "根据条件查询品牌成功", brandService.search(brand));
//    }
//
//    /**
//     *品牌列表分页查询
//     * @param page 第几页
//     * @param size 每页大小
//     * @return
//     */
//    @GetMapping("/search/{page}/{size}")
//    public Result<PageInfo<Brand>> Page(@PathVariable int page,
//                                              @PathVariable int size){
//        return new Result<>(true, StatusCode.OK, "分页查询品牌成功", brandService.page(page,size));
//    }
//
//    /**
//     * 品牌列表条件+分页查询
//     * @param brand 条件查询数据
//     * @param page 第几页
//     * @param size 每页大小
//     * @return
//     */
//    @PostMapping("/search/{page}/{size}")
//    public Result<PageInfo<Brand>> searchPage(@RequestBody Brand brand,
//                                              @PathVariable int page,
//                                              @PathVariable int size) {
//        return new Result<>(true, StatusCode.OK, "条件分页查询品牌成功",brandService.searchPage(brand, page, size));
//    }
}
