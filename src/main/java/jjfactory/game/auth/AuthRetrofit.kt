package jjfactory.game.auth

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthRetrofit {
//    @GET("/oauth/authorize")
//    fun getCode() : Call<String>

    @FormUrlEncoded
    @POST("/oauth/token")
    fun postCode(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code: String): Call<Any>
}