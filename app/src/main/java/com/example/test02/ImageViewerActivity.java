package com.example.test02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ImageViewerActivity extends AppCompatActivity {

    private ImageView ivDisplay;
    private Button btnPrev, btnNext, btnAlphaAdd, btnAlphaSub;
    private TextView tvAlphaStatus;

    private int[] imageIds = {
            R.drawable.img_01,
            R.drawable.img_02
    };

    private int currentIndex = 0;
    private float currentAlpha = 1.0f; // 1.0f 表示完全不透明 (100%)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        // 1. 初始化控件
        ivDisplay = findViewById(R.id.iv_display);
        btnPrev = findViewById(R.id.btn_prev);
        btnNext = findViewById(R.id.btn_next);
        btnAlphaAdd = findViewById(R.id.btn_alpha_add);
        btnAlphaSub = findViewById(R.id.btn_alpha_sub);
        tvAlphaStatus = findViewById(R.id.tv_alpha_status);

        // 设置初始图片
        updateImage();

        // 绑定点击事件

        // 上一张
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageIds.length == 0) return;
                currentIndex--;
                if (currentIndex < 0) {
                    currentIndex = imageIds.length - 1; // 循环到最后一张
                }
                updateImage();
                Toast.makeText(ImageViewerActivity.this, "上一张", Toast.LENGTH_SHORT).show();
            }
        });

        // 下一张
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageIds.length == 0) return;
                currentIndex++;
                if (currentIndex >= imageIds.length) {
                    currentIndex = 0; // 循环到第一张
                }
                updateImage();
                Toast.makeText(ImageViewerActivity.this, "下一张", Toast.LENGTH_SHORT).show();
            }
        });

        // 透明度增加
        btnAlphaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAlpha += 0.1f;
                if (currentAlpha > 1.0f) {
                    currentAlpha = 1.0f;
                    Toast.makeText(ImageViewerActivity.this, "已达到最小透明度", Toast.LENGTH_SHORT).show();
                }
                applyAlpha();
            }
        });

        // 透明度减少
        btnAlphaSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentAlpha -= 0.1f;
                if (currentAlpha < 0.0f) {
                    currentAlpha = 0.0f;
                    Toast.makeText(ImageViewerActivity.this, "已达到最大透明度", Toast.LENGTH_SHORT).show();
                }
                applyAlpha();
            }
        });
    }

    // 更新图片显示
    private void updateImage() {
        if (imageIds.length > 0) {
            ivDisplay.setImageResource(imageIds[currentIndex]);
            ivDisplay.setAlpha(currentAlpha);
            updateAlphaText();
        }
    }

    // 应用透明度
    private void applyAlpha() {
        ivDisplay.setAlpha(currentAlpha);
        updateAlphaText();
    }

    // 更新文字提示
    private void updateAlphaText() {
        int percent = (int) (currentAlpha * 100 - 100);
        tvAlphaStatus.setText("当前透明度: " + percent + "%");
    }
}