package com.changgou.search.service;

import java.util.Map;

public interface SearchService {
    void importFromDbToEs();

    Map search(Map<String, String> searchMap);
}
