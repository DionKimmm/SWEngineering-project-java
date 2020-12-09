package Controller;

import Model.Record;
import View.ErrorView;
import View.MainView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadFileCSV {

	LinkedList<Record> records = new LinkedList<Record>();
	String path;
	public ReadFileCSV() throws IOException { // 생성자를 이용해서 읽어오기.
		try {
			System.out.println(records.size());
			if(records.size() == 0) {
				this.path = System.getProperty("user.dir"); // 현재폴더의 디렉토리 가지고 오기.
				System.out.println(path);
				File file = new File(path + "\\Data.csv"); // 현재 폴더의 디렉토리에 파일 저장해놓고 경로 지정.

				BufferedReader br = new BufferedReader(new FileReader(file)); // 버퍼리더 만들기.

				String line = "";
				int num = 0;
				while ((line = br.readLine()) != null) { // 한 라인씩 읽어오기.

					// 이 부분에서 큰 따옴표가 삭제됨.
					 String first_Cut_string[] = line.replace(" ", "").split("\"");
					 // 큰 따옴표 삭제 -> 띄워쓰기 삭제 -> 구분자 ","를
					// 이용하여 자른다음 Cut_string에 넣기
					if (num != 0) {

						// 이 부분에서 콤마가 삭제됨.
						for (int i = 0; i < first_Cut_string.length; i++) {
							if (first_Cut_string[i].compareTo(",") == 0) {
								first_Cut_string[i] = "";
							}
						}
						// 여러 배열을 하나의 문자열로 합치기
						String temp_string = "";
						for (int i = 0; i < first_Cut_string.length; i++) {

							if (i != first_Cut_string.length - 1) {
								temp_string += first_Cut_string[i] + ";";
							} else {
								temp_string += first_Cut_string[i];
							}
						}
						// System.out.println(temp_string);

						String[] Second_Cut_String = temp_string.replace
								(";;", ";").split(";");

//						System.out.println(Second_Cut_String.length);
						records.add(new Record(
						Second_Cut_String[1], Second_Cut_String[2],
								Second_Cut_String[3], Second_Cut_String[4],
								Second_Cut_String[5], Second_Cut_String[6],
								Second_Cut_String[7]
						));
					}
					num++;
				}
				new MainView(records);
			} else {
			}

			/*
			 * 레코드가 잘 저장되어 넘어왔는지
			 * 첫 값, 중간 값, 끝 값을 테스트하는 함수입니다.
			 * csv파일과 대조하며 확인할 수 있습니다.
			 */
//			TestForRecords(records);
		}

		/*
		 * 지정된 경로에 Data.csv 파일을 찾을 수 없는 경우
		 */
		catch(FileNotFoundException e) {
			System.out.println("path = " + path + "\\Data.csv");
			new ErrorView(path);
		}
	}

	private void TestForRecords(LinkedList<Record> records) {
		/*
		 * 레코드가 잘 저장되어 넘어왔는지에 대한 테스트 구문입니다.
		 */
		System.out.println("레코드의 열의 개수: "+records.size());
		Record testFirstRecord = records.get(0);
		int rand = (int) (Math.random() * (records.size() - 1));
		Record testRandomRecord = records.get(rand-1);
		Record testLastRecord = records.get(records.size()-1);
		System.out.println("--------첫 값 테스트--------"+
				"\n시도 : "+testFirstRecord.getState()+
				"\n시군구 : "+testFirstRecord.getCity()+
				"\n업태 : "+testFirstRecord.getType()+
				"\n주메뉴 : "+testFirstRecord.getMenu()+
				"\n업소명 : "+testFirstRecord.getName()+
				"\n주소 : "+testFirstRecord.getAddress()+
				"\n전화번호 : "+testFirstRecord.getNumber()+
				"\n---------------------------\n"
		);
		System.out.println("--------"+rand+"번째 값 테스트--------"+
				"\n시도 : "+testRandomRecord.getState()+
				"\n시군구 : "+testRandomRecord.getCity()+
				"\n업태 : "+testRandomRecord.getType()+
				"\n주메뉴 : "+testRandomRecord.getMenu()+
				"\n업소명 : "+testRandomRecord.getName()+
				"\n주소 : "+testRandomRecord.getAddress()+
				"\n전화번호 : "+testRandomRecord.getNumber()+
				"\n---------------------------\n"
		);
		System.out.println("--------마지막 값 테스트--------"+
				"\n시도 : "+testLastRecord.getState()+
				"\n시군구 : "+testLastRecord.getCity()+
				"\n업태 : "+testLastRecord.getType()+
				"\n주메뉴 : "+testLastRecord.getMenu()+
				"\n업소명 : "+testLastRecord.getName()+
				"\n주소 : "+testLastRecord.getAddress()+
				"\n전화번호 : "+testLastRecord.getNumber()+
				"\n---------------------------\n"
		);
	}
}