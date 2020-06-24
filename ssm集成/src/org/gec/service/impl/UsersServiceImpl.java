package org.gec.service.impl;

import org.gec.mapper.UsersMapper;
import org.gec.pojo.Users;
import org.gec.pojo.UsersExample;
import org.gec.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService
{
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public int countByExample(UsersExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(UsersExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Users record) {
        return 0;
    }

    @Override
    public int insertSelective(Users record) {
        return 0;
    }

    @Override
    public List<Users> selectByExample(UsersExample example) {



        return null;
    }

    @Override
    public Users selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Users record, UsersExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Users record, UsersExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Users record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Users record) {
        return 0;
    }

    @Override
    public Users login(String username, String password) {

     return usersMapper.login(username, password);

    }
}
