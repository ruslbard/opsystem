package com.tsystems.jschool20.srvengine.services;

import com.tsystems.jschool20.srvengine.api.OptionService;
import com.tsystems.jschool20.srvengine.entites.DTOOption;
import com.tsystems.jschool20.srvengine.entites.Option;
import com.tsystems.jschool20.srvengine.repos.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ruslbard on 02.04.2017.
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    private OptionRepository optionRepository;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Transactional
    public Collection<DTOOption> getAllOptions() {

        Collection<Option> allOptions = this.optionRepository.findAll();
        Collection<DTOOption> dtos = new ArrayList<DTOOption>(allOptions.size());
        for (Option option : allOptions) {

            DTOOption dto = new DTOOption();
            dto.setId(option.getId());
            dto.setName(option.getName());
            dto.setPrice(option.getPrice());
            dto.setAdd_coast(option.getAdd_coast());
            dto.setIsActive(option.getIsActive());
            dto.setDefaultForRates(option.getDefaultForRates());

            Collection<Option> includeOptions = option.getIncludeOptions();

            if (!includeOptions.isEmpty()) {

                dto.setIncludeOptionsIds(new ArrayList<Long>(includeOptions.size()));

                for (Option includeOption : includeOptions) {

                    dto.getIncludeOptionsIds().add(includeOption.getId());
                }

            }

            Collection<Option> excludeOptions = option.getExcludeOptions();

            if (!excludeOptions.isEmpty()) {

                dto.setExcludeOptionsIds(new ArrayList<Long>(excludeOptions.size()));

                for (Option excludeOption : excludeOptions) {

                    dto.getExcludeOptionsIds().add(excludeOption.getId());
                }

            }


            dtos.add(dto);

        }

        return dtos;
    }
}