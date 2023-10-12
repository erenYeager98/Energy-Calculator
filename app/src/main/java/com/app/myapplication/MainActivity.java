package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

private TextView energyPerMonth;
private TextView energyPerYear;
private TextView energyPerDay;
private String item;
private TextView powerUsage;
private TextView hoursUsage;
private String selectedOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.view2);
        float alphaValue = 0.3f; // Set the desired opacity (0.0f for fully transparent, 1.0f for fully opaque)
        imageView.setAlpha(alphaValue);
        ImageView imageView1 = findViewById(R.id.view3);
        // Set the desired opacity (0.0f for fully transparent, 1.0f for fully opaque)
        imageView1.setAlpha(alphaValue);


        Spinner spinner = findViewById(R.id.applianceSpinner);
        // Create an ArrayAdapter using the string array and a default Spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.appliances, // The string array resource
                android.R.layout.simple_spinner_item // Default layout
        );

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the Spinner
        spinner.setAdapter(adapter);
        ImageButton btn = (ImageButton) findViewById(R.id.imageButton);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = (String) adapterView.getItemAtPosition(i);
                System.out.println(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                item = null;

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    energyPerDay = (TextView) findViewById(R.id.energyPerDayEditText);
                    energyPerMonth =(TextView) findViewById(R.id.energyPerMonthEditText);
                    energyPerYear = (TextView) findViewById(R.id.energyPerYearEditText);
                    hoursUsage = (TextView) findViewById(R.id.hourUsageEditText);
                    powerUsage = (TextView) findViewById(R.id.powerUsageEditText);
                    String hrs=null;
                    String pwr=null;
                    hrs = hoursUsage.getText().toString();
                    pwr = powerUsage.getText().toString();
                System.out.println(selectedOption);
                    float unit;
                    if(Objects.equals(selectedOption, "High Tension")){
                        unit = (float) 6.35;
                    }
                    else if(Objects.equals(selectedOption, "Low Tension")){
                        unit = 2.50F;
                    }
                    else{
                        unit = 0;
                    }
                    if(!hrs.equals("") && !pwr.equals("")) {

                        float hrs0 = Integer.parseInt(hrs);
                        float pwr0 = Integer.parseInt(pwr);
                        float perDay = (hrs0*pwr0/1000)*unit;

                        String result0 = String.valueOf(perDay);
                        float perMonth = perDay*30;
                        float perYear = perMonth*12;
                        String result1 = String.valueOf(perMonth);
                        String result2 = String.valueOf(perYear);
                        energyPerDay.setText(result0);
                        energyPerMonth.setText(result1);
                        energyPerYear.setText(result2);
                    }
                    else{
                        Toast toast = Toast.makeText(MainActivity.this,"Please enter a valid digit",Toast.LENGTH_SHORT);
                        toast.show();
                    }





            }
        });
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.tensionRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);

                if (selectedRadioButton != null) {
                    selectedOption = selectedRadioButton.getText().toString();
                    // 'selectedOption' now contains the text of the selected RadioButton
                }
            }
        });
    }
}