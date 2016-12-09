/* -*- Mode: Java; c-basic-offset: 4; tab-width: 4; indent-tabs-mode: nil; -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.focus.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.mozilla.focus.R;
import org.mozilla.focus.widget.UrlBar;

public class BrowserActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_browser);

        final UrlBar urlBar = (UrlBar) findViewById(R.id.urlbar);

        final WebView webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                urlBar.onPageStarted(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                urlBar.onPageFinished();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

        });

        urlBar.setOnUrlEnteredListener(new UrlBar.OnUrlAction() {
            @Override
            public void onUrlEntered(String url) {
                webView.loadUrl(url);
            }

            @Override
            public void onErase() {
                finish();
            }
        });
    }
}