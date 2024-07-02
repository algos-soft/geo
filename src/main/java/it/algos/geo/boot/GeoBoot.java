package it.algos.geo.boot;

import it.algos.vbase.backend.annotation.AMenu;
import it.algos.vbase.backend.boot.*;
import it.algos.vbase.backend.constant.Gruppo;
import it.algos.vbase.backend.tree.MenuObject;
import it.algos.vbase.backend.tree.TreeNode;
import org.springframework.stereotype.*;

import java.util.List;

/**
 * Project wiki24
 * Created by Algos
 * User: gac
 * Date: Thu, 16-Nov-2023
 * Time: 13:55
 */
@Service
@Component("geoBoot")
@AMenu
public class GeoBoot extends BaseBoot {


    public GeoBoot() {
    }

    @Override
    public TreeNode<MenuObject> fixCustomMenu(TreeNode<MenuObject> treeNode) {

        List<TreeNode<MenuObject>> lista = treeNode.getChildren();
        for (TreeNode<MenuObject> node : lista) {
            if (node.getData().getPath().equals(Gruppo.GEO)) {
                node.getData().setPath("Geografia");
            }
        }

        return treeNode;
    }

}



