package HtmlEditor.listeners;

import HtmlEditor.MyView;


import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class UndoMenuListener implements MenuListener {
    private MyView view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;

    // Проверка доступности пунктов перед показом меню
    @Override
    public void menuSelected(MenuEvent e) {
        undoMenuItem.setEnabled(view.canUndo());
        redoMenuItem.setEnabled(view.canRedo());
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    public UndoMenuListener(MyView view, JMenuItem undoMenuItem, JMenuItem redoMenuItem) {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }
}
