package io.nath.c4ontv.xposed.mods.c4ontv;

import android.media.MediaRouter.Callback;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class C4OnTV implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
    	if (!lpparam.packageName.equals("com.channel4.ondemand")) {
            return;
    	}
    	
		XposedBridge.log("Hooking " + lpparam.packageName);
		
    	Class<?> MediaRouter = findClass("android.media.MediaRouter", null);
    	if (MediaRouter == null) {
    		XposedBridge.log("Couldn't find MediaRouter");
    		return;
    	}
    	
    	XposedBridge.log("Found MediaRouter");
    	
    	findAndHookMethod(MediaRouter, "addCallback", int.class, Callback.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            	XposedBridge.log("Bypassing MediaRouter.addCallback");
            	param.setResult(null);
            }
        });
    	
    	findAndHookMethod(MediaRouter, "removeCallback", Callback.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            	XposedBridge.log("Bypassing MediaRouter.removeCallback");
            	param.setResult(null);
            }
        });
    	
    	findAndHookMethod(MediaRouter, "getSelectedRoute", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            	XposedBridge.log("Bypassing MediaRouter.getSelectedRoute");
            	param.setResult(null);
            }
        });
    }
}
