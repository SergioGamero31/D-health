package Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.example.diabetes.R;
import com.facebook.Profile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment {

    private CircleImageView profileImage;

    private EditText editName, editEmail, editPass, editNpass;
    private EditText editAge, editGender, editHeight, editWeight;

    private Button btnpBack;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        changeStatusBarColor();

        profileImage = (CircleImageView) view.findViewById(R.id.profileImage);

        editName = (EditText) view.findViewById(R.id.peditTextName);
        editEmail = (EditText) view.findViewById(R.id.peditTextEmail);
        editPass = (EditText) view.findViewById(R.id.peditTextPass);
        editNpass = (EditText) view.findViewById(R.id.peditTextNpass);

        editAge = (EditText) view.findViewById(R.id.peditTextAge);
        editGender = (EditText) view.findViewById(R.id.peditTextSex);
        editHeight = (EditText) view.findViewById(R.id.peditTextHeight);
        editWeight = (EditText) view.findViewById(R.id.peditTextWeight);

        btnpBack = view.findViewById(R.id.btnpBack);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Profile profile = Profile.getCurrentProfile();
        if (profile != null){
            displayProfileInfo(profile);
        }else{
            Profile.fetchProfileForCurrentAccessToken();
        }

        displayUserInfo();

        btnpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }

    private void displayProfileInfo(Profile profile){
        String photoUrl = profile.getProfilePictureUri(100,100).toString();
        Glide.with(getContext()).load(photoUrl).into(profileImage);
    }

    private void displayUserInfo(){
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Users").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();
                    String gender = snapshot.child("gender").getValue().toString();
                    String age = snapshot.child("age").getValue().toString();
                    String height = snapshot.child("height").getValue().toString();
                    String weight = snapshot.child("weight").getValue().toString();

                    editName.setText(name);
                    editEmail.setText(email);
                    editAge.setText(age);
                    editGender.setText(gender);
                    editHeight.setText(height);
                    editWeight.setText(weight);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.themeColor));
        }
    }
}