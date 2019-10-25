package com.hites.movieapplication.data.datasource.remote

import com.google.gson.reflect.TypeToken
import com.hites.movieapplication.data.model.ApiResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

//internal class EnvelopingConverter : Converter.Factory() {
//    override fun responseBodyConverter(
//        type: Type?,
//        annotations: Array<Annotation>?,
//        retrofit: Retrofit
//    ): Converter<ResponseBody, *>? {
//
//        val envelopedType = TypeToken.getParameterized(ApiResponse::class.java, type!!).type
//        val delegate =
//            retrofit.nextResponseBodyConverter<ApiResponse<*>>(this, envelopedType, annotations!!)
//
//        return Converter<ResponseBody, Any> { body ->
//            val envelope = delegate.convert(body)
//            envelope!!.movies
//        }
//    }
//}
