package it.algos.geo.boot;

import it.algos.vbase.backend.annotation.AMenu;
import it.algos.vbase.backend.constant.Gruppo;
import it.algos.vbase.backend.service.LayoutService;
import it.algos.vbase.backend.tree.TreeNode;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;

@Component
public class GeoMenu {


    @Inject
    LayoutService layoutService;


    @AMenu
    public TreeNode buildMenu(TreeNode treeNode) {
        layoutService.fixOrderGroup(treeNode, Arrays.asList("Anagrafica", "Crono", "Geo", "Utility", "Task", "Test"));
        layoutService.fixNomeGruppo(treeNode, Gruppo.GEO, "Geografia");
        return treeNode;
    }

}
