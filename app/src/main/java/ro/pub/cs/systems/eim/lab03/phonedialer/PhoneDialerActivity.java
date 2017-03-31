package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PhoneDialerActivity extends AppCompatActivity {

    String phoneNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
    }

    public void addDigit(View view) {
        String digit = view.getTag().toString();
        phoneNo += digit;
        EditText displayedNum = (EditText) findViewById(R.id.phone_number_edit_text);
        displayedNum.setText(phoneNo);
    }


    public void deleteDigit(View view) {
        if (phoneNo.length() != 0)
            phoneNo = phoneNo.substring(0, phoneNo.length() - 1);
        EditText displayedNum = (EditText) findViewById(R.id.phone_number_edit_text);
        displayedNum.setText(phoneNo);
    }
}