package com.example.restapi.TextRecognizationApp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restapi.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

public class RecognizationApp extends AppCompatActivity  {
//    Button btn_detect,btncapture;
//    ImageView image;
//    TextView textView;
//    Bitmap imageBitmap;
//    static int REQUEST_IMAGE_CAPTURE = 1;
//    boolean isCaptureBtnClicked = false;
//    View view;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recognization_app);
//        init();
//        btn_detect.setOnClickListener(this);
//        btncapture.setOnClickListener(this);
//    }
//    private void init(){
//        btn_detect = findViewById(R.id.detect_text_image);
//        btncapture = findViewById(R.id.capture_image);
//        image = findViewById(R.id.image);
//        textView = findViewById(R.id.text_display);
//        view = findViewById(R.id.main_layout);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.capture_image:{
//                isCaptureBtnClicked = true;
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if(takePictureIntent.resolveActivity(getPackageManager())!=null){
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                }
//                break;
//            }
//            case R.id.detect_text_image:{
//                if(isCaptureBtnClicked){
//
//                    detectTextFromImage();
//                }
//                else toast("Let take a photo");
//                break;
//            }
//            default:break;
//        }
//    }
//
//    private void detectTextFromImage() {
//        if(textView.getText().toString().length()!=0){
//            textView.setText("");
//        }
//        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
//        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
//                .getOnDeviceTextRecognizer();
//                detector.processImage(firebaseVisionImage)
//                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
//                    @Override
//                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
//                        toast("Success");
//                        doWithText(firebaseVisionText);
//                    }
//                })
//                .addOnCanceledListener(new OnCanceledListener() {
//                    @Override
//                    public void onCanceled() {
//                        toast("Check for your internet Connection");
//                    }
//                });
//    }
//
//    private void doWithText(FirebaseVisionText text) {
//        List<FirebaseVisionText.TextBlock> blockList = text.getTextBlocks();
//        if(blockList.size()==0){
//            toast("Nothing to show");
//        }
//        else{
//            for(FirebaseVisionText.TextBlock block:blockList){
//                String textRe = block.getText();
//                textView.append(textRe+"\n");
//            }
//
//        }m
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
//            Bundle bundle = data.getExtras();
//            imageBitmap = (Bitmap) bundle.get("data");
//            image.setImageBitmap(imageBitmap);
//        }
//    }
//    public void toast(String text){
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//    }
}
