package HtmlEditor.listeners;

import HtmlEditor.MyView;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by Rumata on 25.04.2017.
 */
public class TabbedPaneChangeListener implements ChangeListener {
    private MyView view;

    public TabbedPaneChangeListener(MyView view) {
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();
    }
}
