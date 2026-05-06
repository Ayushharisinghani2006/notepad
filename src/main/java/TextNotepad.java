import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextNotepad implements ActionListener {

    // THEME
    Color bgDark = new Color(30, 30, 30);
    Color fgLight = new Color(220, 220, 220);
    Color caretColor = new Color(200, 200, 200);

    // FONT
    int fsize = 25;
    int fstyle = Font.PLAIN;
    String fontFamily = "Consolas";

    JFrame f;
    JTextArea area;
    JScrollPane scpane;

    JPanel statusPanel;
    JLabel statusLabel;

    JMenuBar menuBar;
    JMenu file, edit, format, view, help;

    JMenuItem newdoc, open, save, print, exit;
    JMenuItem copy, paste, cut, selectall;
    JMenuItem fontfamily, fontstyle, fontsize;
    JMenuItem about;

    JCheckBoxMenuItem wordWrap, statusBar;

    String[] fontFamilyValues = {"Consolas", "Arial", "Calibri", "Courier New", "Serif"};
    String[] fontStyleValues = {"PLAIN", "BOLD", "ITALIC"};
    String[] fontSizeValues = {"10", "12", "14", "16", "18", "20", "24", "28", "32"};
    int[] stylevalue = {Font.PLAIN, Font.BOLD, Font.ITALIC};

    public TextNotepad() {
        initUI();
        addActionEvents();
    }

    private void initUI() {

        f = new JFrame("Notepad");
        f.setSize(1000, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        // TEXT AREA
        area = new JTextArea();
        area.setBackground(bgDark);
        area.setForeground(fgLight);
        area.setCaretColor(caretColor);
        area.setFont(new Font(fontFamily, fstyle, fsize));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setMargin(new Insets(10, 10, 10, 10));

        // CARET LISTENER (STATUS BAR)
        area.addCaretListener(e -> {
            int pos = area.getCaretPosition();
            try {
                int line = area.getLineOfOffset(pos) + 1;
                int col = pos - area.getLineStartOffset(line - 1) + 1;
                statusLabel.setText(
                        "Ln " + line + ", Col " + col +
                        " | " + area.getText().length() + " chars | UTF-8 | Windows (CRLF)"
                );
            } catch (Exception ignored) {}
        });

        // SCROLL
        scpane = new JScrollPane(area);
        scpane.getViewport().setBackground(bgDark);

        // MENU BAR
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(45, 45, 45));

        file = createMenu("File");
        edit = createMenu("Edit");
        format = createMenu("Format");
        view = createMenu("View");
        help = createMenu("Help");

        // FILE MENU
        newdoc = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        print = new JMenuItem("Print");
        exit = new JMenuItem("Exit");

        newdoc.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        open.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        save.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        print.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.addSeparator();
        file.add(exit);

        // EDIT MENU
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");

        cut.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        copy.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        paste.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        selectall.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.addSeparator();
        edit.add(selectall);

        // FORMAT MENU
        wordWrap = new JCheckBoxMenuItem("Word Wrap", true);
        fontfamily = new JMenuItem("Font Family");
        fontstyle = new JMenuItem("Font Style");
        fontsize = new JMenuItem("Font Size");

        format.add(wordWrap);
        format.addSeparator();
        format.add(fontfamily);
        format.add(fontstyle);
        format.add(fontsize);

        // VIEW MENU
        statusBar = new JCheckBoxMenuItem("Status Bar", true);
        view.add(statusBar);

        // HELP MENU
        about = new JMenuItem("About Notepad");
        help.add(about);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(view);
        menuBar.add(help);

        f.setJMenuBar(menuBar);

        // STATUS BAR
        statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(new Color(45, 45, 45));
        statusLabel = new JLabel("Ln 1, Col 1 | UTF-8 | Windows (CRLF)");
        statusLabel.setForeground(fgLight);
        statusPanel.add(statusLabel);

        f.add(scpane, BorderLayout.CENTER);
        f.add(statusPanel, BorderLayout.SOUTH);
        f.setVisible(true);
    }

    private JMenu createMenu(String name) {
        JMenu m = new JMenu(name);
        m.setForeground(fgLight);
        m.setFont(new Font("Segoe UI", Font.BOLD, 16));
        return m;
    }

    private void addActionEvents() {

        // FILE
        newdoc.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);

        // EDIT
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);

        // FORMAT
        wordWrap.addActionListener(this);
        fontfamily.addActionListener(this);
        fontstyle.addActionListener(this);
        fontsize.addActionListener(this);

        // VIEW
        statusBar.addActionListener(this);

        // HELP
        about.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        // FILE
        if (src == newdoc) area.setText("");
        else if (src == open) openFile();
        else if (src == save) saveFile();
        else if (src == print) { try { area.print(); } catch (Exception ignored) {} }
        else if (src == exit) f.dispose();

        // EDIT
        else if (src == cut) area.cut();
        else if (src == copy) area.copy();
        else if (src == paste) area.paste();
        else if (src == selectall) area.selectAll();

        // FORMAT
        else if (src == wordWrap) {
            area.setLineWrap(wordWrap.isSelected());
            area.setWrapStyleWord(wordWrap.isSelected());
        }
        else if (src == fontfamily) chooseFontFamily();
        else if (src == fontstyle) chooseFontStyle();
        else if (src == fontsize) chooseFontSize();

        // VIEW
        else if (src == statusBar) statusPanel.setVisible(statusBar.isSelected());

        // HELP
        else if (src == about) {
            JOptionPane.showMessageDialog(f,
                    "Java Notepad\nDark Theme\nBuilt with Swing\n© Ayush",
                    "About", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        if (chooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                area.read(br, null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Error opening file");
            }
        }
    }

    private void saveFile() {
        JFileChooser saver = new JFileChooser();
        if (saver.showSaveDialog(f) == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(saver.getSelectedFile()))) {
                area.write(bw);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Error saving file");
            }
        }
    }

    private void chooseFontFamily() {
        JList<String> list = new JList<>(fontFamilyValues);
        if (JOptionPane.showConfirmDialog(f, list, "Font Family",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            fontFamily = list.getSelectedValue();
            if (fontFamily != null)
                area.setFont(new Font(fontFamily, fstyle, fsize));
        }
    }

    private void chooseFontStyle() {
        JList<String> list = new JList<>(fontStyleValues);
        if (JOptionPane.showConfirmDialog(f, list, "Font Style",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            int idx = list.getSelectedIndex();
            if (idx >= 0) {
                fstyle = stylevalue[idx];
                area.setFont(new Font(fontFamily, fstyle, fsize));
            }
        }
    }

    private void chooseFontSize() {
        JList<String> list = new JList<>(fontSizeValues);
        if (JOptionPane.showConfirmDialog(f, list, "Font Size",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            fsize = Integer.parseInt(list.getSelectedValue());
            area.setFont(new Font(fontFamily, fstyle, fsize));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TextNotepad::new);
    }
}
