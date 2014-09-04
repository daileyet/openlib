package testframework;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 测试工具类
 * 
 * @author dmj
 * @since 2010/11/11
 */
public class TestUtil {
	// ------------------------打印消息到控制台----------------------------//
	public static void setPrint(PrintStream out) {
		System.setOut(out);
	}

	public static void setPrint(File file) throws IOException {
		if (!file.exists())
			file.createNewFile();
		setPrint(new PrintStream(file));
	}

	public static void print(Object message) {
		System.out.print(message);
	}

	public static void println(Object message) {
		System.out.println(message);
	}

	public static void error(Object message) {
		System.err.print(message);
	}

	public static void errorln(Object message) {
		System.err.println(message);
	}

	public static void print(Object[] message) {
		if (message == null) {
			errorln("Error:No Element");
			return;
		}
		for (int i = 0; i < message.length; i++) {
			print(message[i]);
			print(",");
		}
	}

	public static void println(Object[] message) {
		if (message == null) {
			errorln("Error:No Element");
			return;
		}
		for (int i = 0; i < message.length; i++) {
			println(message[i]);
		}
	}

	// ----------------------计算方法调用所消耗的时间(时间复杂度)-----------------------------//
	/**
	 * @param tm
	 *            可以测试接口类
	 * @param foramtLabel
	 *            测试信息显示标签
	 * @param showInfo
	 *            是否显示测试标签
	 */
	public static void runTime(final Testable tm, final Object foramtLabel,
			final boolean showInfo) {
		new Thread(new Runnable() {
			public long diff = 0l;

			@Override
			public void run() {
				tm.initialMethod();
				long begin = System.currentTimeMillis();
				tm.callMethod();
				long end = System.currentTimeMillis();
				diff = end - begin;
				if (showInfo) {
					println(foramtLabel + "" + diff);
				}
				tm.endMethod();
			}
		}).start();
	}

	public static void runTime(final Testable tm, final Object foramtLabel) {
		runTime(tm, foramtLabel, true);
	}

	public static void runTime(final Testable tm) {
		runTime(tm, null, false);
	}

}
