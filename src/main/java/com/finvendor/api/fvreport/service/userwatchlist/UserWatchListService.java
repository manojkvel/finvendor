package com.finvendor.api.fvreport.service.userwatchlist;

import com.finvendor.api.fvreport.dto.UserWatchListData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserWatchListService {
    public List<UserWatchListData> findUserWatchlistData() throws Exception{
        return null;
    }
}
