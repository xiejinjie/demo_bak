import com.demo.SpringbootApplication;
import com.demo.tree.TreeNode;
import com.demo.tree.TreeSelectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jjxiek
 * @since 2019/11/10 15:17
 */
@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TreeSelectTest {
    @Autowired
    TreeSelectService treeSelectService;
    @Test
    public void selectAllNode(){
        treeSelectService.selectAllTreeNode();
    }
    @Test
    public void selectTree(){
        TreeNode treeNode = treeSelectService.selectTree(1);
        printTree(treeNode, 0);
    }
    @Test
    public void selectRoot(){
        TreeNode root = treeSelectService.selectRoot(5);
        printTree(root,0);
    }

    private void printTree(TreeNode node, int deep){
        if (node == null) {
            return;
        }
        if (deep > 0){
            for (int i=0; i < deep-1; i++){
                System.out.print("  ");
            }
            System.out.print("`-");
        }
        System.out.println(node.getNodename());
        if (node.getChilds() == null){
            return;
        }
        for (TreeNode child: node.getChilds()) {
            printTree(child, deep+1);
        }
    }
}
