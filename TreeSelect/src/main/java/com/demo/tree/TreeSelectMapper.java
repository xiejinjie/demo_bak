package com.demo.tree;

import com.demo.tree.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jjxiek
 * @since 2019/11/10 15:09
 */
public interface TreeSelectMapper {
    List<TreeNode> selectAllTreeNode();
    TreeNode selectTree(@Param("rootId") int rootId);
    TreeNode selectNode(@Param("id") int id);
    List<TreeNode> selectChild(@Param("rootId") int pid);
}
