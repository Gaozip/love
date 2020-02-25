

/**
 * @Author: Wangxh
 * @Date:2018/6/26 0026 10:01
 * @Description: 项目常量
 */
public final class ProjectConstant {

    /**
     * 项目基础包名称
     */
    public static final String BASE_PACKAGE = "org.lizhishu.love";

    /**
     * Model所在包
     */
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";

    /**
     * Mapper接口所在包
     */
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";

    /**
     * Service所在包
     */
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";

    /**
     * ServiceImpl所在包
     */
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    /**
     * Controller所在包
     */
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

    /**
     * Mapper插件基础接口的完全限定名
     */
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";

    /**
     * tomcat端口
     */
    public static Integer POST = 8381;

    /**
     * 设置最大连接数
     */
    public static Integer MAX_CONNECTTION = 2000;

    /**
     * 设置最大线程数
     */
    public static Integer MAX_THREAD = 2000;

    /**
     * 连接超时时间
     */
    public static Integer CONNECTION_TIMEOUT = 30000;

    /**
     * 告警展示最大条数
     */
    public static Integer MAX_ALARM_COUNT = 10000;

    /**
     * 密码密钥
     */
    public static String ENCRYPTION_KEY = "TwoToNone";


}
