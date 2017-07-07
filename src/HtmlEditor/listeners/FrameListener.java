package HtmlEditor.listeners;



import HtmlEditor.MyView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Rumata on 25.04.2017.
 */
public class FrameListener extends WindowAdapter {
    private MyView view;

    public FrameListener(MyView view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        view.exit();
    }
}
