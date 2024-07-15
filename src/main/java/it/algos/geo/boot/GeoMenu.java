package it.algos.geo.boot;

import it.algos.vbase.backend.constant.Gruppo;
import it.algos.vbase.backend.service.LayoutService;
import it.algos.vbase.backend.tree.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
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
