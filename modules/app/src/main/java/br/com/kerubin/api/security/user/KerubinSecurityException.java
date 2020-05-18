package br.com.kerubin.api.security.user;

import java.text.MessageFormat;

public class KerubinSecurityException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
    public KerubinSecurityException() {
        super();
    }

    public KerubinSecurityException(String message) {
        super(decorateMessage(message));
    }

    public KerubinSecurityException(String message, Throwable cause) {
        super(decorateMessage(message), cause);
    }
    
    private static String decorateMessage(String message) {
    	return MessageFormat.format("{0}: {1}", 
    			KerubinSecurityException.class.getName(), message);
    }

}
