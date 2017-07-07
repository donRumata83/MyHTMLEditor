package HtmlEditor.actions;

import HtmlEditor.MyView;


import javax.swing.*;
import java.awt.event.ActionEvent;


public class RedoAction extends AbstractAction {
    private MyView view;
    public RedoAction(MyView view) {this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }
}
