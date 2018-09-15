package in.gotongroyong.gotongroyong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import in.gotongroyong.gotongroyong.R;
import in.gotongroyong.gotongroyong.api.GotongRoyongAPI;
import in.gotongroyong.gotongroyong.data.BaseResponse;
import in.gotongroyong.gotongroyong.data.HeroData;
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

        return root;
    }

    public void fetchData(HeroData hero) {
        ((TextView) root.findViewById(R.id.profile_name)).setText(hero.getName());

        String donation = Integer.toString(hero.getTotalDonation());
        String share = Integer.toString(hero.getTotalShare());
        ((TextView) root.findViewById(R.id.profile_data_bar)).setText(getResources().getString(R.string.profile_data_bar, donation, share));

        String value = Integer.toString(hero.getTotalDonation() * 1000);
        ((TextView) root.findViewById(R.id.profile_data_value)).setText(getResources().getString(R.string.profile_value, value));

        ((TextView) root.findViewById(R.id.field_name)).setHint(hero.getName());
        ((TextView) root.findViewById(R.id.field_birth_place)).setHint(hero.getBirthPlace());
        ((TextView) root.findViewById(R.id.field_birth_date)).setHint(hero.getBirthDate());
        ((TextView) root.findViewById(R.id.field_city)).setHint(hero.getCity());
        ((TextView) root.findViewById(R.id.field_email)).setHint(hero.getEmail());

        switch (hero.getGender()) {
            case 1:
                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(true);
                break;
            case 2:
                ((RadioButton) root.findViewById(R.id.radio_female)).setChecked(true);
                break;
            default:
                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(false);
                ((RadioButton) root.findViewById(R.id.radio_male)).setChecked(false);
        }

        ((TextView) root.findViewById(R.id.field_phone)).setHint("+628123456789");
    }
}
