package co.edu.unipiloto.stopwatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Locale;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class StopwatchActivity extends Activity {

    private int seconds=0;
    private int secPorVuelta=0;
    private int vueltas=0;
    private boolean running;
    ArrayList<String> lista=new ArrayList<String>();
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        lv=(ListView)findViewById(R.id.lista);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista);

        lv.setAdapter(arrayAdapter);

        if(savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
        }

        runTimer();
    }

    //start
    public void onClickStart(View view){
        running=true;
    }

    //stop
    public void onClickStop(View view){
        running=false;
    }

    //reset
    public void onClickReset(View view){
        running=false;
        seconds=0;
        secPorVuelta=0;
    }

    //marcar vuelta
    public void onClickMarcar(View view){
        vueltas++;
        int hours=secPorVuelta/3600;
        int minutes=(secPorVuelta%3600)/60;
        int secs=secPorVuelta%60;
        //Vuelta vuelta=new Vuelta(vueltas,hours+":"+minutes+":"+secs);
        lista.add("Vuelta: "+vueltas+" Tiempo: "+hours+":"+minutes+":"+secs);
        Toast.makeText(this,"Nueva vuelta: "+hours+":"+minutes+":"+secs, Toast.LENGTH_LONG).show();
        secPorVuelta=0;
    }

    private void runTimer(){
        final TextView timeView=(TextView)findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3600;
                int minutes=(seconds%3600)/60;
                int secs=seconds%60;
                String time=String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);
                if (running){
                    seconds++;
                    secPorVuelta++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
    }

}