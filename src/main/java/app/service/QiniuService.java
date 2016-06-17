package app.service;

import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

/**
 * Created by zhujay on 16/6/17.
 */
@Service
public class QiniuService {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "3qjnSC1OG3OZw0RJhG2wcdwyn9V7HGLjjbQaI7T3";
    String SECRET_KEY = "s2GJRWKC-w4Cjztj6FaEk-c3oQP5a4ZJrQT2dry8";
    //要上传的空间
    String bucketname = "pic-share";
//    //上传到七牛后保存的文件名
//    String key = "default.png";
//    //上传文件的路径
//    String FilePath = "/";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
//    //创建上传对象
//    UploadManager uploadManager = new UploadManager();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }
}
