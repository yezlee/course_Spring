package kr.or.ddit.user.repository;


import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;


// <bean id="" class=""  �ٵ� Ŭ������ �̹̹��� �˾�. ���̵� ����
// @Repository ���� ���ٸ� ������ ���� ������ ������ �� �̸����� class�̸����� ù���ڸ� �ҹ��ڷ� �� ���ڿ��� ������ �� �̸����� �����ȴ�.
// UserDaoImpl ==> userDaoImpl


// ���̹� : �������̽��� UserDao �����ϴ�Ŭ���� UserDaoImpl ==> @Resource(name="userDaoImpl")
// �ٵ� ������ �������̽��� UserDaoI �� �ϰ� ����Ŭ������ UserDao�� ���� �־��� �̰� �ٲ��ַ��� �̸��� �������ָ�� @Repository("userDao") �̷��� �ϸ� �׽�Ʈ���ٰ� @Resource(name="userDao")

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Override
	public UserVo getUser(String userid) {
		//������ �����ͺ��̽����� ��ȸ�� �ؾ��ϳ�, ���� �ʱ�ܰ�� ������ �Ϸ���� ����, ���� Ȯ���Ϸ��� �ϴ� ����� ������ �����̳ʿ� ������ ���߱�����
		//new �����ڸ� ���� ������ vo��ü�� ��ȯ
		
		return new UserVo("brown", "����");
		
	}
}
