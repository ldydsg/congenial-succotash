package Library;

import java.util.ArrayList;
import java.util.List;
//商品分类类
public class ProductCategory {
    private String name;
    private List<ProductCategory> subcategories;

    public ProductCategory(String name) {
        this.name = name;
        this.subcategories = new ArrayList<>();//初始化子分类列表
    }

    public String getName() {
        return name;
    }

    //获取子分类列表
    public List<ProductCategory> getSubcategories() {
        return subcategories;
    }
    //添加子分类
    public void addSubcategories(ProductCategory subcategory){
        subcategories.add(subcategory);
    }
    //移除子分类
    public boolean removeSubcategory(ProductCategory subcategory) {
        return subcategories.remove(subcategory);
    }
    //在分类及其子类中查找指定商品目录
    public ProductCategory  findCategory(String categoryName){
        if (name.equals(categoryName)){
            return this;
        }
        for (ProductCategory subcategory:subcategories){
            ProductCategory foundCategory=subcategory.findCategory(categoryName);
            if (categoryName!=null){
                return foundCategory;
            }
        }
        return null;
    }
    @Override
    public  String toString(){
        return name;
    }
}
