package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {





    TextView solutionTV;
    TextView resultTV;
    MaterialButton button_equals,button_decimal, button_c, button_close_bracket, button_open_bracket,button_divide,button_7,button_8,button_9,button_6,button_5,button_4,button_3,button_2,button_1,button_0,button_plus,button_minus,button_multiply,button_ac;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV=findViewById(R.id.result);
        solutionTV=findViewById(R.id.solution);
        //assign IDs
        assignIDs(button_0,(R.id.button_0));
        assignIDs(button_1,(R.id.button_1));
        assignIDs(button_2,(R.id.button_2));
        assignIDs(button_3,(R.id.button_3));
        assignIDs(button_4,(R.id.button_4));
        assignIDs(button_6,(R.id.button_6));
        assignIDs(button_5,(R.id.button_5));
        assignIDs(button_7,(R.id.button_7));
        assignIDs(button_8,(R.id.button_8));
        assignIDs(button_9,(R.id.button_9));
        assignIDs(button_plus,(R.id.button_plus));
        assignIDs(button_minus,(R.id.button_minus));
        assignIDs(button_multiply,(R.id.button_multiply));
        assignIDs(button_divide,(R.id.button_divide));
        assignIDs(button_close_bracket,(R.id.button_close_bracket));
        assignIDs(button_open_bracket,(R.id.button_open_bracket));
        assignIDs(button_c,(R.id.button_c));
        assignIDs(button_ac,(R.id.button_ac));
        assignIDs(button_equals,(R.id.button_equals));
        assignIDs(button_decimal,R.id.button_decimal);



    }

    void assignIDs(MaterialButton button, int id)
    {
        button=findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        MaterialButton button = (MaterialButton) v;
        String bText = button.getText().toString();
        String data =  solutionTV.getText().toString();

        if(bText.equals("AC"))
        {
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }
        if(bText.equals("C"))
        {
            if(!(solutionTV.getText().toString().equals("")))
            data=data.substring(0,(data.length()-1));
            solutionTV.setText(data);
            return;
        }
        if(bText.equals("="))
        {
            solutionTV.setText(resultTV.getText());
            return;
        }
        else
        {
            data = data+bText; //concatenate the string
        }

        solutionTV.setText(data);
        String res = calc(data);
        if(!res.equals("ERROR"))
        {
            resultTV.setText(res);
        }
    }
    String calc(String data)
    {
        try {
            Context ct = Context.enter();
            ct.setOptimizationLevel(-1);
            Scriptable script = ct.initStandardObjects();

            String res = ct.evaluateString(script, data, "Javascript", 1, null).toString(); //calculation
                                                                                                     //from dependencies
                                                                                                     //above
            if (res.endsWith(".0"))
            {
                res=res.replace(".0","");
            }
            return res;
        }
        catch(Exception e)
        {
            return "ERROR";
        }
    }
}