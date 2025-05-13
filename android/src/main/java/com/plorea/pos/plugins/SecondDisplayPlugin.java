package com.plorea.pos.plugins;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.view.Display;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "SecondDisplay")
public class SecondDisplayPlugin extends Plugin {

    private Presentation mPresentation;

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }

    @PluginMethod
    public void showOnSecondScreen(PluginCall call) {
        String url = call.getString("url");
        
        if (url == null || url.isEmpty()) {
            call.reject("URL not found");
            return;
        }

        Context context = getActivity();
        DisplayManager mDisplayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();

        if (displays.length > 1) {
            getBridge().executeOnMainThread(() -> {
                try {
                    Display targetDisplay = findValidDisplay(displays);
                    if (targetDisplay == null) {
                        call.reject("Second display is not compatible");
                        return;
                    }

                    if (mPresentation != null && mPresentation.isShowing()) {
                        mPresentation.dismiss();
                        mPresentation = null;
                    }

                    mPresentation = new DifferentDisplay(context, targetDisplay, url);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        mPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                    } else {
                        mPresentation.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    }

                    mPresentation.show();
                    call.resolve();
                } catch (Exception e) {
                    call.reject(e.getMessage());
                }
            });
        } else {
            call.reject("No second display found");
        }
    }

    private Display findValidDisplay(Display[] displays) {
        for (Display display : displays) {
            if (display.getDisplayId() != Display.DEFAULT_DISPLAY &&
                    !display.getName().contains("virtual")) {
                return display;
            }
        }
        return null;
    }

    public static class DifferentDisplay extends Presentation {
        private final String url;

        public DifferentDisplay(Context outerContext, Display display, String url) {
            super(outerContext, display);
            this.url = url;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            WebView webView = new WebView(getContext());
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setAllowFileAccess(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
            setContentView(webView);
        }
    }
}
