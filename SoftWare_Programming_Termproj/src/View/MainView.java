package View;


import Controller.Functions;
import Model.Record;
import View.springLayout.SpringUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Objects;

public class MainView extends JFrame {
    int[] recordNum = new int[5];
    Functions f;

    public MainView(LinkedList<Record> records) {
        /*
         * ��ũ�帮��Ʈ�� ������ ���ڵ尡 �� �Ѿ�Դ�����
         * �׽�Ʈ�ϴ� �����Դϴ�.
         */
//        System.out.println("���ڵ��� ���� ����: "+records.size());
//        Record testFirstRecord = records.get(0);
//        int rand = (int) (Math.random() * (records.size() - 1));
//        Record testRandomRecord = records.get(rand-1);
//        Record testLastRecord = records.get(records.size()-1);
//        System.out.println("--------ù �� �׽�Ʈ--------"+
//                "\n�õ� : "+testFirstRecord.getState()+
//                "\n�ñ��� : "+testFirstRecord.getCity()+
//                "\n���� : "+testFirstRecord.getType()+
//                "\n�ָ޴� : "+testFirstRecord.getMenu()+
//                "\n���Ҹ� : "+testFirstRecord.getName()+
//                "\n�ּ� : "+testFirstRecord.getAddress()+
//                "\n��ȭ��ȣ : "+testFirstRecord.getNumber()+
//                "\n---------------------------\n"
//        );
//        System.out.println("--------"+rand+"��° �� �׽�Ʈ--------"+
//                "\n�õ� : "+testRandomRecord.getState()+
//                "\n�ñ��� : "+testRandomRecord.getCity()+
//                "\n���� : "+testRandomRecord.getType()+
//                "\n�ָ޴� : "+testRandomRecord.getMenu()+
//                "\n���Ҹ� : "+testRandomRecord.getName()+
//                "\n�ּ� : "+testRandomRecord.getAddress()+
//                "\n��ȭ��ȣ : "+testRandomRecord.getNumber()+
//                "\n---------------------------\n"
//        );
//        System.out.println("--------������ �� �׽�Ʈ--------"+
//                        "\n�õ� : "+testLastRecord.getState()+
//                        "\n�ñ��� : "+testLastRecord.getCity()+
//                        "\n���� : "+testLastRecord.getType()+
//                        "\n�ָ޴� : "+testLastRecord.getMenu()+
//                        "\n���Ҹ� : "+testLastRecord.getName()+
//                        "\n�ּ� : "+testLastRecord.getAddress()+
//                        "\n��ȭ��ȣ : "+testLastRecord.getNumber()+
//                        "\n---------------------------\n");

        /*
         * ������(Ʋ) ����
         * �׸����� Ű�� �� ȭ�鿡�� �׸��� �׸� �� �ִ� ��ó�� �� ȭ��(�ʱ�ȭ��) ���� ������ �Ѵ�.
         * �����̳����� ��ư, �� ���� ������Ʈ ���� ����(add)�ϴ� ����̴�.
         */
        setTitle("��󳲵� ��������� �˻� ���α׷�"); // ���α׷� â ���� ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X ��ư ������ ���μ����� ����ȴ�.
        Container container = getContentPane();
        container.setLayout(null); // ������ ���̾ƿ��� ������� ����
        // -----------------------------------------------------------------------------------------------------
        /*
         * ȭ�� ��� ���� ��
         * ���� ��� ������Ʈ���� ���� ������ ������ �����ϴ�.
         * ������Ʈ ����
         * ������Ʈ ũ��� ���̾ƿ� ����
         * ������Ʈ�� �������� Ʋ�� ����
         */
        JLabel labelTitle1, labelTitle2;
        Font titleFont;
        titleFont = new Font("���", Font.BOLD, 20);
        labelTitle1 = new JLabel("��󳲵� ���������");
        labelTitle1.setFont(titleFont);
        labelTitle1.setBounds(0, 20, 360, 30);
        labelTitle1.setHorizontalAlignment(JLabel.CENTER);
        labelTitle2 = new JLabel("�˻� ���α׷�");
        titleFont = new Font("���", Font.BOLD, 24);
        labelTitle2.setFont(titleFont);
        labelTitle2.setBounds(0, 50, 360, 30);
        labelTitle2.setHorizontalAlignment(JLabel.CENTER);
        container.add(labelTitle1);
        container.add(labelTitle2);

        /*
         * �˻� ����
         */
        JComboBox combobox;
        JLabel labelType;
        String[] search_rule = {"�������� �˻�", "�ּҷ� �˻�", "�޴��� �˻�"};

        combobox = new JComboBox(search_rule);
        combobox.setBounds(5,120,110,40);
        container.add(combobox);

        JTextField textField = new JTextField();
        textField.setBounds(120, 120,160,40);
        container.add(textField);

        // �˻� ��ư
        JButton button_search = new JButton();
        button_search.setText("�˻�");
        button_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = "";
                String searchString = "";
                try {
                    selectedItem = Objects.requireNonNull(combobox.getSelectedItem()).toString();
                    searchString = textField.getText();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(button_search, "���������� �����Դϴ�.", "���", JOptionPane.WARNING_MESSAGE);
                }
                if (selectedItem.equals("")) {
                    JOptionPane.showMessageDialog(button_search, "������ �����ϼ���.", "����", JOptionPane.INFORMATION_MESSAGE);
                } else if (searchString.equals("")) {
                    JOptionPane.showMessageDialog(button_search, "�˻��� �ܾ �Է��ϼ���.", "����", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println(selectedItem);
                    System.out.println(searchString);
                    System.out.println("�� â ����");
                    new SearchView(records, selectedItem, searchString);
                }
            }
        });
        button_search.setBounds(285, 120, 60, 40);
        container.add(button_search);

        /*
         * combobox���� ������ �������� ������ �׽�Ʈ�� ��
         */
        labelType = new JLabel("�˻� ������ �����ϰ� �˻�� �Է��ϼ���.");
        labelType.setBounds(0,180,360,40);
        labelType.setHorizontalAlignment(JLabel.CENTER);
        container.add(labelType);
        combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = combobox.getSelectedItem().toString();
                labelType.setText("���õ� ���:  " + selectedItem);
            }
        });


        /*
         * ���� ��õ ���� �� ���̺� ����
         */
        JLabel randomLabel = new JLabel();
        randomLabel.setText("���� ��õ");
        randomLabel.setFont(new Font("���", Font.BOLD, 36));
        randomLabel.setBounds(50, 330, 260, 50);
