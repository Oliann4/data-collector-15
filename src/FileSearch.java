import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {
    private final String TYPE_JSON = ".json";
    private final String TYPE_CSV = ".csv";
    private List<String> fileList;

    public FileSearch(String path) {
        fileList = new ArrayList<>();
        search(path);
    }

    public List<String> getFileList() {
        return fileList;
    }

    private void search(String path) {
        String[] folderList = new File(path).list();
        for (int i = 0; i < folderList.length; i++) {
            String newPath = path + File.separator + folderList[i];
            File file = new File(newPath);
            if (file.isFile()) {
                checkFileType(newPath);
            } else {
                search(newPath);
            }
        }
    }

    private void checkFileType(String path) {
        if(path.endsWith(TYPE_JSON) || path.endsWith(TYPE_CSV)) {
            fileList.add(path);
        }
    }
}
