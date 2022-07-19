package com.yum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yum.domain.BranchDTO;
import com.yum.mapper.BranchMapper;

@Service
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	private BranchMapper branchMapper;
	
	
	@Override
	public List<BranchDTO> getBranchList() {
//		List<BoardDTO> boardList = Collections.emptyList();

//		int boardTotalCount = boardMapper.selectBoardTotalCount();

		List<BranchDTO>branchList = branchMapper.selectBranchList();
		
		return branchList;
	}


}
