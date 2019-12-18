package ro.sapi.retrofitloginregistration.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.sapi.retrofitloginregistration.API.APIService;
import ro.sapi.retrofitloginregistration.API.RetrofitClient;
import ro.sapi.retrofitloginregistration.R;
import ro.sapi.retrofitloginregistration.models.Result;
import ro.sapi.retrofitloginregistration.models.Student;

public class HomeActivity extends AppCompatActivity {
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent i = getIntent();
        token = i.getStringExtra("token");
        final TextView tv = (TextView) findViewById(R.id.textView);
        //tv.setText(token);

        APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<List<Student>> call = service.getStudents("Bearer " + token);

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                //Log.d("response", response.toString());
                for (Student stud:students) {
                    tv.append(stud.getName() + '\n');
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d("response", "Hiba");
                Toast.makeText(HomeActivity.this, "Hiba!!!!!!!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });



    }
}
