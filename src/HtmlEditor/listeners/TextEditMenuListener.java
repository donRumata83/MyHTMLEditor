package HtmlEditor.listeners;


import HtmlEditor.MyView;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener{
    private MyView view;

    public TextEditMenuListener(MyView view) {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        JMenu menu = (JMenu) menuEvent.getSource();
        Component[] components = menu.getMenuComponents();
        for (Component component : components){
            component.setEnabled(view.isHtmlTabSelected());
    }}

    @Override
    public void menuDeselected(MenuEvent menuEvent) {

    }

    @Override
    public void menuCanceled(MenuEvent menuEvent) {

    }
}
