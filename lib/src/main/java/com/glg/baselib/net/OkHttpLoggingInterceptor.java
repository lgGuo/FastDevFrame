package com.glg.baselib.net;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class OkHttpLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();  // 获取到我们的请求封装对象
        HttpUrl url = request.url();
        RequestBody requestBody = request.body();

        String body = null;

        if(requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

//            Charset charset = Charset.forName("UTF-8");
//            MediaType contentType = requestBody.contentType();
//            if (contentType != null) {
//                charset = contentType.charset(charset);
//            }
            body = buffer.readString(Charset.defaultCharset());
        }

        long startNs = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long estimatedTime = System.currentTimeMillis() - startNs;
        //此处本来是想用 contentLength 来代替 1000 * 1000(会导致内容不完整) .但是contentLength 返回 -1 。会导致奔溃。
        ResponseBody responseBody = response.peekBody(1000 * 1000);
        String bodyInfo = responseBody.string();
        Logger.e("地址:" + URLDecoder.decode(url.toString()) + "\n"+"耗时:"+estimatedTime+"毫秒"+"\n"+"参数:" + body+"\n"+"结果:"+bodyInfo);


        return response;
    }
}
