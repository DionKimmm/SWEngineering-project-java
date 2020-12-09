package View;

import Controller.Functions;
import Model.Record;
import View.springLayout.SpringUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.LinkedList;

public class SearchView extends JFrame {

    LinkedList<Record> data; // csv ���� ������
    LinkedList<Record> records_searched; // �˻����ǰ� �˻�� �����ϴ� ����Ʈ
    String searchType; // �˻� ���� ������ ���ڿ�
    String searchString; // �˻��� ���ڿ�
    Functions f;

    public SearchView(LinkedList<Record> data, String searchType, String searchString) {
        System.out.println(data.size());
        this.data = data;
        this.searchType = searchType;
        this.searchString = searchString;

        setTitle("'" + searchString + "' �˻� ���");
        System.out.println("---------- �˻� ���ǰ� �˻��� �� �׽�Ʈ ----------");
        System.out.println("'" + searchString + "' �˻� ���");
        System.out.println(searchType + "���� ����");
        System.out.println("------------------------------------------------");

        Container container = getContentPane();
        container.setLayout(null); // ������ ���̾ƿ��� ������� ����

        /*
         * ���� ��
         */
        JLabel labelTitle1, labelTitle2;
        Font titleFont;
        titleFont = new Font("���", Font.BOLD, 20);
        labelTitle1 = new JLabel(searchString);
        labelTitle1.setFont(titleFont);
        labelTitle1.setBounds(0, 20, 360, 30);
        labelTitle1.setHorizontalAlignment(JLabel.CENTER);
        labelTitle2 = new JLabel("(��)�� �˻��� ���");
        titleFont = new Font("���", Font.BOLD, 24);
        labelTitle2.setFont(titleFont);
        labelTitle2.setBounds(0, 50, 360, 30);
        labelTitle2.setHorizontalAlignment(JLabel.CENTER);
        container.add(labelTitle1);
        container.add(labelTitle2);

        /*
         * ���̺� ����� �� - selected Item�� ���� Search�Լ� ����
         */
        if (searchType.equals("�������� �˻�")) {
            System.out.println("������������ �˻� �Լ� ����");
        } else if (searchType.equals("�ּҷ� �˻�")) {
            System.out.println("�ּҷ� �˻� �Լ� ����");
        } else if (searchType.equals("�޴��� �˻�")) {
            System.out.println("�޴��� �˻� �Լ� ����");
        }
        f = new Functions(data.size());
        records_searched = f.Search(data, searchType, searchString);

        /*
         * ���̺� ����� �� - �˻� ��� ������ Ȯ��
         */
        int rowSize = 0;
        if (records_searched == null) {
            System.out.println("null �׽�Ʈ : �˻� ����� �����ϴ�.");
            JLabel label = new JLabel("�˻� ����� �����ϴ�.");
            label.setBounds(0, 130, 360, 30);
            label.setFont(new Font("���", Font.BOLD, 26));
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label);
            label = new JLabel("�˻���� ������ Ȯ���ϼ���.");
            label.setFont(new Font("���", Font.PLAIN, 18));
            label.setBounds(0, 170,360, 20);
            label.setHorizontalAlignment(JLabel.CENTER);
            container.add(label);
            setSize(360, 240);
//        setSize(365, 660);
            setResizable(false); //ũ�� ����
            setVisible(true);
        } else {
            System.out.println("null �׽�Ʈ : �˻� ����� �ֳ׿�");
            rowSize = records_searched.size();
            System.out.println("�˻��� ���� ���� : �� " + rowSize + " ��");

            /*
             * ���̺� ����
             */
            Object[] columnNames = new Object[]
                    {"���Ҹ�", "����", "�ñ���", "�󼼺���"};
            Object[][] rowData = new Object[rowSize][columnNames.length];
            for (int i = 0; i < rowSize; i++) {
                Record tempRecord = records_searched.get(i);
                Object[] tempData = {tempRecord.getName(), tempRecord.getType(), tempRecord.getCity(), new JButton()};

                for (int j = 0; j < tempData.length; j++) {
                    rowData[i][j] = tempData[j];
                }
            }

            // ���̺� �𵨿� �迭 ����
            DefaultTableModel dtm = new DefaultTableModel(rowData, columnNames);
            JTable jtable = new JTable(dtm);
            // JTable�� JScrollPane�� �ݵ�� �������־�� �Ѵ�.
            // ContentPane ���� ScrollPane ������ JTable���� ä���� �ִٰ� �����ϸ� �ȴ�.
            JScrollPane scrollpane = new JScrollPane(jtable);

            jtable.getColumnModel().getColumn(columnNames.length-1).setCellRenderer(new Search_TableCell(jtable, records_searched));
            jtable.getColumnModel().getColumn(columnNames.length-1).setCellEditor(new Search_TableCell(jtable, records_searched));

            jtable.getTableHeader().setReorderingAllowed(false); // �÷��� �̵� �Ұ�
            int tableSizeHeight = 25+rowSize*16; // ���̺���� ���̰� ��¹��� ������ ���� �������� �ٲ�� �ϱ� ���� ����
            scrollpane.setBounds(5, 100, 340, tableSizeHeight); // ��ũ������ ũ�� ���� (= ���̺�ũ�� ����)
            container.add(scrollpane);

            container.setBackground(Color.white);
            int containerSizeHeight = 145 + tableSizeHeight;
            System.out.println("â ���� ũ��: "+containerSizeHeight);
            setSize(360, containerSizeHeight);
            setResizable(false); //ũ�� ����
            setVisible(true);
        }


    }
    class Search_TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
        JButton jb;

        public Search_TableCell(JTable table, LinkedList<Record> record) {
            jb = new JButton("Ŭ��");
            jb.addActionListener(e -> {
                System.out.println("Button Clicked!");
                int row = table.getSelectedRow();
                for(int i=0; i<table.getColumnCount()-1; i++) {
                    System.out.println(table.getValueAt(row, i).toString()+"\t");
                }
                System.out.println(record.get(row).getName()+" ������ â ����");
                new dialog(record.get(row));
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
                    Font fontForName = new Font("���", Font.BOLD, 28);
                    Font fontForDetail = new Font("���", Font.PLAIN, 28);

                    for (int i = 0; i < pairs; i++) {

                        JLabel colName = new JLabel(colNames[i]);
                        colName.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                        colName.setFont(fontForName);
                        colName.setBackground(Color.lightGray);
                        colName.setOpaque(true);
                        panel.add(colName);

                        JLabel colDetail = new JLabel(colDetails[i]);
                        colDetail.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
                        colDetail.setFont(fontForDetail);
                        panel.add(colDetail);
                    }

                    // ���� ���·� UI�� ����ִ� ���̺귯���Դϴ�.
                    // https://docs.oracle.com/javase/tutorial/displayCode.html
                    SpringUtilities.makeCompactGrid(panel,
                            pairs, 2, //rows, cols
                            6, 15,        //initX, initY
                            6, 0);       //xPad, yPad
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
