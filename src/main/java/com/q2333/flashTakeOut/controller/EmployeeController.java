package com.q2333.flashTakeOut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.q2333.flashTakeOut.common.Return;
import com.q2333.flashTakeOut.entity.Employee;
import com.q2333.flashTakeOut.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author q2333
 * @date 2022/06/13 10:17
 **/
@Slf4j
@SuppressWarnings("all")
@RestController
@RequestMapping("/employee")//下面所有地址映射都在/employee下
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    //request 作为session用到
    public Return<Employee> login
    (HttpServletRequest request, @RequestBody Employee employee) {
        //密码加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex
                (password.getBytes(StandardCharsets.UTF_8));
        //根据用户名查数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        //数据用户名约束unique,可以使用getOne方法
        //emp为查询到数据库中的一行,映射为java Employee类实例化emp
        //employee为调用Servlet接收的前端请求,由Tomcat封装成的Employee对象employee
        Employee emp = employeeService.getOne(queryWrapper);
        //查询到的类校验
        if (emp == null) {
            return Return.error("login failed");
        }
        //密码比对,emp为数据库中的行
        if (!emp.getPassword().equals(password)) {
            return Return.error("password wrong ");
        }
        //员工状态
        if (emp.getStatus() == 0) {
            return Return.error("account disabled");
        }
        //登录成功,id存入session
        request.getSession().setAttribute("employee", emp.getId());
        return Return.success(emp);

    }

    /**
     * 退出功能
     */
    @PostMapping("logout")
    public Return<String> logout(HttpServletRequest request) {
        //清理session
        request.getSession().removeAttribute("employee");
        return Return.success("logout success");
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @PostMapping
    public Return<String> save
    (HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工,信息:{}", employee.toString());
        //设置初始密码,加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //公共部分统一处理:
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setCreateUser((Long) request.getSession().getAttribute("employee"));
//        employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));

        //校验前端传来的employee对象,然后service-dao传入数据库
        employeeService.save(employee);//MP-IService的save方法写sql语句
        return Return.success("add employee success");
    }

    /**
     * 员工信息分页查询
     * 返回一页内容records和total数据库行数
     */
    @GetMapping("/page")
    public Return<Page> page(int page, int pageSize, String name) {
        log.info("page:{} pageSize:{} name={}", page, pageSize, name);//{}占位符
        Page pageContent = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.like
                (StringUtils.isNotEmpty(name), Employee::getName, name);//判断name是否为空
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageContent, queryWrapper);//查询sql
        return Return.success(pageContent);
    }

    /**
     * 根据员工id修改员工信息
     *
     * @return
     */
    @PutMapping//上方的  /employee  目录
    public Return<String> update
    (HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.toString());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser//查询id,设置更新数据的操作者
                ((Long) request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);//映射sql语句
        return Return.success("员工信息修改成");
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @GetMapping("/{id}")//url路径变量,是一个get方法,url中携带了参数
    public Return<Employee> getById(@PathVariable Long id) {
        log.info("根据id查到的员工信息", id.toString());
        Employee employee=employeeService.getById(id);//sql查询
        if (employee!=null){
            return Return.success(employee);
        }
        return Return.error("未查询到id对应的员工信息");
    }
}


















