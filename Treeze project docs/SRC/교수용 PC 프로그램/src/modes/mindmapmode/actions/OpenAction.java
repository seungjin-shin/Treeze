package freemind.modes.mindmapmode.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import freemind.modes.ControllerAdapter;
import freemind.modes.mindmapmode.MindMapController;

public class OpenAction extends AbstractAction {
	MindMapController mc;
    public OpenAction(ControllerAdapter modeController) {
        super(modeController.getText("open"), new ImageIcon(modeController.getResource("images/fileopen.png")));
        mc = (MindMapController) modeController;
    }
    public void actionPerformed(ActionEvent e) {
        mc.open(mc, "0");
        mc.getController().setTitle(); // Possible update of read-only
    }
}