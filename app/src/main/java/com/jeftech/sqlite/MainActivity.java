package com.jeftech.sqlite;

import android.content.Context;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    BarprodDB.insertdata();
/*    BarprodDB.searchUser("1213");*/
    Log.i(TAG, "onCreate: "+BarprodDB.searchUser("1213"));
  
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

