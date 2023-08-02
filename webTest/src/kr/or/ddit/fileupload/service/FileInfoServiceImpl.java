package kr.or.ddit.fileupload.service;

import java.util.List;

import kr.or.ddit.fileupload.dao.FileInfoDaoImpl;
import kr.or.ddit.fileupload.dao.IFileInfoDao;
import kr.or.ddit.vo.FileInfoVO;

public class FileInfoServiceImpl implements IFileInfoService{

	private IFileInfoDao dao;
	
	private FileInfoServiceImpl() {
		dao= FileInfoDaoImpl.getInstance();
	}
	
	private static FileInfoServiceImpl service;
	
	public static FileInfoServiceImpl getInstance(){
		if(service==null) service = new FileInfoServiceImpl();
		return service;
	
	}

	@Override
	public int insertFileinfo(FileInfoVO fileVo) {
		// TODO Auto-generated method stub
		return dao.insertFileinfo(fileVo);
	}

	@Override
	public List<FileInfoVO> getAllFileInfo() {
		// TODO Auto-generated method stub
		return dao.getAllFileInfo();
	}

	@Override
	public FileInfoVO getFileInfo(int fileNo) {
		// TODO Auto-generated method stub
		return dao.getFileInfo(fileNo);
	}
	
	
	

}
