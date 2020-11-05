import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window extends JFrame {
    private final JButton code, decode;
    private final JLabel strLabel, keyLabel, resLabel;
    private final JTextField strField, keyField, resField;
    private final aHandler handler = new aHandler();

    public Window() {
        super("Lab 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(100, 100);
        setSize(1000, 400);

        JPanel grid = new JPanel();
        GridLayout layout = new GridLayout(0, 3, 12, 12);
        grid.setLayout(layout);

        code = new JButton("Закодировать");
        decode = new JButton("Декодировать");
        strLabel = new JLabel("Исходный текст:");
        keyLabel = new JLabel("Ключ:");
        resLabel = new JLabel("Результат");
        strField = new JTextField(10);
        keyField = new JTextField("Очень сложный ключ", 10);
        resField = new JTextField();

        grid.add(strLabel);
        grid.add(strField);
        grid.add(code);

        grid.add(keyLabel);
        grid.add(keyField);
        grid.add(decode);

        grid.add(resLabel);
        grid.add(resField);

        getContentPane().add(grid);

        code.addActionListener(handler);
        decode.addActionListener(handler);

        setVisible(true);
    }

    public class aHandler implements ActionListener {
        String result = null;

        public void actionPerformed(ActionEvent e) {
            String str = strField.getText();
            String key = keyField.getText();
            Vigener vigener = new Vigener();

            try {
                if (code.equals(e.getSource())) {
                    result = vigener.encrypt(str, key);
                } else if (decode.equals(e.getSource())) {
                    result = vigener.decrypt(str, key);
                } else {
                    throw new IOException("Произошла неизвестная ошибка");
                }

                if (result != null) {
                    resLabel.setText("Результат:");
                    resField.setText(result);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
}
