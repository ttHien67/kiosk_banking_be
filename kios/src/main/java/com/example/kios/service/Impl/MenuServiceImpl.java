package com.example.kios.service.Impl;

import com.example.kios.mapper.MenuMapper;
import com.example.kios.mapper.PermissionMapper;
import com.example.kios.model.request.MenuRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.model.response.MenuResponse;
import com.example.kios.service.IMenuService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper mapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public BaseResponse getMenu(MenuRequest request) {
        try{
            List<MenuResponse> result = mapper.get(request);
            int countMenu = mapper.countMenu(request);

            for(MenuResponse item : result){
                List<String> listPermission = permissionMapper.getPermission(item.getId());
                if(listPermission != null){
                    item.setRoleCode(listPermission);
                }
            }


            if(result != null){
                return new BaseResponse(result, countMenu, "0", "get successfully");
            }else {
                return new BaseResponse("1", "get fail");
            }
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse getMenuByRole(MenuRequest request) {
        try{
            List<MenuResponse> result = mapper.getMenuByRole(request);

            if(result != null){
                return new BaseResponse(result, "0", "get successfully");
            }else {
                return new BaseResponse("1", "get fail");
            }
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse updateMenu(MenuRequest request) {
        try{
            if(Strings.isNullOrEmpty(request.getId())){
                return new BaseResponse("1", "Id is empty");
            }

            int deleteOldPermission = permissionMapper.deletePermission(request.getId());

            int result = mapper.update(request);

            if(result > 0){
                permissionMapper.createPermission(request.getId(), request.getRoleCode());

                return new BaseResponse(result, "0", "update successfully");
            }else {
                return new BaseResponse("1", "update fail");
            }

        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse createMenu(MenuRequest request) {
        try{
            if(Strings.isNullOrEmpty(request.getName()) ||
                    Strings.isNullOrEmpty(request.getPath()) ||
                    Strings.isNullOrEmpty(request.getIcon())
            ){
                return new BaseResponse("1", "Field is required");
            }

            int checkPathExisted = mapper.checkPathExisted(request);
            if(checkPathExisted > 0){
                return new BaseResponse("1", "Path has been existed");
            }

            MenuResponse result = mapper.create(request);

            if(result != null){
                permissionMapper.createPermission(result.getId(), request.getRoleCode());

                return new BaseResponse(result, "0", "create successfully");
            }else {
                return new BaseResponse("1", "create fail");
            }
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }

    @Override
    public BaseResponse deleteMenu(MenuRequest request) {
        try{
            if(Strings.isNullOrEmpty(request.getId())){
                return new BaseResponse("1", "Id is empty");
            }

            int result = mapper.delete(request);
            if(result > 0){
                return new BaseResponse(result, "0", "delete successfully");
            }else {
                return new BaseResponse("1", "delete fail");
            }
        }catch (Exception e){
            return new BaseResponse("-1", "fail");
        }
    }
}
