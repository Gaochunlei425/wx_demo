package com.company.project.logic.service.impl;

import com.company.project.logic.mapper.ContactMapper;
import com.company.project.logic.entity.Contact;
import com.company.project.logic.service.ContactService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by GCL on 2023/03/21.
 */
@Service
@Transactional
public class ContactServiceImpl extends AbstractService<Contact> implements ContactService {
    @Resource
    private ContactMapper contactMapper;

}
