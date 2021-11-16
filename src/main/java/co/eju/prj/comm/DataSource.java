package co.eju.prj.comm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//가장 기본적인 sigleton class
public class DataSource {
	private static SqlSessionFactory sqlSessionFactory;
	
	
	
	private DataSource() {
		//외부에서 생성자를 못 만들도록
		
	}
	
	public static SqlSessionFactory getDataSource() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlSessionFactory;
	}
}
