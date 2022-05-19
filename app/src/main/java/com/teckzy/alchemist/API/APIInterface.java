package com.teckzy.alchemist.API;

import com.teckzy.alchemist.Model.LessonFavouritesPojo;
import com.teckzy.alchemist.Model.LessonPojo;
import com.teckzy.alchemist.Model.LinksPojo;
import com.teckzy.alchemist.Model.SearchListPojo;
import com.teckzy.alchemist.Model.LessonStatementPojo;
import com.teckzy.alchemist.Model.SubscriptionPojo;
import com.teckzy.alchemist.Model.WordPojo;
import com.teckzy.alchemist.Model.WordStatementPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface
{
    //LoginSignup
    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/check_login")
    @FormUrlEncoded
    Call<String> checkLogin(@Field("mobile_no") String mobileNo, @Field("password") String password);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/forgot_password")
    @FormUrlEncoded
    Call<String> forgotPassword(@Field("mobile_no") String mobileNo);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/change_forgot_password")
    @FormUrlEncoded
    Call<String> changeForgotPassword(@Field("mobile_no") String mobileNo, @Field("password") String password);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/generate_otp")
    @FormUrlEncoded
    Call<String> generateOtp(@Field("mobile_no") String mobileNo);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/register")
    @FormUrlEncoded
    Call<String> register(@Field("username") String username, @Field("password") String password,
                          @Field("email") String email, @Field("mobile_no") String mobileNo);

    @Headers({"X-API-KEY: alchemist@123"})
    @GET("Userapi/get_lesson")
    Call<List<LessonPojo>> getLesson();

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/get_lesson_statement")
    @FormUrlEncoded
    Call<List<LessonStatementPojo>> getLessonStatement(@Field("lesson_id") int lessonId, @Field("statement") String statement,
                                                       @Field("customer_id") int customerId);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/add_lesson_favourites")
    @FormUrlEncoded
    Call<String> addLessonFavourites(@Field("lesson_id") int lessonId, @Field("state_id") int stateId,
                                     @Field("customer_id") int customerId);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/get_lesson_favourites")
    @FormUrlEncoded
    Call<List<LessonFavouritesPojo>> getLessonFavourites(@Field("lesson_id") int lessonId, @Field("customer_id") int customerId);

    @Headers({"X-API-KEY: alchemist@123"})
    @GET("Userapi/get_word")
    Call<List<WordPojo>> getWord();

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/get_word_statement")
    @FormUrlEncoded
    Call<List<WordStatementPojo>> getWordStatement(@Field("word_id") int wordId);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/add_word_favourites")
    @FormUrlEncoded
    Call<String> addWordFavourites(@Field("word_id") int wordId, @Field("wstate_id") int wStateId,
                                                   @Field("customer_id") int customerId);

    @Headers({"X-API-KEY: alchemist@123"})
    @GET("Userapi/get_links")
    Call<List<LinksPojo>> getLinks();

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/search_sentence")
    @FormUrlEncoded
    Call<List<SearchListPojo>> searchSentence(@Field("start") String start, @Field("end") String end,
                                              @Field("contains") String contains);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/search_word")
    @FormUrlEncoded
    Call<List<SearchListPojo>> searchWord(@Field("start") String start);

    @Headers({"X-API-KEY: alchemist@123"})
    @POST("Userapi/add_feedback")
    @FormUrlEncoded
    Call<String> addFeedback(@Field("customer_id") int customerId, @Field("rate_value") int rateValue,
                             @Field("message") String message);

    @Headers({"X-API-KEY: alchemist@123"})
    @GET("Userapi/get_subscription")
    Call<List<SubscriptionPojo>> getSubscription();
}
