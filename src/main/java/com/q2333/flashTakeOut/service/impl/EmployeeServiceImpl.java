package com.q2333.flashTakeOut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q2333.flashTakeOut.entity.Employee;
import com.q2333.flashTakeOut.mapper.EmployeeMapper;
import com.q2333.flashTakeOut.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author q2333
 * @date 2022/06/13 10:08
 **/
@Service
public class EmployeeServiceImpl
        extends ServiceImpl<EmployeeMapper,Employee>
        implements EmployeeService {

}
