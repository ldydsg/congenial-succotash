package Library;

//超市类
public class Supermarket {
    private ProductCategory rootCategory;
    //创建根目录
    public  Supermarket(){
        rootCategory=new ProductCategory("总目录");
    }


    //显示商品目录分类
    public void displayCategories(){
        displayCategory(rootCategory,0);
    }

    //递归显示分类及其子类
    public  void displayCategory(ProductCategory category,int level){
        StringBuilder indent=new StringBuilder();//用于缩进显示，便于区分父类与子类
        for (int i = 0; i < level; i++) {
            indent.append(" ");
        }
        System.out.println(indent+category.getName());//打印商品名称
        //遍历子分类并递归
        for(ProductCategory subcategory:category.getSubcategories()){
            displayCategory(subcategory,level+1);
        }
    }

    //向目录树中添加新的商品分类
    public void addCategory(String categoryName,String parentCategoryName){
        //创建新的商品分类对象
        ProductCategory category=new ProductCategory(categoryName);
       ProductCategory parentCategory=rootCategory.findCategory(parentCategoryName);
       if (parentCategory!=null){
           parentCategory.addSubcategories(category);
           System.out.println("category:"+categoryName+" add to "+" parentCategory:"+parentCategoryName+".");
       }else{
           System.out.println("parentCategory:"+parentCategoryName+" not found");
       }
    }

    //删除商品分类
    public void removeCategory(String categoryName){
        ProductCategory category=rootCategory.findCategory(categoryName);
        if (category!=null){
            ProductCategory parentCategory=findParentCategory(rootCategory,categoryName);
            if (parentCategory!=null){
                parentCategory.removeSubcategory(category);
                System.out.println("Category:"+categoryName+" has removed");
            }else {
                System.out.println("Cannot remove root category");
            }

        }else {
            System.out.println("Category:"+categoryName+" not found");
        }
    }

    //查询父类商品目录
    private ProductCategory findParentCategory(ProductCategory category,String categoryName){
        for (ProductCategory subcategory:category.getSubcategories()){
            if (subcategory.getName().equals(categoryName)){
                return  category;
            }else {
                ProductCategory parentCategory=findParentCategory(subcategory,categoryName);
                if (parentCategory!=null){
                    return parentCategory;
                }
            }
        }
        return null;
    }

    //查询商品目录
    public ProductCategory findCategory(String productName){
        ProductCategory category=findCategoryInTree(rootCategory,productName);
        if (category!=null){
            System.out.println("Category of "+productName+" is "+category.getName());
            return category;
        }else {
            System.out.println("Category "+productName+" not found");
            return null;
        }
    }

    //在目录树中找到商品
    private ProductCategory findCategoryInTree(ProductCategory category, String productName){
        if (category.getName().equals(productName)){
            return category;

        }
        for (ProductCategory subcategory:category.getSubcategories()){
            ProductCategory foundCategory=findParentCategory(subcategory,productName);
            if (foundCategory!=null){
                return foundCategory;
            }
        }
        return null;
    }

    //获取根目录
    public ProductCategory getRootCategory() {
        return rootCategory;
    }
}
