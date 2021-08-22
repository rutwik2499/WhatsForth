package com.invictus.whatsforth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button btn;
    String phone,message;
    EditText ph, msg;
    Context cxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        btn=findViewById(R.id.button);
        ph=findViewById(R.id.phone);
        msg=findViewById(R.id.message);

        PackageManager packageManager = this.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        cxt=this.getApplicationContext();
    }


    public void openwapp(View x)
    {
        PackageManager packageManager = cxt.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        phone=ph.getText().toString();
        message=msg.getText().toString();

        try {
            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(message, "UTF-8");
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null)
            {
                startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getTitle().equals("About Us")) {
            Intent i = new Intent(this, About_Us.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


}