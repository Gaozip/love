//
//
//import org.mybatis.generator.api.IntrospectedColumn;
//import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
//import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;
//import org.mybatis.generator.internal.util.StringUtility;
//
//import java.math.BigDecimal;
//import java.sql.Types;
//import java.util.Properties;
//
///**
// * @Author: Wangxh
// * @Date: 2018/5/14 0014 12:11
// * @Description:mybatis自定义类型生成器
// */
//public class
//JavaTypeResolverExt extends JavaTypeResolverDefaultImpl {
//
//    protected boolean forceInteger = true;//无小数位时，优先使用Integer
//    protected boolean forceDouble = true;//无小数位时，优先使用double,而不是bigDecimal,比forceBigDecimals优先级高
//
//    @Override
//    public void addConfigurationProperties(Properties properties) {
//        forceInteger = StringUtility
//                .isTrue(properties
//                        .getProperty("forceLong", "true"));
//        forceDouble = StringUtility
//                .isTrue(properties
//                        .getProperty("forceDouble", "true"));
//        properties.setProperty("forceBigDecimals", "false");
//        super.addConfigurationProperties(properties);
//    }
//
//
//    public FullyQualifiedJavaType calculateJavaType(
//            IntrospectedColumn introspectedColumn) {
//        super.calculateJavaType(introspectedColumn);
//        FullyQualifiedJavaType answer = null;
//        JdbcTypeInformation jdbcTypeInformation = typeMap
//                .get(introspectedColumn.getJdbcType());
//
//        /**
//         * 判断是否为数字类型，如果为数字类型，没有小数点默认为Integer，有小数点默认为Double
//         */
//        if (jdbcTypeInformation != null) {
//            if (introspectedColumn.getJdbcType() == Types.DECIMAL) {
//                int scale = introspectedColumn.getScale();
//                int length = introspectedColumn.getLength();
//                if (scale > 0) {//有小数位
//                    if (forceDouble) {
//                        answer = new FullyQualifiedJavaType(Double.class
//                                .getName());
//                    } else {
//                        answer = new FullyQualifiedJavaType(BigDecimal.class
//                                .getName());
//                    }
//                } else {//无小数位
//                    if (forceInteger) {
//                        answer = new FullyQualifiedJavaType(Integer.class
//                                .getName());
//                    } else if (forceBigDecimals) {
//                        answer = new FullyQualifiedJavaType(BigDecimal.class.getName());
//                    } else if (length > 18) {
//                        new FullyQualifiedJavaType(BigDecimal.class
//                                .getName());
//                    } else if (length < 4) {
//                        answer = new FullyQualifiedJavaType(Short.class.getName());
//                    } else if (length < 9) {
//                        answer = new FullyQualifiedJavaType(Integer.class.getName());
//                    } else {
//                        answer = new FullyQualifiedJavaType(Long.class.getName());
//                    }
//                }
//            } else if (introspectedColumn.getJdbcType() == Types.NUMERIC) {
//                int scale = introspectedColumn.getScale();
//                int length = introspectedColumn.getLength();
//                if (scale > 0) {//有小数位
//                    if (forceDouble) {
//                        answer = new FullyQualifiedJavaType(Double.class
//                                .getName());
//                    } else {
//                        answer = new FullyQualifiedJavaType(BigDecimal.class
//                                .getName());
//                    }
//                } else {//无小数位
//                    if (forceInteger) {
//                        answer = new FullyQualifiedJavaType(Long.class
//                                .getName());
//                    } else if (forceBigDecimals) {
//                        answer = new FullyQualifiedJavaType(BigDecimal.class
//                                .getName());
//                    } else if (length > 18) {
//                        new FullyQualifiedJavaType(BigDecimal.class
//                                .getName());
//                    } else if (length < 4) {
//                        answer = new FullyQualifiedJavaType(Short.class.getName());
//                    } else if (length < 9) {
//                        answer = new FullyQualifiedJavaType(Integer.class.getName());
//                    } else {
//                        answer = new FullyQualifiedJavaType(Long.class.getName());
//                    }
//                }
//            } else {
//                //其他未设置的类型为默认转换类型
//                answer = jdbcTypeInformation.getFullyQualifiedJavaType();
//            }
//        } else {
//            answer = jdbcTypeInformation.getFullyQualifiedJavaType();
//        }
//
//        return answer;
//    }
//}
