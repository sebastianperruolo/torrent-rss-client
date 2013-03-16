package ar.com.kache.config;

public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 2767695169239262526L;

	public ConfigurationException() {
		super();
	}

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigurationException(String message) {
		super(message);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}
	
}
