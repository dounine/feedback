package dnn.web.admin.clouddisk.file;

import com.alibaba.fastjson.JSON;
import com.dounine.clouddisk360.parser.AuthTokenParser;
import com.dounine.clouddisk360.parser.FileListParser;
import com.dounine.clouddisk360.parser.FileSearchParser;
import com.dounine.clouddisk360.parser.deserializer.authtoken.AuthToken;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileList;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileListParameter;
import com.dounine.clouddisk360.parser.deserializer.file.search.FileSearch;
import com.dounine.clouddisk360.parser.deserializer.file.search.FileSearchParameter;
import com.dounine.clouddisk360.pool.PoolingHttpClientConnection;
import dnn.web.admin.clouddisk.ClouddiskAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.lf5.util.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("admin/clouddisk/file")
public class FileAction implements IFileAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileAction.class);

    @Override
    public FileList list(FileListParameter fileListParameter) {
        LOGGER.info(JSON.toJSONString(fileListParameter));
        FileListParser fileListParser = new FileListParser(ClouddiskAction.LOGIN_USER_TOKEN);
        return fileListParser.parse(fileListParameter);
    }

    @Override
    public FileSearch search(String key, FileSearchParameter fileSearchParameter) {
        fileSearchParameter.setKey(key);
        FileSearchParser fileSearchParser = new FileSearchParser(ClouddiskAction.LOGIN_USER_TOKEN);
        return fileSearchParser.parse(fileSearchParameter);
    }

    @Override
    public void readImgStream(HttpServletRequest request, HttpServletResponse response) {
        String queryString = request.getQueryString();
        String imgUrl = queryString.substring(7);
        if (StringUtils.isNotBlank(imgUrl)){
            AuthTokenParser authTokenParser = new AuthTokenParser(ClouddiskAction.LOGIN_USER_TOKEN);
            HttpGet imgStreamRequest = new HttpGet(imgUrl);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(2000)
                    .build();
            AuthToken authToken = authTokenParser.parse();
            HttpClient httpClient =  HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .setDefaultCookieStore(authTokenParser.getHttpClientContext().getCookieStore())
                    .setConnectionManager(PoolingHttpClientConnection.getInstalce())
                    .build();
            try {
                HttpResponse resultResponse = httpClient.execute(imgStreamRequest);
                response.getOutputStream().write(StreamUtils.getBytes(resultResponse.getEntity().getContent()));
            } catch (IOException e) {
                LOGGER.info("图片获取超时");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