//        randomLabel.setOpaque(true);
//        randomLabel.setBackground(Color.GREEN);
        randomLabel.setHorizontalAlignment(JLabel.CENTER);
        container.add(randomLabel);

        /*
         * ���� ��õ ���� �� ���ΰ�ħ
         */
        ImageIcon icon_Reload = new ImageIcon("image/reload.png");
        JButton button_Reload = new JButton(icon_Reload);
        button_Reload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                container.removeAll();
                container.add(labelTitle1);
                container.add(labelTitle2);
                container.add(combobox);
                container.add(textField);
                container.add(button_search);
                container.add(labelType);
                container.add(randomLabel);
                container.add(button_Reload);
                container.setBackground(Color.white);

                f = new Functions(records.size());
                recordNum = f.RandomGeneration();
                Record[] record = new Record[5];
                for (int i=0; i<recordNum.length; i++) {
                    record[i] = records.get(recordNum[i]);
                }

                Object[] columnNames = new Object[]
                        { "���Ҹ�", "����", "�󼼺���" };
                Object[][] rowData = new Object[][] {
                        { record[0].getName(), record[0].getType(), new JButton() },
                        { record[1].getName(), record[1].getType(), new JButton() },
                        { record[2].getName(), record[2].getType(), new JButton() },
                        { record[3].getName(), record[3].getType(), new JButton() },
                        { record[4].getName(), record[4].getType(), new JButton() }
                };

                // ���̺� �𵨿� �迭 ����
                DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);
                JTable jtable = new JTable(dtm);
                // JTable�� JScrollPane�� �ݵ�� �������־�� �Ѵ�.
                // ContentPane ���� ScrollPane ������ JTable���� ä���� �ִٰ� �����ϸ� �ȴ�.
                JScrollPane scrollpane = new JScrollPane(jtable);

                jtable.getColumnModel().getColumn(2).setCellRenderer(new Main_TableCell(jtable, record));
                jtable.getColumnModel().getColumn(2).setCellEditor(new Main_TableCell(jtable, record));

                jtable.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
                scrollpane.setBounds(5, 380, 340, 105); //��ũ������ ũ�� ���� (= ���̺�ũ�� ����)
                container.add(scrollpane);
            }
        });
        button_Reload.setBounds(310,340,32,32);
        container.add(button_Reload);

        /*
         * ���� ��õ ���̺� ����
         * ���̺� ���� �迭�� �̷���� �־�
         * ������ ��Ÿ���� �迭�� ���� ��������� �Ѵ�.
         */
        f = new Functions(records.size());
        recordNum = f.RandomGeneration();
        Record[] records_random = new Record[5];
        System.out.println("-------------���� ���̺� �׽�Ʈ-------------");
        for (int i=0; i<recordNum.length; i++) {
            records_random[i] = records.get(recordNum[i]);
            System.out.println(records_random[i].getName());
        }
        System.out.println("-------------�׽�Ʈ ����-------------------");

        Object[] columnNames = new Object[]
                { "���Ҹ�", "����", "�󼼺���" };
        Object[][] rowData = new Object[][] {
                { records_random[0].getName(), records_random[0].getType(), new JButton() },
                { records_random[1].getName(), records_random[1].getType(), new JButton() },
                { records_random[2].getName(), records_random[2].getType(), new JButton() },
                { records_random[3].getName(), records_random[3].getType(), new JButton() },
                { records_random[4].getName(), records_random[4].getType(), new JButton() }
        };

        // ���̺� �𵨿� �迭 ����
        DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);
        JTable jtable = new JTable(dtm);
        // JTable�� JScrollPane�� �ݵ�� �������־�� �Ѵ�.
        // ContentPane ���� ScrollPane ������ JTable���� ä���� �ִٰ� �����ϸ� �ȴ�.
        JScrollPane scrollpane = new JScrollPane(jtable);

        jtable.getColumnModel().getColumn(2).setCellRenderer(new Main_TableCell(jtable, records_random));
        jtable.getColumnModel().getColumn(2).setCellEditor(new Main_TableCell(jtable, records_random));

        jtable.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
        scrollpane.setBounds(5, 380, 340, 105); //��ũ������ ũ�� ���� (= ���̺�ũ�� ����)
        container.add(scrollpane);



        /*
         * �����̳� ũ�� �� ����
         */
        container.setBackground(Color.white);
        setSize(360, 530);
        setResizable(false); //ũ�� ����
        setVisible(true);
    }

        class Main_TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
            JButton jb;

            public Main_TableCell(JTable table, Record[] record) {
                jb = new JButton("Ŭ��");
                jb.addActionListener(e -> {
                    System.out.println("Button Clicked!");
                    int row = table.getSelectedRow();
                    for(int i=0; i<table.getColumnCount()-1; i++) {
                        System.out.println(table.getValueAt(row, i).toString()+"\t");
                    }
                    System.out.println(record[row].getName()+" ������ â ����");
                    new dialog(record[row]);
                });
            }
        // �ʿ���� �������̵��

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return jb;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                                                     int column) {
            return jb;
        }

        // �󼼺��⸦ Ŭ���ϸ� ����Ǵ� �޼����Դϴ�.
        // �������� ���� ������ ��� â�� ����ݴϴ�.
        private class dialog extends JDialog {

            public dialog(Record record){

                String[] colNames = {
                        "���Ҹ�: ",
                        "����: ",
                        "�ָ޴�: ",
                        "�õ�: ",
                        "�ñ���: ",
                        "�ּ�: ",
                        "��ȭ��ȣ: "
                };
                String[] colDetails = {
                        record.getName(),
                        record.getType(),
                        record.getMenu(),
                        record.getState(),
                        record.getCity(),
                        record.getAddress(),
                        record.getNumber()
                };

                JPanel panel = new JPanel(new SpringLayout());

                if(colNames.length != colDetails.length) {
                    System.out.println("���̺��� �ùٸ��� �ʽ��ϴ�.");
                } else {
                    int pairs = colNames.length;

                    // �г� ���θ� ä��ϴ�. (0,0), (0,1), (1,0)...(5,1) �����Դϴ�.
                    Font fontforName = new Font("���", Font.BOLD, 28);
                    Font fontforDetail = new Font("���", Font.PLAIN, 28);

                    for (int i = 0; i < pairs; i++) {

                        JLabel colName = new JLabel(colNames[i]);
                        colName.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                        colName.setFont(fontforName);
                        colName.setBackground(Color.lightGray);
                        colName.setOpaque(true);
                        panel.add(colName);

                        JLabel colDetail = new JLabel(colDetails[i]);
                        colDetail.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                        colDetail.setFont(fontforDetail);
                        panel.add(colDetail);
                    }

                    // ���� ���·� UI�� ����ִ� �޼����Դϴ�.
                    // https://docs.oracle.com/javase/tutorial/displayCode.html
                    SpringUtilities.makeCompactGrid(panel,
                            pairs, 2,               //rows, cols
                            6, 15,        //initX, initY
                            6, 0);           //xPad, yPad
                }

                // ���� JPanel�� JFrame�� �����ϱ�
                // JPanel �����δ� â�� ��� �� �����ϴ�. JFrame�� �̿��ؼ� ����� �մϴ�.
                JFrame frame = new JFrame(record.getName()+" ������");
                panel.setOpaque(true);  //content panes must be opaque
                panel.setBackground(Color.white);
                frame.setContentPane(panel);

                //JFrame ��Ÿ ����
                frame.pack();
                frame.setVisible(true);
                frame.setLayout(null); // â�� ����� ������ �� ����
                frame.setResizable(false); //ũ�� ����
            }
        }
    }
}
