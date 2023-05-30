package Information_Security;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.stream.Collectors;

public class test_utf_8_binary {
	public test_utf_8_binary() {
		String d = "�ȳ� ��9"; // �ڹٴ� ���� ���ڿ��� ��� �����ڵ� ó���Ѵ�
		  
		// �����ڵ� ���ڿ��� UTF-8 ĳ���� ����Ʈ�迭�� ��ȯ�Ͽ� ��ȯ
		byte[] utf8 = null;
		try {
			utf8 = d.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �����ڵ� ���ڿ��� EUC-KR ĳ���� ����Ʈ�迭�� ��ȯ�Ͽ� ��ȯ
		byte[] euckr = null;
		try {
			euckr = d.getBytes("EUC-KR");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		// �翬�� �ٸ� ����Ʈ �迭�̹Ƿ� ����� �ٸ���.
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

        System.out.println("���ڵ�=>" + encodeVal);
        System.out.println("���ڵ�=>" + decodeVal);
        System.out.println((char)Integer.parseInt("11111011", 2));
	}
	
	public static void main(String[] args) {
		new test_utf_8_binary();

	}

}
