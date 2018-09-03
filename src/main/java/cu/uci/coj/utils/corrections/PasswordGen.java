package cu.uci.coj.utils.corrections;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

public class PasswordGen {

	public static void main(String[] args) {
		MessageDigestPasswordEncoder md5 = new MessageDigestPasswordEncoder("MD5");
		System.out.println(md5.encode("password"));

	}

}
