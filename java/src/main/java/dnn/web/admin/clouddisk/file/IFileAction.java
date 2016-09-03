package dnn.web.admin.clouddisk.file;

import com.dounine.clouddisk360.parser.deserializer.file.list.FileList;
import com.dounine.clouddisk360.parser.deserializer.file.list.FileListParameter;
import com.dounine.clouddisk360.parser.deserializer.file.search.FileSearch;
import com.dounine.clouddisk360.parser.deserializer.file.search.FileSearchParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("admin/clouddisk/file")
public interface IFileAction {

    @RequestMapping(value = "list", method = RequestMethod.POST)
    FileList list(FileListParameter fileListParameter);

    @RequestMapping(value = "search", method = RequestMethod.POST)
    FileSearch search(@RequestParam(value = "path",defaultValue = "/") String key, FileSearchParameter fileSearchParameter);

    @RequestMapping(value = "readImgStream", method = RequestMethod.GET)
    void readImgStream(HttpServletRequest request, HttpServletResponse response);

}
