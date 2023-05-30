package Information_Security;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
class DES_view extends JFrame{
	JTextField[] key = new JTextField[3];
	JButton create_key_bt;
	JTextField[] encryption = new JTextField[2];
	JTextField[] decryption = new JTextField[2];
	JButton[] des_bt = new JButton[3];
	
	public DES_view() {
		setTitle("단순 DES");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600,300);
		
		Container c = getContentPane();
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER,1,5);
		//BorderLayout border = new BorderLayout(20,20);
		c.setLayout(flow);
		
		c.add(KeyPanel());
		c.add(DES_Panel());
		c.add(Button_Panel());
		
		setResizable(false);
		setVisible(true);
	}
	private JPanel KeyPanel() {
		JPanel keyPanel = new JPanel();
		keyPanel.setBorder(new TitledBorder("키 생성"));
		for(int i=0; i < key.length; i++) {
			if(i==0) {
				JLabel in_key = new JLabel("KEY(0~1023)");
				key[i] = new JTextField(6);
				create_key_bt = new JButton("키 생성");
				create_key_bt.setPreferredSize(new Dimension(75,25));
				keyPanel.add(in_key);
				keyPanel.add(key[i]);
				keyPanel.add(create_key_bt);
			}else {
				JLabel in_key = new JLabel("K" + i);
				key[i] = new JTextField(9);
				key[i].setEnabled(false);
				keyPanel.add(in_key);
				keyPanel.add(key[i]);
			}
		}
		return keyPanel;
	}
	private JPanel DES_Panel() {
		JPanel des_panel = new JPanel();
		JPanel encryption_panel = new JPanel();
		encryption_panel.setBorder(new TitledBorder("암호화"));
		JPanel decryption_panel = new JPanel();
		decryption_panel.setBorder(new TitledBorder("복호화"));
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER,10,5);
		GridLayout grid = new GridLayout(0,1,5,5);
		des_panel.setLayout(flow);
		encryption_panel.setLayout(grid);
		decryption_panel.setLayout(grid);
		
		JLabel[] text_label = new JLabel[4];
		String[] text = new String[] {"평문을 입력하세요", "암호문", "암호문을 입력하세요", "복호화된 평문"};
		for(int i = 0; i < text.length; i++) {
			text_label[i] = new JLabel(text[i]);
		}
		
		for(int i = 0; i < 2; i++) {
			encryption[i] = new JTextField(21);
			decryption[i] = new JTextField(21);
		}
		encryption_panel.add(text_label[0]);
		encryption_panel.add(encryption[0]);
		encryption_panel.add(text_label[1]);
		encryption_panel.add(encryption[1]);
		
		decryption_panel.add(text_label[2]);
		decryption_panel.add(decryption[0]);
		decryption_panel.add(text_label[3]);
		decryption_panel.add(decryption[1]);
		
		des_panel.add(encryption_panel);
		des_panel.add(decryption_panel);
		
		return des_panel;
	}
	private JPanel Button_Panel() {
		JPanel bt_Panel = new JPanel();
		JLabel uni_num_name = new JLabel("컴퓨터공학과 18110078 우병섭");
		String[] des_bt_text = new String[] {"암호화", "복호화","초기화"}; 
		
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER,10,5);
		bt_Panel.setLayout(flow);
		uni_num_name.setFont(new Font("SanSerif",Font.BOLD, 15));
		bt_Panel.add(uni_num_name);
		for(int i = 0; i < des_bt.length; i++) {
			des_bt[i] = new JButton(des_bt_text[i]);
			bt_Panel.add(des_bt[i]);
		}
		
		return bt_Panel;
	}
}
class S_DES_Algorithm{// S-DES 구조
	public int create_key(int key) {
		int result = 0;
		String binaryS = String.format("%08d", Integer.parseInt(Integer.toBinaryString(key)));
		 
		return result;
	}
}
public class DES  extends JFrame {
	DES_view show = new DES_view();
	DES_handler button = new DES_handler();
	public DES() {
		show.create_key_bt.addActionListener(button);
		for(int i=0; i < show.des_bt.length; i++) {
			show.des_bt[i].addActionListener(button);
		}
	}
	class DES_handler implements ActionListener{ //버튼 컨트롤
		String key1 = null, key2 = null;
		String Decryption = null, Encryption = null;
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton)e.getSource();
			if(bt.getText().equals("키 생성")) {
				int key = Integer.parseInt(show.key[0].getText());
				String[] keys = new String[2]; 
				System.out.println(key);
				keys = DES_create_key(key);
				show.key[1].setText(keys[0]);
				key1 = keys[0];
				show.key[2].setText(keys[1]);
				key2 = keys[1];
			}else if(bt.getText().equals("암호화")) {
				Decryption = show.encryption[0].getText();
				Encryption = data_to_DES(Decryption, key1, key2);
				show.encryption[1].setText(Encryption);
				show.decryption[0].setText(Encryption);
					
			}else if(bt.getText().equals("복호화")) {
				Encryption = show.decryption[0].getText();
				Decryption = data_to_DES(Encryption, key2, key1);
				show.decryption[1].setText(Decryption);
			}else if(bt.getText().equals("초기화")) {
				show.key[0].setText("");
				show.key[1].setText("");
				show.key[2].setText("");
				show.encryption[0].setText("");
				show.encryption[1].setText("");
				show.decryption[0].setText("");
				show.decryption[1].setText("");
			}
			
		}
		
	}
	//키 생성
	public String[] DES_create_key(int key) {
		String[] keys = new String[2];
		
		String binaryS = String.format("%010d", Integer.parseInt(Integer.toBinaryString(key)));
		String[] K_arr = binaryS.split("",10);
		int[] p10 = {2,4,1,6,3,9,0,8,7,5};
		int[] p8 = {5,2,6,3,7,4,9,8};
		String[] p10_K_arr = new String[10];
		StringBuilder  k_str_Build = new StringBuilder();
			
		for(int i = 0; i < p10.length; i++ ) {
			p10_K_arr[i] = K_arr[p10[i]];
		}
		//key1 생성
		p10_K_arr = left_shift(p10_K_arr);
		for(int i = 0; i < p8.length; i++ ) {
			k_str_Build.append(p10_K_arr[p8[i]]);
		}
		keys[0] = k_str_Build.toString();
		//key2 생성
		p10_K_arr = left_shift(p10_K_arr);
		p10_K_arr = left_shift(p10_K_arr);
			
		k_str_Build.setLength(0);
		for(int i = 0; i < p8.length; i++ ) {
			k_str_Build.append(p10_K_arr[p8[i]]);
		}
		keys[1] = k_str_Build.toString();
			
		return keys;
	}
	//shift 연산
	public String[] left_shift(String[] P10) {
		String[] data = P10;
		String data1 = data[0];
		String data2 = data[data.length/2];
		for(int i=0; i < data.length/2; i++) {
			if(i == data.length/2-1) {
				data[i] = data1;
				data[i+data.length/2] = data2;
			}else if(i < data.length/2) {
				data[i] = data[i+1];
				data[i+data.length/2] = data[i+data.length/2+1];
			}
		}
		return data;
	}
	public  String data_to_DES(String str_data, String subkey1, String subkey2) {//문자열 받아서 암호화
		int[] ip_start = {1,5,2,0,3,7,4,6};
		int[] ip_last = {3,0,2,4,6,1,7,5};
		
		String data_des = str_data;
		String[] data_8bit = new String[8];
		String[][] data_8bit_LR = new String[2][4];
		StringBuilder des_result = new StringBuilder();
		
		int char_to_int = 0;
		
		for(int i=0; i < data_des.length(); i++) {
			System.out.println("\n입력된 데이터" + data_des.charAt(i));
			char_to_int = (int)data_des.charAt(i);
			System.out.println("data_DES");
			data_8bit = str_char_binary(char_to_int);
			data_8bit_LR = des_ip_funtion(data_8bit,ip_start);

			data_8bit_LR = des_f_funtion(data_8bit_LR[0],data_8bit_LR[1],subkey1);
			data_8bit_LR = des_f_funtion(data_8bit_LR[1],data_8bit_LR[0],subkey2);
			for(int r=0; r < data_8bit_LR.length; r++) {
				for(int k=0; k < data_8bit_LR[r].length; k++) {
					data_8bit[k+((data_8bit.length/2)*r)] = data_8bit_LR[r][k];
				}
			}
			data_8bit_LR = des_ip_funtion(data_8bit, ip_last);

			System.out.println("암호화된 데이터 2진수");
			for(int r=0; r < data_8bit_LR.length; r++) {
				for(int k=0; k < data_8bit_LR[r].length; k++) {
					data_8bit[k+((data_8bit.length/2)*r)] = data_8bit_LR[r][k];
					System.out.print(data_8bit[k+((data_8bit.length/2)*r)]);
				}
			}
			System.out.println();
			des_result.append(binary_char_str(data_8bit));
			System.out.println("암호화된 데이터 문자"+ des_result);
		}
		
		return des_result.toString();
	}
	public  String[] str_char_binary(int int_data) {//한 문자씩 받아서 2진 비트로 변환
		String data_8bit = String.format("%08d", Integer.parseInt(Integer.toBinaryString(int_data)));
		String[] result = data_8bit.split("",8);
		System.out.println("평문 10진수"+int_data);
		return result;
	}
	
	public  String binary_char_str(String[] binary_data) {
		StringBuilder marge_binary = new StringBuilder();
		String result = new String();
		int binary_to_10 = 0;
		
		for(int i=0; i < binary_data.length; i++) {
			marge_binary.append(binary_data[i]);
		}
		binary_to_10 = Integer.parseInt(marge_binary.toString(), 2);
		System.out.println("평문 10진수"+binary_to_10);
		result = String.valueOf((char)binary_to_10);
		
		return result;
	}
	public  String[][] des_ip_funtion(String[] binary_data, int[] ip_arr){
		String[] data = binary_data;
		int[] ip_position = ip_arr;
		String[][] ip_result = new String[2][4];
		
		for(int i=0; i < ip_result.length; i++) {
			for(int k=0; k < ip_result[i].length; k++) {
				ip_result[i][k] = data[ip_position[k+(i * (ip_position.length/2))]];
			}
		}
		
		return ip_result;
	}
	public  String[][] des_f_funtion(String[] ip_data_L, String[] ip_data_R, String subkey){
		String[][] data = new String[2][4];
		String[] xor_L = ip_data_L;
		String[] xor_R = des_4bit(ip_data_R, subkey);
		
		//xor_L xor xor_R 해야함 그값을 data[0][]에 저장
		for(int i=0; i <xor_L.length; i++) {
			data[0][i] = XOR(xor_L[i], xor_R[i]);
		}
		
		data[1] = ip_data_R;
		
		return data;
	}
	public  String[] des_4bit(String[] data_4bit, String subkey) {
		StringBuilder  S_data = new StringBuilder();		
		String[] data_p4 = new String[4];
		String[] result_out = new String[4];
		int[] EP = {3,0,1,2, 1,2,3,0};
		int[][] s0 = {{1,0,3,2},
					{3,2,1,0},
					{0,2,1,3},
					{3,1,3,2}};
		int[][] s1 = {{0,1,2,3},
					{2,0,1,3},
					{3,0,1,0},
					{2,1,0,3}};
		int[] p4 = {1,3,2,0};
		
		String[] data = data_4bit;
		String[] subk_arr = subkey.split("",8);
		String[] ep_R = new String[8];
		String[] xor_d = new String[8];
		int col=0,row=0;
		
		for(int i=0; i < EP.length; i++) {
			ep_R[i] = data[EP[i]];
		}
		for(int i=0; i < xor_d.length; i++) {
			xor_d[i] = XOR(ep_R[i], subk_arr[i]);
		}
		row = Integer.parseInt(xor_d[0]+xor_d[3], 2);
		col = Integer.parseInt(xor_d[1]+xor_d[2], 2);
		S_data.append(String.format("%02d", Integer.parseInt(Integer.toBinaryString(s0[row][col]))));
		
		row = Integer.parseInt(xor_d[4]+xor_d[7], 2);
		col = Integer.parseInt(xor_d[5]+xor_d[6], 2);
		S_data.append(String.format("%02d", Integer.parseInt(Integer.toBinaryString(s1[row][col]))));
		
		data_p4 = S_data.toString().split("",4);
		
		for(int i=0; i < result_out.length; i++) {
			result_out[i] = data_p4[p4[i]];
		}
		return result_out;
	}
	public  String XOR(String key, String data) {
		String result = new String();
		if(key.equals(data)) {
			result ="0";
		}else if(!key.equals(data)){
			result = "1";
		}
		return result;
	}
	public static void main(String[] args) {
		new DES();
	}

}
