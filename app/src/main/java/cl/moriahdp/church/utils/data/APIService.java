package cl.moriahdp.church.utils.data;

import java.util.List;

import cl.moriahdp.church.connect.modelObject.ConnectModelObject;
import cl.moriahdp.church.events.modelObject.EventModelObject;
import cl.moriahdp.church.sermon.modelObject.SermonModelObject;
import cl.moriahdp.church.login.modelObject.LoginModelObject;
import cl.moriahdp.church.registry.modelObject.ResetPasswordModelObject;
import cl.moriahdp.church.registry.modelObject.UserModelObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIService {

    @POST("api/1.0/users/sign-in/")
    Call<UserModelObject> loginUser(@Body LoginModelObject loginModelObject);

    @POST("api/1.0/users/")
    Call<UserModelObject> registerUser(@Body UserModelObject userModelObject);

    @PUT("api/1.0/users/update_user/")
    Call<UserModelObject> updateUser(@Header("Authorization") String token,
                                     @Body UserModelObject userModelObject);

    @POST("api/1.0/users/password/")
    Call<ResetPasswordModelObject> resetPassword(@Body ResetPasswordModelObject reset);

    @GET("sermons/")
    Call<List<SermonModelObject>> getSermons();

    @GET("events/")
    Call<List<EventModelObject>> getEvents();

    @GET("connects/")
    Call<List<ConnectModelObject>> getConnects();
}