package com.sharemedia.restservices.dao;

import java.util.List;
import java.util.Map;

import com.sharemedia.restservices.model.ZeroDechet;

public interface ZeroDechetDao {

    void saveZero(ZeroDechet zero);

    void removeZero(String key);

    void updateZero(ZeroDechet zero);

    List<ZeroDechet> getAll();


}
