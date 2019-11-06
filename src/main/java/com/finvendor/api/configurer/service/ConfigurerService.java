package com.finvendor.api.configurer.service;

import com.finvendor.api.configurer.dao.ConfigurerDao;
import com.finvendor.api.configurer.dto.ConfigurationDto;
import com.finvendor.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConfigurerService {

    private final ConfigurerDao dao;

    @Autowired
    public ConfigurerService(ConfigurerDao dao) {
        this.dao = dao;
    }

    public void update(Integer id, ConfigurationDto configurationDto) throws Exception {
        Configuration existingConfiguration = dao.findById(id);
        if (existingConfiguration != null) {
            if (configurationDto.getEmailEnabled() != null) {
                existingConfiguration.setEmailEnabled(configurationDto.getEmailEnabled());
            }
            if (configurationDto.getTrialPeriodInDays() != null) {
                existingConfiguration.setTrialPeriodInDays(configurationDto.getTrialPeriodInDays());
            }
            if (configurationDto.getReminderDays() != null) {
                existingConfiguration.setReminderDays(configurationDto.getReminderDays());
            }

            if (configurationDto.getFeatureAccessDailyLimit() != null) {
                existingConfiguration.setFeatureAccessDailyLimit(configurationDto.getFeatureAccessDailyLimit());
            }

            if (configurationDto.getFeatureAccessWeeklyLimit() != null) {
                existingConfiguration.setFeatureAccessWeeklyLimit(configurationDto.getFeatureAccessWeeklyLimit());
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
}
