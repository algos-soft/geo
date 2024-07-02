package it.algos.geo.boot;

import it.algos.vbase.backend.annotation.AMenu;
import it.algos.vbase.backend.boot.BaseBoot;
import it.algos.vbase.backend.constant.Gruppo;
import it.algos.vbase.backend.tree.MenuObject;
import it.algos.vbase.backend.tree.TreeNode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static it.algos.vbase.backend.boot.BaseCost.CUSTOM_MENU;
import static it.algos.vbase.backend.boot.BaseCost.CUSTOM_MENU_KEY;

/**
 * Project wiki24
 * Created by Algos
 * User: gac
 * Date: Thu, 16-Nov-2023
 * Time: 13:55
 */
@Service
@Component("geoBoot")
public class GeoBoot extends BaseBoot {


    public GeoBoot() {
    }


    @AMenu(CUSTOM_MENU_KEY)
    public List<String> fixCustomMenuKey(List<String> keyList) {
        return Arrays.asList("Anagrafica", "Crono", "Geo", "Utility", "Task", "Test");
    }

    @AMenu(CUSTOM_MENU)
    public TreeNode<MenuObject> fixCustomMenu(TreeNode<MenuObject> treeNode) {

        List<TreeNode<MenuObject>> lista = treeNode.getChildren();
        for (TreeNode<MenuObject> node : lista) {
            if (node.getData().getLabel().equals(Gruppo.GEO)) {
                node.getData().setLabel("Geografia");
            }
        }

        return treeNode;
    }

}



