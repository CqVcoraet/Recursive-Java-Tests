// Imported Packages
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Console extends JFrame {
    private static Console instance;
    private JTextPane textPane;
    private static boolean isVisible;
    private static StyledDocument doc;
    private static Style defaultStyle, warningStyle, errorStyle;

    // Singleton to ensure one instance
    public static Console getInstance() {
        if (instance == null) {
            instance = new Console("Console");
        }
        return instance;
    }

    private Console(String title) {
        super(title);
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK);
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14));

        doc = textPane.getStyledDocument();
        defaultStyle = textPane.addStyle("default", null);
        StyleConstants.setForeground(defaultStyle, Color.WHITE);

        warningStyle = textPane.addStyle("warning", null);
        StyleConstants.setForeground(warningStyle, Color.decode("#FFC900"));

        errorStyle = textPane.addStyle("error", null);
        StyleConstants.setForeground(errorStyle, Color.decode("#D40000"));

        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.CENTER);

        // Set the location of the frame to the upper right corner
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - getWidth();
        int y = 0;
        setLocation(x, y);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem about = new JMenuItem("About");
        JMenuItem clear = new JMenuItem("Clear");
        menu.add(about);
        menu.add(clear);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        clear.addActionListener(e -> {
            int result = JOptionPane.showOptionDialog(null, "Are you sure you want to clear the console?", "Clear Console",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Yes", "No" }, "No");
            if (result == JOptionPane.YES_OPTION) {
                textPane.setText("");
            }
        });

        setVisible(true);
    }

    public static void print(Object text) {
        try {
            getInstance().appendText(String.valueOf(text), defaultStyle);
        } catch (Exception e) {
            Console.errprintln("Error: " + e.getMessage());
            Console.errprintln("Error Stack Trace: " + e.getStackTrace());
        }
    }

    public static void warnprint(Object text) {
        getInstance().appendText(String.valueOf(text), warningStyle);
    }

    public static void errprint(Object text) {
        getInstance().appendText(String.valueOf(text), errorStyle);
    }
    
    public static void println(Object text) {
        try {
            getInstance().appendText(String.valueOf(text) + "\n", defaultStyle);
        } catch (Exception e) {
            Console.errprintln("Error: " + e.getMessage());
            Console.errprintln("Error Stack Trace: " + e.getStackTrace());
        }
    }

    public static void warnprintln(Object text) {
        getInstance().appendText(String.valueOf(text) + "\n", warningStyle);
    }
    
    public static void errprintln(Object text) {
        getInstance().appendText(String.valueOf(text) + "\n", errorStyle);
    }

    public static void spacer() {
        getInstance().appendText("\n", defaultStyle);
    }

    private void appendText(String text, Style style) {
        SwingUtilities.invokeLater(() -> {
            try {
                doc.insertString(doc.getLength(), text, style);
                textPane.setCaretPosition(doc.getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }

    public static void toggleVisibility() {
        Console console = getInstance(); // Ensure the instance exists
        isVisible = !isVisible;
        console.setVisible(isVisible);
    }
}
