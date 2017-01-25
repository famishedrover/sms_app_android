package angryziber.com.smsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button sendButton;
    public static final String TAG = "Send SMS";
    EditText txtPhoneNumber , txtMessage;
    TextView textView;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        sendButton = (Button) findViewById(R.id.button);
        txtMessage = (EditText) findViewById(R.id.message);
        txtPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        textView = (TextView) findViewById(R.id.textView);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
                txtPhoneNumber.setText("");
                txtMessage.setText("");
            }
        });


        textView.setText( "0 of144");

        txtMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                x = s.length();
                String xString =Integer.toString(x);

                textView.setText(xString + " of 144");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

    }

    protected  void sendMessage () {
        Log.i(TAG,"inside sendSMS");

        String phoneNumber = txtPhoneNumber.getText().toString();
        final String message = txtMessage.getText().toString();

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(144); //Filter to 10 characters
        txtMessage .setFilters(filters);



        try{

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,message,null,null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();


        }catch(Exception e){
            Log.i(TAG," SMS failed");
            Toast.makeText(getApplicationContext(),
                    "SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }




}
