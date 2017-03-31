package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.phoneNumber;


public class PhoneDialerActivity extends AppCompatActivity {

    String phoneNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

    public void callNumber(View view) {
        EditText phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edit_text);
        if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    PhoneDialerActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    0);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
            startActivity(intent);
        }
    }

    public void addContact (View view) {
        EditText phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edit_text);
        String phoneNumber = phoneNumberEditText.getText().toString();
        if (phoneNumber.length() > 0) {
            Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
            intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
            startActivityForResult(intent, 0);
        } else {
            Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
        }
    }

    public void endCall(View view) {
        finish();
    }
}
