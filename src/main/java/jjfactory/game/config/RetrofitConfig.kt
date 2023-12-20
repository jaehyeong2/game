package jjfactory.game.config

import com.google.gson.GsonBuilder
import jjfactory.game.auth.AuthRetrofit
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Configuration
class RetrofitConfig {
    @Bean
    fun createRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl("https://kauth.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Bean
    fun authRetrofit(retrofit: Retrofit): AuthRetrofit {
        return retrofit.create(AuthRetrofit::class.java)
    }
}