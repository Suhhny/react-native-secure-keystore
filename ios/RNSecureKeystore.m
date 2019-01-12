
#import "RNSecureKeystore.h"
#import <LocalAuthentication/LocalAuthentication.h>

@implementation RNSecureKeystore

RCT_EXPORT_MODULE();

RCT_EXPORT_MODULE(isAvailable: (RCTPromiseResolveBlock)resolve rejecter: (RCTPromiseRejectBlock)reject){
	LAContext *context = [ [LAContext alloc] init];
	if ([context canEvaluatePolicy: LAPolicyDeviceOwnerAuthenticationWithBiometrics error: NULL]) {
		resolve(@(YES));
	}else {
		reject(@(NO));
	}

}

@end


