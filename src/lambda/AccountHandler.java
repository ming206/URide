package lambda;

import com.amazonaws.services.lambda.runtime.Context;

import model.Account;

import java.util.*;

public class AccountHandler {

	public static void main(String[] args) {
		Map<String, Object> test = new HashMap<String, Object>();
		test.put("username", "diaosi");
		test.put("password", "rong");
        test.put("email", "snailin@hotmail.com");
        test.put("firstname", "qinrong");
        test.put("lastname", "liu");
        
		System.out.println(new AccountHandler().signup(test, null));

		System.out.println(new AccountHandler().login(test, null));
	}

	/**
	 * Login lambda
     *
	 * @param test username and password as key in a Map<String, String>
	 * @param context
	 * @return a Map stores user info if username and password are matched, or empty map
	 */
	public Map<String, Object> login(Map<String, Object> test, Context context) {
		if (test == null) {
			throw new IllegalArgumentException();
		}
        String username = (String)test.get("username");
		String password = (String)test.get("password");
		if (username == null || password == null) {
			return new HashMap<String, Object>();
		}
		System.out.println("(login) " + username + ": " + password);
		Map<String, Object> res = new HashMap<String, Object>();
		Map<String, Object> loginInfo = new Account(username, password).isLogin();
		if (!loginInfo.isEmpty()) {
			res.put("account", new Account(username, password).isLogin());
		}
        return res;
	}

	/**
	 * Signup lambda, register user account to the system if username and email have not been used.
	 *
	 * @param input username, password, email, lastname, firstname as key in a Map<String, String>
	 * @param context
	 * @return a Map stores user info if username and email are unique (never be used by others), or empty map
	 */
	public Map<String, Object> signup(Map<String, Object> input, Context context) {
		if (input == null) {
			throw new IllegalArgumentException();
		}
		String username = (String)input.get("username");
		String password = (String)input.get("password");
		String email = (String)input.get("email");
		String lastname = (String)input.get("lastname");
		String firstname = (String)input.get("firstname");
		if (username == null || password == null || email == null || lastname == null || firstname == null) {
			return new HashMap<String, Object>();
		}
		System.out.println("(signup) " + username + ": " + password);
		return Account.signup(username, password, email, lastname, firstname);
	}
}
