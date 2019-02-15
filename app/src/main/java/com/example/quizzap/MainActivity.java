package com.example.quizzap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView listview;
float score;
float correct, total;
int REQ_CODE = 2424;
String[] questions = {"Drake","TravisScott","LilBaby"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.list);
        Button reset= (Button) findViewById(R.id.reset);
        listview = (TextView) findViewById(R.id.scoreboard);

        score = 0;
        correct = 0;
        total =0;
        listview.setText(String.format("%.1f%%", score));

        final Intent intent = new Intent(this, Question.class);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questions);
        list.setAdapter(adapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> list, View row, int index, long id) {
                        String topic = list.getItemAtPosition(index).toString();
                        intent.putExtra("topic", topic);
                        startActivityForResult(intent, REQ_CODE);
                    }
                }
        );
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score = 0;
                correct = 0;
                total = 0;
                listview.setText(String.format("%.1f%%", score));
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        if (requestCode == REQ_CODE){
            String ans = intent.getStringExtra("answer");

            try {
                // score += Integer.parseInt(ans);
                ++total;
                correct += Float.parseFloat(ans);
                score = (correct / total) * 100;

            } catch (Exception e){
                e.printStackTrace();
            }
            listview.setText(String.format("%.1f%%", score));
            // scoreboard.setText(Float.toString(score));
        }
    }

}
