import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.Course_order.model.Course_orderVO;
import com.Teacher.model.TeacherDAO;
import com.Teacher.model.TeacherJDBCDAO;
import com.card.model.CardDAO;
import com.card.model.CardVO;
import com.commodity.model.CommodityDAO;
import com.commodity.model.CommodityVO;
import com.member.model.MemberDAO;
import com.member.model.MemberJDBCDAO;
import com.member.model.MemberVO;

public class TestInsertFile {

	public static void main(String[] args) throws IOException {

//		addToTable("WebContent/temp/files/english100.txt");
//		insertVoice(-19, 100, 20);
		
//		generateInsertCard2000("english7000.txt");
//		generateTct();
//		generateMcg();
//		generateMct();
		
		//插入商城圖片1-10
		insertProductImage(1, 10, 0);
		//插入老師圖片1-10
		insertTeacherImage(1, 10, 0);
		//插入學伴圖片1-10
		insertFriendPic(1, 10, 0);
		//插入會員圖片1-10
		insertMemPic(1, 10, 0);

	}
	
	// insert images to mem_pic
		public static void insertMemPic(int start, int end, int offset) throws IOException {

			MemberJDBCDAO memberDAO = new MemberJDBCDAO();

			for (int i = start; i <= end; i++) {

				File file = new File("WebContent/temp/mem_pic/" + i + ".jpg");
				byte[] image = Files.readAllBytes(file.toPath());
//				System.out.println(voice);
				String Index = String.format("%05d", i + offset);
				MemberVO mVO = memberDAO.findByPrimaryKey("M" + Index);
				mVO.setMem_pic(image);
				memberDAO.update(mVO);
				
				System.out.println("updated WebContent/temp/mem_pic/" + i + ".jpg to " + "M" + Index);
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	// insert images to friend_pic
	public static void insertFriendPic(int start, int end, int offset) throws IOException {

		MemberJDBCDAO memberDAO = new MemberJDBCDAO();

		for (int i = start; i <= end; i++) {

			File file = new File("WebContent/temp/friend_pic/" + i + ".jpg");
			byte[] image = Files.readAllBytes(file.toPath());
//			System.out.println(voice);
			String Index = String.format("%05d", i + offset);
			memberDAO.updateFriendPic("M" + Index, image);
			
			System.out.println("updated WebContent/temp/friend_pic/" + i + ".jpg to " + "M" + Index);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void generateMct() {

		System.out.println();
		//Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) values 
		//('MCG00001', 'C00001', CURRENT_DATE, 0,0,0,0 );
		String member_card_group_id = "";

		int index = 0;
		int MCGindex = 30;

		// for teacher_id 1
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 1:
				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
				break;
			case 51:
				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
				break;
			case 121:
				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
				break;
			}

			
//			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
//					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
		}
		
		MCGindex += 3;
		index += 200;

		// for teacher_id 2 
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 201:
				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
				break;
			case 281:
				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
				break;
			case 331:
				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
				break;
			}

			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
		}
		MCGindex += 3;
		index += 200;

		// for teacher_id 3
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 401:
				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
				break;
			case 441:
				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
				break;
			case 511:
				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
				break;
			}

			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
		}
