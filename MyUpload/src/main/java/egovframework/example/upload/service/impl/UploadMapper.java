package egovframework.example.upload.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("uploadMapper")
public interface UploadMapper {

	void insertFile(Map<String, Object> params) throws Exception;

	List<Map<String, Object>> selectFiles(Map<String, Object> params) throws Exception;

}
