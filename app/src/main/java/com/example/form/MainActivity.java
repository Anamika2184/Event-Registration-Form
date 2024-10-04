package com.example.form;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText eventName, participantName, mobileNumber, emailAddress, password;
    private Spinner spinnerEventType;
    private Button joineventButton;
    private TextView outputText;
    private View outputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing views
        eventName = findViewById(R.id.eventName);
        participantName = findViewById(R.id.participantName);
        mobileNumber = findViewById(R.id.mobileNumber);
        emailAddress = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        spinnerEventType = findViewById(R.id.spinnerEventType);
        joineventButton = findViewById(R.id.submitButton);
        outputText = findViewById(R.id.outputText);
        outputLayout = findViewById(R.id.outputLayout);

        // Setting up the Event Type Spinner
        String[] eventTypes = {"Select Event Type", "Conference", "Workshop", "Seminar", "Webinar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eventTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEventType.setAdapter(adapter);

        // Setting up the Submit button click listener
        joineventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void submitForm() {
        String eventNameText = eventName.getText().toString();
        String participantNameText = participantName.getText().toString();
        String mobileNumberText = mobileNumber.getText().toString();
        String emailAddressText = emailAddress.getText().toString();
        String passwordText = password.getText().toString();
        String selectedEventType = spinnerEventType.getSelectedItem().toString();

        // Validating the input fields
        if (TextUtils.isEmpty(eventNameText)) {
            eventName.setError("Please enter event name");
            return;
        }
        if (TextUtils.isEmpty(participantNameText)) {
            participantName.setError("Please enter participant name");
            return;
        }
        if (TextUtils.isEmpty(mobileNumberText) || mobileNumberText.length() != 10) {
            mobileNumber.setError("Please enter a valid 10-digit mobile number");
            return;
        }
        if (TextUtils.isEmpty(emailAddressText) || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddressText).matches()) {
            emailAddress.setError("Please enter a valid email address");
            return;
        }
        if (TextUtils.isEmpty(passwordText)) {
            password.setError("Please enter a password");
            return;
        }
        if (selectedEventType.equals("Select Event Type")) {
            Toast.makeText(this, "Please select an event type", Toast.LENGTH_SHORT).show();
            return;
        }

        // If everything is valid, display the output
        outputLayout.setVisibility(View.VISIBLE);
        outputText.setText("Event Name: " + eventNameText + "\n" +
                "Participant Name: " + participantNameText + "\n" +
                "Mobile Number: " + mobileNumberText + "\n" +
                "Email Address: " + emailAddressText + "\n" +
                "Event Type: " + selectedEventType);
    }
}
