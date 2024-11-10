# small_DES
- small DES 암호화 기법 실제 구현 코드

**사용**
- java

![실행사진](https://github.com/user-attachments/assets/e66b4a1e-2827-4c38-8a4f-54c45ceb07b9)

## code
key 생성 함수
``` java
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
```
key 생성을 위한 shift 연산 함수
```java
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
```

S-DES 암호화
```java
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
```
