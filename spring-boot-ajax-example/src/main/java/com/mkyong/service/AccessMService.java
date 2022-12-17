package com.mkyong.service;

import com.mkyong.model.AccessModel;
import com.mkyong.repository.IAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class AccessMService implements IAccessService {
@Autowired
IAccessRepository accessRepository;
    @Override
    public void save(String Lookup, String country, int employees, int ARank, int QRank, int MJRank, int MJTLDRank, int refSN, int refIP, int Followers, int lastIndexed, int CDimensions) {
        AccessModel accessModel=new AccessModel( Lookup, country, employees, ARank, QRank,  MJRank,  MJTLDRank,  refSN,  refIP, Followers, lastIndexed, CDimensions);
        accessRepository.save(accessModel);
    }

    @Override
    public List<AccessModel> findAll() {
      List<AccessModel>find=  accessRepository.findAll();
        return find;
    }
}