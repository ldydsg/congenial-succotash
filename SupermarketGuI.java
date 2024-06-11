package Library;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//在超市商品目录基础上添加可视化界面便于演示与操作
public class SupermarketGuI extends JFrame {
    private Supermarket supermarket;
    private DefaultTreeModel categoryTreeModel;
    private JTree categoryTree;
    public SupermarketGuI(){
        supermarket=new Supermarket();

        //创建主窗口
        setTitle("超市商品目录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        //创建商品分类树
        categoryTreeModel = new DefaultTreeModel(null);
        categoryTree = new JTree(categoryTreeModel);
        JScrollPane categoryScrollPane = new JScrollPane(categoryTree);
        add(categoryScrollPane, BorderLayout.CENTER);

        //创建按钮和文本框
        JButton addButton=new JButton(" 添加");//添加目录按钮
        JButton deleteButton = new JButton("删除");
        JButton searchButton = new JButton("查找");
        JTextField categoryTextField=new JTextField();
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(categoryTextField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        inputPanel.add(deleteButton, BorderLayout.WEST);
        inputPanel.add(searchButton, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.SOUTH);

        //添加按钮的点击监听器事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoryName = categoryTextField.getText();
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) categoryTree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    ProductCategory parentCategory = (ProductCategory) selectedNode.getUserObject();
                    supermarket.addCategory(categoryName, parentCategory.getName());
                } else {
                    supermarket.addCategory(categoryName, null);
                }
                updateCategoryTree();
                categoryTextField.setText("");
            }
        });

        //删除按钮
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) categoryTree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    ProductCategory category = (ProductCategory) selectedNode.getUserObject();
                    supermarket.removeCategory(category.getName());
                    updateCategoryTree();
                }
            }
        });

        //查找按钮
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoryName = categoryTextField.getText();
                ProductCategory category = supermarket.findCategory(categoryName);
                if (category != null) {
                    JOptionPane.showMessageDialog(null, "Category found: " + category.getName());
                } else {
                    JOptionPane.showMessageDialog(null, "Category not found!");
                }
            }
        });

        // 初始化商品分类列表
        updateCategoryTree();
    }


    // 更新商品分类树
    private void updateCategoryTree() {
        ProductCategory rootCategory = supermarket.getRootCategory();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootCategory);
        buildCategoryTree(rootCategory, rootNode);
        categoryTreeModel.setRoot(rootNode);
    }

    // 递归地构建商品分类树
    private void buildCategoryTree(ProductCategory category, DefaultMutableTreeNode parentNode) {
        for (ProductCategory subcategory : category.getSubcategories()) {
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(subcategory);
            parentNode.add(childNode);
            buildCategoryTree(subcategory, childNode);
        }
    }



}
