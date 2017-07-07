package HtmlEditor;

import HtmlEditor.actions.*;
import HtmlEditor.listeners.TextEditMenuListener;
import HtmlEditor.listeners.UndoMenuListener;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuHelper {

    /* Создает и добавляет в родителя пункт меню
    Параметры: родитель, Название пункта меню, слушатель для пункта
     */
    private static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        parent.add(menuItem);
        return menuItem;
    }

    /* Создает и добавляет в родителя пункт меню
        Параметры: родитель, Название пункта меню, Действие
         */
    public static JMenuItem addMenuItem(JMenu parent, String text, Action action) {
        JMenuItem menuItem = addMenuItem(parent, action);
        menuItem.setText(text);
        return menuItem;
    }

    /* Создает и добавляет в родителя пункт меню
        Параметры: родитель, слушатель для пункта
        Название устанвливаеться по умолчанию
         */
    public static JMenuItem addMenuItem(JMenu parent, Action action) {
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);
        return menuItem;
    }

    // Инциализация меню "Помощь"
    public static void initHelpMenu(MyView myView, JMenuBar menuBar) {
        JMenu helpMenu = new JMenu("Помощь");
        menuBar.add(helpMenu);

        addMenuItem(helpMenu, "О программе", myView);
    }

    // Инциализация меню "Шрифты"
    public static void initFontMenu(MyView myView, JMenuBar menuBar) {
        JMenu fontMenu = new JMenu("Шрифт");
        menuBar.add(fontMenu);

        JMenu fontTypeMenu = new JMenu("Шрифт");
        fontMenu.add(fontTypeMenu);

        String[] fontTypes = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED, Font.DIALOG, Font.DIALOG_INPUT};
        for (String fontType : fontTypes) {
            addMenuItem(fontTypeMenu, fontType, new StyledEditorKit.FontFamilyAction(fontType, fontType));
        }

        JMenu fontSizeMenu = new JMenu("Размер шрифта");
        fontMenu.add(fontSizeMenu);

        String[] fontSizes = {"6", "8", "10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
        for (String fontSize : fontSizes) {
            addMenuItem(fontSizeMenu, fontSize, new StyledEditorKit.FontSizeAction(fontSize, Integer.parseInt(fontSize)));
        }

        fontMenu.addMenuListener(new TextEditMenuListener(myView));
    }

    // Инциализация меню "Цвета"
    public static void initColorMenu(MyView myView, JMenuBar menuBar) {
        JMenu colorMenu = new JMenu("Цвет");
        menuBar.add(colorMenu);

        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Красный", Color.red));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Оранжевый", Color.orange));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Желтый", Color.yellow));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Зеленый", Color.green));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Синий", Color.blue));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Голубой", Color.cyan));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Пурпурный", Color.magenta));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction("Черный", Color.black));

        colorMenu.addMenuListener(new TextEditMenuListener(myView));
    }

    // Инциализация меню "Выравнивание"
    public static void initAlignMenu(MyView myView, JMenuBar menuBar) {
        JMenu alignMenu = new JMenu("Выравнивание");
        menuBar.add(alignMenu);

        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По левому краю", StyleConstants.ALIGN_LEFT));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По центру", StyleConstants.ALIGN_CENTER));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction("По правому краю", StyleConstants.ALIGN_RIGHT));

        alignMenu.addMenuListener(new TextEditMenuListener(myView));
    }

    // Инциализация меню "Стиль"
    public static void initStyleMenu(MyView myView, JMenuBar menuBar) {
        JMenu styleMenu = new JMenu("Стиль");
        menuBar.add(styleMenu);

        addMenuItem(styleMenu, "Полужирный", new StyledEditorKit.BoldAction());
        addMenuItem(styleMenu, "Подчеркнутый", new StyledEditorKit.UnderlineAction());
        addMenuItem(styleMenu, "Курсив", new StyledEditorKit.ItalicAction());

        styleMenu.addSeparator();

        addMenuItem(styleMenu, "Подстрочный знак", new SubscriptAction());
        addMenuItem(styleMenu, "Надстрочный знак", new SuperscriptAction());
        addMenuItem(styleMenu, "Зачеркнутый", new StrikeThroughAction());

        styleMenu.addMenuListener(new TextEditMenuListener(myView));
    }

    // Инциализация меню "Редактировать"
    public static void initEditMenu(MyView myView, JMenuBar menuBar) {
        JMenu editMenu = new JMenu("Редактировать");
        menuBar.add(editMenu);

        JMenuItem undoItem = addMenuItem(editMenu, "Отменить", new UndoAction(myView));
        JMenuItem redoItem = addMenuItem(editMenu, "Вернуть", new RedoAction(myView));
        addMenuItem(editMenu, "Вырезать", new DefaultEditorKit.CutAction());
        addMenuItem(editMenu, "Копировать", new DefaultEditorKit.CopyAction());
        addMenuItem(editMenu, "Вставить", new DefaultEditorKit.PasteAction());

        editMenu.addMenuListener(new UndoMenuListener(myView, undoItem, redoItem));
    }

    // Инциализация меню "Файл"
    public static void initFileMenu(MyView myView, JMenuBar menuBar) {
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        addMenuItem(fileMenu, "Новый", myView);
        addMenuItem(fileMenu, "Открыть", myView);
        addMenuItem(fileMenu, "Сохранить", myView);
        addMenuItem(fileMenu, "Сохранить как...", myView);
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Выход", myView);
    }
}