//		MCGindex += 3;
//		index += 200;
//
//		// for teacher_id 4
//		for (int i = 1 + index; i <= 200 + index; i++) {
//
//			String card_id = String.format("C%05d", i);
//
//			switch (i) {
//			case 601:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
//				break;
//			case 671:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
//				break;
//			case 761:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
//				break;
//			}
//
//			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
//					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
//		}
//		MCGindex += 3;
//		index += 200;
//
//		// for teacher_id 5
//		for (int i = 1 + index; i <= 200 + index; i++) {
//
//			String card_id = String.format("C%05d", i);
//
//			switch (i) {
//			case 801:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
//				break;
//			case 891:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
//				break;
//			case 926:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
//				break;
//			}
//
//			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
//					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
//		}
//		MCGindex += 3;
//		index += 200;
//
//		// for teacher_id 6
//		for (int i = 1 + index; i <= 200 + index; i++) {
//
//			String card_id = String.format("C%05d", i);
//
//			switch (i) {
//			case 1001:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
//				break;
//			case 1036:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
//				break;
//			case 1131:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
//				break;
//			}
//
//			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
//					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
//		}
//		MCGindex += 3;
//		index += 200;
//
//		// for teacher_id 7
//		for (int i = 1 + index; i <= 200 + index; i++) {
//
//			String card_id = String.format("C%05d", i);
//
//			switch (i) {
//			case 1201:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
//				break;
//			case 1261:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
//				break;
//			case 1301:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
//				break;
//			}
//
//			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
//					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
//		}
//		MCGindex += 3;
//		index += 200;
//
//		// for teacher_id 8
//		for (int i = 1 + index; i <= 200 + index; i++) {
//
//			String card_id = String.format("C%05d", i);
//
//			switch (i) {
//			case 1401:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
//				break;
//			case 1491:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
//				break;
//			case 1521:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
//				break;
//			}
//
//			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
//					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
//		}
//		MCGindex += 3;
//		index += 200;
//
//		// for teacher_id 9
//		for (int i = 1 + index; i <= 200 + index; i++) {
//
//			String card_id = String.format("C%05d", i);
//
//			switch (i) {
//			case 1601:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
//				break;
//			case 1676:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
//				break;
//			case 1741:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
//				break;
//			}
//
//			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
//					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
//		}
//		MCGindex += 3;
//		index += 200;
//
//		// for teacher_id 10
//		for (int i = 1 + index; i <= 200 + index; i++) {
//
//			String card_id = String.format("C%05d", i);
//
//			switch (i) {
//			case 1801:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 1);
//				break;
//			case 1866:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 2);
//				break;
//			case 1931:
//				member_card_group_id = String.format("MCG%05d", MCGindex + 3);
//				break;
//			}
//
//			System.out.println("Insert into member_card_table (member_card_group_id,card_id,review_time,wrong_number,right_number,daily_answer,choice_card_group) "
//					+ "values ('" + member_card_group_id +"', '"+ card_id +"', CURRENT_DATE, 0,0,0,0 );");
//		}
//		MCGindex += 3;
//		index += 200;

	}
	public static void generateMcg() {

//		Insert into member_card_group (member_card_group_id,teacher_card_group_id,member_id,expiration_num,new_card_num,spend_time) values 
//		('MCG'||LPAD(to_char(mc_seq.NEXTVAL), 5, '0'),'TCG00001', 'M00011', 0, 0, 0);

		int index = 0;
		
		// for member_id 1
//		for (int i = 1 + index; i <= 30; i++) {
//			
//			String teacher_card_group_id = String.format("TCG%05d", i);
//			String member_id = String.format("M%05d", 11);
//
//			System.out.println("Insert into member_card_group (member_card_group_id,teacher_card_group_id,member_id,expiration_num,new_card_num,spend_time) values "
//					+ "('MCG'||LPAD(to_char(mc_seq.NEXTVAL), 5, '0'),'" + teacher_card_group_id + "', '" + member_id + "', 0, 0, 0);");
//		}
		
		 index = 0;
		
		// for member_id 2
		for (int i = 1 ; i <= 30; i+=index) {
			
			index = (int) (Math.random()*5)+1;
			String teacher_card_group_id = String.format("TCG%05d", i);
			String member_id = String.format("M%05d", 13);

			System.out.println("Insert into member_card_group (member_card_group_id,teacher_card_group_id,member_id,expiration_num,new_card_num,spend_time) values "
					+ "('MCG'||LPAD(to_char(mc_seq.NEXTVAL), 5, '0'),'" + teacher_card_group_id + "', '" + member_id + "', 0, 0, 0);");
		}
	}

	public static void generateTct() {

		System.out.println();
		// Insert into teacher_card_table (card_id,teacher_card_group_id) values
		// ('C00001', 'TCG00001');
		String teacher_card_group_id = "";

		int index = 0;
		int TCGindex = 0;

		// for teacher_id 1
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 1:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 51:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 121:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		
		TCGindex += 3;
		index += 200;

		// for teacher_id 2 
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 201:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 281:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 331:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

		// for teacher_id 3
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 401:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 441:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 511:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

		// for teacher_id 4
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 601:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 671:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 761:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

		// for teacher_id 5
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 801:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 891:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 926:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

		// for teacher_id 6
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 1001:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 1036:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 1131:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

		// for teacher_id 7
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 1201:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 1261:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 1301:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

		// for teacher_id 8
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 1401:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 1491:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 1521:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

		// for teacher_id 9
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 1601:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 1676:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 1741:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

		// for teacher_id 10
		for (int i = 1 + index; i <= 200 + index; i++) {

			String card_id = String.format("C%05d", i);

			switch (i) {
			case 1801:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 1);
				break;
			case 1866:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 2);
				break;
			case 1931:
				teacher_card_group_id = String.format("TCG%05d", TCGindex + 3);
				break;
			}

			System.out.println("Insert into teacher_card_table (card_id,teacher_card_group_id) values ('" + card_id
					+ "', '" + teacher_card_group_id + "');");
		}
		TCGindex += 3;
		index += 200;

	}

	// insert images to commodity
	public static void insertProductImage(int start, int end, int offset) throws IOException {

		CommodityDAO commodityDAO = new CommodityDAO();

		for (int i = start; i <= end; i++) {

			File file = new File("WebContent/temp/images/" + i + ".jpg");
			byte[] image = Files.readAllBytes(file.toPath());
//			System.out.println(voice);
			String commodityIndex = String.format("%05d", i + offset);
			commodityDAO.updateImage("CM" + commodityIndex, image);
			System.out.println("updated WebContent/temp/images/" + i + ".jpg to " + "CM" + commodityIndex);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	//insertTeacherPic
	public static void insertTeacherImage(int start, int end, int offset) throws IOException {

		TeacherJDBCDAO teacherDao = new TeacherJDBCDAO();

		for (int i = start; i <= end; i++) {

			File file = new File("WebContent/temp/teachers/" + i + ".jpg");
			byte[] image = Files.readAllBytes(file.toPath());
//			System.out.println(voice);
			String teacher_id = String.format("%05d", i + offset);
			teacherDao.updatePic("T" + teacher_id, image);
			System.out.println("updated WebContent/temp/teachers/" + i + ".jpg to T" + teacher_id);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// insert mp3 to card
	public static void insertVoice(int start, int end, int offset) throws IOException {

		CardDAO cardDAO = new CardDAO();

		for (int i = start; i <= end; i++) {

			File file = new File("WebContent/temp/sound/" + i + ".mp3");
			byte[] voice = Files.readAllBytes(file.toPath());
//			System.out.println(voice);
			String cardIndex = String.format("%05d", i + offset);
			cardDAO.updateVoice("C" + cardIndex, voice);
			System.out.println("WebContent/temp/updated sound/" + i + ".mp3 to " + "C" + cardIndex);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// add word data to card
	public static void addToTable(String path) {

		String filename = path;
		Scanner scanner = null;

		try {
			scanner = new Scanner(new File(filename), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			String strArray[] = s.split(",");
			String word = strArray[0];
			String phonetic_symbol = strArray[1];
			String native_explain = strArray[2];
			String example = strArray[3];

			if (strArray[0].length() <= 5) {
				if (strArray[1].length() <= 5) {
					System.out.printf("%d \t %s \t\t %s \t\t %s \t %s \t \n", Integer.parseInt(strArray[4]),
							strArray[0], strArray[1], strArray[2], strArray[3]);
				} else {
					System.out.printf("%d \t %s \t\t %s \t %s \t %s \t \n", Integer.parseInt(strArray[4]), strArray[0],
							strArray[1], strArray[2], strArray[3]);
				}
			} else {
				if (strArray[1].length() <= 5) {
					System.out.printf("%d \t %s \t %s \t\t %s \t %s \t \n", Integer.parseInt(strArray[4]), strArray[0],
							strArray[1], strArray[2], strArray[3]);
				} else {
					System.out.printf("%d \t %s \t %s \t %s \t %s \t \n", Integer.parseInt(strArray[4]), strArray[0],
							strArray[1], strArray[2], strArray[3]);
				}
			}

			CardVO cardVO = new CardVO();
			CardDAO dao = new CardDAO();
			cardVO.setTeacher_id("T00001");
			cardVO.setWord(word);
			cardVO.setPhonetic_symbol(phonetic_symbol);
			cardVO.setNative_explain(native_explain);
			cardVO.setExample(example);
			dao.insert(cardVO);

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void generateInsertCard2000(String path) {
		String filename = path;
		int audio_key = 0;
//		String word = "";
//		String pron = "";
//		String m1 = "";
//		String m2 = "";
		String s = "";
		Scanner scanner = null;
		String teacher_id = "";

		try {
			scanner = new Scanner(new File(filename), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int index = 1;
		for (int i = 1; i <= 2000; i++) {
			s = scanner.nextLine();
			String strArray[] = s.split(",");
			audio_key = Integer.parseInt(strArray[4]);
			switch (i) {
			case 1:
				teacher_id = "T00001";
				break;
			case 201:
				teacher_id = "T00002";
				break;
			case 401:
				teacher_id = "T00003";
				break;
			case 601:
				teacher_id = "T00004";
				break;
			case 801:
				teacher_id = "T00005";
				break;
			case 1001:
				teacher_id = "T00006";
				break;
			case 1201:
				teacher_id = "T00007";
				break;
			case 1401:
				teacher_id = "T00008";
				break;
			case 1601:
				teacher_id = "T00009";
				break;
			case 1801:
				teacher_id = "T00010";
				break;
			}
			String word = strArray[0];
			String pron = strArray[1];
			String native_explain = strArray[2];
			String example = strArray[3];

			System.out
					.println("Insert into CARD (CARD_ID,TEACHER_ID,WORD,PHONETIC_SYMBOL,NATIVE_EXPLAIN,EXAMPLE) values "
							+ "('C'||LPAD(to_char(card_seq.NEXTVAL), 5, '0'),'" + teacher_id + "', '" + word + "','"
							+ pron + "','" + native_explain + "','" + example + "');"

					);

		}

	}

}
