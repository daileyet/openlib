package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * 
 * @author dmj
 * @since 2010/11/11
 */
public class RegexUtil {

	public static boolean isMatch(String rule, String target) {
		Matcher matcher = getPattern(rule).matcher(target);
		return matcher.matches();
	}

	public static Pattern getPattern(String rule, int flags) {
		return Pattern.compile(rule, flags);
	}

	public static Pattern getPattern(String rule) {
		return Pattern.compile(rule);
	}

}
