package ro.sapi.retrofitloginregistration.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import ro.sapi.retrofitloginregistration.models.Result;
import ro.sapi.retrofitloginregistration.models.Student;

public interface APIService {

    //The login call
    @FormUrlEncoded
    @POST("login")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    //The register call
    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password);


    @GET("students")
    Call<List<Student>> getStudents(@Header("Authorization") String token);

    //logout
    //@POST("logout")
   //Call<LogoutResult> logoutUser(@Header("Authorization") String token);

}
