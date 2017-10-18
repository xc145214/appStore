/*************************************************************************
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *
 *                COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 *    ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *************************************************************************/
package com.appStore.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用工具类
 *
 * @author xiachuan at 2017/8/24 16:57。
 */

public class CommonUtils {

    private static final Pattern CHINESE_UNICODE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 通过对象获取字符串。
     *
     * @param object        传入参数。
     * @param isReturnNull  是否返回null值，可不传。
     * @return              字符串。
     */
    public static String getString(Object object, Boolean... isReturnNull){
        if(object == null){
            if(isReturnNull != null && isReturnNull.length == 1 && !isReturnNull[0]) {
                return "";
            }
            return null;
        }

        return object.toString();
    }

    /**
     * 通过字符串获取整形数值。
     *
     * @param value    传入参数。
     * @return         整形数。
     */
    public static Integer getInteger(Object value){
        if(value == null || StringUtils.isEmpty(value.toString().trim())){
            return null;
        } else {
            try {
                return Integer.parseInt(value.toString());
            } catch (NumberFormatException ex) {
                return null;
            }

        }
    }

    /**
     * 通过字符串获取长整形数值。
     *
     * @param value    传入参数。
     * @return         长整形数。
     */
    public static Long getLong(Object value){
        if(value == null || StringUtils.isEmpty(value.toString().trim())){
            return null;
        } else {
            try {
                return Long.parseLong(value.toString());
            } catch (NumberFormatException ex) {
                return null;
            }
        }
    }

