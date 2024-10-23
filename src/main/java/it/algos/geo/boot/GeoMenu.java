package it.algos.geo.boot;

import it.algos.vbase.constant.Gruppo;
import it.algos.vbase.service.LayoutService;
import it.algos.vbase.tree.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GeoMenu {


    @Autowired
    LayoutService layoutService;


//    @AMenu
    public TreeNode buildMenu(TreeNode treeNode) {
        layoutService.fixOrderGroup(treeNode, Arrays.asList("Anagrafica", "Crono", "Geo", "Utility", "Task", "Test"));
//        layoutService.fixOrderGroup(treeNode, Arrays.asList( "Geo", "Utility"));
        layoutService.fixNomeGruppo(treeNode, Gruppo.GEO, "Geografia");
        return treeNode;
    }

}
