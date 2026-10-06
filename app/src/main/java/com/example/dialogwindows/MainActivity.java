package com.example.dialogwindows;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(v ->
                Toast.makeText(this, "кнопка номер 1 нажата", Toast.LENGTH_SHORT).show());

        button2.setOnClickListener(v ->
                Toast.makeText(this, "кнопка номер 2 нажата", Toast.LENGTH_LONG).show());

        button3.setOnClickListener(v -> showButton3Dialog());

        button4.setOnClickListener(v -> showTestDialog());
    }

    private void showButton3Dialog() {
        new AlertDialog.Builder(this)
                .setTitle("кнопка 3")
                .setIcon(R.drawable.test_icon)
                .setMessage("Это диалоговое окно. Выберите действие:")
                .setPositiveButton("да", (dialog, which) -> {
                    int red = Color.RED;
                    button1.setTextColor(red);
                    button2.setTextColor(red);
                    button3.setTextColor(red);
                    button4.setTextColor(red);
                })
                .setNegativeButton("отмена", (dialog, which) -> {
                    dialog.dismiss();
                    Toast.makeText(this, "Окно закрыто", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void showTestDialog() {
        final String[] items = {
                "Яблоко",
                "Картофель",
                "Банан",
                "Морковь",
                "Апельсин",
                "Свёкла",
                "Ананас",
                "Редиска",
                "Гранат"
        };
        final boolean[] isFruit = {true, false, true, false, true, false, true, false, true};
        final boolean[] checked = new boolean[items.length];

        new AlertDialog.Builder(this)
                .setTitle("Выберите только фрукты")
                .setMultiChoiceItems(items, checked, (dialog, which, isChecked) ->
                        checked[which] = isChecked)
                .setPositiveButton("Проверить", (dialog, which) -> {
                    boolean allCorrect = true;
                    for (int i = 0; i < items.length; i++) {
                        if (checked[i] != isFruit[i]) {
                            allCorrect = false;
                            break;
                        }
                    }
                    if (allCorrect) {
                        Toast.makeText(this, "Всё верно!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this,
                                "Ответы неверные. Кнопки теперь невидимые",
                                Toast.LENGTH_LONG).show();
                        button1.setVisibility(View.INVISIBLE);
                        button2.setVisibility(View.INVISIBLE);
                        button3.setVisibility(View.INVISIBLE);
                        button4.setVisibility(View.INVISIBLE);
                    }
                })
                .setNegativeButton("Отмена", null)
                .show();
    }
}