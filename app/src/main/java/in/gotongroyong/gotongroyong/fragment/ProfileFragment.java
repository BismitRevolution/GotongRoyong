package in.gotongroyong.gotongroyong.fragment;

import android.app.DatePickerDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements BaseFragment {
    private View root;

    @Override
    public String getTitle() {
        return "Data Pribadi";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        String id = "1";
        Call<BaseResponse<HeroData>> call = new GotongRoyongAPI().getService().getHero(id);
        call.enqueue(new Callback<BaseResponse<HeroData>>() {
            @Override
            public void onResponse(Call<BaseResponse<HeroData>> call, Response<BaseResponse<HeroData>> response) {
                if (response.isSuccessful()) {
                    fetchData(response.body().getPayload());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<HeroData>> call, Throwable t) {
                Toast.makeText(getContext(), "Connection failed! Please check your internet network!", Toast.LENGTH_SHORT).show();
            }
        });

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
        String format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        ((EditText) root.findViewById(R.id.field_birth_date)).setText(dateFormat.format(calendar.getTime()));
    }

    public void fetchData(HeroData hero) {
//        ((TextView) root.findViewById(R.id.profile_name)).setText(hero.getName());
//
//        String donation = Integer.toString(hero.getTotalDonation());
//        String share = Integer.toString(hero.getTotalShare());
//        ((TextView) root.findViewById(R.id.profile_data_bar)).setText(getResources().getString(R.string.profile_data_bar, donation, share));
//
//        String value = Integer.toString(hero.getTotalDonation() * 1000);
//        ((TextView) root.findViewById(R.id.profile_data_value)).setText(getResources().getString(R.string.profile_value, value));
//
//        ((TextView) root.findViewById(R.id.field_name)).setHint(hero.getName());
//        ((TextView) root.findViewById(R.id.field_birth_place)).setHint(hero.getBirthPlace());
//        ((TextView) root.findViewById(R.id.field_birth_date)).setHint(hero.getBirthDate());
//        ((TextView) root.findViewById(R.id.field_city)).setHint(hero.getCity());
//        ((TextView) root.findViewById(R.id.field_email)).setHint(hero.getEmail());
//
//        switch (hero.getGender()) {
//            case 1:
//                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(true);
//                break;
//            case 2:
//                ((RadioButton) root.findViewById(R.id.radio_female)).setChecked(true);
//                break;
//            default:
//                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(false);
//                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(false);
//        }

        ((TextView) root.findViewById(R.id.field_phone)).setHint("+628123456789");
    }
}
