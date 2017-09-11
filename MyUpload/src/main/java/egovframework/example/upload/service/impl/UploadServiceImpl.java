package egovframework.example.upload.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.upload.service.UploadService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("uploadService")
public class UploadServiceImpl extends EgovAbstractServiceImpl implements UploadService{

	@Resource(name = "uploadMapper")
	private UploadMapper uploadMapper;
	
	@Override
	public void insertFile(Map<String, Object> params) throws Exception {
		uploadMapper.insertFile(params);
		
	}

	@Override
	public List<Map<String, Object>> selectFiles(Map<String, Object> params) throws Exception {
		return uploadMapper.selectFiles(params);
	}

}