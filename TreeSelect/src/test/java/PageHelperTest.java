import com.demo.SpringbootApplication;
import com.demo.pagehelper.NodeService;
import com.demo.tree.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jjxiek
 * @since 2019/12/6 14:14
 */
@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringRunner.class)
public class PageHelperTest {
    @Autowired
    private NodeService nodeService;
    @Test
    public void fun1(){
        List<TreeNode> treeNodes = nodeService.listNode(2, 2);
        List<TreeNode> treeNodes1 = nodeService.listNode2();
        System.out.println(1);
        System.out.println(treeNodes);
    }
}
