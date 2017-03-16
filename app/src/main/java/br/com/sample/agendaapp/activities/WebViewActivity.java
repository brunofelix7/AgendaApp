package br.com.sample.agendaapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import br.com.sample.agendaapp.R;

public class WebViewActivity extends AppCompatActivity {

    private EditText et_web_view;
    private WebView wv_url;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        et_web_view = (EditText) findViewById(R.id.et_web_view);
        wv_url      = (WebView)  findViewById(R.id.wv_url);

        wv_url.setWebViewClient(new CustomWebClient());
        wv_url.getSettings().setLoadsImagesAutomatically(true);
        wv_url.getSettings().setJavaScriptEnabled(true);
        wv_url.getSettings().setSupportZoom(true);
    }

    public void goToURL(View view) {
        url = et_web_view.getText().toString();
        wv_url.loadUrl("http://" + url);
    }

    private class CustomWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
