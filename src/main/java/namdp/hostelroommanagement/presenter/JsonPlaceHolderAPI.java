package namdp.hostelroommanagement.presenter;

import java.util.List;

import namdp.hostelroommanagement.model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("posts")
    Call<List<User>> getModels();
}
