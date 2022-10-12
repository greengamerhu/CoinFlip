package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView imageVievCoin;
    private Button buttonFej, buttonIras;
    private TextView textViewOut;
    private boolean gepFej;
    private boolean felhasznaloFej;
    private int dobasok;
    private int gyozelem;
    private int vereseg;
    private Random rnd = new Random();
    private AlertDialog.Builder jatekVege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonFej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gepFej = rnd.nextBoolean();
                felhasznaloFej = true;
                kepMegjelenit();
                jatek();
                changeText();
            }
        });
        buttonIras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gepFej = rnd.nextBoolean();
                felhasznaloFej = false;
                kepMegjelenit();
                jatek();
                changeText();
            }
        });

    }
    private void jatek() {
        if (felhasznaloFej != gepFej) {
            dobasok++;
            vereseg++;
        } else {
            dobasok++;
            gyozelem++;
        }

    }
    private void ujJatek() {
        dobasok = 0;
        vereseg = 0;
        gyozelem = 0;
        
    }
    private void kepMegjelenit( ) {
        if (gepFej) {
            imageVievCoin.setImageResource(R.drawable.heads);
        } else {
            imageVievCoin.setImageResource(R.drawable.tails);
        }
    }
    private void changeText() {
        textViewOut.setText("Dobások: " + dobasok + "\n" + "gyözelmek: " + gyozelem + "\n" + "verességek: " + vereseg);
    }
    private void init() {
        imageVievCoin = findViewById(R.id.imageVievCoin);
        buttonFej = findViewById(R.id.buttonFej);
        buttonIras = findViewById(R.id.buttonIras);
        textViewOut = findViewById(R.id.textViewOut);
        dobasok = 0;
        vereseg = 0;
        gyozelem = 0;
        textViewOut.setText("Dobások: " + dobasok + "\n" + "gyözelmek: " + gyozelem + "\n" + "verességek: " + vereseg);
        jatekVege = new AlertDialog.Builder(MainActivity.this);
        jatekVege.setTitle("")
                .setMessage("Szeretnél új játékot indítani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ujJatek();
                    }
                });
    }
}