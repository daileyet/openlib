package mail.app.backup;

import java.io.File;

public abstract class StorePath {

	public abstract String getStorePath();

	public String getStoreName() {
		return new File(getStorePath()).getName();
	}

	public static StorePath getDefaultStorePath() {
		return new DefaultStorePath();
	}

	public static StorePath getStorePath(String path) {
		return new SpecialStorePath(path);
	}

	static class SpecialStorePath extends StorePath {
		private String path;

		public SpecialStorePath(String path) {
			this.path = path;
		}

		@Override
		public String getStorePath() {
			if (path != null && !"".equals(path.trim())) {
				return path;
			} else {
				return getDefaultStorePath().getStorePath();
			}
		}
	}

	static class DefaultStorePath extends StorePath {
		static final String DEFAULT_STORE_NAME = "automail.bak";

		@Override
		public String getStorePath() {
			return new File(System.getProperty("user.dir"), DEFAULT_STORE_NAME)
					.getAbsolutePath();
		}
	}
}
