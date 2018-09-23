package cu.uci.coj.utils.corrections;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGen {

	public static void main(String[] args) {
		BCryptPasswordEncoder md5 = new BCryptPasswordEncoder();
		System.out.println(md5.encode("admin"));

	}

}
