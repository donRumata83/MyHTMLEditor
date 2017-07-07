package HtmlEditor;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private MyView myView;
    private HTMLDocument document;
    private File currentFile;

    // конструктор принимает представление
    public Controller(MyView myView) {
        this.myView = myView;
    }

    public void init() {
        createNewDocument();
    }

    public static void main(String[] args) {
        MyView myView = new MyView();

        Controller controller = new Controller(myView);
        myView.setController(controller);
        myView.init();
        controller.init();
    }

    // выход
    public void exit() {
        System.exit(0);
    }

    //геттер для документа
    public HTMLDocument getDocument() {
        return document;
    }

    // удаляет существующий документ и создает пустой
    public void resetDocument() {
        if (document != null) {
            // удаляет существующий документ
            document.removeUndoableEditListener(myView.getUndoListener());
        }
        // создает документ по умолчанию
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(myView.getUndoListener());
        myView.update();
    }


    //записывает переданный текст с html тегами в документ document
    public void setPlainText(String text) {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    // получает текст из документа со всеми html тегами.
    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    // Новый документ
    public void createNewDocument() {
        myView.selectHtmlTab();
        resetDocument();
        myView.setTitle("HTML редактор");
        currentFile = null;
        myView.resetUndo();

    }

    // открыть документ
    public void openDocument() {
        myView.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int choose = jFileChooser.showOpenDialog(myView);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            myView.setTitle(currentFile.getName());
            try {
                FileReader fileReader = new FileReader(currentFile);
                new HTMLEditorKit().read(fileReader, document, 0);
                myView.resetUndo();

            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    //сохранить документ
    public void saveDocument() {
        if (currentFile == null) saveDocumentAs();
        else {
            myView.selectHtmlTab();
            myView.setTitle(currentFile.getName());

            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }

        }
    }

    //сохранить документ как..
    public void saveDocumentAs() {
        myView.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int choose = jFileChooser.showSaveDialog(myView);
        if (choose == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            myView.setTitle(currentFile.getName());
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


}
