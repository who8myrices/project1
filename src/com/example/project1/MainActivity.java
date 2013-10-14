package com.example.project1;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
	private Button button;
	private EditText time;
	private TextView finalResult;
	private String htmlStrings ="";
	public String theZipCode;
	public String tempString;
	private static String tag = "Weather_app";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		time = (EditText) findViewById(R.id.et_time);
		button = (Button) findViewById(R.id.btn_do_it);
		finalResult = (TextView) findViewById(R.id.tv_result);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(tag, "in here");
				new AsyncTaskRunner().execute(time.getText().toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	private class AsyncTaskRunner extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String htmlString;
			try {
				htmlString = toConnectandParse.getInternetData(params[0]);
	             return htmlString;
			} catch (Exception e) {
				return null;
			}
		}


		@Override
		protected void onPostExecute(String result) {
			// execution of result of Long time consuming operation
			String outPut="";
			htmlStrings = result;
			
			org.jsoup.nodes.Document doc =Jsoup.parse(htmlStrings);
			Elements Tempe =doc.select("table tbody td center tbody tr");
	
				for(org.jsoup.nodes.Element e: Tempe){
					outPut+= e.text()+"\n";

			
			}
			finalResult.setText(outPut);

		}



	}
}
