package com.darkenergy.photorooms;

import static com.darkenergy.photorooms.MainActivity.imageUri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.darkenergy.photorooms.databinding.ActivityFinalBinding;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;

public class FinalActivity extends AppCompatActivity {
    ActivityFinalBinding binding;
    ImageView imageView, backbtn, homebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFinalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imageView=findViewById(R.id.imageView);

        backbtn=findViewById(R.id.backImage);
        homebtn=findViewById(R.id.homeImage);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalActivity.this,MainActivity.class));
                Toast.makeText(FinalActivity.this, "Home Page", Toast.LENGTH_SHORT).show();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalActivity.this,MainActivity.class));
                Toast.makeText(FinalActivity.this, "Back", Toast.LENGTH_SHORT).show();
            }
        });

        Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);

        dsPhotoEditorIntent.setData(imageUri);
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Photo xRooms");

        int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_CROP};
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
        startActivityForResult(dsPhotoEditorIntent, 200);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case 200:

                    Uri outputUri = data.getData();
                    imageView.setImageURI(outputUri);
                    break;

            }

        }

    }
}