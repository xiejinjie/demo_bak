package com.demo.pagehelper;

import com.demo.tree.TreeNode;
import com.demo.tree.TreeSelectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jjxiek
 * @since 2019/12/6 14:25
 */
@Service
public class NodeService {
    @Autowired
    private TreeSelectMapper treeSelectMapper;

    public List<TreeNode> listNode(int startPage, int pageSize){
        //  创建Page对象，将page，limit参数传入，必须位于从数据库查询数据的语句之前，否则不生效
        Page page= PageHelper.startPage(startPage, pageSize);
        //  ASC是根据id 正向排序，DESC是反向排序
        PageHelper.orderBy("id ASC");
        // 从数据库查询，这里返回的的allUser就已经分页成功了
        List<TreeNode> list = treeSelectMapper.selectAllTreeNode();
        List<TreeNode> treeNodes = treeSelectMapper.selectAllTreeNode();

        // 获取查询记录总数，必须位于从数据库查询数据的语句之后，否则不生效
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        return list;
    }
    public List<TreeNode> listNode2(){
        List<TreeNode> list = treeSelectMapper.selectAllTreeNode();
        return list;
    }
}
