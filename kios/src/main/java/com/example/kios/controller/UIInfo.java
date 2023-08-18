package com.example.kios.controller;

import com.example.kios.model.request.UIInfoRequest;
import com.example.kios.model.response.BaseResponse;
import com.example.kios.service.IUIInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ui")
public class UIInfo {

    @Autowired
    private IUIInfoService iuiInfoService;

    @PostMapping(value = "/getUiById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getUiById(@RequestBody UIInfoRequest request){
        return new ResponseEntity<>(iuiInfoService.getUiById(request), HttpStatus.OK);
    }

    @PostMapping(value = "/getAll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> getAll(@RequestBody UIInfoRequest request){
        return new ResponseEntity<>(iuiInfoService.getAll(request), HttpStatus.OK);
    }

    @PostMapping(value = "/createUi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> createUi(@RequestBody UIInfoRequest request){
        return new ResponseEntity<>(iuiInfoService.createUi(request), HttpStatus.OK);
    }

    @PostMapping(value = "/updateUi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> updateUi(@RequestBody UIInfoRequest request){
        return new ResponseEntity<>(iuiInfoService.updateUi(request), HttpStatus.OK);
    }

    @PostMapping(value = "/deleteUi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> deleteUi(@RequestBody UIInfoRequest request){
        return new ResponseEntity<>(iuiInfoService.deleteUi(request), HttpStatus.OK);
    }

}
