package com.example.testing;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class QuestionsFragment extends Fragment{
	
    public static final String ARG_OBJECT = "object";

	
	public static Fragment newInstance(Context context) {
		QuestionsFragment f = new QuestionsFragment();
 
        return f;
    }
	
	public static final Fragment addItem(String problem, String opt1){
		QuestionsFragment qf = new QuestionsFragment();
		Bundle bdl = new Bundle(1);
		bdl.putString("PROBLEM", problem);
		bdl.putString("OPT1", opt1);
		qf.setArguments(bdl);
	
		return qf;
	}
	
	private WebView webView;

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// get the url to open
		Bundle args = getArguments();
		String url = args.getString("PROBLEM");
		// set up the WebView
		webView = (WebView) getView().findViewById(R.id.webView1);
		webView.setWebViewClient(new MyBrowser());
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
	    webView.loadData(url, "text/html", "UTF-8");

 	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		
	    View view = inflater.inflate(R.layout.web_view, container, false);
	    return view;		
	}	

	private class MyBrowser extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String customHtml) {
	       view.loadData(customHtml, "text/html", "UTF-8");
	       return true;
	    }
	}

}
