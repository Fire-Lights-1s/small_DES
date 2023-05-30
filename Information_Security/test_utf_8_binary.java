package Information_Security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.stream.Collectors;

public class test_utf_8_binary {
	public test_utf_8_binary() {
		String d = "안녕 親9"; // 자바는 내부 문자열을 모두 유니코드 처리한다
		  
		// 유니코드 문자열을 UTF-8 캐릭터 바이트배열로 변환하여 반환
		byte[] utf8 = null;
		try {
			utf8 = d.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 유니코드 문자열을 EUC-KR 캐릭터 바이트배열로 변환하여 반환
		byte[] euckr = null;
		try {
			euckr = d.getBytes("EUC-KR");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		// 당연히 다른 바이트 배열이므로 사이즈가 다르다.
		System.out.println("byte length > " + utf8.length); // byte length > 11
		System.out.println("byte length > " + euckr.length); // byte length > 8
		System.out.println("byte  > " + utf8); // byte length > 11
		System.out.println("byte  > " + euckr); // byte length > 8
		
		String val = "a";
        String encodeVal = "";
        String decodeVal = "";

        try {
			encodeVal = URLEncoder.encode(val, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			decodeVal = URLDecoder.decode(encodeVal, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("인코딩=>" + encodeVal);
        System.out.println("디코딩=>" + decodeVal);
        System.out.println((char)Integer.parseInt("11111011", 2));
	}
	
	public static void main(String[] args) {
		new test_utf_8_binary();

	}

}
