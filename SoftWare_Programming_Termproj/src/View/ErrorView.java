package View;

import javax.swing.*;

public class ErrorView extends JFrame {
    String data = "Data.csv";

    public ErrorView(String error) {
        JOptionPane.showMessageDialog
                (null, error + " \n������" + data + " ������ �������� �ʽ��ϴ�.", " File Not Exist", JOptionPane.WARNING_MESSAGE);
    }
}
