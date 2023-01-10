package lk.techseya.offloader;
import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class OffLoader {

    public static void ReviveView(String url, Context context, int id){
        SharedPreferences preferences= context.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String code=preferences.getString("code", "");

        Activity activity= (Activity) context;
        ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo anetwork=manager.getActiveNetworkInfo();
        if (null==anetwork){
            WebView webView=activity.findViewById(id);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (null==anetwork){
                        context.startActivity(new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY));
                    }
                }
            }, 3000);
            webView.loadDataWithBaseURL(null,code,"text/html",  "UTF-8","");


        }else {
            WebView webView=activity.findViewById(id);
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient(){

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                    context.startActivity(new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY));};

            });
            webView.loadUrl(url);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL google = null;
            try {
                google = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(google.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String input = null;
            StringBuffer stringBuffer = new StringBuffer();
            while (true)
            {
                try {
                    if (!((input = in.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stringBuffer.append(input);
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("code", stringBuffer.toString());
            editor.apply();
        }

    }
}
