package Model;

public class Record {

	private String name = "��ϵ��� ����"; //���Ҹ�
	private String type = "��ϵ��� ����"; // ����
	private String state = "��ϵ��� ����"; // �õ�
	private String city = "��ϵ��� ����"; // �ñ���
	private String address = "��ϵ��� ����"; // �ּ�
	private String menu = "��ϵ��� ����"; //�ָ޴�
	private String number = "��ϵ��� ����"; //��ȭ��ȣ

	public Record(String state, String city, String type, String menu, String name, String address, String number){
		this.state = state;
		this.name = name;
		this.type = type;
		this.city = city;
		this.type = type;
		this.menu = menu;
		this.address = address;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return address;
	}

	public String getMenu() {
		return menu;
	}

	public String getNumber() {
		return number;
	}
}
