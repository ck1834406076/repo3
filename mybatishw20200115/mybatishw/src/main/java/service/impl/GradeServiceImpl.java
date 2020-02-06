package service.impl;

import mapper.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Grade;
import service.GradeService;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;
    @Override
    public List<Grade> queryAll() {
        return gradeMapper.queryAll();
    }
}
