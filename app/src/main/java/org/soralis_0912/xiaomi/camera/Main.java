package org.soralis_0912.xiaomi.camera;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XposedBridge;

import android.os.Build;

public class Main implements IXposedHookLoadPackage {
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if(!lpparam.packageName.equals("com.android.camera")) {
            return;
        }


        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("android.os.SystemProperties", lpparam.classLoader), "native_get", String.class, String.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) {
                final String key = param.args[0].toString();

                if (key == "ro.miui.customized.region") {
                    param.setResult("");
                }
                if (key == "ro.miui.region") {
                    param.setResult("IN");
                }
                
            }
        });
    }
}
