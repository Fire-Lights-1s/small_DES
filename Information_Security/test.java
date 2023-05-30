package Information_Security;

public class test {
	public static String[] left_shift(String[] P10) {
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
	public static void main(String[] args) {
		
		
		int a = 15;
        String b="";
        String b1="";
        b = Integer.toString(a);
        b1 = String.format("%08d", Integer.parseInt(Integer.toBinaryString(a)));
        System.out.println("a = "+a);
        System.out.println("b = "+b);
        System.out.println("b1 = "+b1);
        
        System.out.println("Test1 = "+Integer.toBinaryString(a));
        System.out.println("Test2 = "+Integer.parseInt(Integer.toBinaryString(a)));
        System.out.println("Test3 = "+Integer.parseInt("1111"));
        System.out.println("Test4 = "+String.format("%08d", 1111));
		
        int key = 642;
		String binaryS = String.format("%010d", Integer.parseInt(Integer.toBinaryString(key)));
		System.out.println(binaryS);
		
		String[] K_arr = binaryS.split("",10);
		int[] p10 = {2,4,1,6,3,9,0,8,7,5};
		int[] p8 = {5,2,6,3,7,4,9,8};
		String[] p10_K_arr = new String[10];
		StringBuilder  k_str_Build = new StringBuilder();
		
		
		
		int[] EP = {3,0,1,2, 1,2,3,0};
		
		for(int i = 0; i < p10.length; i++ ) {
			p10_K_arr[i] = K_arr[p10[i]];
			
			System.out.print(p10_K_arr[i]);
			
		}
		
		System.out.println();
		//k1 만들기
		p10_K_arr = left_shift(p10_K_arr);
		
		for(int i = 0; i < p8.length; i++ ) {
			k_str_Build.append(p10_K_arr[p8[i]]);
		}
		String key1 = k_str_Build.toString();
		System.out.println();
		//k2 만들기
		p10_K_arr = left_shift(p10_K_arr);
		p10_K_arr = left_shift(p10_K_arr);
		
		
		k_str_Build.setLength(0);
		for(int i = 0; i < p8.length; i++ ) {
			k_str_Build.append(p10_K_arr[p8[i]]);
		}
		String key2 = k_str_Build.toString();
		System.out.println();
		
		System.out.println("key1 : "+key1);
		System.out.println("key2 : "+key2);
		
		//데이터 입력 암호화 과정 
		int[] ip_start = {1,5,2,0,3,7,4,6};
		int[] ip_last = {3,0,2,4,6,1,7,5};
		
		String test_2 = "1";
		int test_int = (int)test_2.charAt(0);
		
		System.out.println(test_int);
		
		String data = "A";
		int data_int = (int)data.charAt(0);
		String data_8bit = String.format("%08d", Integer.parseInt(Integer.toBinaryString(data_int)));
		String[] RC_arr = data_8bit.split("",8);
		String[] data_arr = new String[8];
		String[][] data_arr2 = new String[2][4];
		
		System.out.println(data_int);
		System.out.println(data_8bit);
		
		data_arr2 = des_ip_funtion(RC_arr, ip_start);
		
		String[][] f_data = new String[2][4];
		String[][] f_data_test = new String[2][4];
		String[] data_L = new String[4];
		String[] data_R = new String[4];
		
//		for(int i=0; i < data_arr.length/2; i++) {
//			data_L[i] = data_arr[i];
//			data_R[i] = data_arr[i+data_arr.length/2];
//		}
		
		f_data=des_f_funtion(data_arr2[0], data_arr2[1], key1);
		System.out.print("f_data k1: ");
		for(int i=0; i < f_data.length; i++) {
			for(int k=0; k < f_data[i].length; k++) {
				System.out.print(f_data[i][k]);
			}
		}
		System.out.println();
		f_data=des_f_funtion(data_arr2[1], data_arr2[0], key2);
		System.out.print("f_data k2: ");
		for(int i=0; i < f_data.length; i++) {
			for(int k=0; k < f_data[i].length; k++) {
				System.out.print(f_data[i][k]);
			}
		}
		System.out.println();
		//복호화 테스트
		f_data_test = des_f_funtion(f_data[0], f_data[1], key2);
		System.out.print("f_data_test k2: ");
		for(int i=0; i < f_data_test.length; i++) {
			for(int k=0; k < f_data_test[i].length; k++) {
				System.out.print(f_data_test[i][k]);
			}
		}
		System.out.println();
		f_data_test = des_f_funtion(f_data_test[1], f_data_test[0], key1);
		System.out.print("f_data_test k1: ");
		for(int i=0; i < f_data_test.length; i++) {
			for(int k=0; k < f_data_test[i].length; k++) {
				System.out.print(f_data_test[i][k]);
			}
		}
		System.out.println();
		
		for(int r=0; r < data_arr2.length; r++) {
			for(int k=0; k < data_arr2[r].length; k++) {
				data_arr[k+(r * (data_arr.length/2))] = f_data_test[r][k];
			}
		}
		f_data_test = des_ip_funtion(data_arr, ip_last);
		System.out.print("\nf_data_test:");
		for(int i=0; i < f_data_test.length; i++) {
			for(int k=0; k < f_data_test[i].length; k++) {
				System.out.print(f_data_test[i][k]);
			}
		}
		
		
		
		System.out.println("\n");
		System.out.println("암호화 복호화 테스트");
		String text_data = "aaa";
		System.out.println("입력 데이터 : "+ text_data);
		String text_des = data_to_DES(text_data,key1,key2);
		System.out.println(text_des);
		String des_text = data_to_DES(text_des,key2,key1);
		System.out.println("복호화 데이터 : "+des_text);
	}
	public static void f_print(String[][] data , String key) {
		System.out.print("f_data_test " + key +" : ");
		for(int i=0; i < data.length; i++) {
			for(int k=0; k < data[i].length; k++) {
				System.out.print(data[i][k]);
			}
		}
		System.out.println();
	}
	public static String DES_to_data(String str_data, String subkey1, String subkey2) {//문자열 받아서 암호화
		int[] ip_start = {1,5,2,0,3,7,4,6};
		int[] ip_last = {3,0,2,4,6,1,7,5};
		int[] ip_start2 = {1,5,2,0,3,7,4,6};
		int[] ip_last2 = {3,0,2,4,6,1,7,5};
		
		String data_des = str_data;
		String[] data_8bit = new String[8];
		String[][] data_8bit_LR = new String[2][4];
		StringBuilder des_result = new StringBuilder();
		
		int char_to_int = 0;
		
		for(int i=0; i < data_des.length(); i++) {
			
			char_to_int = (int)data_des.charAt(i);
			System.out.println("DES_data");
			data_8bit = str_char_binary(char_to_int);
			data_8bit_LR = des_ip_funtion(data_8bit,ip_start);
			f_print(data_8bit_LR, "ipS");
			data_8bit_LR = des_f_funtion(data_8bit_LR[0],data_8bit_LR[1],subkey2);
			f_print(data_8bit_LR, "k2");
			data_8bit_LR = des_f_funtion(data_8bit_LR[1],data_8bit_LR[0],subkey1);
			f_print(data_8bit_LR, "k1");
			
			for(int r=0; r < data_8bit_LR.length; r++) {
				for(int k=0; k < data_8bit_LR[r].length; k++) {
					data_8bit[k+((data_8bit.length/2)*r)] = data_8bit_LR[r][k];
				}
			}
			data_8bit_LR = des_ip_funtion(data_8bit, ip_last);
			f_print(data_8bit_LR, "ipL");
			for(int r=0; r < data_8bit_LR.length; r++) {
				for(int k=0; k < data_8bit_LR[r].length; k++) {
					data_8bit[k+((data_8bit.length/2)*r)] = data_8bit_LR[r][k];
				}
			}
			des_result.append(binary_char_str(data_8bit));
		}
		
		return des_result.toString();
	}
	public static String data_to_DES(String str_data, String subkey1, String subkey2) {//문자열 받아서 암호화
		int[] ip_start = {1,5,2,0,3,7,4,6};
		int[] ip_last = {3,0,2,4,6,1,7,5};
		
		String data_des = str_data;
		String[] data_8bit = new String[8];
		String[][] data_8bit_LR = new String[2][4];
		StringBuilder des_result = new StringBuilder();
		
		int char_to_int = 0;
		
		for(int i=0; i < data_des.length(); i++) {
			
			char_to_int = (int)data_des.charAt(i);
			System.out.println("data_DES");
			data_8bit = str_char_binary(char_to_int);
			data_8bit_LR = des_ip_funtion(data_8bit,ip_start);
			f_print(data_8bit_LR, "ipS");

			data_8bit_LR = des_f_funtion(data_8bit_LR[0],data_8bit_LR[1],subkey1);
			f_print(data_8bit_LR, "k1");
			data_8bit_LR = des_f_funtion(data_8bit_LR[1],data_8bit_LR[0],subkey2);
			f_print(data_8bit_LR, "k2");
			for(int r=0; r < data_8bit_LR.length; r++) {
				for(int k=0; k < data_8bit_LR[r].length; k++) {
					data_8bit[k+((data_8bit.length/2)*r)] = data_8bit_LR[r][k];
				}
			}
			data_8bit_LR = des_ip_funtion(data_8bit, ip_last);
			f_print(data_8bit_LR, "ipL");

			for(int r=0; r < data_8bit_LR.length; r++) {
				for(int k=0; k < data_8bit_LR[r].length; k++) {
					data_8bit[k+((data_8bit.length/2)*r)] = data_8bit_LR[r][k];
				}
			}
			des_result.append(binary_char_str(data_8bit));
		}
		
		return des_result.toString();
	}
	public static String[] str_char_binary(int int_data) {//한 문자씩 받아서 2진 비트로 변환
		String data_8bit = String.format("%08d", Integer.parseInt(Integer.toBinaryString(int_data)));
		String[] result = data_8bit.split("",8);
		System.out.println("평문 10진수 "+int_data);
		return result;
	}
	
	public static String binary_char_str(String[] binary_data) {
		StringBuilder marge_binary = new StringBuilder();
		String result = new String();
		int binary_to_10 = 0;
		
		for(int i=0; i < binary_data.length; i++) {
			marge_binary.append(binary_data[i]);
		}
		binary_to_10 = Integer.parseInt(marge_binary.toString(), 2);
		System.out.println("평문 10진수 "+binary_to_10);
		result = String.valueOf((char)binary_to_10);
		
		return result;
	}
	public static String[][] des_ip_funtion(String[] binary_data, int[] ip_arr){
		String[] data = binary_data;
		int[] ip_position = ip_arr;
//		String[] ip_data_arr = new String[8];
		String[][] ip_result = new String[2][4];
		
		for(int i=0; i < ip_result.length; i++) {
			for(int k=0; k < ip_result[i].length; k++) {
				ip_result[i][k] = data[ip_position[k+(i * (ip_position.length/2))]];
			}
		}
//		System.out.println("IP 함수");
//		for(int i=0; i < ip_position.length; i++) {
//			ip_data_arr[i] = data[ip_position[i]];
//			System.out.print(ip_data_arr[i]);
//		}
//		System.out.println();
		
		return ip_result;
	}
	public static String[][] des_f_funtion(String[] ip_data_L, String[] ip_data_R, String subkey){
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
	public static String[] des_4bit(String[] data_4bit, String subkey) {
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
		
		//System.out.println("EP 확장 ");
		for(int i=0; i < EP.length; i++) {
			ep_R[i] = data[EP[i]];
			//System.out.print(ep_R[i]);
		}
		//System.out.println("xor_d : ");
		for(int i=0; i < xor_d.length; i++) {
			xor_d[i] = XOR(ep_R[i], subk_arr[i]);
			//System.out.print(xor_d[i]);
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
	public static String XOR(String key, String data) {
		String result = new String();
		if(key.equals(data)) {
			result ="0";
		}else if(!key.equals(data)){
			result = "1";
		}
		
		return result;
	}

}
