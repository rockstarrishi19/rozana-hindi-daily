package com.rishimahendra.rozana;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.Base64;

public class MainActivity extends Activity {
    private static final String HOME = "https://rockstarrishi19.github.io/rozana-hindi-daily/";
    private WebView webView;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(0xFF11100F);
        getWindow().setNavigationBarColor(0xFF11100F);
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportMultipleWindows(false);
        webView.addJavascriptInterface(new DownloadBridge(), "RozAnaDownload");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return openExternal(request.getUrl());
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return openExternal(Uri.parse(url));
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!Uri.parse(url).getHost().equals("rockstarrishi19.github.io")) {
                    view.post(() -> view.loadUrl(HOME));
                }
            }
        });
        if (state == null) webView.loadUrl(HOME);
        else webView.restoreState(state);
        setContentView(webView);
    }

    private boolean openExternal(Uri uri) {
        if ("rockstarrishi19.github.io".equals(uri.getHost())) return false;
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception ignored) { }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle out) {
        if (webView != null) webView.saveState(out);
        super.onSaveInstanceState(out);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }

    private final class DownloadBridge {
        @JavascriptInterface
        public void saveImage(String encoded, String name) {
            runOnUiThread(() -> {
                try {
                    byte[] bytes = Base64.getDecoder().decode(encoded);
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DISPLAY_NAME, name);
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/RozAna");
                    Uri image = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    if (image == null) throw new IllegalStateException("Could not create image");
                    try (OutputStream stream = getContentResolver().openOutputStream(image)) {
                        if (stream == null) throw new IllegalStateException("Could not open image");
                        stream.write(bytes);
                    }
                    Toast.makeText(MainActivity.this, "JPEG तस्वीर Pictures/RozAna में सेव हुई", Toast.LENGTH_LONG).show();
                } catch (Exception error) {
                    Toast.makeText(MainActivity.this, "तस्वीर सेव नहीं हो पाई", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
