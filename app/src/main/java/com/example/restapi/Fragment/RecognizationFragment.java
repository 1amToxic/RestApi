package com.example.restapi.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restapi.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecognizationFragment extends Fragment implements View.OnClickListener {
    static int CAPTURE_IMAGE = 1;

    public RecognizationFragment() {
        // Required empty public constructor
    }
    ImageView imageCapture;
    Button buttonDetect;
    TextView textDisplay;
    View recognizeLayout;
    Bitmap imageBitmap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recognization, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        buttonDetect.setOnClickListener(this);
        imageCapture.setOnClickListener(this);
    }

    private void init(View view){
        imageCapture = view.findViewById(R.id.image_capture);
        buttonDetect = view.findViewById(R.id.detect_text_image);
        textDisplay = view.findViewById(R.id.text_display);
        recognizeLayout = view.findViewById(R.id.recognizeLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_capture:{
                Intent takenImageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takenImageIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(takenImageIntent,CAPTURE_IMAGE);
                }
                break;
            }
            case R.id.detect_text_image:{
                detectTextFromImage();
                break;
            }
            default:break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAPTURE_IMAGE && resultCode == RESULT_OK && data!=null){
            Bundle bundle = data.getExtras();
            imageBitmap = (Bitmap) bundle.get("data");
            imageCapture.setImageBitmap(imageBitmap);
        }
    }
    private void detectTextFromImage() {
        if(textDisplay.getText().toString().length()!=0){
            textDisplay.setText("");
        }
        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        detector.processImage(firebaseVisionImage)
                .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText firebaseVisionText) {
                        toast("Success");
                        doWithText(firebaseVisionText);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        toast("Internet Connection Failed");
                    }
                });
    }
    private void doWithText(FirebaseVisionText text) {
        List<FirebaseVisionText.TextBlock> blockList = text.getTextBlocks();
        if(blockList.size()==0){
            toast("Nothing to show");
        }
        else{
            for(FirebaseVisionText.TextBlock block:blockList){
                String textRe = block.getText();
                textDisplay.append(textRe+"\n");
            }

        }
    }
    private void toast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
