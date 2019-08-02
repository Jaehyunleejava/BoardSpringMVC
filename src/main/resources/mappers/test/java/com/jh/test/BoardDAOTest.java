package mappers.test.java.com.jh.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jh.dao.BoardDAO;
import com.jh.vo.BoardVO;
import com.jh.vo.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {

    @Inject
    private BoardDAO dao;
 
    private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
 
    
    @Test
    public void testListPage() throws Exception{
    	Criteria cri = new Criteria();
    	cri.setPage(2);
    	cri.setPerPageNum(20);
    	
    	List<BoardVO> list = dao.listCriteria(cri);
    	
    	for(BoardVO boardVO : list) {
    		logger.info(boardVO.getBno()+":"+boardVO.getTitle());
    	}
    }


}
