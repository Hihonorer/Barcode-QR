package com.example.barcodeqrenkripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView nama, tvEnkripsi;
    Button btn_generate, btn_reset;
    ImageView Ivbarcodenya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialComponent();
        generateQR();
        resetkolom();

    }

    private void inisialComponent(){
        nama = findViewById(R.id.tietname);
        tvEnkripsi = findViewById(R.id.tv_Enkripsi);
        btn_generate = findViewById(R.id.btngenerate);
        btn_reset = findViewById(R.id.btnreset);
        Ivbarcodenya = findViewById(R.id.barcodenya);
    }

    private void generateQR(){
        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nama.getText().toString();
                StringBuilder nol = new StringBuilder();

                for (int i = 0; i< name.length();i++){
                    int index = name.charAt(i);
                    char s = (char)(index + 20);
                    nol.append(s);
                }
                tvEnkripsi.setText(nol.toString());
                String text = tvEnkripsi.getText().toString();

                if (!text.equals("")){
                    new QRDownloader(Ivbarcodenya).execute("http://chart.apis.google.com/chart?cht=qr&chs=500x500&chl="+text+"&chld=H|0");
                    btn_generate.setVisibility(View.GONE);
                    btn_reset.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(MainActivity.this,"Name tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void resetkolom(){
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama.setText("");
                tvEnkripsi.setText("");
                Ivbarcodenya.setImageDrawable(Drawable.createFromPath(String.valueOf(R.drawable.noqr)));
                btn_generate.setVisibility(View.VISIBLE);
                btn_reset.setVisibility(View.GONE);
            }
        });
    }
}
