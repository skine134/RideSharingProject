package com.example.tekbae;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

public class check_sign extends Activity {

    SignaturePad signpad;
    Button btnOK, btnCANCEL;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sign);

        btnOK = (Button) findViewById(R.id.btnOk);
        btnCANCEL = (Button) findViewById(R.id.btnCsl);

        signpad = (SignaturePad) findViewById(R.id.signature_pad);

        signpad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(check_sign.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                btnOK.setEnabled(true);
                btnCANCEL.setEnabled(true);

            }

            @Override
            public void onClear() {
                btnOK.setEnabled(false);
                btnCANCEL.setEnabled(false);
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}