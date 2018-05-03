package classloader;

public class ClassLoaderTest {

	public static void main(String[] args) {
		ClassLoader fatherLoader = ClassLoaderTest.class.getClassLoader();  
        System.out.println("当前类的父加载器名称：" + fatherLoader.getClass().getName());  
  
        // 这是因为AppClassLoader的父加载器虽然是ExtClassLoader，但是却是Bootstrap  
        // 加载的（所以它的.getClassLoader()返回为null）  
        // Bootstrap、 ExtClassLoader、  AppClassLoader的关系很符合中国过去，
        //可以这么描述：ExtClassLoader是AppClassLoader的爸爸，但是AppClassLoader确实他爷爷Bootstrap一把屎一把尿喂大的。  
        System.out.println("AppClassLoader的直接加载器是null吗："  
                + (fatherLoader.getClass().getClassLoader() == null));  
  
       
  
        ClassLoader grandfatherLoader = fatherLoader.getParent();  
        System.out.println("爷爷载器名称：" + grandfatherLoader.getClass().getName());  
  
	}

}
