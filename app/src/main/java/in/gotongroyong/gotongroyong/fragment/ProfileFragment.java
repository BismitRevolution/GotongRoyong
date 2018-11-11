package in.gotongroyong.gotongroyong.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import in.gotongroyong.gotongroyong.api.FirebaseAPI;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.LoginResponse;
import in.gotongroyong.gotongroyong.data.gotongroyong.UserDataResponse;
import in.gotongroyong.gotongroyong.entity.API;
import in.gotongroyong.gotongroyong.entity.Preferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements BaseFragment, ResponseActivity {
    private View root;

    @Override
    public String getTitle() {
        return "Data Pribadi";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences userData = getContext().getSharedPreferences(Preferences.SETTING_USER, Context.MODE_PRIVATE);
        String api_token = userData.getString(Preferences.USER_API_TOKEN, "");

        GotongRoyongAPI.getUserData(this, "Bearer " + api_token);

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

        root.findViewById(R.id.field_birth_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return root;
    }

    private void updateDatePicker(Calendar calendar) {
        String format = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        ((EditText) root.findViewById(R.id.field_birth_date)).setText(dateFormat.format(calendar.getTime()));
    }

    private void errorConnection() {
        Toast.makeText(getContext(), getResources().getString(R.string.error_connection), Toast.LENGTH_SHORT).show();
    }

    private void errorUnknown() {
        Toast.makeText(getContext(), getResources().getString(R.string.field_warning_unknown_error), Toast.LENGTH_SHORT).show();
    }

    public void fetchData(LoginResponse hero) {
        ((TextView) root.findViewById(R.id.profile_name)).setText(hero.getFullname());

        UserDataResponse userData = hero.getDataPahlawan();

        String donation = Integer.toString(userData.getCountDonations());
        String share = Integer.toString(userData.getCountShares());
        ((TextView) root.findViewById(R.id.profile_data_bar)).setText(getResources().getString(R.string.profile_data_bar, donation, share));

        String value = Integer.toString(userData.getCountDonations() * 1000);
        ((TextView) root.findViewById(R.id.profile_data_value)).setText(getResources().getString(R.string.profile_value, value));

        ((TextView) root.findViewById(R.id.field_name)).setHint(hero.getFullname());
        ((TextView) root.findViewById(R.id.field_birth_place)).setHint(hero.getBirthplace());
        ((TextView) root.findViewById(R.id.field_birth_date)).setHint(hero.getBirthdate());
//        ((TextView) root.findViewById(R.id.field_city)).setHint(hero.getBirthplace());

        FirebaseUser logged = FirebaseAPI.getLoggedUser();
        ((TextView) root.findViewById(R.id.field_email)).setHint(logged.getEmail());

        switch (hero.getGender().toLowerCase()) {
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

//        ((TextView) root.findViewById(R.id.field_phone)).setHint("+628123456789");
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
}
