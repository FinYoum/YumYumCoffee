package com.yum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yum.domain.ImgDTO;

@Mapper
public interface AttachMapper {

	public int insertAttach(List<ImgDTO> attachList);

	public ImgDTO selectAttachDetail(Long imgNum);

	public int deleteAttach(Long productNum);

	public List<ImgDTO> selectAttachList(Long productNum);

	public int selectAttachTotalCount(Long productNum);

}
