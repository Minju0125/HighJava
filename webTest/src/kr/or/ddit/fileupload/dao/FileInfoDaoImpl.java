package kr.or.ddit.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.FileInfoVO;

public class FileInfoDaoImpl implements IFileInfoDao{

	private static FileInfoDaoImpl dao;
	
	private FileInfoDaoImpl() {}
	
	public static FileInfoDaoImpl getInstance() {
		if(dao==null) dao=new FileInfoDaoImpl();
		return dao;
	}
	
	
	@Override
	public int insertFileinfo(FileInfoVO fileVo) {
		SqlSession session = null;
		int cnt = 0;	
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("fileinfo.insertFileinfo", fileVo);
			if(cnt>0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return cnt;
	
	}

	@Override
	public List<FileInfoVO> getAllFileInfo() {
		SqlSession session = null;
		List<FileInfoVO> list = null;
		try {
			session = MybatisUtil.getSqlSession();
			list = session.selectList("fileinfo.getAllFileinfo");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		
		return list;
	}

	   @Override
	   public FileInfoVO getFileInfo(int fileNo) {
	      SqlSession session = null;
	      FileInfoVO fileVo = null;
	      
	      try {
	         session = MybatisUtil.getSqlSession();
	         fileVo = session.selectOne("fileinfo.getFileinfo",fileNo);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         if(session!=null) session.close();
	      }
	      
	      return fileVo;
	   }

	}
