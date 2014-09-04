package binder.support;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * manager for default binder support provider and service
 * @author dailey
 *
 */
public class BinderSupportManager {
	private static final String DEFAULT_PROVIDER_NAME = "binder.provider.default";
	private static final Map<String, BinderSupportProvider> providers = new ConcurrentHashMap<String, BinderSupportProvider>();

	public static void registerProvider(String providerName, BinderSupportProvider provider) {
		providers.put(providerName, provider);
	}

	public static void registerDefaultProvider(BinderSupportProvider provider) {
		registerProvider(DEFAULT_PROVIDER_NAME, provider);
	}

	public static BinderSupportService newInstance(String providerName) {
		BinderSupportProvider provider = providers.get(providerName);
		if (provider == null) {
			throw new IllegalArgumentException("No provider registered with name: " + providerName);
		}
		return provider.newBinderSupportService();
	}

	public static BinderSupportService newInstance() {
		return newInstance(DEFAULT_PROVIDER_NAME);
	}

	// initial, load default provider from configure file
	static {
		final String binder_file = "/binder/support/binder.service.properties";
		Properties contxt = new Properties();
		try {
			contxt.load(BinderSupportManager.class.getResourceAsStream(binder_file));
			String providerClassName = contxt.getProperty(DEFAULT_PROVIDER_NAME);
			Class.forName(providerClassName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private BinderSupportManager() {
	}

}
