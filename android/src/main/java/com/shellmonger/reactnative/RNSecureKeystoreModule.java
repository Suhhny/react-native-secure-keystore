
package com.shellmonger.reactnative;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNSecureKeystoreModule extends ReactContextBaseJavaModule {

	private final ReactApplicationContext reactContext;

	public RNSecureKeystoreModule(ReactApplicationContext reactContext) {
		super(reactContext);
		this.reactContext = reactContext;
	}

	@Override
	public String getName() {
		return "RNSecureKeystore";
	}

	// PUBLIC REACT API : isAvailable() Returns true if the fingerprint reader can be used
	@ReactMethod  // Indicates the method is exposed to React   promise must needed, so resolve or reject is neccessary
	public void isAvailable(final Promise promise) {
		try{
			FingerprintManager manager = getFingerprintManager();
			boolean v = (manager != null && manager.isHardwareDetected() && manager.hasEnrolledFingerprints());
			promise.resolve(v);
		}catch(Exception ex) {
			promise.reject("ERR_UNEXPECTED_EXCEPTION", ex)
		}
	}

	private FingerprintManager getFingerprintManager() {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			return (FingerprintManager)
			reactContext.getSystemService(reactContext.FINGERPRINT_SERVICE);
		}else {
			return null;
		}
	}
}


































