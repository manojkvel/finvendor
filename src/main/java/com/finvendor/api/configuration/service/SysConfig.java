package com.finvendor.api.configuration.service;

import com.finvendor.api.configuration.dao.SysConfigDao;
import com.finvendor.api.configuration.dto.SysConfigDto;
import com.finvendor.model.Configuration;
import com.finvendor.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysConfig {

    private final SysConfigDao dao;

    @Autowired
    public SysConfig(SysConfigDao dao) {
        this.dao = dao;
    }

    public void update(Integer id, SysConfigDto sysConfigDto) throws Exception {
        Configuration existingConfiguration = dao.findById(id);
        if (existingConfiguration != null) {
            if (sysConfigDto.getEmailEnabled() != null) {
                existingConfiguration.setEmailEnabled(sysConfigDto.getEmailEnabled());
            }
            if (sysConfigDto.getTrialPeriodInDays() != null) {
                existingConfiguration.setTrialPeriodInDays(sysConfigDto.getTrialPeriodInDays());
            }
            if (sysConfigDto.getReminderDays() != null) {
                existingConfiguration.setReminderDays(sysConfigDto.getReminderDays());
            }

            if (sysConfigDto.getFeatureAccessDailyLimit() != null) {
                existingConfiguration.setFeatureAccessDailyLimit(sysConfigDto.getFeatureAccessDailyLimit());
            }

            if (sysConfigDto.getFeatureAccessWeeklyLimit() != null) {
                existingConfiguration.setFeatureAccessWeeklyLimit(sysConfigDto.getFeatureAccessWeeklyLimit());
            }
            dao.saveOrUpdate(existingConfiguration);
        }
        else {
            throw new Exception("Configuration does not exists");
        }
    }

    public List<Configuration> findAll() {
        return dao.findAll();
    }

    public static Configuration config() {
        List<Configuration> allConfig = BeanUtils.getBean(SysConfigDao.class).findAll();
        if (allConfig != null && allConfig.size() > 0) {
            return allConfig.get(0);
        }
        return null;
    }
}
