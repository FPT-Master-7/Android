package namdp.hostelroommanagement.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import namdp.hostelroommanagement.model.User;
import namdp.hostelroommanagement.presenter.JsonPlaceHolderAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements JsonPlaceHolderAPI {
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderAPI api = retrofit.create(JsonPlaceHolderAPI.class);
        Call<List<User>> call = api.getModels();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<User> users = response.body();
                for (User user : users){
                    String content = "";
                    content += "UserId: " + user.getUserId() + "\n";
                    content += "Id: " + user.getId() + "\n";
                    content += "Title: " + user.getTitle() + "\n";
                    content += "Body: " + user.getText() + "\n\n";

                    textViewResult.append(content);


                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });




    }

    @Override
    public Call<List<User>> getModels() {
        return null;
    }

}
