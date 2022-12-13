package com.util;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class IconTreeCellRender extends DefaultTreeCellRenderer {
    /*
     * 重写TreeCellRenderer接口getTreeCellRendererComponent方法：
     * 	tree指的是设置setCellRenderer()方法的JTree
     * 	value值得是JTree具体的节点
     * 	expanded指的是JTree节点是否展开
     * 	leaf指的是JTree当前的节点是否是叶子
     * 	row当前节点在JTree所在的行数，从上往下数组，root根节点，row=0
     * 	hasFocus是否获取焦距
     * 将当前树单元格的值设置为 value。
     * 如果 selected 为 true，则将单元格作为已选择的单元格进行绘制。
     * 如果 expanded 为 true，则当前扩展该节点
     * 如果 leaf 为 true，则该节点表示叶节点，
     * 如果 hasFocus 为 true，则该节点当前拥有焦点。
     * tree 是为其配置接收者的 JTree。返回渲染器用来绘制值的 Component。
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        IconTreeNode node = (IconTreeNode) value;// 设定传进来的值转为自定义节点
        String txt = node.getUsername();// 从节点获取文本
        this.setText(txt);// 设置文本
        this.setFont(new Font("微软雅黑",Font.PLAIN,20));


        /*if(node.getEn_no() == -1){// 是根节点
        } else*/

        Icon icon = node.getImage();

        this.setIcon(icon);
        return this;

    }
}
