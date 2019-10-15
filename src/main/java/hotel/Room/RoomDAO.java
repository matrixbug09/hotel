package hotel.Room;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomDAO {

    private static final String namespace = "RoomMapper";


    @Inject
    private SqlSession sqlSession;


    public void roomchange(RoomVO roomVO) {
        System.out.println(roomVO.getRoom_date());
        System.out.println(roomVO.getRoom_date());
        System.out.println(roomVO.getRoom_date());
        System.out.println(roomVO.getRoom_date());

        sqlSession.update("RoomMapper.roomchange", roomVO);
    }

    public RoomVO RemainRoomCheck(RoomVO roomVo) {
        System.out.println("sqlSessionRemainRoomCheck");
        System.out.println(roomVo.toString() + "3438756384563bc7bc783467c436cb346bc87346b4cb8");
        return sqlSession.selectOne(namespace + ".remainingrooms", roomVo);

    }

    public  RoomVO stayroomcheck(RoomVO roomVO){
        return sqlSession.selectOne("RoomMapper.stayroomcheck",roomVO);
    }
    public RoomVO roomcheck(RoomVO roomVo) {

        return sqlSession.selectOne("RoomMapper.roomcheck", roomVo);
    }

    public void updateRoom(RoomVO roomVO) {

        sqlSession.update("RoomMapper.roomupdate", roomVO);

    }

    public ArrayList<String> getRoom(String room_type, String required_room){
        Map<String, String> map = new HashMap<>();
        map.put("room_type", room_type);
        map.put("required_room", required_room);
        System.out.println("getRommgetRommgetRommgetRommgetRommgetRommgetRommgetRommgetRomm");
        return (ArrayList)sqlSession.selectList("RoomMapper.getRoom",map);
    }
}
