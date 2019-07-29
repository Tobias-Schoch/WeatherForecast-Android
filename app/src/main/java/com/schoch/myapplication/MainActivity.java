package com.schoch.myapplication;

        import android.os.Bundle;
        import android.app.Activity;
        import android.support.v4.app.ActivityCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.view.Menu;
        import android.webkit.WebChromeClient;
        import android.webkit.WebView;
        import android.webkit.GeolocationPermissions;
        import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    public static WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 0);
        webview = (WebView) findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setGeolocationEnabled(true);
        webview.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
                callback.invoke(str, true, false);
            }
        });
        webview.setOverScrollMode(2);
        webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webview, String str) {
                MainActivity.webview.loadUrl("javascript:(function() { document.getElementsByClassName('menu-icone-container')[0].style.display='none';  })()");
            }
        });
        webview.loadUrl("https://photography-schoch.de/weather/");
    }

}