    /**
     * 通过字符串获取布尔值。
     *
     * @param value    传入参数。
     * @return         长整形数。
     */
    public static Boolean getBoolean(Object value){
        if(value == null || StringUtils.isEmpty(value.toString().trim())){
            return Boolean.FALSE;
        } else {
            if (value.toString().matches("^(.*(true|false)).*$")){
                return Boolean.parseBoolean(value.toString());
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 获取精确类型数字。
     *
     * @param value            原值。
     * @param isNullReturnZero 原值为null是否返回默认值0(可不写参数)。
     * @return {@link java.math.BigDecimal}。
     */
    public static BigDecimal getPreciseBigDecimal(Object value, Boolean... isNullReturnZero) {
        if (value == null) {
            if (isNullReturnZero != null && isNullReturnZero.length == 1 && isNullReturnZero[0]) {
                return BigDecimal.ZERO;
            } else {
                return null;
            }
        } else {
            return (BigDecimal) value;
        }
    }

    /**
     * 验证传入参数是否存在SQL注入风险，仅适合传入字段名称。
     *
     * @param params    传入参数(只能传入字母、数字和下划线)。
     * @return true：有；false：无。
     */
    public static Boolean checkSqlInject(String... params){
        if(params != null && params.length > 0){
            for(String param : params){
                if(!param.matches("^[a-zA-Z0-9_]+$")){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 验证多个参数是否为空，至少一个参数。
     *
     * <p>
     *     <ul>
     *         <li>对字符串对象，通过{@link org.apache.commons.lang3.StringUtils#isBlank(CharSequence)}判断是否为空；</li>
     *         <li>对私有类型，都当做非空对象对待；</li>
     *         <li>对其他对象类型，仅仅判断是否为空。</li>
     *     </ul>
     * </p>
     *
     * @param first 至少一个参数。
     * @param others 验证对象集合。
     * @return true：参数中存在至少一个对象为空；false：参数中全部对象都不为空。
     */
    public static Boolean isBlank(Object first, Object... others){
        //为空，毋庸置疑。
        if(first == null) {
            return true;
        }

        //如果是字符串，为空，毋庸置疑。
        if(!first.getClass().isPrimitive() && (first instanceof CharSequence && StringUtils.isBlank((CharSequence)first))) {
            return true;
        }

        for(Object other : others) {
            //为空，毋庸置疑。
            if(other == null) {
                return true;
            }

            //如果是字符串，为空，毋庸置疑。
            if(!other.getClass().isPrimitive() && (other instanceof CharSequence && StringUtils.isBlank((CharSequence)other))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将字符串两边的N个字符转为*号。
     *
     * @param str 待处理的字符串。
     * @param leftLength  左边隐藏个数。
     * @param rightLength 右边隐藏个数。
     * @return 处理后的字符串。
     */
    public static String fuzzyAsteriskAtSides(String str, int leftLength, int rightLength) {
        if (StringUtils.isNotEmpty(str)) {
            if (leftLength >= 0 && rightLength >= 0 && leftLength + rightLength <= str.length()) {
                char[] strChars = str.toCharArray();
                for(int a = 0; a < strChars.length; a++){
                    if(a < leftLength || a >= str.length() - rightLength){
                        strChars[a] = '*';
                    }
                }
                return new String(strChars);
            }
        }

        return str;
    }

    /**
     * 将字符串中间的N个字符转为*号。
     *
     * @param str           待处理的字符串。
     * @param start         左边开始[第一位下标为0]。
     * @param count         替换个数。
     * @return 处理后的字符串。
     */
    public static String fuzzyAsteriskAtMiddle(String str, int start, int count) {
        if (StringUtils.isNotEmpty(str)) {
            if (start >= 0 && count > 0) {
                char[] strChars = str.toCharArray();
                for(int a = 0; a < strChars.length; a++){
                    if(a >= start && a < start + count){
                        strChars[a] = '*';
                    }
                }
                return new String(strChars);
            }
        }

        return str;
    }

    /**
     * 判断字符串是否为日期格式。
     *
     * @param mayBeStringByDateformat 待判断的时间字符串，格式可为yyyy-MM-dd、yyyyMMdd、yyyy/MM/dd，后面可带可不带时刻HH:mm:ss。
     * @return true：是；false：否。
     */
    public static boolean isDateformat(String mayBeStringByDateformat) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(mayBeStringByDateformat);
        return m.matches();
    }

    /**
     * 判断字符串中是否包含汉字。
     *
     * @param asString 待判断的字符串。
     * @return true：包含；false：不包含。
     */
    public static boolean hasChineseIncluded(String asString) {
        return CHINESE_UNICODE_PATTERN.matcher(asString).find();
    }

    /**
     * 对象转化为二进制数组。
     *
     * @param obj 对象。
     * @return byte[]。
     * @throws IOException
     */
    public static byte[] objectToByte(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } finally {
            IOUtils.closeQuietly(byteArrayOutputStream);
            IOUtils.closeQuietly(objectOutputStream);
        }
    }

    /**
     * 二进制数组转化为对象。
     *
     * @param bytes 二进制数组。
     * @return 对象。
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T byteToObject(byte[] bytes) throws Exception {
        if(bytes == null){
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (T)objectInputStream.readObject();
        } finally {
            IOUtils.closeQuietly(byteArrayInputStream);
            IOUtils.closeQuietly(objectInputStream);
        }
    }


    /**
     * 屏蔽11位数字用户名。
     *  <p>
     *      13612345678 -> 136****5678
     *  </p>
     * @param number
     * @return
     */
    public String shieldNumberName(String number){
        StringBuilder sb = new StringBuilder();
        if(Pattern.matches("\\d{11}",number)){
            return sb.append(number.substring(0,3))
                    .append("****")
                    .append(number.substring(7))
                    .toString();
        }else{
            return number;
        }
    }

    /**
     * 判断是否通过手机浏览器访问。
     * @param request   {@link javax.servlet.http.HttpServletRequest}。
     * @return  {@link java.lang.Boolean}。
     */
    public static boolean accessFromMobile(HttpServletRequest request) {
        String[] mobileAgents = {"iphone", "ipad", "android", "phone", "mobile",
                "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",
                "windows ce", "symbian", "series", "webos", "sony", "vivo",
                "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",
                "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin",
                "huawei", "novarra", "coolpad", "webos", "techfaith",
                "palmsource", "alcatel", "amoi", "ktouch", "nexian",
                "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",
                "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop",
                "benq", "haier", "^lct", "320x320", "240x320", "176x220",
                "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
                "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
                "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",
                "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",
                "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
                "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",
                "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",
                "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",
                "sony", "sph-", "symb", "t-mo", "teli", "tim-", /*"tosh",*/ "tsm-",
                "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp", "mi",
                "wapr", "webc", "winw", "winw", "xda", "xda-", "Googlebot-Mobile"};
        if (request.getHeader("User-Agent") != null) {
            for (String mobileAgent : mobileAgents) {
                if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 通过外链获取手机归属地。
     * @param phone 手机号码。
     * @return  手机归属地。
     */
    public static String getPhonePlace(final String phone) {
        if (StringUtils.isBlank(phone) || phone.length() != 11) {
            return null;
        }
        try {
            String content = HttpUtils.doGet("http://m.ip138.com/mobile.asp", new HashMap<String, String>() {
                {
                    put("mobile", phone);
                }
            }, "UTF-8");

            Matcher matcher = Pattern.compile("(?is)(<tr><td>卡号归属地</td><td><span>([^<]+)</span></td></tr>)").matcher(content);
            String result = null;
            while (matcher.find()) {
                if (matcher.start(2) > 0) {
                    result = matcher.group(2);
                    result = result.replaceAll("&nbsp;", "#").replace(" ", "#");
                }
            }

            return result;
        } catch (IOException e) {
            return null;
        }
    }
}

