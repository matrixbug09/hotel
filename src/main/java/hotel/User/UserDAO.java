package hotel.User;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private static final String namespace="UserMapper";

    @Autowired
    private SqlSession sqlSession;


    public UserVO join(UserVO userVO){
        return sqlSession.selectOne(namespace+".join",userVO);
    }

    public UserVO insert1(UserVO userVO){
        return sqlSession.selectOne(namespace+".insert1",userVO);
    }


    public UserVO loginCheck(UserVO userVO) {
        return sqlSession.selectOne(namespace+".loginCheck",userVO);
    }

    public String checkId(String id){
        System.out.println("dao CheckId");

        return sqlSession.selectOne("UserMapper.checkId",id);
    }
}
