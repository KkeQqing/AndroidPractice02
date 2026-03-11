package com.example.test02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btRegRegist=null;
    private Button btRegLogin=null;
    private EditText etRegName=null;
    private EditText etRegPwd=null;
    private CheckBox ckRegeSexFemale=null;
    private CheckBox ckRegSexMale=null;
    private TextView tvRegShow=null;

    private Button btnGotoImage = null;

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

        btRegRegist=(Button) findViewById(R.id.reg_regist_bt);
        btRegLogin=(Button) findViewById(R.id.reg_login_bt);
        etRegName=(EditText) findViewById(R.id.reg_name_et);
        etRegPwd=(EditText) findViewById(R.id.reg_pwd_et);
        ckRegSexMale=(CheckBox) findViewById(R.id.reg_sex_male_cb);
        ckRegeSexFemale=(CheckBox) findViewById(R.id.reg_sex_female_cb);
        tvRegShow=(TextView) findViewById(R.id.reg_show_tv);

        btnGotoImage = (Button) findViewById(R.id.btn_goto_image);

        btRegRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtShow="姓名："+etRegName.getText()+",密码："+etRegPwd.getText();
                if(ckRegSexMale.isChecked())
                    txtShow+="男";
                if(ckRegeSexFemale.isChecked())
                    txtShow+="女";
                tvRegShow.setText(txtShow);
            }
        });

        btRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtShow = "姓名: " + etRegName.getText() + ", 密码: " + etRegPwd.getText();
                if (ckRegSexMale.isChecked())
                    txtShow += ", 性别: " + "男";
                if (ckRegeSexFemale.isChecked())
                    txtShow += ", 性别: " + "女";

                Toast toast = Toast.makeText(MainActivity.this, txtShow, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btnGotoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建意图，从 MainActivity 跳转到 ImageViewerActivity
                Intent intent = new Intent(MainActivity.this, ImageViewerActivity.class);
                startActivity(intent);
            }
        });
    }
}