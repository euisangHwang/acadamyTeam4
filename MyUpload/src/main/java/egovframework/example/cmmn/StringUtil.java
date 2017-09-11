package egovframework.example.cmmn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
	private static Log log = LogFactory.getLog(StringUtil.class);

	public StringUtil() {
	}

	@SuppressWarnings("unused")
	public static void simpleTokenizer(String s, String delimiter) {
		int i = 0;
		int j = s.indexOf(delimiter); // First substring
		String sub = null;
		while (j >= 0) {
			sub = s.substring(i, j);
			i = j + 1;
			j = s.indexOf(delimiter, i); // Rest of substrings
		}
		sub = s.substring(i); // Last substring
	}

	public static boolean isValidEmail(String email) {
		Pattern p = Pattern.compile("^[\\w\\-]+(\\.[\\w\\-_]+)*@[\\w\\-]+(\\.[\\w\\-]+)*(\\.[a-zA-Z]{2,3})$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * <p>
	 * 입력한 데이터(바이트 배열)을 SHA1 알고리즘으로 처리하여 해쉬값을 도출한다.
	 * </p>
	 * 
	 * <pre>
	 * getHash([0x68, 0x61, 0x6e]) = [0x4f, 0xf6, 0x15, 0x25, 0x34, 0x69, 0x98, 0x99, 0x32, 0x53, 0x2e, 0x92, 0x60, 0x06, 0xae, 0x5c, 0x99, 0x5e, 0x5d, 0xd6]
	 * </pre>
	 * 
	 * @param input
	 *            입력 데이터(<code>null</code>이면 안된다.)
	 * @return 해쉬값
	 */
	public static byte[] getHash(byte[] input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			return md.digest(input);
		} catch (NoSuchAlgorithmException e) {
			// 일어날 경우가 없다고 보지만 만약을 위해 Exception 발생
			throw new RuntimeException("SHA1" + " Algorithm Not Found", e);
		}
	}

	public static int byteArrayToInt(byte[] bytes) {
		final int size = Integer.SIZE / 8;
		ByteBuffer buff = ByteBuffer.allocate(size);
		final byte[] newBytes = new byte[size];
		for (int i = 0; i < size; i++) {
			if (i + bytes.length < size) {
				newBytes[i] = (byte) 0x00;
			} else {
				newBytes[i] = bytes[i + bytes.length - size];
			}
		}
		buff = ByteBuffer.wrap(newBytes);
		buff.order(ByteOrder.BIG_ENDIAN);

		return buff.getInt();
	}

	public static String toHexString(byte[] rawBytes) {
		StringBuilder sb = new StringBuilder(rawBytes.length * 2);
		String s;
		for (int i = 0; i < rawBytes.length; i++) {
			s = Integer.toHexString(rawBytes[i] & 0xFF).toUpperCase();
			if (s.length() == 1) { // leading zero
				sb.append('0');
			}
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * Encode a string using algorithm specified in web.xml and return the resulting encrypted password. If exception, the plain credentials string is returned
	 * 
	 * @param password
	 *            Password or other credentials to use in authenticating this username
	 * @param algorithm
	 *            Algorithm used to do the digest
	 * @return encypted password based on the algorithm.
	 */
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if (((int) encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString((int) encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();
	}

	/**
	 * Encode a string using Base64 encoding. Used when storing passwords as cookies. This is weak encoding in that anyone can use the decodeString routine to reverse the encoding.
	 * 
	 * @param str
	 *            String to be encoded
	 * @return String encoding result
	 */
	@SuppressWarnings("restriction")
	public static String encodeString(String str) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		return new String(encoder.encodeBuffer(str.getBytes())).trim();
	}

	/**
	 * Decode a string using Base64 encoding.
	 * 
	 * @param str
	 *            String to be decoded
	 * @return String decoding String
	 */
	@SuppressWarnings("restriction")
	public static String decodeString(String str) {
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(str));
		} catch (IOException io) {
			throw new RuntimeException(io.getMessage(), io.getCause());
		}
	}

	/**
	 * convert first letter to a big letter or a small letter.<br>
	 * 
	 * <pre>
	 * StringUtil.trim('Password') = 'password'
	 * StringUtil.trim('password') = 'Password'
	 * </pre>
	 * 
	 * @param str
	 *            String to be swapped
	 * @return String converting result
	 */
	public static String swapFirstLetterCase(String str) {
		StringBuffer sbuf = new StringBuffer(str);
		sbuf.deleteCharAt(0);
		if (Character.isLowerCase(str.substring(0, 1).toCharArray()[0])) {
			sbuf.insert(0, str.substring(0, 1).toUpperCase());
		} else {
			sbuf.insert(0, str.substring(0, 1).toLowerCase());
		}
		return sbuf.toString();
	}

	/**
	 * If original String has a specific String, remove specific Strings from original String.
	 * 
	 * <pre>
	 * StringUtil.trim('pass*word', '*') = 'password'
	 * </pre>
	 * 
	 * @param origString
	 *            original String
	 * @param trimString
	 *            String to be trimmed
	 * @return converting result
	 */
	public static String trim(String origString, String trimString) {
		int startPosit = origString.indexOf(trimString);
		if (startPosit != -1) {
			int endPosit = trimString.length() + startPosit;
			return origString.substring(0, startPosit) + origString.substring(endPosit);
		}
		return origString;
	}

	/**
	 * Break a string into specific tokens and return a String of last location.
	 * 
	 * <pre>
	 * StringUtil.getLastString('password*password*a*b*c', '*') = 'c'
	 * </pre>
	 * 
	 * @param origStr
	 *            original String
	 * @param strToken
	 *            specific tokens
	 * @return String of last location
	 */
	public static String getLastString(String origStr, String strToken) {
		StringTokenizer str = new StringTokenizer(origStr, strToken);
		String lastStr = "";
		while (str.hasMoreTokens()) {
			lastStr = str.nextToken();
		}
		return lastStr;
	}

	/**
	 * If original String has token, Break a string into specific tokens and change String Array. If not, return a String Array which has original String as it is.
	 * 
	 * <pre>
	 * StringUtil.getStringArray('passwordabcpassword', 'abc') 		= String[]{'password','password'}
	 * StringUtil.getStringArray('pasword*password', 'abc') 		= String[]{'pasword*password'}
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @param strToken
	 *            specific String token
	 * @return String[]
	 */
	public static String[] getStringArray(String str, String strToken) {
		if (str.indexOf(strToken) != -1) {
			StringTokenizer st = new StringTokenizer(str, strToken);
			String[] stringArray = new String[st.countTokens()];
			for (int i = 0; st.hasMoreTokens(); i++) {
				stringArray[i] = st.nextToken();
			}
			return stringArray;
		}
		return new String[] { str };
	}

	/**
	 * If string is null or empty string, return true. <br>
	 * If not, return false.
	 * 
	 * <pre>
	 * StringUtil.isEmpty('') 		= true
	 * StringUtil.isEmpty(null) 	= true
	 * StringUtil.isEmpty('abc') 	= false
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @return which empty string or not.
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	/**
	 * replace replaced string to specific string from original string. <br>
	 * 
	 * <pre>
	 * StringUtil.replace('work$id', '$', '.') 	= 'work.id'
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @param replacedStr
	 *            to be replaced String
	 * @param replaceStr
	 *            replace String
	 * @return converting result
	 */
	public static String replace(String str, String replacedStr, String replaceStr) {
		String newStr = "";
		/*
		 * if (str.indexOf(replacedStr) != -1) { String s1 = str.substring(0, str.indexOf(replacedStr)); String s2 = str.substring(str.indexOf(replacedStr) + 1); newStr = s1 + replaceStr + s2; }
		 */

		newStr = str.replace(replacedStr, replaceStr);

		return newStr;
	}

	/**
	 * It converts the string representation of a number to integer type (eg. '27' -> 27)
	 * 
	 * <pre>
	 * StringUtil.string2integer('14') 	= 14
	 * </pre>
	 * 
	 * @param str
	 *            string representation of a number
	 * @return integer integer type of string
	 */
	public static int string2integer(String str) {
		int ret = Integer.parseInt(str.trim());

		return ret;
	}

	/**
	 * It converts the string representation of a number to integer type (eg. '27' -> 27)
	 * 
	 * <pre>
	 * StringUtil.str2int('14') 	= 14
	 * </pre>
	 * 
	 * @param str
	 *            string representation of a number
	 * @return integer integer type of string
	 */
	public static int str2int(String str) {
		if (voidNull(str.trim()).equals(""))
			str = "0";

		int ret = Integer.parseInt(str);

		return ret;
	}

	/**
	 * It converts integer type to String ( 27 -> '27')
	 * 
	 * <pre>
	 * StringUtil.integer2string(14) 	= '14'
	 * </pre>
	 * 
	 * @param integer
	 *            integer type
	 * @return String string representation of a number
	 */
	public static String integer2string(int integer) {
		return ("" + integer);
	}

	/**
	 * It returns true if str matches the pattern string. It performs regular expression pattern matching.
	 * 
	 * <pre>
	 * StringUtil.isPatternMatching('abc-def', '*-*') 	= true
	 * StringUtil.isPatternMatching('abc', '*-*') 	= false
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @param pattern
	 *            pattern String
	 * @return boolean which matches the pattern string or not.
	 * @throws Exception
	 *             fail to check pattern matched
	 */
	public static boolean isPatternMatching(String str, String pattern) throws Exception {
		// if url has wild key, i.e. "*", convert it to ".*" so that we can
		// perform regex matching
		if (pattern.indexOf('*') >= 0) {
			pattern = pattern.replaceAll("\\*", ".*");
		}

		pattern = "^" + pattern + "$";

		return Pattern.matches(pattern, str);
	}

	/**
	 * It returns true if string contains a sequence of the same character.
	 * 
	 * <pre>
	 * StringUtil.containsMaxSequence('password', '2') 	= true
	 * StringUtil.containsMaxSequence('my000', '3') 	= true
	 * StringUtil.containsMaxSequence('abbbbc', '5')	= false
	 * </pre>
	 * 
	 * @param str
	 *            original String
	 * @param maxSeqNumber
	 *            a sequence of the same character
	 * @return which contains a sequence of the same character
	 */
	public static boolean containsMaxSequence(String str, String maxSeqNumber) {
		int occurence = 1;
		int max = string2integer(maxSeqNumber);
		if (str == null) {
			return false;
		}

		int sz = str.length();
		for (int i = 0; i < (sz - 1); i++) {
			if (str.charAt(i) == str.charAt(i + 1)) {
				occurence++;

				if (occurence == max)
					return true;
			} else {
				occurence = 1;
			}
		}
		return false;
	}

	/**
	 * 문자열 정해진 길이의 배열로 변환
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static String[] toArray(Object str, int maxLength) {
		String[] result = new String[maxLength];
		String source = String.valueOf(str);

		for (int i = 0; i < maxLength; i++) {
			if (source.length() > 0 && source.length() > i) {
				result[i] = new String(String.valueOf(source.charAt(i)));
			} else {
				result[i] = new String("");
			}
		}

		return result;
	}

	/**
	 * reverse 문자열 정해진 길이의 배열로 변환
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static String[] toArrayRev(Object str, int maxLength) {
		String[] result = new String[maxLength];
		String source = String.valueOf(str);
		int seq = source.length() - 1;

		for (int i = 0; i < maxLength; i++) {
			if (source.length() > 0 && source.length() > i) {
				result[i] = new String(String.valueOf(source.charAt(seq)));
			} else {
				result[i] = new String("");
			}
			seq--;
		}

		return result;
	}

	/**
	 * <p>
	 * Checks that the String contains certain characters.
	 * </p>
	 * <p>
	 * A <code>null</code> String will return <code>false</code>. A <code>null</code> invalid character array will return <code>false</code>. An empty String ("") always returns false.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsInvalidChars(null, *)       			= false
	 * StringUtil.containsInvalidChars(*, null)      			= false
	 * StringUtil.containsInvalidChars(&quot;&quot;, *)         = false
	 * StringUtil.containsInvalidChars(&quot;ab&quot;, '')      = false
	 * StringUtil.containsInvalidChars(&quot;abab&quot;, 'xyz') = false
	 * StringUtil.containsInvalidChars(&quot;ab1&quot;, 'xyz')  = false
	 * StringUtil.containsInvalidChars(&quot;xbz&quot;, 'xyz')  = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param invalidChars
	 *            an array of invalid chars, may be null
	 * @return false if it contains none of the invalid chars, or is null
	 */

	public static boolean containsInvalidChars(String str, char[] invalidChars) {
		if (str == null || invalidChars == null) {
			return false;
		}
		int strSize = str.length();
		int validSize = invalidChars.length;
		for (int i = 0; i < strSize; i++) {
			char ch = str.charAt(i);
			for (int j = 0; j < validSize; j++) {
				if (invalidChars[j] == ch) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Checks that the String contains certain characters.
	 * </p>
	 * <p>
	 * A <code>null</code> String will return <code>false</code>. A <code>null</code> invalid character array will return <code>false</code>. An empty String ("") always returns false.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.containsInvalidChars(null, *)       			= false
	 * StringUtil.containsInvalidChars(*, null)      			= false
	 * StringUtil.containsInvalidChars(&quot;&quot;, *)         = false
	 * StringUtil.containsInvalidChars(&quot;ab&quot;, '')      = false
	 * StringUtil.containsInvalidChars(&quot;abab&quot;, 'xyz') = false
	 * StringUtil.containsInvalidChars(&quot;ab1&quot;, 'xyz')  = false
	 * StringUtil.containsInvalidChars(&quot;xbz&quot;, 'xyz')  = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @param invalidChars
	 *            a String of invalid chars, may be null
	 * @return false if it contains none of the invalid chars, or is null
	 */
	public static boolean containsInvalidChars(String str, String invalidChars) {
		if (str == null || invalidChars == null) {
			return true;
		}
		return containsInvalidChars(str, invalidChars.toCharArray());
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode letters or digits.
	 * </p>
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAlphaNumeric(null)   			 = false
	 * StringUtil.isAlphaNumeric(&quot;&quot;)     = false
	 * StringUtil.isAlphaNumeric(&quot;  &quot;)   = false
	 * StringUtil.isAlphaNumeric(&quot;abc&quot;)  = true
	 * StringUtil.isAlphaNumeric(&quot;ab c&quot;) = false
	 * StringUtil.isAlphaNumeric(&quot;ab2c&quot;) = true
	 * StringUtil.isAlphaNumeric(&quot;ab-c&quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains letters or digits, and is non-null
	 */
	public static boolean isAlphaNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		if (sz == 0)
			return false;
		for (int i = 0; i < sz; i++) {
			if (!Character.isLetterOrDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode letters.
	 * </p>
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAlpha(null)   			= false
	 * StringUtil.isAlpha(&quot;&quot;)     = false
	 * StringUtil.isAlpha(&quot;  &quot;)   = false
	 * StringUtil.isAlpha(&quot;abc&quot;)  = true
	 * StringUtil.isAlpha(&quot;ab2c&quot;) = false
	 * StringUtil.isAlpha(&quot;ab-c&quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains letters, and is non-null
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		if (sz == 0)
			return false;
		for (int i = 0; i < sz; i++) {
			if (!Character.isLetter(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode digits. A decimal point is not a unicode digit and returns false.
	 * </p>
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String ("") will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNumeric(null)   		   = false
	 * StringUtil.isNumeric(&quot;&quot;)     = false
	 * StringUtil.isNumeric(&quot;  &quot;)   = false
	 * StringUtil.isNumeric(&quot;123&quot;)  = true
	 * StringUtil.isNumeric(&quot;12 3&quot;) = false
	 * StringUtil.isNumeric(&quot;ab2c&quot;) = false
	 * StringUtil.isNumeric(&quot;12-3&quot;) = false
	 * StringUtil.isNumeric(&quot;12.3&quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if only contains digits, and is non-null
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		if (sz == 0)
			return false;
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Reverses a String as per {@link StringBuffer#reverse()}.
	 * </p>
	 * <p>
	 * <A code>null</code> String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.reverse(null)  		   = null
	 * StringUtil.reverse(&quot;&quot;)    = &quot;&quot;
	 * StringUtil.reverse(&quot;bat&quot;) = &quot;tab&quot;
	 * </pre>
	 * 
	 * @param str
	 *            the String to reverse, may be null
	 * @return the reversed String, <code>null</code> if null String input
	 */

	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * Make a new String that filled original to a special char as cipers
	 * 
	 * @param originalStr
	 *            original String
	 * @param ch
	 *            a special char
	 * @param cipers
	 *            cipers
	 * @return filled String
	 */
	public static String fillString(String originalStr, char ch, int cipers) {
		int originalStrLength = originalStr.length();

		if (cipers < originalStrLength)
			return null;

		int difference = cipers - originalStrLength;

		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < difference; i++)
			strBuf.append(ch);

		strBuf.append(originalStr);
		return strBuf.toString();
	}

	/**
	 * Determine whether a (trimmed) string is empty
	 * 
	 * @param foo
	 *            The text to check.
	 * @return Whether empty.
	 */
	public static final boolean isEmptyTrimmed(String foo) {
		return (foo == null || foo.trim().length() == 0);
	}

	/**
	 * Return token list
	 * 
	 * @param lst
	 * @param separator
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getTokens(String lst, String separator) {
		List tokens = new ArrayList();

		if (lst != null) {
			StringTokenizer st = new StringTokenizer(lst, separator);
			while (st.hasMoreTokens()) {
				try {
					String en = st.nextToken().trim();
					tokens.add(en);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return tokens;
	}

	/**
	 * Return token list which is separated by ","
	 * 
	 * @param lst
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List getTokens(String lst) {
		return getTokens(lst, ",");
	}

	/**
	 * This method convert "string_util" to "stringUtil"
	 * 
	 * @param String
	 *            targetString
	 * 
	 * @param char posChar
	 * 
	 * @return String result
	 */
	public static String convertToCamelCase(String targetString, char posChar) {
		StringBuffer result = new StringBuffer();
		boolean nextUpper = false;
		String allLower = targetString.toLowerCase();

		for (int i = 0; i < allLower.length(); i++) {
			char currentChar = allLower.charAt(i);
			if (currentChar == posChar) {
				nextUpper = true;
			} else {
				if (nextUpper) {
					currentChar = Character.toUpperCase(currentChar);
					nextUpper = false;
				}
				result.append(currentChar);
			}
		}
		return result.toString();
	}

	/**
	 * Convert a string that may contain underscores to camel case.
	 * 
	 * @param underScore
	 *            Underscore name.
	 * @return Camel case representation of the underscore string.
	 */
	public static String convertToCamelCase(String underScore) {
		return convertToCamelCase(underScore, '_');
	}

	/**
	 * Convert a camel case string to underscore representation.
	 * 
	 * @param camelCase
	 *            Camel case name.
	 * @return Underscore representation of the camel case string.
	 */
	public static String convertToUnderScore(String camelCase) {
		String result = "";
		for (int i = 0; i < camelCase.length(); i++) {
			char currentChar = camelCase.charAt(i);
			// This is starting at 1 so the result does not end up with an
			// underscore at the begin of the value
			if (i > 0 && Character.isUpperCase(currentChar)) {
				result = result.concat("_");
			}
			result = result.concat(Character.toString(currentChar).toLowerCase());
		}
		return result;
	}

	public static final String toEUC_KR(String str) {
		return changeCharset(str, "ISO-8859-1", "EUC-KR");
	}

	public static final String toKR_EUC(String str) {
		return changeCharset(str, "EUC-KR", "ISO-8859-1");
	}

	public static final String toUTF8_EUC(String str) {
		return changeCharset(str, "UTF-8", "ISO-8859-1");
	}

	public static final String changeCharset(String str, String to_code, String form_code) {
		if (str == null)
			return null;

		try {
			return new String(str.getBytes(to_code), form_code);
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}

	public static final String getSysVal(String baseName, String key) {
		return getSysVal(baseName, key, null);
	}

	public static final String getSysVal(String baseName, String key, String charset) {
		ResourceBundle rb = ResourceBundle.getBundle(baseName);
		if (charset == null)
			return rb.getString(key);
		else
			return changeCharset(rb.getString(key), "ISO-8859-1", charset);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final String[] split(String str, String chr) {
		String temp[] = (String[]) null;
		if (str == null)
			return new String[0];
		if (str.trim().equals(""))
			return new String[0];
		Vector buf = new Vector();
		for (int pos = -1; (pos = str.indexOf(chr)) > -1;) {
			buf.addElement(str.substring(0, pos));
			str = str.substring(pos + 1);
		}

		buf.addElement(str);
		temp = new String[buf.size()];
		for (int i = 0; i < buf.size(); i++)
			temp[i] = (String) buf.get(i);

		return temp;
	}

	public static String delChar(String str, String value) {
		if (str == null)
			return "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!(new StringBuffer(String.valueOf(c))).toString().equals(value))
				sb.append(c);
		}

		return sb.toString();
	}

	public static String[] getTokenData(String str, char _char) {
		String tempbuf = str;
		if (str == null)
			return null;
		if (str.length() == 0)
			return null;
		int j = 0;
		for (int i = 0; i < str.length(); i++)
			if (tempbuf.charAt(i) == _char)
				j++;

		String rtnbuf[] = new String[j + 1];
		j = 0;
		rtnbuf[0] = "";
		for (int i = 0; i < str.length(); i++)
			if (tempbuf.charAt(i) == _char) {
				j++;
				rtnbuf[j] = "";
			} else {
				rtnbuf[j] = rtnbuf[j] + String.valueOf(tempbuf.charAt(i));
			}

		return rtnbuf;
	}

	public static String voidNull(Integer param) {
		return voidNull(String.valueOf(param));
	}

	public static String voidNullToZero(String para) {
		String param = String.valueOf(para);
		param = voidNull(String.valueOf(param)).equals("") ? "0" : param;
		return param;
	}

	public static String voidNullToZero(Integer para) {
		String param = String.valueOf(para);
		if (param == null)
			return "0";
		if (param.trim().equals("null"))
			return "0";
		if (param.trim().equals(""))
			return "0";
		else
			return param.trim();
	}

	public static String voidNull(String param) {
		if (param == null)
			return "";
		if (param.trim().equals("null"))
			return "";
		if (param.trim().equals(""))
			return "";
		else
			return param.trim();
	}

	public static String voidNull(String param, String tmp) {
		if (param == null)
			return tmp;

		if ("null".equals(param.trim()))
			return tmp;

		if ("".equals(param.trim()))
			return tmp;
		else
			return param.trim();
	}

	@SuppressWarnings("rawtypes")
	public static final String getCommaList(ArrayList cds) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < cds.size(); i++) {
			if (i > 0)
				str.append(",");
			str.append("'");
			str.append(cds.get(i));
			str.append("'");
		}

		return str.toString();
	}

	public static final String getCommaList(String cds[]) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < cds.length; i++) {
			if (i > 0)
				str.append(",");
			str.append("'");
			str.append(cds[i]);
			str.append("'");
		}

		return str.toString();
	}

	public static final String getLeft(String str, int n) {
		String result = "";

		if (n <= 0)
			result = "";
		else if (n > str.length())
			result = str;
		else {
			result = str.substring(0, n);
		}

		return result;
	}

	public static final String getRight(String str, int n) {
		String result = "";

		if (n <= 0) {
			result = "";
		} else if (n > str.length()) {
			result = str;
		} else {
			int iLen = str.length();
			result = str.substring(iLen - n, iLen);
		}

		return result;
	}

	public static final String getMid(String str, int start, int end) {
		String result = "";

		if (start <= 0) {
			result = "";
		} else if (end > str.length()) {
			result = str;
		} else {
			result = str.substring(start, end);
		}

		return result;
	}

	public static final String getMid(String str, int start) {
		String result = "";

		if (start <= 0) {
			result = "";
		} else if (start > str.length()) {
			result = str;
		} else {
			int iLen = str.length();
			result = str.substring(start, iLen - start);
		}

		return result;
	}

	public static final String getZeroMask(int str, int digits) {
		return getZeroMask(String.valueOf(str), digits);
	}

	/**
	 * 문자열 길이 측정
	 * 
	 * @return
	 */
	public static final String getZeroMask(String str, int digits) {
		String zero = "";

		if (str.length() < digits) {
			for (int i = 0; i < digits - str.length(); i++)
				zero += '0';
		}

		return zero + str;
	}

	/**
	 * ######-####### 마스크 입력에 따라 문자열을 변환후 리턴
	 * 
	 * @param format
	 * @param str
	 * @return
	 */
	public static final String formatValueMask(String format, String str) {
		String rv = "";
		int numcount = countChr(format, '#');
		str = str.replace("/\\D/g", "").substring(0, numcount);
		char chrAt;
		int validx = 0;
		for (int n = 0; n < format.length(); n++) {
			chrAt = format.charAt(n);
			rv += (chrAt == '#') ? str.charAt(validx++) : chrAt;
			if (validx >= str.length())
				break;
		}

		return rv;
	}

	public static final int countChr(String str, char chr) {
		int count = 0;
		int length = str.length();
		for (int n = 0; n < length; n++) {
			if (chr == str.charAt(n))
				count++;
		}
		return count;
	}

	/**
	 * 사업자번호 형식
	 * 
	 * @param str
	 * @return
	 */
	public static final String getSoupNo(String str) {
		String result = formatValueMask("###-##-#####", str);
		return result;
	}

	/**
	 * 주민번호(법인번호) 형식
	 * 
	 * @param str
	 * @return
	 */
	public static final String getIDNo(String str) {
		String result = formatValueMask("######-#######", str);
		return result;
	}

	/**
	 * 사업자번호 체크
	 * 
	 * @param str
	 * @return
	 */
	public static final boolean checkSoupNo(String val) {
		if (val.length() > 10 && val.length() < 14) {
			return false;
		}

		for (int i = 0; i < val.length(); i++) {
			if (!Character.isDigit(val.charAt(i))) {
				return false;
			}
		}

		return true;

		/*
		 * String scode = "137137135";
		 * 
		 * int sum = 0; for (int n = 0; n < 9; n++) { int t_val = val.charAt(n); int t_scode = scode.charAt(n);
		 * 
		 * sum += t_val * t_scode; } int t_val8 = val.charAt(8); sum += t_val8 * 5 / 10;
		 * 
		 * int sidliy = sum % 10; int sidchk = (sidliy != 0) ? 10 - sidliy : 0;
		 * 
		 * int t_val9 = val.charAt(9);
		 * 
		 * if (sidchk == t_val9) { // 정상적인 사업자번호 return true; } else { // 비정상적인 사업자번호 return false; }
		 */
	}

	// 주민등록번호 체크
	public static final boolean checkJuminNo(String val) {
		if (val.length() != 13) {
			return false;
		}

		for (int i = 0; i < val.length(); i++) {
			if (!Character.isDigit(val.charAt(i))) {
				return false;
			}
		}

		return true;

		/*
		 * int cBit = 0; String scode = "234567892345";
		 * 
		 * for (int n = 0; n < 12; n++) { int t_val = val.charAt(n); int t_scode = scode.charAt(n);
		 * 
		 * cBit += t_val * t_scode; }
		 * 
		 * cBit = 11 - (cBit % 11); cBit = cBit % 10;
		 * 
		 * int t_val12 = val.charAt(12);
		 * 
		 * if (t_val12 == cBit) { return true; } return false;
		 */
	}

	public static final String getRandStr() {
		// String key = new String("qaa0bclduk2ehfjgtp8xhifj61doklnm9nboi5pgqyvrs3ctsumzvwwx4ye7zr");
		String key = new String("1234567890");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int te = 0;
		for (int i = 1; i <= 8; i++) {
			te = r.nextInt(62);
			sb.append(key.charAt(te));
		}

		return sb.toString();
	}

	public static final String getRandFullStr() {
		String key = new String("qaa0bclduk2ehfjgtp8xhifj61doklnm9nboi5pgqyvrs3ctsumzvwwx4ye7zr");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int te = 0;
		for (int i = 1; i <= 8; i++) {
			te = r.nextInt(62);
			sb.append(key.charAt(te));
		}

		return sb.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final String[] eraseDuplicatedValue(String[] srcArr) {
		Vector tempVector = new Vector(); // 정렬을 위한 임시벡터
		int loopCount = 0; // 루프카운터
		String[] resultStrArr = null; // 중복값을 제거한 값 저장 배열

		// 문자열을 정렬을 위해 Vector에 삽입
		for (loopCount = 0; loopCount < srcArr.length; loopCount++) {
			tempVector.add(srcArr[loopCount]);
		}

		// 문자열 정렬
		Collections.sort(tempVector);

		// 정렬된 문자열을 문자열 배열에 재삽입
		for (loopCount = 0; loopCount < srcArr.length; loopCount++) {
			srcArr[loopCount] = (String) (tempVector.elementAt(loopCount));
		}

		// 임시저장소 청소(^__^);
		tempVector.clear();

		// 최초의 값 입력
		tempVector.add(srcArr[0]);

		// 인접한 두 값을 비교하여 같지 않으면 뒤의 값을 임시저장소에 저장.
		for (loopCount = 1; loopCount < srcArr.length; loopCount++) {
			if (!srcArr[loopCount].equals(srcArr[loopCount - 1])) {
				tempVector.add(srcArr[loopCount]);
			}
		}

		// 리턴될 문자열 배열 생성
		resultStrArr = new String[tempVector.size()];

		// 임시저장소의 문자열 배열을 리턴될 문자열 배열에 삽입
		for (loopCount = 0; loopCount < resultStrArr.length; loopCount++) {
			resultStrArr[loopCount] = (String) (tempVector.elementAt(loopCount));
			// System.out.println(resultStrArr[loopCount]);
		}

		// 결과 문자열 배열 리턴.
		return resultStrArr;
	}

	public static String randomUuid() {
		return UUID.randomUUID().toString();
	}

	// 배열에 특정값이 있거나 없으면 참거짓.
	public static boolean isArrayValue(String[] inArray, String values) {
		for (String v : inArray) {
			if (v.equals(values)) {
				return true;
			}
		}
		return false;
	}

	// 배열에 특정값이 있거나 없으면 참거짓.
	public static boolean isMobile(String values) {
		String v = StringUtil.getLeft(values, 3);
		if ("010".equals(v) || "011".equals(v) || "016".equals(v) || "017".equals(v) || "018".equals(v) || "019".equals(v)) {
			return true;
		}
		return false;
	}

	public static String getUniqeFileName(String strutsFileName, String orgName) {
		return strutsFileName.substring(0, strutsFileName.indexOf(".")) + orgName.substring(orgName.indexOf("."), orgName.length());

	}

	public static final String getComma(String str) {
		if ("".equals(voidNull(str)))
			return "";

		String temp = "";
		int j = 1;
		String val = str.replaceAll(",", "").replace(".00", "");

		int cPos = val.indexOf(".", 0);// 소수점위치.

		if (cPos == -1)
			cPos = val.length();

		for (int i = val.length() - 1; i >= 0; i--) {

			if (i != 0 && "-".equals(val.charAt(i))) {
				continue;// minus 허용.

			} else if (!"-".equals(val.charAt(i))) {
				if (j % 3 == 1 && j > 3)
					temp = "," + temp;
			}

			temp = val.charAt(i) + temp;
			if (i < cPos)
				j++;
		}

		return temp.replace("-,", "-");
	}

	public static final boolean isPatternMatcher(String args, String patt) {
		try {

			Pattern p = Pattern.compile(patt);

			Matcher m = p.matcher(args);
			int cnt = 0;
			for (int i = 0; m.find(i); i = m.end())
				cnt++;

			if (cnt > 0)
				return true;

		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public static final int isPatternMatcher(String args, String patt, int cnt) {
		try {

			Pattern p = Pattern.compile(patt);

			Matcher m = p.matcher(args);
			for (int i = 0; m.find(i); i = m.end())
				cnt++;

			if (cnt > 0)
				return cnt;

		} catch (Exception e) {
			return 0;
		}

		return 0;
	}
	
	public static int ilen(String s)
	{
		int z = stoi(s);
		if ( z < 0 ) z = -z;
		return String.valueOf(z).length();
	}
	
	public static int stoi(String str)
	{
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
	public static double stod(String str)
	{
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException ex) {
			return 0.0D;
		}
	}
	public static int dlen(String s)
	{
		double z = stod(s);
		if ( z < 0 ) z = -z;
		return String.valueOf(z).length()-1;
	}
	
    /**
     * 지정한 정수의 개수 만큼(빈칸(" ")스트링을 구한다.
     * 절단된 String의 바이트수가 자를 바이트 개수보다 모자라지 않도록 한다.
     * 
     * @param str 원본 String
     * @param length 자를 바이트개수
     * @return String 절단된 스트링
     */
    public static int strLeng(String str){
   	 
   	 byte[] bytes = str.getBytes();
   	 
   	 return bytes.length;
    }

    /**
     * 금액(double)을 금액표시타입(소숫점2자리) 으로 변환한다. <BR>
     * (예) 12345678.1 --> 12,345,678.10         <BR>
     * delemeter를 주지 않는 경우 처리           <BR>
     * </pre>
     *
     * @param    moneyString 금액 (double형).
     * @return   변경된 금액 문자열.
     */

    public static String makeMoneyType(double dblMoneyString)
    {
            String moneyString = new Double(dblMoneyString).toString();

            //String format = "#,##.00";
            String format = "#,##";
            DecimalFormat df = new DecimalFormat(format);
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();

            dfs.setGroupingSeparator(',');// 구분자를 ,로 
            df.setGroupingSize(3);//3자리 단위마다 구분자처리 한다. 
            df.setDecimalFormatSymbols(dfs);

            return (df.format(Double.parseDouble(moneyString))).toString();
    }
    
    public static String makeMoneyType(String dblMoneyString)
    {
            String moneyString = dblMoneyString;

            //String format = "#,##.00";
            String format = "#,##";
            DecimalFormat df = new DecimalFormat(format);
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();

            dfs.setGroupingSeparator(',');// 구분자를 ,로 
            df.setGroupingSize(3);//3자리 단위마다 구분자처리 한다. 
            df.setDecimalFormatSymbols(dfs);

            return (df.format(Double.parseDouble(moneyString))).toString();
    }
    
    
    /**
     * 무조건 소문자로 변형시킨다.
     * 예) INVSEQ = invseq 
     *     INV_SEQ = inv_seq
     *     invSeq = invseq
     * @param underScore
     *        - '_' 가 포함된 변수명
     * @return 소문자 표기법 변수명
     */
    public static String convert2LowerCase(String underScore) {

        // '_' 가 나타나지 않으면 이미 camel case 로 가정함.
        // 단 첫째문자가 대문자이면 camel case 변환 (전체를 소문자로) 처리가
        // 필요하다고 가정함. --> 아래 로직을 수행하면 바뀜
        if (underScore.indexOf('_') < 0
            && Character.isLowerCase(underScore.charAt(0))) {
            return underScore;
        }
        StringBuilder result = new StringBuilder();
        int len = underScore.length();

        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            result.append(Character.toLowerCase(currentChar));
        }
        return result.toString();
    }	
}
