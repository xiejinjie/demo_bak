package com.demo.model;

import java.util.List;

/**
 * @author jjxiek
 * @since 2019/11/10 15:25
 */
public class TreeNode {
    private Integer id;
    private String nodename;
    private Integer pid;
    private List<TreeNode> childs;
    private TreeNode parent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeNode> childs) {
        this.childs = childs;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "id=" + id + ", nodename='" + nodename +  '}';
    }
}
