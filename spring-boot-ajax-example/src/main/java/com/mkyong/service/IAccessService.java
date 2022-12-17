package com.mkyong.service;

import com.mkyong.model.AccessModel;

import java.util.List;

public interface IAccessService {
    public void save(String Lookup, String country, int employees, int ARank, int QRank, int MJRank, int MJTLDRank, int refSN, int refIP, int Followers, int lastIndexed, int CDimensions);
    public List<AccessModel>findAll();
}
