package com.tribo_mkt.evaluation.koin

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tribo_mkt.evaluation.EvaluationApp
import com.tribo_mkt.evaluation.network.EvaluationApi
import com.tribo_mkt.evaluation.respository.Repository
import com.tribo_mkt.evaluation.util.Constants
import com.tribo_mkt.evaluation.viewmodels.AlbumsViewModel
import com.tribo_mkt.evaluation.viewmodels.MainViewModel
import com.tribo_mkt.evaluation.viewmodels.PostsViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

val userViewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val albumsViewModelModule = module {
    viewModel {
        AlbumsViewModel(get())
    }
}

val postsViewModelModule = module {
    viewModel {
        PostsViewModel(get())
    }
}

val repositoryModule = module {
    single {
        Repository(get())
    }
}

val retrofitModule = module {

    single<Gson> {
        GsonBuilder().create()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        val cacheDir = File((get<Context>() as EvaluationApp).cacheDir, "http")
        val cache = Cache(
            cacheDir,
            10 * 1024 * 1024 // 10 MB
        )

        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(Constants.EVALUATION_URL)
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single {
        get<Retrofit>().create(EvaluationApi::class.java)
    }
}