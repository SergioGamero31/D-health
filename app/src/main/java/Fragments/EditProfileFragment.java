package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.diabetes.R;
import com.facebook.Profile;

import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileFragment extends Fragment {

    private CircleImageView profileImage;

    public EditProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        profileImage = (CircleImageView) view.findViewById(R.id.profileImage);

        Profile profile = Profile.getCurrentProfile();
        if (profile != null){
            displayProfileInfo(profile);
        }else{
            Profile.fetchProfileForCurrentAccessToken();
        }

        return view;
    }

    private void displayProfileInfo(Profile profile){
        String photoUrl = profile.getProfilePictureUri(100,100).toString();
        Glide.with(getContext()).load(photoUrl).into(profileImage);
    }
}