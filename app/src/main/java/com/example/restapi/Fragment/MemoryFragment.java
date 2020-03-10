package com.example.restapi.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restapi.Database.DatabaseHelper;
import com.example.restapi.R;
import com.example.restapi.TextRecognizationApp.DisplayMemory;
import com.example.restapi.Utils.DbBitmapUtility;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends Fragment implements View.OnClickListener {
    static int CAPTURE_IMAGE = 2;
    DatabaseHelper databaseHelper;
    public MemoryFragment() {
        // Required empty public constructor
    }
    Bitmap bitmap;
    ImageView image_save;
    Button buttonSave;
    EditText textContent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memory, container,false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        databaseHelper = new DatabaseHelper(getActivity());
        image_save.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
    }

    private void init(View view) {
        image_save = view.findViewById(R.id.image_save);
        buttonSave = view.findViewById(R.id.btnSave);
        textContent = view.findViewById(R.id.content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_save:{
                Intent takenImageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takenImageIntent.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(takenImageIntent,CAPTURE_IMAGE);
                }
                break;
            }
            case R.id.btnSave:{
                databaseHelper.addEntry(textContent.getText().toString(), DbBitmapUtility.getBytes(bitmap));
                Toast.makeText(getActivity(), "Successfully", Toast.LENGTH_SHORT).show();
                break;
            }
            default: break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAPTURE_IMAGE && resultCode == RESULT_OK && data !=null){
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");
            image_save.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.info:{
                Toast.makeText(getActivity(), "Text Recognization : Chuyển ảnh thành chữ\n" +
                        "Save Information : Lưu thông tin theo ảnh\n" +
                        "Ấn vào ảnh để chụp", Toast.LENGTH_LONG).show();
                return true;
            }
            case R.id.save:{
                Intent intent = new Intent(getActivity(), DisplayMemory.class);
                startActivity(intent);
                return true;
            }
            default:return false;
        }
    }
}
