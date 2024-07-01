package it.algos.geo.boot;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.backend.annotation.ALib;
import it.algos.vbase.backend.boot.BaseLib;
import it.algos.vbase.backend.tree.MenuObject;
import it.algos.vbase.backend.tree.TreeNode;

import java.util.List;

import static it.algos.geo.boot.GeoCost.GROUP_GEO;

/**
 * Project crono
 * Created by Algos
 * User: gac
 * Date: mer, 19-giu-2024
 * Time: 07:27
 */
@SpringComponent
@ALib()
public class GeoLib extends BaseLib {

    public GeoLib() {
        super.usaMenuLibreria = true;
        super.menuGroup = "Geografia";
        super.icon = new Icon(VaadinIcon.DOWNLOAD);
    }

    @Override
    public TreeNode<MenuObject> createMenu(TreeNode<MenuObject> treeNode) {

        List<TreeNode<MenuObject>> lista = treeNode.getChildren();
        for (TreeNode<MenuObject> node : lista) {
            if (node.getData().getPath().equals(GROUP_GEO)) {
                node.getData().setPath("Geografia");
            }
        }

        return treeNode;
    }

}
