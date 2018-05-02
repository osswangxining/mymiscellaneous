/**
 * 
 */
package dubbo.sample;

/**
 * @author xiningwang
 *
 */
public class DemoServiceImpl implements DemoService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sample101.DemoService#sayHello(java.lang.String)
	 */
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
