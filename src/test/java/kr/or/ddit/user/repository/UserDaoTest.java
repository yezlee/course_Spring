package kr.or.ddit.user.repository;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVo;

//�׽�Ʈ�� ���θ޼ҵ尡 ���µ� ����ȴ�. ���????????? 
//==> ��򰡿� ������ �������ִ� �� �ִ�.
//�̰� ������ ���� ���°Ŷ� ��Ŭ�������� ��ü������ ��������
// eclipse, maven - junit�� ���θ޼ҵ尡 ���µ� ������ �Ǵ������� �� �ΰ����� ���θ޼ҵ带 �������ִϱ�


//������ ȯ�濡�� jUnit �ڵ带 ���� ==> jUnit�ڵ嵵 ������ ������ ���(������������ ����� �Ǿ��־�� ������ �����ϴ�!!!!!)
@RunWith(SpringJUnit4ClassRunner.class)
//���������� �˷��ִ� ������̼�
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class UserDaoTest {
	

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.getUser(userid); 

		/***Then***/
		assertEquals("����", userVo.getUsernm());
	}

}
