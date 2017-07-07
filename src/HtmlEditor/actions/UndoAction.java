package HtmlEditor.actions;

import HtmlEditor.MyView;


import javax.swing.*;
import java.awt.event.ActionEvent;


public class UndoAction extends AbstractAction {
    private MyView view;
    public UndoAction(MyView view)
    {this.view = view;}

    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();
    }
}
