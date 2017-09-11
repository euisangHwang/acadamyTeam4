package egovframework.example.upload.service;

import java.util.List;
import java.util.Map;


public interface UploadService {

	void insertFile(Map<String, Object> params) throws Exception;

	List<Map<String, Object>> selectFiles(Map<String, Object> params) throws Exception;


}

