package RBPO_avtomatom_5_na_exam;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

public class NotepadEditor extends JFrame {
    private UndoManager undo = new UndoManager();
    private String fileName;
    private ArrayList<String> wordList = new ArrayList();
    private JButton changeButton;
    private JFrame changeFrame;
    private JMenuItem checkTextBtn;
    private JMenuItem closeFile;
    private JMenu editBar;
    private JMenu fileBar;
    private JButton findButton;
    private JTextField findWord;
    private JMenuItem findWordBtn;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JScrollPane jScrollPane1;
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem removeHlightBtn;
    private JTextField replaceWord;
    private JMenuItem saveFile;
    private JTextArea textArea;
    private JMenuItem undoButton;

    public NotepadEditor() {
        this.initComponents();
        this.startTitle();
        this.readWordList();
        this.addListener();
        this.setLocation(650, 100);
    }

    private void addListener() {
        Document doc = this.textArea.getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                NotepadEditor.this.undo.addEdit(evt.getEdit());
            }
        });
    }

    private void startTitle() {
        this.setTitle("Новый файл");
    }

    private ArrayList<String> readWordList() {
        try {
            Scanner scanner = new Scanner(new FileInputStream("words.txt"));

            while(scanner.hasNextLine()) {
                String temp = scanner.nextLine();
                this.wordList.add(temp);
            }

            return this.wordList;
        } catch (FileNotFoundException var3) {
            Logger.getLogger(NotepadEditor.class.getName()).log(Level.SEVERE, (String)null, var3);
            return null;
        }
    }

    private void textChecker(String content) {
        content = content.toLowerCase();
        ArrayList<String> wrongWordList = new ArrayList();
        ArrayList<String> similarWordList = new ArrayList();
        String[] splitContent = content.split("[\\p{Punct}\\s\\d]+");
        SingleTransposition singleTransposition = new SingleTransposition();

        for(int i = 0; i < splitContent.length; ++i) {
            boolean isExist = false;
            boolean isSimilar = false;
            String similarWord = null;
            ListIterator listIterator = this.wordList.listIterator();

            while(listIterator.hasNext()) {
                String dictWord = (String)listIterator.next();
                if (splitContent[i].equals(dictWord)) {
                    content = content.replaceAll(splitContent[i], dictWord);
                    isSimilar = false;
                    isExist = true;
                    break;
                }

                if (singleTransposition.findSimilar(splitContent[i], dictWord)) {
                    similarWord = dictWord;
                    isSimilar = true;
                    isExist = true;
                }
            }

            if (isSimilar) {
                content = content.replaceAll(splitContent[i], similarWord);
                similarWordList.add(splitContent[i]);
            }

            if (!isExist) {
                wrongWordList.add(splitContent[i]);
            }
        }

        this.textArea.setText(content);
        System.out.println("неправильные слова: ");
        System.out.println("-------------------");
        Iterator wrongWordIterator = wrongWordList.iterator();

        while(wrongWordIterator.hasNext()) {
            System.out.println("+ " + wrongWordIterator.next());
        }

        System.out.println();
        System.out.println("Исправленные слова: ");
        System.out.println("++++++++++++++++++++++");
        Iterator fixedWordIterator = similarWordList.iterator();

        while(fixedWordIterator.hasNext()) {
            System.out.println("+ " + fixedWordIterator.next());
        }

    }

    private void initComponents() {
        this.changeFrame = new JFrame();
        this.jLabel1 = new JLabel();
        this.findWord = new JTextField();
        this.jLabel2 = new JLabel();
        this.replaceWord = new JTextField();
        this.findButton = new JButton();
        this.changeButton = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.textArea = new JTextArea();
        this.jMenuBar1 = new JMenuBar();
        this.fileBar = new JMenu();
        this.newFile = new JMenuItem();
        this.openFile = new JMenuItem();
        this.saveFile = new JMenuItem();
        this.closeFile = new JMenuItem();
        this.editBar = new JMenu();
        this.undoButton = new JMenuItem();
        this.removeHlightBtn = new JMenuItem();
        this.jMenu2 = new JMenu();
        this.checkTextBtn = new JMenuItem();
        this.jMenu1 = new JMenu();
        this.findWordBtn = new JMenuItem();
        this.changeFrame.setTitle("Найти и заменить");
        this.changeFrame.setMinimumSize(new Dimension(403, 250));
        this.jLabel1.setText("Поисковое слово: ");
        this.findWord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.findWordActionPerformed(evt);
            }
        });
        this.jLabel2.setText("слово для замены:");
        this.replaceWord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.replaceWordActionPerformed(evt);
            }
        });
        this.findButton.setText("Найти");
        this.findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.findButtonActionPerformed(evt);
            }
        });
        this.changeButton.setText("Заменить");
        this.changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.changeButtonActionPerformed(evt);
            }
        });
        GroupLayout changeFrameLayout = new GroupLayout(this.changeFrame.getContentPane());
        this.changeFrame.getContentPane().setLayout(changeFrameLayout);
        changeFrameLayout.setHorizontalGroup(changeFrameLayout.createParallelGroup(Alignment.LEADING).addGroup(changeFrameLayout.createSequentialGroup().addGap(26, 26, 26).addGroup(changeFrameLayout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel2, -1, -1, 32767).addComponent(this.jLabel1, -1, -1, 32767).addGroup(Alignment.TRAILING, changeFrameLayout.createSequentialGroup().addComponent(this.findButton).addGap(15, 15, 15))).addGap(63, 63, 63).addGroup(changeFrameLayout.createParallelGroup(Alignment.LEADING).addGroup(changeFrameLayout.createParallelGroup(Alignment.LEADING, false).addComponent(this.findWord).addComponent(this.replaceWord, -1, 102, 32767)).addComponent(this.changeButton)).addContainerGap(75, 32767)));
        changeFrameLayout.setVerticalGroup(changeFrameLayout.createParallelGroup(Alignment.LEADING).addGroup(changeFrameLayout.createSequentialGroup().addGap(31, 31, 31).addGroup(changeFrameLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel1, -2, 33, -2).addComponent(this.findWord, -2, 33, -2)).addGap(34, 34, 34).addGroup(changeFrameLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.jLabel2, -2, 29, -2).addComponent(this.replaceWord, -2, 37, -2)).addGap(36, 36, 36).addGroup(changeFrameLayout.createParallelGroup(Alignment.BASELINE).addComponent(this.findButton).addComponent(this.changeButton)).addContainerGap(54, 32767)));
        this.setDefaultCloseOperation(3);
        this.textArea.setColumns(20);
        this.textArea.setRows(5);
        this.textArea.setCursor(new Cursor(2));
        this.textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                NotepadEditor.this.textAreaKeyTyped(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.textArea);
        Document doc = this.textArea.getDocument();
        final UndoManager undo = new UndoManager();
        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });
        this.fileBar.setText("Файл");
        this.fileBar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.fileBarActionPerformed(evt);
            }
        });
        this.newFile.setAccelerator(KeyStroke.getKeyStroke(78, 2));
        this.newFile.setText("Новый файл");
        this.newFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.newFileActionPerformed(evt);
            }
        });
        this.fileBar.add(this.newFile);
        this.openFile.setAccelerator(KeyStroke.getKeyStroke(79, 2));
        this.openFile.setText("Открыть файл");
        this.openFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.openFileActionPerformed(evt);
            }
        });
        this.fileBar.add(this.openFile);
        this.saveFile.setAccelerator(KeyStroke.getKeyStroke(83, 2));
        this.saveFile.setText("Сохранить файл");
        this.saveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.saveFileActionPerformed(evt);
            }
        });
        this.fileBar.add(this.saveFile);
        this.closeFile.setAccelerator(KeyStroke.getKeyStroke(81, 2));
        this.closeFile.setText("Закрыть файл");
        this.closeFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.closeFileActionPerformed(evt);
            }
        });
        this.fileBar.add(this.closeFile);
        this.jMenuBar1.add(this.fileBar);
        this.editBar.setText("Редактировать");
        this.undoButton.setAccelerator(KeyStroke.getKeyStroke(90, 2));
        this.undoButton.setText("Отменить");
        this.undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.undoButtonActionPerformed(evt);
            }
        });
        this.editBar.add(this.undoButton);
        this.removeHlightBtn.setText("Удалить выделение");
        this.removeHlightBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.removeHlightBtnActionPerformed(evt);
            }
        });
        this.editBar.add(this.removeHlightBtn);
        this.jMenuBar1.add(this.editBar);
        this.jMenu2.setText("Проверить");
        this.checkTextBtn.setText("Проверка текста");
        this.checkTextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.checkTextBtnActionPerformed(evt);
            }
        });
        this.jMenu2.add(this.checkTextBtn);
        this.jMenuBar1.add(this.jMenu2);
        this.jMenu1.setText("Найти");
        this.findWordBtn.setAccelerator(KeyStroke.getKeyStroke(70, 2));
        this.findWordBtn.setText("Найти и заменить");
        this.findWordBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NotepadEditor.this.findWordBtnActionPerformed(evt);
            }
        });
        this.jMenu1.add(this.findWordBtn);
        this.jMenuBar1.add(this.jMenu1);
        this.setJMenuBar(this.jMenuBar1);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1, -1, 751, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1, Alignment.TRAILING, -1, 791, 32767));
        this.pack();
    }

    private void closeFileActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void openFileActionPerformed(ActionEvent evt) {
        FileDialog fileDialog = new FileDialog(this, "Открыть файл", 0);
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) {
            this.fileName = fileDialog.getDirectory() + fileDialog.getFile();
        }

        Context context = new Context(new FileOpen());
        context.executeStrategy(this.textArea, this.fileName, this);
    }

    private void saveFileActionPerformed(ActionEvent evt) {
        FileDialog fileDialog = new FileDialog(this, "Сохранить файл", 1);
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) {
            this.fileName = fileDialog.getDirectory() + fileDialog.getFile();
        }

        Context context = new Context(new FileSave());
        context.executeStrategy(this.textArea, this.fileName, this);
    }

    private void fileBarActionPerformed(ActionEvent evt) {
    }

    private void newFileActionPerformed(ActionEvent evt) {
        Context context = new Context(new FileNew());
        context.executeStrategy(this.textArea, this.fileName, this);
    }

    private void undoButtonActionPerformed(ActionEvent evt) {
        Invoker invoker = new Invoker();
        TextDocument textDocument = new TextDocument(this.textArea, this.undo);
        TextUndo docUndo = new TextUndo(textDocument);
        invoker.setCommand(docUndo);
        invoker.executeCommand();
    }

    private void findWordBtnActionPerformed(ActionEvent evt) {
        this.changeFrame.setVisible(true);
        this.changeFrame.setLocation(800, 200);
        this.changeFrame.setSize(600, 700);
    }

    private void removeHlightBtnActionPerformed(ActionEvent evt) {
        HighLighterClass.removeHighlights(this.textArea);
    }

    private void findWordActionPerformed(ActionEvent evt) {
    }

    private void replaceWordActionPerformed(ActionEvent evt) {
    }

    private void findButtonActionPerformed(ActionEvent evt) {
        if (this.findWord.getText().equals((Object)null) && this.findWord.getText().matches("\\s*") && !this.findWord.getText().equals("")) {
            JOptionPane.showMessageDialog(this.changeFrame, "Неверный синтаксис для поиска текстовой области", "Опасно", 2);
        } else if (this.textArea.getText().contains(this.findWord.getText())) {
            try {
                HighLighterClass.highlight(this.textArea, this.findWord.getText(), HighLighterClass.findHighlightPainter);
            } catch (Exception var3) {
                Logger.getLogger(NotepadEditor.class.getName()).log(Level.SEVERE, (String)null, var3);
            }
        } else {
            JOptionPane.showMessageDialog(this.changeFrame, "'" + this.findWord.getText() + "'  не найден!", "Опасно", 2);
        }

    }

    private void changeButtonActionPerformed(ActionEvent evt) {
        String text_ici = this.textArea.getText();
        if (!this.findWord.getText().equals("") && !this.findWord.getText().equals((Object)null) && !this.findWord.getText().matches("\\s*") && !this.replaceWord.getText().equals((Object)null) && !this.replaceWord.getText().equals("") && !this.replaceWord.getText().matches("\\s*")) {
            if (!this.textArea.getText().contains(this.findWord.getText())) {
                JOptionPane.showMessageDialog(this.changeFrame, "'" + this.findWord.getText() + "'  не найден!", "Опасно", 2);
            } else {
                this.textArea.setText(text_ici.replaceAll(this.findWord.getText(), this.replaceWord.getText()));
            }
        } else {
            JOptionPane.showMessageDialog(this.changeFrame, "Не указаны слова для поиска и замены!", "Опасно", 2);
        }

    }

    private void textAreaKeyTyped(KeyEvent evt) {
        HighLighterClass.removeHighlights(this.textArea);
    }

    private void checkTextBtnActionPerformed(ActionEvent evt) {
        HighLighterClass.removeHighlights(this.textArea);
        String content = this.textArea.getText();
        this.textChecker(content);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                (new NotepadEditor()).setVisible(true);
            }
        });
    }
}
