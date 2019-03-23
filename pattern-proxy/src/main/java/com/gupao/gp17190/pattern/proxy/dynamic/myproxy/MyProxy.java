package com.gupao.gp17190.pattern.proxy.dynamic.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MyProxy {

    private static final String ln = "\r\n";

    private static HashMap<Class<?>, String> RETURN_EMPTY_MAP = new HashMap<Class<?>, String>();

    static {
        RETURN_EMPTY_MAP.put(int.class, "return 0;");
        RETURN_EMPTY_MAP.put(Void.class, "return;");
    }

    public static Object newProxyInstance(MyClassLoader classLoader, Class<?>[] interfaces, MyInvocationHandler h) throws Exception {
        // 1.生成java文件内容
        String srcCode = genSrc(interfaces);

        // 2.将文件内容写入磁盘.java文件
        String basePath = MyProxy.class.getResource("").getPath();
        File javaFile = new File(basePath + "$Proxy0.java");
        FileWriter fw = new FileWriter(javaFile);
        fw.write(srcCode);
        fw.flush();
        fw.close();

        // 3.将java文件加载到内存并编译成.class文件
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        Iterable iterable =manager.getJavaFileObjects(javaFile);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        manager.close();

        // 3.将.class文件加载到类加载器
        Class proxyClass = classLoader.findClass("$Proxy0");
        Constructor c = proxyClass.getConstructor(MyInvocationHandler.class);

        // 4.根据class对象生成代理对象
        return c.newInstance(h);


    }

    private static String genSrc(Class<?>[] interfaces) throws Exception {
        StringBuffer sb = new StringBuffer("package com.gupao.gp17190.pattern.proxy.dynamic.myproxy;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("import com.gupao.gp17190.pattern.proxy.dynamic.*;" + ln);
        sb.append("public class $Proxy0 implements "  + interfaces[0].getName() + "{" + ln);
        sb.append("private MyInvocationHandler h;" + ln);
        sb.append("public $Proxy0(MyInvocationHandler h) {" + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);
        for(Class<?> itfc : interfaces){
            Method[] methods = itfc.getMethods();
            for (Method m : methods) {
                Class<?>[] params = m.getParameterTypes();
                StringBuffer paramTypes = new StringBuffer();
                StringBuffer paramLists = new StringBuffer();
                StringBuffer paramClasses = new StringBuffer();
                for (int i = 0; i < params.length; i++) {
                    Class<?> param = params[i];
                    String paramType = param.getName();
                    paramTypes.append(paramType);
                    paramLists.append(paramType + " o" + i);
                    paramClasses.append(paramType + ".class");
                    if (i > 0 && i < (params.length - 1)) {
                        paramTypes.append(",");
                        paramLists.append(",");
                        paramClasses.append(",");
                    }
                }
                sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramLists + ") {" + ln);
                sb.append("try {" + ln);
                sb.append("Method m = " + itfc.getName() + ".class.getMethod(\"" + m.getName() + "\", new Class[] {" + paramClasses.toString() + "});" + ln);
                sb.append((m.getReturnType() == void.class ? "" : "return ") + "this.h.invoke(this, m, new Object[] {});" + ln);
                sb.append("} catch(Throwable e) {" + ln);
                sb.append("e.printStackTrace();" + ln);
//                sb.append(getEmptyReturn(m.getReturnType()));
                sb.append("}" + ln);
                sb.append("}" + ln);
            }
        }

        sb.append("}");

        return sb.toString();
    }

    private static String getEmptyReturn(Class<?> returnType) {
        if (RETURN_EMPTY_MAP.containsKey(returnType)) {
            return RETURN_EMPTY_MAP.get(returnType);
        }
        return "return null;";
    }


}
