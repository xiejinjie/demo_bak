package com.demo.tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author jjxiek
 * @since 2019/11/10 15:09
 */
@Service
public class TreeSelectService {
    @Autowired
    TreeSelectMapper treeSelectMapper;
    public void selectAllTreeNode(){
        System.out.println(treeSelectMapper.selectAllTreeNode());
    }
    public TreeNode selectTree(int rootId){
        return treeSelectMapper.selectTree(rootId);
    }
    public TreeNode selectRoot(int id){
        // 方法一 查找返回当前节点
        TreeNode node1 = findParent(id);
        TreeNode root1 = selectRoot(node1);
        //方法二 查找返回根节点
        TreeNode node2 = treeSelectMapper.selectNode(id);
        TreeNode root2 = selectParent(node2);

        return root2;
    }

    /**
     * 递归查找父节点
     * @param id
     * @return 当前节点
     */
    private TreeNode findParent(int id){
        TreeNode node = treeSelectMapper.selectNode(id);
        if (node == null){
            return null;
        }
        TreeNode parent = findParent(node.getPid());
        if (parent != null){
            node.setParent(parent);
            parent.setChilds(Collections.singletonList(node));
        }
        return node;
    }

    /**
     * 查找根节点
     * @param node
     * @return
     */
    private TreeNode selectRoot(TreeNode node){
        if (node == null){
            return null;
        }
        TreeNode parent = selectRoot(node.getParent());
        return parent == null ? node : parent;
    }

    /**
     * 查找根节点
     * 较好算法
     * @param node
     * @return
     */
    private TreeNode selectParent(TreeNode node){
        TreeNode parent = treeSelectMapper.selectNode(node.getPid());
        if (parent == null){
            return node;
        }
        parent.setChilds(Collections.singletonList(node));
        return selectParent(parent);
    }
}
