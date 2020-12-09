package Controller;

import Model.Record;

import java.util.LinkedList;

public class Functions {

    int[] num = new int[5];
    int[] index = new int[20];
    int dataSize;

    public Functions() {
    }

    public Functions(int dataSize) {

        this.dataSize = dataSize;
    }
    public int[] RandomGeneration() {
        System.out.println("-------�ߺ����� �ʴ� ���� �� �׽�Ʈ-------");
        for (int i = 0; i < 5; i++) {
            num[i] = (int) (Math.random() * (dataSize - 1) + 1); // ������ �� �ֱ�
            for (int j = 0; j < i; j++) { // ���� ���� num�� �ٸ� ���� ��
                if (num[i] == num[j]) {
                    i--; // ������ i���ְ� �ٽ� �ʱ�ȭ�۾�
                    break;
                }
            }
            System.out.print(num[i]+" ");
        }
        System.out.println("");
        System.out.println("------------------------------------------");

        return num;
    }

    public LinkedList<Record> Search(LinkedList<Record> data, String search_type, String search_string) {
        LinkedList<Record> temp_record = new LinkedList<Record>(); // �˻��� �������� �����ϴ� ��
        System.out.println(data.size());

        if (search_type.equals("�������� �˻�")) {
            System.out.println("�������� �˻�: " + search_string);
            System.out.println(search_string);
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getName().contains(search_string)) {
                    temp_record.add(data.get(i));
                }
            }
        } else if (search_type.equals("�ּҷ� �˻�")) {
            System.out.println("�ּҷ� �˻�: " + search_string);
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getAddress().contains(search_string) || data.get(i).getCity().contains(search_string)
                        || data.get(i).getState().contains(search_string)) {
                    temp_record.add(data.get(i));
                }
            }
        } else if (search_type.equals("�޴��� �˻�")) {
            System.out.println("�޴��� �˻�: " + search_string);
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getMenu().contains(search_string)) {
                    temp_record.add(data.get(i));
                }
            }
        }

        if (temp_record.size() >= 1) {
            LinkedList<Record> rand_record = new LinkedList<>();
            if(temp_record.size() > 20) {
                for (int i = 0; i < 20; i++) {
                    index[i] = (int) (Math.random() * (temp_record.size() - 1) + 1); // ������ �� �ֱ�
                    for (int j = 0; j < i; j++) { // ���� ���� num�� �ٸ� ���� ��
                        if (index[i] == index[j]) {
                            i--; // ������ i���ְ� �ٽ� �ʱ�ȭ�۾�
                            break;
                        }
                    }
                }
                for(int i=0; i<20; i++) {
                    rand_record.add(temp_record.get(index[i]));
                }
                System.out.println("Search() ���");
                for (int i = 0; i < rand_record.size(); i++) {
                    System.out.println("������ ���ڵ� [" + i + "] = " + rand_record.get(i).getName());
                }
                return rand_record;
            } else {
                System.out.println("Search() ���");
                for (int i = 0; i < temp_record.size(); i++) {
                    System.out.println("�˻��� ���ڵ� [" + i + "] = " + temp_record.get(i).getName());
                }
                return temp_record;
            }
        } else {
            System.out.println("Search() ���");
            System.out.println("�˻� ����� �����ϴ�.");
            return null;
        }
    }
}