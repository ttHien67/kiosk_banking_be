package com.example.kios.mapper;

import com.example.kios.model.request.UIInfoRequest;
import com.example.kios.model.response.UIInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UIInfoMapper {

    public List<UIInfoResponse> getUiById(UIInfoRequest request);

    public List<UIInfoResponse> getAll(UIInfoRequest request);

    public int update(UIInfoRequest request);

    public UIInfoResponse create(UIInfoRequest request);

    public int delete(UIInfoRequest request);
}
