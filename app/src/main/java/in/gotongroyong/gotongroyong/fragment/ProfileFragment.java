package in.gotongroyong.gotongroyong.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.ResponseActivity;
import in.gotongroyong.gotongroyong.ResultActivity;
import in.gotongroyong.gotongroyong.api.FirebaseAPI;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.common.Util;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.body.UpdateProfileBody;
import in.gotongroyong.gotongroyong.data.gotongroyong.LoginResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.UserDataResponse;
import in.gotongroyong.gotongroyong.entity.API;
import in.gotongroyong.gotongroyong.entity.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment implements BaseFragment, ResultActivity, ResponseActivity {
    private View root;

    @Override
    public String getTitle() {
        return "Data Pribadi";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences userData = getContext().getSharedPreferences(Preferences.SETTING_USER, MODE_PRIVATE);
        String api_token = userData.getString(Preferences.USER_API_TOKEN, "");

        GotongRoyongAPI.getUserData(this, api_token);

        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Log.d("DATE TIME NOW : ", dayOfMonth + "/" + month + "/" + year);
                updateDatePicker(calendar);
            }
        };

        ((TextView) root.findViewById(R.id.field_email)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                clearWarning();
            }
        });

        ((TextView) root.findViewById(R.id.field_name)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                clearWarning();
            }
        });

        root.findViewById(R.id.field_birth_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        root.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        return root;
    }

    private void warningInvalidEmail() {
        TextView warning = root.findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_email_invalid));
    }

    private void warningNameEmpty() {
        TextView warning = root.findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_name_empty));
    }


    private void warningUnknown() {
        TextView warning = root.findViewById(R.id.field_warning);
        warning.setText(getResources().getString(R.string.field_warning_unknown_error));
    }

    private void clearWarning() {
        TextView warning = root.findViewById(R.id.field_warning);
        warning.setText("");
    }

    private void update() {
        String email = ((TextView) root.findViewById(R.id.field_email)).getText().toString();
        String fullname = ((TextView) root.findViewById(R.id.field_name)).getText().toString();

        String username = ((TextView) root.findViewById(R.id.field_username)).getText().toString();
        String birthdate = ((TextView) root.findViewById(R.id.field_birth_date)).getText().toString();
        String birthplace = ((TextView) root.findViewById(R.id.field_birth_place)).getText().toString();

        String gender = "";
        if (((RadioButton) root.findViewById(R.id.radio_male)).isChecked()) {
            gender = "male";
        } else if (((RadioButton) root.findViewById(R.id.radio_male)).isChecked()) {
            gender = "female";
        }

        String password = ((TextView) root.findViewById(R.id.field_password)).getText().toString();
        String no_hp = ((TextView) root.findViewById(R.id.field_phone)).getText().toString();
        String fb_link = ((TextView) root.findViewById(R.id.field_link_facebook)).getText().toString();
        String twitter_link = ((TextView) root.findViewById(R.id.field_link_twitter)).getText().toString();
        String instagram_link = ((TextView) root.findViewById(R.id.field_link_instagram)).getText().toString();

        if (validate(email, fullname)) {
            SharedPreferences savedData = getContext().getSharedPreferences(Preferences.SETTING_USER, MODE_PRIVATE);
            String api_token = savedData.getString(Preferences.USER_API_TOKEN, "");
            UpdateProfileBody body = new UpdateProfileBody(username, email, birthdate, birthplace, gender, fullname, password, no_hp, fb_link, twitter_link, instagram_link);
            GotongRoyongAPI.updateProfile(this, api_token, body);
        }
    }

    private boolean validate(String email, String fullname) {
        if (!Util.isValidEmail(email)) {
            warningInvalidEmail();
        } else if (Util.isEmpty(fullname)) {
            warningNameEmpty();
        }
        return Util.isValidEmail(email) && !Util.isEmpty(fullname);
    }

    private void updateDatePicker(Calendar calendar) {
        String format = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        ((EditText) root.findViewById(R.id.field_birth_date)).setText(dateFormat.format(calendar.getTime()));
    }

    private void updateSuccess() {
        Toast.makeText(getContext(), getResources().getString(R.string.profile_update_success), Toast.LENGTH_SHORT).show();
    }

    private void errorConnection() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
    }

    private void errorUnknown() {
        Toast.makeText(getContext(), getResources().getString(R.string.field_warning_unknown_error), Toast.LENGTH_SHORT).show();
    }

    public void fetchData(LoginResponse hero) {
        String fullname = (hero.getFullname() == null)? "" : hero.getFullname();
        ((TextView) root.findViewById(R.id.profile_name)).setText(fullname);

        UserDataResponse userData = hero.getDataPahlawan();
        String donation = Integer.toString(userData.getCountDonations());
        String share = Integer.toString(userData.getCountShares());
        ((TextView) root.findViewById(R.id.profile_data_bar)).setText(getResources().getString(R.string.profile_data_bar, donation, share));

        String value = Integer.toString(userData.getCountDonations() * 1000);
        ((TextView) root.findViewById(R.id.profile_data_value)).setText(getResources().getString(R.string.profile_value, value));

        ((TextView) root.findViewById(R.id.field_name)).setText(fullname);

        String birthplace = (hero.getBirthplace() == null)? "" : hero.getBirthplace();
        ((TextView) root.findViewById(R.id.field_birth_place)).setText(birthplace);

        String birthdate = (hero.getBirthdate() == null)? "" : hero.getBirthdate();
        ((TextView) root.findViewById(R.id.field_birth_date)).setText(birthdate);

        String username = (hero.getUsername() == null)? "" : hero.getUsername();
        ((TextView) root.findViewById(R.id.field_username)).setText(username);

        String facebook = (userData.getFacebookLink() == null)? "" : userData.getFacebookLink();
        ((TextView) root.findViewById(R.id.field_link_facebook)).setText(facebook);

        String twitter = (userData.getTwitterLink() == null)? "" : userData.getTwitterLink();
        ((TextView) root.findViewById(R.id.field_link_twitter)).setText(twitter);

        String instagram = (userData.getInstagramLink() == null)? "" : userData.getInstagramLink();
        ((TextView) root.findViewById(R.id.field_link_instagram)).setText(instagram);

        FirebaseUser logged = FirebaseAPI.getLoggedUser();
        ((TextView) root.findViewById(R.id.field_email)).setText(logged.getEmail());

        String gender = (hero.getGender() == null)? "" : hero.getGender();
        switch (gender.toLowerCase()) {
            case "male":
                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(true);
                break;
            case "female":
                ((RadioButton) root.findViewById(R.id.radio_female)).setChecked(true);
                break;
            default:
                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(false);
                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(false);
        }

        String phoneNumber = (hero.getPhoneNumber().equals("0"))? "" : hero.getPhoneNumber();
        ((TextView) root.findViewById(R.id.field_phone)).setText(phoneNumber);
//        Toast.makeText(getContext(), "PHONE : [" + phoneNumber + "]", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResponse(int responseCode, int resultCode, Object response) {
        switch (responseCode) {
            case API.HERO_USER_DATA:
                if (resultCode == API.IS_SUCCESS) {
                    try {
                        LoginResponse data = (LoginResponse) response;
                        fetchData(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                        errorUnknown();
                    }
                } else if (resultCode == API.ERROR_NO_CONNECTION) {
                    errorConnection();
                } else {
                    errorUnknown();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int responseCode, int resultCode) {
        switch (responseCode) {
            case API.HERO_UPDATE_PROFILE:
                if (resultCode == API.IS_SUCCESS) {
                    updateSuccess();
                } else if (resultCode == API.ERROR_NO_CONNECTION) {
                    errorConnection();
                    warningUnknown();
                } else {
                    errorUnknown();
                    warningUnknown();
                }
                break;
        }
    }
}
