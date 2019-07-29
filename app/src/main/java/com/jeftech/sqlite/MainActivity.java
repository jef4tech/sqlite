package com.jeftech.sqlite;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

  private Context context = MainActivity.this;
  private BarprodDB BarprodDB;
  private String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    BarprodDB = new BarprodDB(context);
    SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
    boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
    if (isFirstRun)
    {                 BarprodDB.insertdata("AGNIDRAVAKAM","8902579000004","10");
    BarprodDB.insertdata("Dlink","790069419270","400");
      // Code to run once
      SharedPreferences.Editor editor = wmbPreference.edit();
      editor.putBoolean("FIRSTRUN", false);
      editor.commit();
    }


  
    //Stetho
    Stetho.initialize(
        Stetho.newInitializerBuilder(this)
            .enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this))
            .build());


  }

}

